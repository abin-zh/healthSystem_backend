package com.cykj.mapper;
import com.cykj.model.pojo.User;
import com.cykj.model.vo.OrderDeptVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.cykj.model.pojo.Order;

/**
 * 订单操作
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface OrdersMapper {
    int addOneOrder(Order order);

    List<Order> findAllByOrderInfo(@Param("orderInfo") OrderDeptVO orderInfo, @Param("orderStatus") int orderStatus);

    List<Order> findAllByUserId(@Param("order") Order order,@Param("userId") int userId);

    Order findOneByOrderNumber(@Param("orderNumber")String orderNumber);

    int updateOrderStatusByOrderNumber(@Param("updatedOrderStatus")Integer updatedOrderStatus,@Param("orderNumber")String orderNumber);

    List<Order> findAllMonthOrderTotalAmountByOrderStatus(int orderStatus);

}