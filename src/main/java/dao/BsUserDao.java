package dao;

import common.BsMySQLHelper;
import exception.MyException;
import domain.BsUser;
import idao.IBsUserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据访问类
 */

public class BsUserDao implements IBsUserDao {

    // 根据用户名,密码查询用户
    @Override
    public BsUser selectOne(String userName, String userPwd) {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsUser user = null;  // 定义用户对象

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_user t WHERE `user_name` = ? and `user_pwd` = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setString(1, userName);  // 传递参数
            preparedStatement.setString(2, userPwd);

            resultSet = preparedStatement.executeQuery();  // 执行查询

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                user = new BsUser(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("user_pwd"),
                        resultSet.getString("user_realName"), resultSet.getString("user_phone"),
                        resultSet.getString("user_email"), resultSet.getString("user_addr"),
                        resultSet.getTimestamp("user_datetime"), resultSet.getInt("user_right"));
            }

            return user;

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找用户失败!");
        } finally {

            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    // 找回密码
    @Override
    public String selectPwd(String userName, String userEmail) {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsUser user = null;

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_user t WHERE `user_name` = ? and `user_email` = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setString(1, userName);  // 传递参数
            preparedStatement.setString(2, userEmail);

            resultSet = preparedStatement.executeQuery();  // 执行查询


            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                user = new BsUser(resultSet.getInt("user_id"), userName, resultSet.getString("user_pwd"),
                        resultSet.getString("user_realName"), resultSet.getString("user_phone"),
                        userEmail, resultSet.getString("user_addr"),
                        resultSet.getTimestamp("user_datetime"), resultSet.getInt("user_right"));
            }

            return user.getUserPwd();

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找用户失败!");
        } finally {

            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    // 新建用户
    @Override
    public void insert(BsUser user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "INSERT INTO `bs`.`bs_user` (`user_name`, `user_pwd`, `user_realName`, `user_phone`, `user_email`, `user_addr`, `user_datetime`, `user_right`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            //如下6行给预处理设置参数值
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPwd());
            preparedStatement.setString(3, user.getUserRealName());
            preparedStatement.setString(4, user.getUserPhone());
            preparedStatement.setString(5, user.getUserEmail());
            preparedStatement.setString(6, user.getUserAddr());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("添加用户失败!");
        } finally {

            if (preparedStatement != null) {
                BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            }

            if (connection != null) {
                BsMySQLHelper.closeConnection(connection);//关闭连接
            }
        }
    }

    // 修改用户
    @Override
    public void update(BsUser user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "UPDATE `bs`.`bs_user` t " +
                    "SET t.`user_name` = ?, t.`user_pwd` = ?, t.`user_realName` = ?, t.`user_phone` = ?, t.`user_email` = ?, t.`user_addr` = ? " +
                    "WHERE t.`user_id` = ?";

            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            //如下7行给预处理设置参数值
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPwd());
            preparedStatement.setString(3, user.getUserRealName());
            preparedStatement.setString(4, user.getUserPhone());
            preparedStatement.setString(5, user.getUserEmail());
            preparedStatement.setString(6, user.getUserAddr());
            preparedStatement.setInt(7, user.getUserId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("修改用户失败!");
        } finally {

            if (preparedStatement != null) {
                BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            }

            if (connection != null) {
                BsMySQLHelper.closeConnection(connection);//关闭连接
            }
        }
    }

    // 删除用户
    @Override
    public void delete(Integer userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "DELETE FROM `bs`.`bs_user` WHERE `user_id` = ?";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            preparedStatement.setInt(1, userId);

            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("删除用户失败!");
        } finally {

            if (preparedStatement != null) {
                BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            }

            if (connection != null) {
                BsMySQLHelper.closeConnection(connection);//关闭连接
            }
        }
    }

    // 查找用户, 通过id
    @Override
    public BsUser selectById(Integer userId) {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集对象
        BsUser user = null;  // 定义用户对象

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_user t WHERE `user_id` = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, userId);  // 传递参数
            resultSet = preparedStatement.executeQuery();  // 执行查询

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                user = new BsUser(userId, resultSet.getString("user_name"), resultSet.getString("user_pwd"),
                        resultSet.getString("user_realName"), resultSet.getString("user_phone"),
                        resultSet.getString("user_email"), resultSet.getString("user_addr"),
                        resultSet.getTimestamp("user_datetime"), resultSet.getInt("user_right"));
            }
            return user;

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找用户失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);  // 关闭结果集, 预处理, 连接.
        }
    }

    // 查找所有用户
    @Override
    public List<BsUser> selectAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BsUser user;
        List<BsUser> userList;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT t.* FROM bs.bs_user t";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            resultSet = preparedStatement.executeQuery();

            userList = new ArrayList<BsUser>();

            while (resultSet.next()) {
                user = new BsUser(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("user_pwd"),
                        resultSet.getString("user_realName"), resultSet.getString("user_phone"),
                        resultSet.getString("user_email"), resultSet.getString("user_addr"),
                        resultSet.getTimestamp("user_datetime"), resultSet.getInt("user_right"));

                userList.add(user);
            }

            return userList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查找所有用户失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    // 分页查找所有用户
    @Override
    public List<BsUser> selectAll(Integer pageNo, Integer pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<BsUser> userList = new ArrayList<>();

        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT t.* FROM bs.bs_user t LIMIT  ?,?";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            preparedStatement.setInt(1, (pageNo - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            BsUser user;

            while (resultSet.next()) {

                user = new BsUser(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("user_pwd"),
                        resultSet.getString("user_realName"), resultSet.getString("user_phone"),
                        resultSet.getString("user_email"), resultSet.getString("user_addr"),
                        resultSet.getTimestamp("user_datetime"), resultSet.getInt("user_right"));
                userList.add(user);
            }
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("分页查询用户错误!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    // 计算选择总数
    @Override
    public int selectAllCount() {

        String tableName = "book_user";

        return BsMySQLHelper.calTableCount(tableName);
    }

}
