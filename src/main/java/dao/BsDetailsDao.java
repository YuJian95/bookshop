package dao;

import common.BsMySQLHelper;
import domain.BsBook;
import domain.BsDetails;
import exception.MyException;
import idao.IBsDetailDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            preparedStatement.setInt(1, ordId);
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
            preparedStatement.setInt(1, bsDetails.getOrdId());
            preparedStatement.setInt(2, bsDetails.getBookId());
            preparedStatement.setDouble(3, bsDetails.getDetNum());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("添加订单细目失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 更新订单细目
    @Override
    public void update(BsDetails bsDetails) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BsMySQLHelper.connection();
            String sql = "UPDATE bs_details SET book_id = ? ,det_num = ? WHERE det_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bsDetails.getBookId());
            preparedStatement.setDouble(2, bsDetails.getDetNum());
            preparedStatement.setInt(3, bsDetails.getDetId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("更新订单细目失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 删除所有订单细节
    @Override
    public void delete(Integer detId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BsMySQLHelper.connection();
            String sql = "DELETE FROM bs_details WHERE det_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, detId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("删除订单细目失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    @Override
    public BsDetails selectById(Integer integer) {
        return null;
    }

    // 选择指定id的订单
    @Override
    public List<BsDetails> selectSomeById(Integer ordId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        BsDetails bsDetails;
        BsBook book;
        List<BsDetails> list = new ArrayList<>();
        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT * FROM bs_details WHERE ord_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ordId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bsDetails = new BsDetails();
                int book_id = resultSet.getInt("book_id");
                bsDetails.setBookId(book_id);
                bsDetails.setOrdId(ordId);
                bsDetails.setDetId(resultSet.getInt("det_id"));
                bsDetails.setDetNum(resultSet.getDouble("det_num"));

                book = new BsBookDao().selectById(book_id);
                bsDetails.setBook(book);

                list.add(bsDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("查找订单细目失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }

        return list;
    }

    // 获取所有细节
    @Override
    public List<BsDetails> selectAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        BsDetails bsDetails;
        BsBook book;
        List<BsDetails> list = new ArrayList<>();
        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT * FROM bs_details";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bsDetails = new BsDetails();
                int book_id = resultSet.getInt("book_id");
                bsDetails.setBookId(book_id);
                bsDetails.setOrdId(resultSet.getInt("ord_id"));
                bsDetails.setDetId(resultSet.getInt("det_id"));
                bsDetails.setDetNum(resultSet.getDouble("det_num"));

                book = new BsBookDao().selectById(book_id);
                bsDetails.setBook(book);
                list.add(bsDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("查找订单细目失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
        return list;
    }

    // 分页查找订单细目
    @Override
    public List<BsDetails> selectAll(Integer pageSize, Integer pageNo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        BsDetails bsDetails;
        BsBook book;
        List<BsDetails> list = new ArrayList<>();

        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT * FROM bs_details LIMIT  ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (pageNo - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bsDetails = new BsDetails();
                int book_id = resultSet.getInt("book_id");
                bsDetails.setBookId(book_id);
                bsDetails.setOrdId(resultSet.getInt("ord_id"));
                bsDetails.setDetId(resultSet.getInt("det_id"));
                bsDetails.setDetNum(resultSet.getDouble("det_num"));

                book = new BsBookDao().selectById(book_id);
                bsDetails.setBook(book);
                list.add(bsDetails);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("分页查找订单细目失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 获取细节总数
    @Override
    public int selectAllCount() {
        String tableName = "bs_details";
        return BsMySQLHelper.calTableCount(tableName);
    }

}
