package com.cykj.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付vo
 * @author abin
 * @date 2024/8/24 14:44
 */

@Data
public class PayVO {

    /**
     * 支付总金额
     */
    private BigDecimal totalAmount;

}
