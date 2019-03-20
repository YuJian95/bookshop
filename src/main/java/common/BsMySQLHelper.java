package common;

import exception.MyException;

import java.sql.*;

/**
 * mysql数据库连接工具类
 */

public class BsMySQLHelper {

    private static String USERNAME = "root";
    private static String PASSWORD = "lyage1995";
    private static String URL = "jdbc:mysql://192.168.30.142:3306/bs";

    //获取数据库连接
    public static Connection connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载驱动程序
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e1) {
            System.err.println("数据库连接失败!");
            e1.printStackTrace();
        }
        return connection;
    }

    //关闭预编译
    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("数据库预编译语句,关闭失败!");
            e.printStackTrace();
        }
    }

    //关闭结果集
    private static void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("数据库结果集,关闭失败!");
            e.printStackTrace();
        }
    }

    //关闭连接
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("数据库连接,关闭失败!");
            ex.printStackTrace();
        }
    }

    //  关闭结果集, 预处理, 连接.
    public static void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {  //关闭结果集
            BsMySQLHelper.closeResultSet(resultSet);
        }

        closeConPreSta(preparedStatement, connection);
    }

    // 关闭预处理，连接
    public static void closeConPreSta(PreparedStatement preparedStatement, Connection connection) {
        if (preparedStatement != null) {
            BsMySQLHelper.closePreparedStatement(preparedStatement); //关闭预处理
        }

        if (connection != null) {
            BsMySQLHelper.closeConnection(connection);//关闭连接
        }
    }

    // 查询数据表的记录总数
    public static int calTableCount(String tableName) {
        Connection connection = null;  // 创建连接
        PreparedStatement preparedStatement = null;  // 创建预编译
        ResultSet resultSet = null; // 创建结果集

        int count = 0;
        try {
            connection = BsMySQLHelper.connection();  // 建立连接
            String sql = "SELECT COUNT(*) FROM " + tableName;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");  // 查询记录结果
            }
            return count;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查询" + tableName + "记录数错误! ");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }

    // 查询数据表的记录总数
    public static int calTableCount(String tableName, String key, int id) {
        Connection connection = null;  // 创建连接
        PreparedStatement preparedStatement = null;  // 创建预编译
        ResultSet resultSet = null; // 创建结果集

        int count = 0;
        try {
            connection = BsMySQLHelper.connection();  // 建立连接
            String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + key + id;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");  // 查询记录结果
            }
            return count;

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查询" + tableName + "记录数错误! ");
        } finally {
            BsMySQLHelper.closeAll(connection, preparedStatement, resultSet);
        }
    }
}
