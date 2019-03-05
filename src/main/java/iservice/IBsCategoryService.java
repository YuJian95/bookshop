package iservice;

import domain.BsCategory;

import java.util.List;

/**
 * 分类业务逻辑层接口
 */

public interface IBsCategoryService {

    void addCategory(BsCategory category);

    void editCategory(BsCategory category);

    void deleteCategory(Integer catId);

    BsCategory findCategory(Integer catId);

    List<BsCategory> findCategories();

    List<BsCategory> findCategories(Integer pageNo, Integer pageSize);

    int findCount();
}
