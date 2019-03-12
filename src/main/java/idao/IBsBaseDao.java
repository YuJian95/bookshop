package idao;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库访问的基本接口
 */

public interface IBsBaseDao<T, ID extends Serializable> {

    //插入操作
    void insert(T obj);

    //更新操作
    void update(T obj);

    //删除操作
    void delete(ID id);

    //通过id查询
    List<T> selectById(ID id);

    //查找所有
    List<T> selectAll();

    //分页查询
    List<T> selectAll(Integer pageSize, Integer pageNo);

    //查询结果个数
    int selectAllCount();
}
