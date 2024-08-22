package com.cykj.mapper;

import com.cykj.model.pojo.OrderDetails;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface OrderDetailsMapper {
    int addOneOrderDetail(OrderDetails orderDetails);
}