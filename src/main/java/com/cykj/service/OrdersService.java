package com.cykj.service;

import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalCheckupSummary;
import com.cykj.model.pojo.MedicalItemCheckResults;
import com.cykj.model.pojo.MedicalProjectSummary;
import com.cykj.model.pojo.Order;
import com.cykj.model.vo.OrderDeptVO;
import com.cykj.model.vo.OrderVO;
import com.cykj.model.vo.PageVO;

/**
 * 订单业务
 * @author abin
 * @date 2024/8/8 10:47
*/
public interface OrdersService{

    /**
     * 创建订单(开单)，具体事项看实现
     * @param orderVO 开单信息
     * @return 提示信息
     */
    ResponseDTO addOneOrder(OrderVO orderVO);


    /**
     * 获取订单的项目小结信息(订单号查单个订单，手机号、身份证号查多个订单)
     * @param vo 必要查询信息
     * @return 提示信息
     */
    ResponseDTO getOrderDetail(PageVO<OrderDeptVO> vo);

    /**
     * 检查订单项目的细项结果状态是否为已填写
     * @param psId 体检小结id
     * @return 提示信息
     */
    ResponseDTO checkItemResultStatus(int psId);

    /**
     * 获取订单中体检项目的体检细项信息
     * @param psId 项目小结id
     * @return 提示信息
     */
    ResponseDTO getProjectItems(Integer psId);

    /**
     * 编辑/添加细项结果
     * @param results 体检细项结论列表
     * @return 提示信息
     */
    ResponseDTO editItemResult(MedicalItemCheckResults results);

    /**
     * 编辑/添加项目小结
     * @param summary 体检小结
     * @return 提示信息
     */
    ResponseDTO editProjectSummary(MedicalProjectSummary summary);

    /**
     * 获取所有未总结的体检总结
     * @param vo 体检总结信息及分页信息
     * @return 提示信息
     */
    ResponseDTO getAllUndoCheckups(PageVO<MedicalCheckupSummary> vo);


    /**
     * 获取订单关联的所有体检项目(依据体检总结id)
     * @param summary 体检总结信息
     * @return 提示信息
     */
    ResponseDTO getAllOrderProjects(MedicalCheckupSummary summary);

    /**
     * 编辑/添加体检总结
     * @param summary 体检总结信息
     * @return 提示信息
     */
    ResponseDTO editCheckResultSummary(MedicalCheckupSummary summary);

    /**
     * 获取用户的订单
     * @param vo 分页和订单信息
     * @param userId 用户id
     * @return 提示信息
     */
    ResponseDTO getUserOrders(PageVO<Order> vo, int userId);

    /**
     * 获取用户订单的关联体检小结
     * @param vo 订单信息及分页信息
     * @return 提示信息
     */
    ResponseDTO getUserProjectSummaries(PageVO<Order> vo);

    /**
     * 获取用户订单的关联体检总结
     * @param vo 订单信息及分页信息
     * @return 提示信息
     */
    ResponseDTO getUserCheckupSummary(PageVO<Order> vo);

    /**
     * 获取月交易额列表
     * @return 提示信息
     */
    ResponseDTO getMonthOrderTotalAmount();
}
