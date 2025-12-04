package com.boda.secondhandtransaction.DAO;


import java.sql.*;

public class DBUtil {

    // 加载驱动（只加载一次）
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/items?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root1231";
        return DriverManager.getConnection(url, user, password);
    }

    // 关闭资源
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
        } catch (Exception ignored) {}

        try {
            if (stmt != null) stmt.close();
        } catch (Exception ignored) {}

        try {
            if (conn != null) conn.close();
        } catch (Exception ignored) {}
    }
}