package dao;

import common.BsMySQLHelper;
import common.MyException;
import domain.BsCategory;
import idao.IBsCategoryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类数据访问类
 */

public class BsCategoryDao implements IBsCategoryDao {

    // 添加分类
    @Override
    public void insert(BsCategory category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "INSERT INTO `bs`.`bs_category` (`cat_name`) " +
                    "VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            //如下6行给预处理设置参数值
            preparedStatement.setString(1, category.getCatName());


            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("添加分类失败!");
        } finally {

            if (preparedStatement != null) {
                BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            }

            if (connection != null) {
                BsMySQLHelper.closeConnection(connection);//关闭连接
            }
        }
    }

    // 修改分类
    @Override
    public void update(BsCategory category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "UPDATE `bs`.`bs_category` t SET t.`cat_name` = ? WHERE t.cat_id = ?";

            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            //给预处理设置参数值
            preparedStatement.setString(1, category.getCatName());
            preparedStatement.setInt(2, category.getCatId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("修改分类失败!");
        } finally {

            if (preparedStatement != null) {
                BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            }

            if (connection != null) {
                BsMySQLHelper.closeConnection(connection);//关闭连接
            }
        }
    }

    // 删除分类
    @Override
    public void delete(Integer catId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "DELETE FROM `bs`.`bs_category` WHERE `cat_id` = ?";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            //如下6行给预处理设置参数值
            preparedStatement.setInt(1, catId);

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("删除分类失败!");
        } finally {

            if (preparedStatement != null) {
                BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            }

            if (connection != null) {
                BsMySQLHelper.closeConnection(connection);//关闭连接
            }
        }
    }

    //查找分类
    @Override
    public BsCategory selectById(Integer catId) {
        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsCategory category = null;  // 定义用户对象

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_category t WHERE `cat_id` = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, catId);  // 传递参数
            resultSet = preparedStatement.executeQuery();  // 执行查询

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                category = new BsCategory();
                category.setCatId(resultSet.getInt("cat_id"));
                category.setCatName(resultSet.getString("cat_name"));
            }
            return category;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查找分类失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    //查找所有分类
    @Override
    public List<BsCategory> selectAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BsCategory category;
        List<BsCategory> categoryList;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT t.* FROM bs.bs_category t";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            resultSet = preparedStatement.executeQuery();

            categoryList = new ArrayList<>();

            while (resultSet.next()) {
                category = new BsCategory(resultSet.getInt("cat_id"), resultSet.getString("cat_name"));

                categoryList.add(category);
            }

            return categoryList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查找所有分类失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    //分页查找所有分类
    @Override
    public List<BsCategory> selectAll(Integer pageSize, Integer pageNo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<BsCategory> categoryList = new ArrayList<>();

        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT t.* FROM bs.bs_category t LIMIT  ?,?";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            preparedStatement.setInt(1, (pageNo - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            BsCategory category;

            while (resultSet.next()) {

                category = new BsCategory(resultSet.getInt("cat_id"), resultSet.getString("cat_name"));
                categoryList.add(category);
            }
            return categoryList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("分页查询分类错误!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    //查找记录总数
    @Override
    public int selectAllCount() {

        Connection connection = null;  // 创建连接
        PreparedStatement preparedStatement = null;  // 创建预编译
        ResultSet resultSet = null; // 创建结果集

        int count = 0;
        try {
            connection = BsMySQLHelper.connection();  // 建立连接
            String sql = "SELECT COUNT(*) FROM bs_category";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");  // 查询记录结果
            }
            return count;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查询分类记录数错误! ");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

}
