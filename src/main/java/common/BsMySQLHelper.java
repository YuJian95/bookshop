package common;

import java.sql.*;

/**
 * mysql数据库连接工具类
 */

public class BsMySQLHelper {

    private static String USERNAME = "root";
    private static String PASSWORD = "lyage1995";
    private static String URL = "jdbc:mysql://192.168.30.140:3306/bs";

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

        if (preparedStatement != null) {  //关闭预处理
            BsMySQLHelper.closePreparedStatement(preparedStatement);
        }

        if (connection != null) {  //关闭连接
            BsMySQLHelper.closeConnection(connection);
        }
    }
}
