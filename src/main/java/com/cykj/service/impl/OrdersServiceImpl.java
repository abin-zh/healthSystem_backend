package com.cykj.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cykj.exception.AddException;
import com.cykj.exception.UpdateException;
import com.cykj.mapper.*;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.*;
import com.cykj.model.vo.OrderDeptVO;
import com.cykj.model.vo.OrderVO;
import com.cykj.model.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cykj.service.OrdersService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单业务实现层
 * @author abin
 * @date 2024/8/8 10:47
*/
@Service
public class OrdersServiceImpl implements OrdersService{

    private OrdersMapper ordersMapper;

    private MedicalPackageMapper medicalPackageMapper;

    private MedicalProjectMapper medicalProjectMapper;

    private UsersMapper usersMapper;

    private OrderDetailsMapper orderDetailsMapper;

    private MedicalCheckupSummaryMapper medicalCheckupSummaryMapper;

    private MedicalProjectSummaryMapper medicalProjectSummaryMapper;

    private MedicalItemCheckResultsMapper medicalItemCheckResultsMapper;

    private BalanceflowMapper balanceflowMapper;

    @Autowired
    public OrdersServiceImpl(OrdersMapper ordersMapper, MedicalPackageMapper medicalPackageMapper, MedicalProjectMapper medicalProjectMapper, UsersMapper usersMapper, OrderDetailsMapper orderDetailsMapper, MedicalCheckupSummaryMapper medicalCheckupSummaryMapper, MedicalProjectSummaryMapper medicalProjectSummaryMapper, MedicalItemCheckResultsMapper medicalItemCheckResultsMapper, BalanceflowMapper balanceflowMapper) {
        this.ordersMapper = ordersMapper;
        this.medicalPackageMapper = medicalPackageMapper;
        this.medicalProjectMapper = medicalProjectMapper;
        this.usersMapper = usersMapper;
        this.orderDetailsMapper = orderDetailsMapper;
        this.medicalCheckupSummaryMapper = medicalCheckupSummaryMapper;
        this.medicalProjectSummaryMapper = medicalProjectSummaryMapper;
        this.medicalItemCheckResultsMapper = medicalItemCheckResultsMapper;
        this.balanceflowMapper = balanceflowMapper;
    }

    /**
     * 开单
     * 1.要求数据判空
     * 2.金额比对
     * 3.获取用户，判断用户是否存在，余额是否足够
     * 4.添加订单信息
     * 5.添加订单详情信息
     * 6.添加总结表记录
     * 7.添加项目小结表记录
     * 8.添加细项结果表记录
     * 9.更新用户余额(扣款)
     * 10.添加交易流水
     */
    @Override
    @Transactional
    public ResponseDTO addOneOrder(OrderVO orderVO) throws AddException {

        //1.要求数据判空
        if(orderVO.getUserId() == null || orderVO.getOrderTotalAmount() == null){
            return ResponseDTO.fail("提交失败，未提供需求信息");
        } else if(orderVO.getPackageAddIds().isEmpty() && orderVO.getProjectAddIds().isEmpty()){
            return ResponseDTO.fail("提交失败，没有选择的套餐或项目");
        }

        //2.金额比对
        //获取套餐、项目，计算金额
        List<MedicalPackage> packages = new ArrayList<>();
        List<MedicalProject> projects = new ArrayList<>();
        if(!orderVO.getPackageAddIds().isEmpty()) {
            packages = medicalPackageMapper.findAllByPackageIds(orderVO.getPackageAddIds());
        }
        if(!orderVO.getProjectAddIds().isEmpty()) {
            projects = medicalProjectMapper.findAllByProjectIds(orderVO.getProjectAddIds());
        }

        BigDecimal packagesPrice = BigDecimal.ZERO;
        BigDecimal projectsPrice = BigDecimal.ZERO;

        for (MedicalPackage aPackage : packages) {
            packagesPrice = packagesPrice.add(aPackage.getPackagePrice());
        }

        for (MedicalProject project : projects) {
            projectsPrice  = projectsPrice.add(project.getProjectPrice());
        }

        //计算总额
        BigDecimal amount = packagesPrice.add(projectsPrice);
        if(amount.compareTo(orderVO.getOrderTotalAmount()) != 0){
            return ResponseDTO.fail("提交失败，价格有误");
        }

        //3.获取用户，判断用户是否存在，余额是否足够
        User user = usersMapper.findOneByUserId(orderVO.getUserId());
        if(user == null){
            return ResponseDTO.fail("提交失败，用户不存在");
        }

        //尝试扣款
        BigDecimal afterBalance = user.getUserBalance().subtract(amount);
        boolean isPay = afterBalance.compareTo(BigDecimal.ZERO) >= 0;

        //添加订单
        //生成订单号
        String orderNumber = IdUtil.getSnowflake().nextIdStr();
        Order order = new Order(orderNumber, user.getUserId(), amount, isPay ? 1 : 0);

        try{
            //4.添加订单信息
            //添加订单
            int res = ordersMapper.addOneOrder(order);
            if(res <= 0){
                throw new AddException("未知的错误", order.getOrderId());
            }

            //5.添加订单详情信息
            // 添加套餐
            addDetails(orderVO.getPackageAddIds(), order, 1, "添加订单的套餐时错误");

            // 添加项目
            addDetails(orderVO.getProjectAddIds(), order, 0, "添加订单的项目时错误");

            //6.添加总结表记录
            int summaryRes = medicalCheckupSummaryMapper.addOneSummary(order.getOrderId());
            if(summaryRes <= 0){
                throw new AddException("生成记录时错误", order.getOrderId());
            }

            //7.添加项目小结表记录
            //8.添加细项结果表记录
            addProjectSummaryAndItemResult(projects, order, "生成记录时错误");

            List<Integer> packageProjectIds = new ArrayList<>();
            for (MedicalPackage medicalPackage : packages) {
                for (MedicalProject project : medicalPackage.getProjects()) {
                    packageProjectIds.add(project.getProjectId());
                }
            }
            List<MedicalProject> packageProjects = medicalProjectMapper.findAllByProjectIds(packageProjectIds);
            addProjectSummaryAndItemResult(packageProjects, order, "生成记录时错误");

            int subRes;
            if(isPay){
                subRes = usersMapper.updateUserBalance(user.getUserId(), afterBalance);
                if(subRes <= 0){
                    throw new AddException("用户支付时错误", user.getUserId());
                }
            }

            balanceflowMapper.addOneBalanceFlow(new Balanceflow(null, orderNumber, user.getUserId(), null, 0, order.getOrderTotalAmount(),
                    user.getUserPhone() + "支付了" + orderNumber + "订单" + order.getOrderTotalAmount() + "元", 1));

            return isPay ? ResponseDTO.success("订单提交成功，并以成功支付", new Order(orderNumber)) : ResponseDTO.fail("订单提交成功，但并未支付，请充值后前往个人订单中支付");

        } catch (Exception e){
            if(e instanceof AddException){
                throw e;
            }
            throw new AddException("添加时异常", order.getOrderId());
        }
    }

