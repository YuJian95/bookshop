package dao;

import common.BsMySQLHelper;
import domain.BsDetails;
import exception.MyException;
import idao.IBsDetailDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BsDetailsDao implements IBsDetailDao {

    // 删除订单细目
    @Override
    public void deleteSome(Integer ordId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BsMySQLHelper.connection();
            String sql = "DELETE FROM bs_details WHERE ord_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("删除订单细目失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 添加订单细目
    @Override
    public void insert(BsDetails bsDetails) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BsMySQLHelper.connection();
            String sql = "INSERT INTO bs_details (ord_id, book_id, det_num) VALUES(?,?,?) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("删除订单细目失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }

    }

    @Override
    public void update(BsDetails obj) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public BsDetails selectById(Integer integer) {
        return null;
    }

    @Override
    public List<BsDetails> selectAll() {
        return null;
    }

    @Override
    public List<BsDetails> selectAll(Integer pageSize, Integer pageNo) {
        return null;
    }

    @Override
    public int selectAllCount() {
        return 0;
    }
}
