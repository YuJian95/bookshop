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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        BsOrder order = null;  // 定义对象
        List<BsOrder> list = new ArrayList<>();

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "SELECT t.* FROM bs.bs_order t WHERE `user_id` = ? LIMIT ?:?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, userId);  // 传递参数
            preparedStatement.setInt(2, (pageNo - 1) * pageSize);  // 传递参数
            preparedStatement.setInt(3, pageSize);  // 传递参数
            resultSet = preparedStatement.executeQuery();  // 执行查询

            BsUser user = new BsUser();

            if (resultSet.next()) {  // 如果有记录，移动第一条记录
                user.setUserId(userId);
                Set<BsDetails> set = new HashSet<>();
                // set =
                //order = new BsOrder(resultSet.getInt("ord_id"),user,userId,resultSet.getTimestamp("ord_datetime"),resultSet.getString("ord_state"));

            }
            return null;

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
        return 0;
    }

    // 添加新订单
    @Override
    public void insert(BsOrder obj) {

    }

    // 修改订单
    @Override
    public void update(BsOrder obj) {

    }

    // 删除订单
    @Override
    public void delete(Integer integer) {

    }

    // 订单查询
    @Override
    public BsOrder selectById(Integer integer) {
        return null;
    }

    // 查找所有订单
    @Override
    public List<BsOrder> selectAll() {
        return null;
    }

    // 分页查找所有订单
    @Override
    public List<BsOrder> selectAll(Integer pageSize, Integer pageNo) {
        return null;
    }

    // 订单总数
    @Override
    public int selectAllCount() {
        return 0;
    }
}
