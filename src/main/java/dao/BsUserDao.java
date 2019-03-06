package dao;

import common.BsMySQLHelper;
import domain.BsUser;
import idao.IBsUserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class BsUserDao implements IBsUserDao {
    @Override
    public BsUser selectOne(String userName, String userPwd) {
        return null;
    }

    @Override
    public String selectPwd(String userName, String userEmail) {
        return null;
    }

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
        } catch (Exception e) {
            e.printStackTrace();
//                throw new MyException("添加用户失败!");
        } finally {
            BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            BsMySQLHelper.closeConnection(connection);//关闭连接
        }
    }

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

            //如下6行给预处理设置参数值
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPwd());
            preparedStatement.setString(3, user.getUserRealName());
            preparedStatement.setString(4, user.getUserPhone());
            preparedStatement.setString(5, user.getUserEmail());
            preparedStatement.setString(6, user.getUserAddr());
            preparedStatement.setInt(7, user.getUserId());

        } catch (Exception e) {
            e.printStackTrace();
//                throw new MyException("修改用户失败!");
        } finally {
            BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            BsMySQLHelper.closeConnection(connection);//关闭连接
        }
    }

    @Override
    public void delete(Integer userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "DELETE FROM `bs`.`bs_user` WHERE `user_id` = ?";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            //如下6行给预处理设置参数值
            preparedStatement.setInt(1, userId);

        } catch (Exception e) {
            e.printStackTrace();
//                throw new MyException("删除用户失败!");
        } finally {
            BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
            BsMySQLHelper.closeConnection(connection);//关闭连接
        }
    }

    @Override
    public BsUser selectById(Integer integer) {
        return null;
    }

    @Override
    public List<BsUser> selectAll() {
        return null;
    }

    @Override
    public List<BsUser> selectAll(Integer pageSize, Integer pageNo) {
        return null;
    }

    @Override
    public int selectAllCount() {
        return 0;
    }
}
