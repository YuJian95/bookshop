package dao;

import common.BsMySQLHelper;
import domain.BsDetails;
import domain.BsOrder;
import domain.BsUser;
import exception.MyException;
import idao.IBsOrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 订单数据访问接口
 */

public class BsOrderDao implements IBsOrderDao {

    // 订单分页查找
    @Override
    public List<BsOrder> selectSome(Integer userId, Integer pageNo, Integer pageSize) {
        if (userId == null) {
            return selectAll(pageSize, pageNo);
        }

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsOrder order;  // 定义对象
        List<BsOrder> list = new ArrayList<>();
        BsUser user = new BsUser();
        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_order t WHERE `user_id` = ? LIMIT ?,?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, userId);  // 传递参数
            preparedStatement.setInt(2, (pageNo - 1) * pageSize);  // 传递参数
            preparedStatement.setInt(3, pageSize);  // 传递参数
            resultSet = preparedStatement.executeQuery();  // 执行查询

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                user.setUserId(userId);
                int ord_id = resultSet.getInt("ord_id");
                List<BsDetails> bsDetails = new BsDetailsDao().selectSomeById(ord_id);
                order = new BsOrder(ord_id, user, userId, resultSet.getTimestamp("ord_datetime"), resultSet.getInt("ord_state"), bsDetails);
                list.add(order);
            }
            return list;

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找订单失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    // 订单数目查找
    @Override
    public int selectCount(Integer userId) {
        String table_name = "bs_order";
        String key = "user_id = ";
        return BsMySQLHelper.calTableCount(table_name, key, userId);
    }

    // 添加新订单
    @Override
    public void insert(BsOrder order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BsDetailsDao detailsDao = new BsDetailsDao();
        try {
            connection = BsMySQLHelper.connection();
            String sql = "INSERT INTO bs_order (ord_id, user_id) VALUES(?,?) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getOrdId());
            preparedStatement.setInt(2, order.getUserId());
            System.out.println(order.getBsDetailses().get(0).toString());
            for (BsDetails details : order.getBsDetailses()) {
                detailsDao.insert(details);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 修改订单
    @Override
    public void update(BsOrder order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BsMySQLHelper.connection();
            String sql = "UPDATE bs_order SET ord_state = ? WHERE ord_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getOrdState());
            preparedStatement.setInt(2, order.getOrdId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("修改失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }

    }

    // 删除订单
    @Override
    public void delete(Integer orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BsMySQLHelper.connection();
            String sql = "DELETE FROM bs_order where ord_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("删除订单失败!");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 查询订单
    @Override
    public BsOrder selectById(Integer orderId) {
        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsOrder order = null;  // 定义对象
        List<BsOrder> list = new ArrayList<>();
        BsUser user = new BsUser();
        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_order t WHERE ord_id = ?";
            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();  // 执行查询

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                int userId = resultSet.getInt("user_id");
                user.setUserId(userId);
                int ord_id = resultSet.getInt("ord_id");
                List<BsDetails> bsDetails = new BsDetailsDao().selectSomeById(ord_id);
                order = new BsOrder(ord_id, user, userId, resultSet.getTimestamp("ord_datetime"), resultSet.getInt("ord_state"), bsDetails);

            }

            return order;

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找所有订单失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    // 订单查询
    @Override
    public List<BsOrder> selectSomeById(Integer orderId) {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsOrder order;  // 定义对象
        List<BsOrder> list = new ArrayList<>();
        BsUser user = new BsUser();
        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_order t WHERE ord_id = ?";
            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();  // 执行查询

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                int userId = resultSet.getInt("user_id");
                user.setUserId(userId);
                int ord_id = resultSet.getInt("ord_id");
                List<BsDetails> bsDetails = new BsDetailsDao().selectSomeById(ord_id);

                order = new BsOrder(ord_id, user, userId, resultSet.getTimestamp("ord_datetime"), resultSet.getInt("ord_state"), bsDetails);
                list.add(order);
            }

            return list;

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找所有订单失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    // 查找所有订单
    @Override
    public List<BsOrder> selectAll() {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsOrder order;  // 定义对象
        List<BsOrder> list = new ArrayList<>();
        BsUser user = new BsUser();
        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_order t ";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            resultSet = preparedStatement.executeQuery();  // 执行查询

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                int userId = resultSet.getInt("user_id");
                user.setUserId(userId);
                int ord_id = resultSet.getInt("ord_id");
                List<BsDetails> bsDetails = new BsDetailsDao().selectSomeById(ord_id);

                order = new BsOrder(ord_id, user, userId, resultSet.getTimestamp("ord_datetime"), resultSet.getInt("ord_state"), bsDetails);
                list.add(order);
            }
            return list;

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找所有订单失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    // 分页查找所有订单
    @Override
    public List<BsOrder> selectAll(Integer pageSize, Integer pageNo) {
        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsOrder order;  // 定义对象
        List<BsOrder> list = new ArrayList<>();
        BsUser user = new BsUser();
        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_order t LIMIT ?,?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, (pageNo - 1) * pageSize);  // 传递参数
            preparedStatement.setInt(2, pageSize);  // 传递参数
            resultSet = preparedStatement.executeQuery();  // 执行查询

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                int userId = resultSet.getInt("user_id");
                user.setUserId(userId);
                int ord_id = resultSet.getInt("ord_id");
                List<BsDetails> bsDetails = new BsDetailsDao().selectSomeById(ord_id);

                order = new BsOrder(ord_id, user, userId, resultSet.getTimestamp("ord_datetime"), resultSet.getInt("ord_state"), bsDetails);
                list.add(order);
            }
            return list;

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("分页查找订单失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    // 订单总数
    @Override
    public int selectAllCount() {
        String table_name = "bs_order";
        return BsMySQLHelper.calTableCount(table_name);
    }
}
