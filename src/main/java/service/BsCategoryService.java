package service;

import common.BsFactory;
import dao.BsCategoryDao;
import domain.BsCategory;
import iservice.IBsCategoryService;

import java.util.List;

public class BsCategoryService implements IBsCategoryService {

    private BsCategoryDao categoryDao = (BsCategoryDao) BsFactory.getBean("categoryDao");

    @Override
    public void addCategory(BsCategory category) {
        categoryDao.insert(category);
    }

    @Override
    public void editCategory(BsCategory category) {
        categoryDao.update(category);
    }

    @Override
    public void deleteCategory(Integer catId) {
        categoryDao.delete(catId);
    }

    @Override
    public BsCategory findCategory(Integer catId) {
        return categoryDao.selectById(catId);
    }

    @Override
    public List<BsCategory> findCategories() {
        return categoryDao.selectAll();
    }

    @Override
    public List<BsCategory> findCategories(Integer pageNo, Integer pageSize) {
        return categoryDao.selectAll(pageNo, pageSize);
    }

    @Override
    public int findCount() {
        return categoryDao.selectAllCount();
    }
}