    /**
     * 获取订单详情
     * 1.查询已支付订单信息，查看订单是否存在
     * 2.获取项目小结列表
     */
    @Override
    public ResponseDTO getOrderDetail(PageVO<OrderDeptVO> vo) {
        Page<Object> page = PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<Order> orders = ordersMapper.findAllByOrderInfo(vo.getData(), 1);
        if(orders.isEmpty()){
            return ResponseDTO.fail("获取失败，订单号不存在或订单未支付");
        }
        PageInfo<Object> info = new PageInfo<>(orders);
        return ResponseDTO.success((int) info.getTotal(), orders);
    }

    @Override
    public ResponseDTO checkItemResultStatus(int psId) {
        List<MedicalItemCheckResults> results = medicalItemCheckResultsMapper.findAllByIcrProjectSummaryId(psId);
        for (MedicalItemCheckResults result : results) {
            if(result.getIcrStatus() == 0){
                return ResponseDTO.fail("还有细项结果未填写");
            }
        }
        return ResponseDTO.success("");
    }

    @Override
    public ResponseDTO getProjectItems(Integer psId) {
        List<MedicalItemCheckResults> results = medicalItemCheckResultsMapper.findAllByIcrProjectSummaryId(psId);
        return ResponseDTO.success("获取成功", results);
    }

    @Override
    public ResponseDTO editItemResult(MedicalItemCheckResults results) {
        results.setIcrStatus(1);
        int res = medicalItemCheckResultsMapper.updateByIcrId(results, results.getIcrId());
        return res >= 1 ? ResponseDTO.success("修改成功") : ResponseDTO.fail("修改失败");
    }

    /**
     * 编辑项目小结
     * 1.更新项目小结信息
     * 2.查询相关订单的项目小结是否都已经完成，若完成更新总结状态
     */
    @Override
    @Transactional
    public ResponseDTO editProjectSummary(MedicalProjectSummary summary) throws UpdateException{
        summary.setPsStatus(1);
        int res = medicalProjectSummaryMapper.updateByPsId(summary, summary.getPsId());

        if(res <= 0){
            throw new UpdateException("未知修改错误", summary.getPsId());
        }

        MedicalProjectSummary summaryInfo = medicalProjectSummaryMapper.findOneByPsId(summary.getPsId());

        List<MedicalProjectSummary> summaries = medicalProjectSummaryMapper.findAllOrderProjectSummaryByPsId(summaryInfo.getPsOrderId());
        boolean isAllDone = true;
        for (MedicalProjectSummary projectSummary : summaries) {
            if(projectSummary.getPsStatus() < 1){
                isAllDone = false;
                break;
            }
        }

        if(isAllDone){
            int csRes = medicalCheckupSummaryMapper.updateCsStatusByCsOrderId(1, summaryInfo.getPsOrderId());
            if(csRes <= 0){
                throw new UpdateException("未知更新总结状态错误", summary.getPsId());
            }
        }

        return ResponseDTO.success("修改成功");
    }

