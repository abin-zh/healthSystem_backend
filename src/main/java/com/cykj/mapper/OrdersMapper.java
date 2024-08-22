package com.cykj.mapper;
import com.cykj.model.vo.OrderDeptVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.cykj.model.pojo.Order;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface OrdersMapper {
    int addOneOrder(Order order);

    List<Order> findAllByOrderInfo(@Param("orderInfo") OrderDeptVO orderInfo, @Param("orderStatus") int orderStatus);


}