package com.hfl.util;

import java.sql.*;

/**
 * JDBC工具类
 */
public class JDBCUtil {

    static {
        //1.注册数据库驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 封装连接数据库
     */
    public static Connection getConnection() throws SQLException {
        //2.获取数据库连接
        Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powernode","root","123456");

        return conn;
    }

    /**
     * 关闭数据库
     * @param conn 数据库连接对象
     * @param stmt 数据库操作对象
     * @param rs 数据库查询结果集对象
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
