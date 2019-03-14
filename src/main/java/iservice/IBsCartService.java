package iservice;

import domain.BsCartItem;

import java.util.List;

/**
 * 购物车业务逻辑接口
 */

public interface IBsCartService {
    void addItem(BsCartItem cartItem);

    void editItem(BsCartItem cartItem, int num);

    void deleteItem(Integer catId);

    void clear(Integer userId);

    List<BsCartItem> findItems(Integer userId);

    float findTotal(Integer userId);
}
