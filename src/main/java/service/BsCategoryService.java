package service;

import common.BsFactory;
import domain.BsCategory;
import idao.IBsCategoryDao;

import java.util.List;

public class BsCategoryService implements IBsCategoryDao {

    private IBsCategoryDao categoryDao = (IBsCategoryDao) BsFactory.getBean("categoryDao");

    @Override
    public void insert(BsCategory obj) {
        categoryDao.insert(obj);
    }

    @Override
    public void update(BsCategory obj) {
        categoryDao.update(obj);
    }

    @Override
    public void delete(Integer integer) {
        categoryDao.delete(integer);
    }

    @Override
    public BsCategory selectById(Integer integer) {
        return categoryDao.selectById(integer);
    }

    @Override
    public List<BsCategory> selectAll() {
        return categoryDao.selectAll();
    }

    @Override
    public List<BsCategory> selectAll(Integer pageSize, Integer pageNo) {
        return categoryDao.selectAll(pageSize, pageNo);
    }

    @Override
    public int selectAllCount() {
        return categoryDao.selectAllCount();
    }
}
