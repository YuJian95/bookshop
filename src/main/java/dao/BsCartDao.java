package dao;

import common.BsMySQLHelper;
import domain.BsBook;
import domain.BsCartItem;
import exception.MyException;
import idao.IBsCartDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车数据访问类
 */

public class BsCartDao implements IBsCartDao {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private BsCartItem cartItem = null;
    private BsBook book = null;

    // 加入购物车
    @Override
    public void insert(BsCartItem bsCartItem) {
        String sql = "INSERT INTO bs.bs_cart (user_id, book_id, book_num,book_name,book_price) VALUE (?,?,?,?,?)";
        try {
            book = new BsBookDao().selectById(bsCartItem.getBook().getBookId());
            connection = BsMySQLHelper.connection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bsCartItem.getUserId());
            preparedStatement.setInt(2, book.getBookId());
            preparedStatement.setInt(3, bsCartItem.getNum());
            preparedStatement.setString(4, book.getBookName());
            preparedStatement.setDouble(5, book.getBookPrice());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("加入购物车失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 修改购物车
    @Override
    public void update(BsCartItem bsCartItem) {
        String sql = "UPDATE bs.bs_cart SET book_id=?, book_num=?, book_name=?,book_price=? WHERE cart_id = ?";
        try {
            connection = BsMySQLHelper.connection();
            preparedStatement = connection.prepareStatement(sql);
            book = bsCartItem.getBook();
            preparedStatement.setInt(1, bsCartItem.getBook().getBookId());
            preparedStatement.setInt(2, bsCartItem.getNum());
            preparedStatement.setString(3, book.getBookName());
            preparedStatement.setDouble(4, book.getBookPrice());
            preparedStatement.setInt(5, bsCartItem.getCartId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("更新购物车失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 删除当前书籍图书
    @Override
    public void delete(Integer cartId) {
        String sql = "DELETE FROM bs.bs_cart WHERE cart_id=?";
        try {
            connection = BsMySQLHelper.connection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cartId);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("删除购物车失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    @Override
    public BsCartItem selectById(Integer integer) {
        return null;
    }

    // 查找购物车中的书籍
    @Override
    public List<BsCartItem> selectSomeById(Integer userId) {
        List<BsCartItem> itemList = new ArrayList<>();
        String sql = "SELECT * FROM bs.bs_cart WHERE user_id = ?";
        try {
            connection = BsMySQLHelper.connection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cartItem = new BsCartItem();
                cartItem.setUserId(userId);
                book = new BsBookDao().selectById(resultSet.getInt("book_id"));
                cartItem.setBook(book);
                cartItem.setNum(resultSet.getInt("book_num"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setTotal();
                System.out.println(cartItem.getTotal());
                itemList.add(cartItem);
            }
            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查看购物车失败");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<BsCartItem> selectAll() {
        return null;
    }

    @Override
    public List<BsCartItem> selectAll(Integer pageSize, Integer pageNo) {
        return null;
    }

    @Override
    public int selectAllCount() {
        return 0;
    }

    // 修改购物车中的书籍数量
    @Override
    public void update(BsCartItem bsCartItem, int num) {
        String sql = "UPDATE bs.bs_cart SET book_num=? WHERE user_id=? AND book_id =?";
        try {
            connection = BsMySQLHelper.connection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, num);
            preparedStatement.setInt(2, bsCartItem.getUserId());
            preparedStatement.setInt(3, bsCartItem.getBook().getBookId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("更新购物车失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    @Override
    public void clear(Integer userId) {
        String sql = "DELETE FROM bs.bs_cart WHERE user_id=?";
        try {
            connection = BsMySQLHelper.connection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("清空购物车失败");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 返回购物车总数
    @Override
    public int selectAllCount(Integer userId) {
        String tableName = "bs_cart";
        String key = " user_id = ";
        return BsMySQLHelper.calTableCount(tableName, key, userId);
    }

    @Override
    public float calTotal(Integer userId) {
        float total = 0;
        List<BsCartItem> list = new BsCartDao().selectSomeById(userId);
        for (BsCartItem cartItem : list) {
            total += cartItem.getNum() * cartItem.getBook().getBookPrice();
        }
        return total;
    }
}
