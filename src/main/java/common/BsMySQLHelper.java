package common;

import java.sql.*;

/**
 * mysql数据库连接工具类
 */

public class BsMySQLHelper {

    private static String userName = "root";


    //获取数据库连接
    public static Connection connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://", "root", "lyage1995");

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
    public static void closeResultSet(ResultSet resultSet) {
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
}
