package com.cykj.model.vo;

import lombok.Data;

/**
 * 科室关联订单VO
 * @author abin
 * @date 2024/8/18 15:47
 */

@Data
public class OrderDeptVO {

    private Integer deptId;

    private String orderNumber;

    private String userPhone;

    private String userIdCard;

}
