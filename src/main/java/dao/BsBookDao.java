package dao;

import common.BsMySQLHelper;
import domain.BsBook;
import domain.BsCategory;
import exception.MyException;
import idao.IBsBookDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 书籍数据访问类
 */

public class BsBookDao implements IBsBookDao {

    // 分页分类查询
    @Override
    public List<BsBook> selectSome(Integer catId, String bookName, String bookAuthor, Integer pageNo, Integer pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BsBook book;
        List<BsBook> bookList;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT t.* FROM bs.bs_book t WHERE book_name = ? AND cat_id = ? AND book_author = ? LIMIT  ?,?";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象
            preparedStatement.setString(1, bookName);
            preparedStatement.setInt(2, catId);
            preparedStatement.setString(3, bookAuthor);
            preparedStatement.setInt(4, (pageNo - 1) * pageSize);
            preparedStatement.setInt(5, pageSize);

            resultSet = preparedStatement.executeQuery();

            bookList = new ArrayList<>();

            while (resultSet.next()) {
                book = new BsBook();
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookPrice(resultSet.getInt("book_price"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookPicture(resultSet.getString("book_picture"));
                book.setBookNum(resultSet.getInt("book_num"));
                book.setBookName(resultSet.getString("book_name"));
                book.setCategory(new BsCategoryDao().selectById(resultSet.getInt("cat_id")));
                book.setBookIsbn(resultSet.getString("book_isbn"));
                book.setBookDesc(resultSet.getString("book_desc"));
                bookList.add(book);
            }

            return bookList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("分页查找指定书籍失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    // 查询指定结果书
    @Override
    public int selectCount(Integer catId, String bookName, String bookAuthor) {
        Connection connection = null;  // 创建连接
        PreparedStatement preparedStatement = null;  // 创建预编译
        ResultSet resultSet = null; // 创建结果集

        int count = 0;
        try {
            connection = BsMySQLHelper.connection();  // 建立连接
            String sql = "SELECT COUNT(*) FROM bs_book where book_name = ? AND book_author = ? AND cat_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bookName);
            preparedStatement.setString(2, bookAuthor);
            preparedStatement.setInt(3, catId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");  // 查询记录结果
            }
            return count;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查询指定记录数错误! ");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    // 添加书籍
    @Override
    public void insert(BsBook book) {
        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接
            String sql = "INSERT INTO bs.bs_book (cat_id,book_publisher,book_isbn,book_name,book_picture,book_price,book_author,book_desc,book_num) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, book.getCategory().getCatId());  // 传递参数
            preparedStatement.setString(2, book.getBookPublisher());
            preparedStatement.setString(3, book.getBookIsbn());
            preparedStatement.setString(4, book.getBookName());
            preparedStatement.setString(5, book.getBookPicture());
            preparedStatement.setDouble(6, book.getBookPrice());
            preparedStatement.setString(7, book.getBookAuthor());
            preparedStatement.setString(8, book.getBookDesc());
            preparedStatement.setInt(9, book.getBookNum());

            preparedStatement.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("添加书籍失败!");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 更新书籍
    @Override
    public void update(BsBook book) {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接

            String sql = "UPDATE bs.bs_book t " +
                    "SET t.cat_id = ?, t.book_publisher = ?, t.book_isbn = ?, t.book_name = ?, t.book_picture = ?, " +
                    "t.book_price = ?, t.book_author = ?, t.book_desc = ?, t.book_num = ? " +
                    "WHERE t.book_id = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, book.getCategory().getCatId());  // 传递参数
            preparedStatement.setString(2, book.getBookPublisher());
            preparedStatement.setString(3, book.getBookIsbn());
            preparedStatement.setString(4, book.getBookName());
            preparedStatement.setString(5, book.getBookPicture());
            preparedStatement.setDouble(6, book.getBookPrice());
            preparedStatement.setString(7, book.getBookAuthor());
            preparedStatement.setString(8, book.getBookDesc());
            preparedStatement.setInt(9, book.getBookNum());
            preparedStatement.setInt(10, book.getBookId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("更新书籍失败!");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    // 删除书籍
    @Override
    public void delete(Integer bookId) {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接

            String sql = "DELETE FROM `bs`.`bs_book` WHERE `book_id` = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, bookId);  // 传递参数


            preparedStatement.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("书籍失败!");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }


    }

    // 查找书籍
    @Override
    public BsBook selectById(Integer bookId) {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集
        BsBook book = null;
        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接

            String sql = "SELECT t.* FROM `bs`.`bs_book` t WHERE `book_id` = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, bookId);  // 传递参数

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                book = new BsBook();
                book.setBookId(bookId);
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookPrice(resultSet.getInt("book_price"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookPicture(resultSet.getString("book_picture"));
                book.setBookNum(resultSet.getInt("book_num"));
                book.setBookName(resultSet.getString("book_name"));
                book.setCategory(new BsCategoryDao().selectById(resultSet.getInt("cat_id")));
                book.setBookIsbn(resultSet.getString("book_isbn"));
                book.setBookDesc(resultSet.getString("book_desc"));
            }

            return book;
        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找书籍失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    // 查找所有书籍
    @Override
    public List<BsBook> selectAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BsBook book;
        List<BsBook> bookList;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT t.* FROM bs.bs_book t";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            resultSet = preparedStatement.executeQuery();

            bookList = new ArrayList<BsBook>();

            while (resultSet.next()) {
                book = new BsBook();
                BsCategory category = new BsCategory();
                category.setCatId(resultSet.getInt("cat_id"));
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookPrice(resultSet.getInt("book_price"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookPicture(resultSet.getString("book_picture"));
                book.setBookNum(resultSet.getInt("book_num"));
                book.setBookName(resultSet.getString("book_name"));
                book.setCategory(category);
                book.setBookIsbn(resultSet.getString("book_isbn"));
                book.setBookDesc(resultSet.getString("book_desc"));
                bookList.add(book);
            }

            return bookList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查找所有书籍失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    // 分页查找所有书籍
    @Override
    public List<BsBook> selectAll(Integer pageSize, Integer pageNo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BsBook book;
        List<BsBook> bookList;

        try {
            connection = BsMySQLHelper.connection();
            String sql = "SELECT t.* FROM bs.bs_book t LIMIT  ?,?";
            preparedStatement = connection.prepareStatement(sql);  //建立预处理对象

            preparedStatement.setInt(1, (pageNo - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);

            resultSet = preparedStatement.executeQuery();

            bookList = new ArrayList<>();

            while (resultSet.next()) {
                BsCategory category = new BsCategory();
                category.setCatId(resultSet.getInt("cat_id"));

                book = new BsBook();
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookPrice(resultSet.getInt("book_price"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookPicture(resultSet.getString("book_picture"));
                book.setBookNum(resultSet.getInt("book_num"));
                book.setBookName(resultSet.getString("book_name"));
                book.setCategory(category);
                book.setBookIsbn(resultSet.getString("book_isbn"));
                book.setBookDesc(resultSet.getString("book_desc"));
                bookList.add(book);
            }

            return bookList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("分页查找所有书籍失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    // 查找书籍总数
    @Override
    public int selectAllCount() {
        String tableName = "bs_book";
        return BsMySQLHelper.calTableCount(tableName);
    }

    // 查找分类书籍
    @Override
    public List<BsBook> selectSomeById(Integer catId) {
        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集
        BsBook book = null;
        List<BsBook> list = new ArrayList<>();
        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接

            String sql = "SELECT t.* FROM `bs`.`bs_book` t WHERE `cat_id` = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            preparedStatement.setInt(1, catId);  // 传递参数

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                book = new BsBook();
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookPrice(resultSet.getInt("book_price"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookPicture(resultSet.getString("book_picture"));
                book.setBookNum(resultSet.getInt("book_num"));
                book.setBookName(resultSet.getString("book_name"));
                book.setCategory(new BsCategoryDao().selectById(catId));
                book.setBookIsbn(resultSet.getString("book_isbn"));
                book.setBookDesc(resultSet.getString("book_desc"));
                book.setCarousel(resultSet.getInt("iscarousel"));
                list.add(book);
            }

            return list;
        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找书籍失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    public int selectSomeCount(Integer catId) {
        String tableName = "bs_book";
        String key = "cat_id = ";
        return BsMySQLHelper.calTableCount(tableName, key, catId);
    }

    // 修改播单
    @Override
    public void editBook(Integer bookId, Integer isCarousel) {
        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象

        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接

            String sql = "UPDATE bs.bs_book t " +
                    "SET t.iscarousel = ?" + "WHERE t.book_id = ?";

            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象

            preparedStatement.setInt(1, isCarousel);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("修改播单失败!");
        } finally {
            BsMySQLHelper.closeConPreSta(preparedStatement, connection);
        }
    }

    @Override
    public List<BsBook> selectCarouselBook() {

        Connection connection = null;  // 定义连接对象
        PreparedStatement preparedStatement = null;  // 定义预处理对象
        ResultSet resultSet = null;  // 定义结果集
        BsBook book = null;
        List<BsBook> list = new ArrayList<>();
        try {
            connection = BsMySQLHelper.connection();  // 建立数据库连接

            String sql = "SELECT t.* FROM `bs`.`bs_book` t WHERE `iscarousel` = 1";
            preparedStatement = connection.prepareStatement(sql);  // 建立预处理对象
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                book = new BsBook();
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookPrice(resultSet.getInt("book_price"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookPicture(resultSet.getString("book_picture"));
                book.setBookNum(resultSet.getInt("book_num"));
                book.setBookName(resultSet.getString("book_name"));
                book.setCategory(new BsCategoryDao().selectById(resultSet.getInt("cat_id")));
                book.setBookIsbn(resultSet.getString("book_isbn"));
                book.setBookDesc(resultSet.getString("book_desc"));
                book.setCarousel(resultSet.getInt("iscarousel"));
                list.add(book);
            }

            return list;
        } catch (Exception e) {

            e.printStackTrace();
            throw new MyException("查找书籍失败!");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }
}
