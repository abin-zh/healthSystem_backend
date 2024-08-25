package com.cykj.controller;
import cn.hutool.core.bean.BeanUtil;
import com.cykj.annotation.Monitor;
import com.cykj.exception.AddException;
import com.cykj.exception.CurdException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.*;
import com.cykj.model.vo.OrderDeptVO;
import com.cykj.model.vo.OrderVO;
import com.cykj.model.vo.PageVO;
import com.cykj.service.OrdersService;
import com.cykj.util.CommonUtils;
import com.cykj.util.StrUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
* 体检单(订单)控制层
*
* @author abin
*/
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("add")
    @Monitor("开单")
    public ResponseDTO addOrder(@RequestBody OrderVO orderVO){
        try{
            return ordersService.addOneOrder(orderVO);
        } catch (AddException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @PostMapping("user/add")
    public ResponseDTO addUserOrder(@RequestBody OrderVO orderVO, HttpServletRequest request){
        LinkedHashMap<String, Object> user = CommonUtils.parseTokenInfo("user", request);
        User userInfo = BeanUtil.mapToBean(user, User.class, true);
        if(userInfo.getUserId() == null){
            return ResponseDTO.fail(402,"登录凭证错误，无法创建订单");
        }
        orderVO.setUserId(userInfo.getUserId());
        try{
            return ordersService.addOneOrder(orderVO);
        } catch (AddException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @PostMapping("search/user")
    public ResponseDTO getUserOrders(@RequestBody PageVO<Order> vo, HttpServletRequest request){
        LinkedHashMap<String, Object> user = CommonUtils.parseTokenInfo("user", request);
        User userInfo = BeanUtil.mapToBean(user, User.class, true);
        if(userInfo == null || userInfo.getUserId() == null){
            ResponseDTO.fail(402, "错误的登录凭证");
        }
        return ordersService.getUserOrders(vo, userInfo.getUserId());
    }

    /**
     * 获取科室订单信息
     * 1.需要有查询的科室ID
     * 2.订单号(体检编号)存在，查询单次订单
     * 3.用户手机号或身份证号存在，查询对应用户所有订单
     */
    @PostMapping("search/doctor/dept")
    public ResponseDTO getOrder(@RequestBody PageVO<OrderDeptVO> vo, HttpServletRequest request){
        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);
        Integer deptId = (Integer) staff.get("staffDeptId");

        vo.getData().setDeptId(deptId);
        if(deptId == null){
            return ResponseDTO.fail(402, "无法获取您的科室信息，请重新登录");
        } else if(StrUtils.isEmpty(vo.getData().getOrderNumber()) && StrUtils.isEmpty(vo.getData().getUserPhone()) && StrUtils.isEmpty(vo.getData().getUserIdCard())){
            return ResponseDTO.fail("未提供需求信息");
        }
        return ordersService.getOrderDetail(vo);
    }


    @RequestMapping("doctor/dept/item/status")
    public ResponseDTO checkItemResultStatus(@RequestBody MedicalProjectSummary summary){
        if(summary.getPsId() == null){
            return ResponseDTO.fail("检查失败，未提供需求信息");
        }
        return ordersService.checkItemResultStatus(summary.getPsId());
    }

    @RequestMapping("item/all")
    public ResponseDTO getProjectItems(@RequestBody MedicalProjectSummary summary){
        if(summary.getPsId() == null){
            return ResponseDTO.fail("检查失败，未提供需求信息");
        }
        return ordersService.getProjectItems(summary.getPsId());
    }

    @PostMapping("doctor/dept/item/edit")
    @Monitor("编辑细项结论")
    public ResponseDTO editItemResult(@RequestBody MedicalItemCheckResults results, HttpServletRequest request){
        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);
        Integer staffId = (Integer) staff.get("staffId");
        if(staffId == null || results.getIcrId() == null || StrUtils.hasEmpty(results.getIcrSummary(), results.getIcrResult())){
            return ResponseDTO.fail("未提供需求信息");
        }
        results.setIcrStaffId(staffId);
        return ordersService.editItemResult(results);
    }

    @PostMapping("doctor/dept/project/edit")
    @Monitor("编辑体检小结")
    public ResponseDTO editProjectSummary(@RequestBody MedicalProjectSummary summary, HttpServletRequest request){
        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);
        Integer staffId = (Integer) staff.get("staffId");
        if(staffId == null || summary.getPsId() == null || StrUtils.isEmpty(summary.getPsSummary())){
            return ResponseDTO.fail("未提供需求信息");
        }
        summary.setPsStaffId(staffId);
        try{
            return ordersService.editProjectSummary(summary);
        }catch (CurdException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @RequestMapping("chief/checkup/undo/all")
    public ResponseDTO getAllUndoCheckups(@RequestBody PageVO<MedicalCheckupSummary> vo){
        return ordersService.getAllUndoCheckups(vo);
    }

    @RequestMapping("chief/project/all")
    public ResponseDTO getAllOrderProjects(@RequestBody MedicalCheckupSummary summary){
        if(summary.getCsOrderId() == null){
            return ResponseDTO.fail("未提供需求信息");
        }
        return ordersService.getAllOrderProjects(summary);
    }

    @PostMapping("chief/checkup/edit")
    @Monitor("编辑体检总结")
    public ResponseDTO editCheckupEdit(@RequestBody MedicalCheckupSummary summary, HttpServletRequest request){
        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);
        Integer staffId = (Integer) staff.get("staffId");
        if(staffId == null || summary.getCsId() == null || StrUtils.isEmpty(summary.getCsSummary())){
            return ResponseDTO.fail("未提供需求信息");
        }
        summary.setCsStaffId(staffId);
        return ordersService.editCheckResultSummary(summary);
    }

    @RequestMapping("project/user")
    public ResponseDTO getUserProjectSummaries(@RequestBody PageVO<Order> vo, HttpServletRequest request){
        ResponseDTO dto = userOrderCheck(vo, request);
        if (dto != null){
            return dto;
        }
        return ordersService.getUserProjectSummaries(vo);
    }

    @RequestMapping("checkup/user")
    public ResponseDTO getUserCheckupSummary(@RequestBody PageVO<Order> vo, HttpServletRequest request){
        ResponseDTO dto = userOrderCheck(vo, request);
        if (dto != null) {
            return dto;
        }
        return ordersService.getUserCheckupSummary(vo);
    }


    @RequestMapping("amount/month")
    public ResponseDTO getMonthOrderTotalAmount(){
        return ordersService.getMonthOrderTotalAmount();
    }

    /**
     * 用户获取订单关联信息时检查
     * @param vo 检查信息
     * @param request 请求内容
     * @return 提示信息
     */
    private static ResponseDTO userOrderCheck(PageVO<Order> vo, HttpServletRequest request) {
        LinkedHashMap<String, Object> user = CommonUtils.parseTokenInfo("user", request);
        User userInfo = BeanUtil.mapToBean(user, User.class, true);
        if(vo.getData().getOrderId() == null || userInfo.getUserId() == null){
            return ResponseDTO.fail("未提供需求信息");
        } else if(!userInfo.getUserId().equals(vo.getData().getOrderUserId())){
            return ResponseDTO.fail("无法查看他人的内容");
        }
        return null;
    }
}
