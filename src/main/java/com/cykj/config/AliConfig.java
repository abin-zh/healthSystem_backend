package com.cykj.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 支付宝扩展配置
 * @author abin
 * @date 2024/8/24 15:51
 */

@Data
@Component
@PropertySource("classpath:alipay.properties")
public class AliConfig {

    @Value("${notifyUrl}")
    private String notifyUrl;

}
