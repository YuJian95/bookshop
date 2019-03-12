package iservice;

import domain.BsOrder;

import java.util.List;

/**
 * 订单业务逻辑层接口
 */

public interface IBsOrderService {
    void addOrder(BsOrder order);

    void editOrder(BsOrder order);

    void deleteOrder(Integer ordId);

    BsOrder findOrderById(Integer ordId);

    List<BsOrder> findOrders(Integer userId, Integer pageNo, Integer pageSize);

    int findCount(Integer userId);

}