    @Override
    public ResponseDTO getAllUndoCheckups(PageVO<MedicalCheckupSummary> vo) {
        Page<Object> page = PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<MedicalCheckupSummary> summaries = medicalCheckupSummaryMapper.findAllByCsStatus(1);
        PageInfo<MedicalCheckupSummary> info = new PageInfo<>(summaries);
        return ResponseDTO.success((int) info.getTotal(), summaries);
    }

    @Override
    public ResponseDTO getAllOrderProjects(MedicalCheckupSummary summary) {
        List<MedicalProjectSummary> summaries = medicalProjectSummaryMapper.findAllByPsOrderId(summary.getCsOrderId());
        return ResponseDTO.success("获取成功", summaries);
    }

    @Override
    public ResponseDTO editCheckResultSummary(MedicalCheckupSummary summary) {
        summary.setCsStatus(2);
        int res = medicalCheckupSummaryMapper.updateByCsId(summary, summary.getCsId());
        return res >= 1 ? ResponseDTO.success("更新成功") : ResponseDTO.fail("更新失败");
    }

    @Override
    public ResponseDTO getUserOrders(PageVO<Order> vo, int userId) {
        Page<Object> page = PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<Order> orders = ordersMapper.findAllByUserId(vo.getData(), userId);
        PageInfo<Order> info = new PageInfo<>(orders);
        return ResponseDTO.success(String.valueOf(info.getTotal()), orders);
    }

    @Override
    public ResponseDTO getUserProjectSummaries(PageVO<Order> vo) {
        Page<Object> page = PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<MedicalProjectSummary> summaries = medicalProjectSummaryMapper.findAllByOrderIdAndUserId(vo.getData().getOrderUserId(), vo.getData().getOrderId());
        PageInfo<MedicalProjectSummary> info = new PageInfo<>(summaries);
        return ResponseDTO.success((int) info.getTotal(), summaries);
    }

    @Override
    public ResponseDTO getUserCheckupSummary(PageVO<Order> vo) {
        List<MedicalProjectSummary> projectSummaries = medicalProjectSummaryMapper.findAllByOrderIdAndUserId(vo.getData().getOrderUserId(), vo.getData().getOrderId());
        for (MedicalProjectSummary projectSummary : projectSummaries) {
            if(projectSummary.getPsStatus() == 0){
                return ResponseDTO.fail("还有体检小结未被填写，无法查看体检总结");
            }
        }
        MedicalCheckupSummary summary = medicalCheckupSummaryMapper.findOneByOrderIdAndUserId(vo.getData().getOrderUserId(), vo.getData().getOrderId());
        return ResponseDTO.success("获取成功", summary);
    }

    @Override
    public ResponseDTO getMonthOrderTotalAmount() {
        List<Order> orders = ordersMapper.findAllMonthOrderTotalAmountByOrderStatus(1);
        return ResponseDTO.success("获取成功", orders);
    }


    /**
     * 添加项目小结记录和细项结果记录(暂用方法)
     * @param projects 项目列表
     * @param order 订单信息
     * @param errorMessage 错误提示
     * @throws AddException 添加异常
     */
    private void addProjectSummaryAndItemResult(List<MedicalProject> projects, Order order, String errorMessage) throws AddException {
        for (MedicalProject project : projects) {
            MedicalProjectSummary summary = new MedicalProjectSummary(order.getOrderId(), project.getProjectId());
            int result = medicalProjectSummaryMapper.addOneProjectSummary(summary);
            if(result < 0){
                throw new AddException(errorMessage, order.getOrderId());
            }
            for (MedicalItem item : project.getItems()) {
                int res = medicalItemCheckResultsMapper.addOneResult(new MedicalItemCheckResults(item.getItemId(), order.getOrderId(), summary.getPsId()));
                if(res < 0){
                    throw new AddException(errorMessage, order.getOrderId());
                }
            }
        }
    }

    /**
     * 添加订单详情
     * @param addIds 套餐/项目id列表
     * @param order 订单信息
     * @param type 订单类型(套餐/项目)
     * @param errorMessage 错误提示
     * @throws AddException 添加异常
     */
    private void addDetails(List<Integer> addIds, Order order, int type, String errorMessage) throws AddException {
        for (Integer addId : addIds) {
            OrderDetails orderDetail = new OrderDetails(order.getOrderNumber(), addId, type);
            int result = orderDetailsMapper.addOneOrderDetail(orderDetail);
            if (result <= 0) {
                throw new AddException(errorMessage, order.getOrderId());
            }
        }
    }
}
