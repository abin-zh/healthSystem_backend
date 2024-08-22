package com.cykj.controller;
import com.cykj.exception.AddException;
import com.cykj.exception.CurdException;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.MedicalCheckupSummary;
import com.cykj.model.pojo.MedicalItemCheckResults;
import com.cykj.model.pojo.MedicalProjectSummary;
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
* (orders)表控制层
*
* @author xxxxx
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
    public ResponseDTO addOrder(@RequestBody OrderVO orderVO){
        try{
            return ordersService.addOneOrder(orderVO);
        } catch (AddException e){
            return ResponseDTO.fail(e.getMessage());
        }
    }

    @PostMapping("search/doctor/dept")
    public ResponseDTO getOrder(@RequestBody PageVO<OrderDeptVO> vo, HttpServletRequest request){

        /**
         * 1.需要有查询的科室ID
         * 2.订单号(体检编号)存在，查询单次订单
         * 3.用户手机号或身份证号存在，查询对应用户所有订单
         */
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
    public ResponseDTO editCheckupEdit(@RequestBody MedicalCheckupSummary summary, HttpServletRequest request){
        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);
        Integer staffId = (Integer) staff.get("staffId");
        if(staffId == null || summary.getCsId() == null || StrUtils.isEmpty(summary.getCsSummary())){
            return ResponseDTO.fail("未提供需求信息");
        }
        summary.setCsStaffId(staffId);
        return ordersService.editCheckResultSummary(summary);
    }
}
