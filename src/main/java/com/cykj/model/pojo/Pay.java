package com.cykj.model.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付信息
 * @author abin
 * @date 2024/8/24 16:32
 */

@Data
public class Pay {

    private String sign;

    private String subject;

    private String tradeStatus;

    private String tradeNo;

    private String outTradeNo;

    private BigDecimal totalAmount;

    private String buyerId;

    private Date gmtPayment;

    private BigDecimal buyerPayAmount;

    private String businessParams;

}
