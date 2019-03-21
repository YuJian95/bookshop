package service;

import common.BsFactory;
import domain.BsOrder;
import idao.IBsOrderDao;
import iservice.IBsOrderService;

import java.util.List;

public class BsOrderService implements IBsOrderService {

    private IBsOrderDao orderDao = (IBsOrderDao) BsFactory.getBean("orderDao");

    @Override
    public void addOrder(BsOrder order) {
        orderDao.insert(order);
    }

    @Override
    public void editOrder(BsOrder order) {
        orderDao.update(order);
    }

    @Override
    public void deleteOrder(Integer ordId) {
        orderDao.delete(ordId);
    }

    @Override
    public BsOrder findOrderById(Integer ordId) {
        return orderDao.selectById(ordId);
    }

    @Override
    public List<BsOrder> findOrders(Integer userId, Integer pageNo, Integer pageSize) {
        return orderDao.selectSome(userId, pageNo, pageSize);
    }

    @Override
    public int findCount(Integer userId) {
        return orderDao.selectCount(userId);
    }

    @Override
    public List<BsOrder> findAllOrders(Integer pageNo, Integer pageSize) {
        return orderDao.selectAll();
    }

    @Override
    public int findAllCount() {
        return orderDao.selectAllCount();
    }
}
