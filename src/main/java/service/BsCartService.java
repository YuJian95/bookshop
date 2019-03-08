package service;

import common.BsFactory;
import domain.BsCartItem;
import idao.IBsDetailDao;
import iservice.IBsCartService;

import java.util.List;

public class BsCartService implements IBsCartService {

    private IBsDetailDao detailDao = (IBsDetailDao) BsFactory.getBean("detailDao");

    @Override
    public void addItem(Integer bookId) {
        //detailDao.insert(bookId);
    }

    @Override
    public void editItem(Integer bookId, int num) {

    }

    @Override
    public void deleteItem(Integer bookId) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<BsCartItem> findItems() {
        return null;
    }

    @Override
    public float findTotal() {
        return 0;
    }
}
