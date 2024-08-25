package com.cykj.util;

import cn.hutool.core.bean.BeanUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.cykj.model.pojo.Pay;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付工具类
 * @author abin
 * @date 2024/8/24 14:49
 */
public class AliPayUtils {

    /**
     *
     * @param config 配置信息
     * @param nofiyUrl 通知url
     * @param subject 标题
     * @param outTradeNo 订单号
     * @param totalAmount 总金额
     * @return 支付页面
     */
    public static String getTradePayPage(AlipayConfig config, String nofiyUrl,String subject, String outTradeNo, String totalAmount) throws RuntimeException{
        AlipayTradePagePayResponse response;
        try {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            AlipayTradePayModel model = new AlipayTradePayModel();
            model.setOutTradeNo(outTradeNo);
            model.setTotalAmount(totalAmount);
            model.setSubject(subject);
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            request.setBizModel(model);
            request.setNotifyUrl(nofiyUrl);
            DefaultAlipayClient alipayClient = new DefaultAlipayClient(config);
            response = alipayClient.pageExecute(request);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return response.getBody();
    }


    /**
     * 检查交易回调信息(验签)
     * @param config 配置信息
     * @param request 请求内容
     * @return 交易信息
     */
    public static Pay checkPayNotify(AlipayConfig config,HttpServletRequest request){
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }
            Pay pay = BeanUtil.mapToBean(params, Pay.class, true);

            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature; // 验证签名
            try {
                checkSignature = AlipaySignature.rsa256CheckContent(content, pay.getSign(), config.getAlipayPublicKey(), "UTF-8");
            } catch (AlipayApiException e) {
                throw new RuntimeException(e);
            }
            // 支付宝验签
            if (!checkSignature) {
                return null;
            }
            return pay;
        }
        return null;
    }

}
