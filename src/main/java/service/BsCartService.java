package service;

import common.BsFactory;
import domain.BsCartItem;
import idao.IBsCartDao;
import iservice.IBsCartService;

import java.util.List;

public class BsCartService implements IBsCartService {

    //private IBsDetailDao detailDao = (IBsDetailDao) BsFactory.getBean("detailDao");
    private IBsCartDao cartDao = (IBsCartDao) BsFactory.getBean("cartDao");

    // 添加购物车
    @Override
    public void addItem(BsCartItem cartItem) {
        cartDao.insert(cartItem);
    }

    // 修改购物车
    @Override
    public void editItem(BsCartItem cartItem, int num) {
        cartDao.update(cartItem, num);
    }

    // 删除书籍
    @Override
    public void deleteItem(Integer cartId) {
        cartDao.delete(cartId);
    }

    // 清空购物车
    @Override
    public void clear(Integer userId) {
        cartDao.clear(userId);
    }

    // 查找购物车的物品
    @Override
    public List<BsCartItem> findItems(Integer userId) {
        return cartDao.selectSomeById(userId);
    }

    // 计算总价
    @Override
    public float findTotal(Integer userId) {
        return cartDao.calTotal(userId);
    }
}
