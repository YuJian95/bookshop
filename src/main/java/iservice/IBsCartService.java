package iservice;

import domain.BsCartItem;

import java.util.List;

/**
 * 购物车业务逻辑接口
 */

public interface IBsCartService {
    void addItem(Integer bookId);

    void editItem(Integer bookId, int num);

    void deleteItem(Integer bookId);

    void clear();

    List<BsCartItem> findItems();

    float findTotal();
}
