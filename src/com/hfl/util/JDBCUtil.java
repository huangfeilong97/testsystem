package com.hfl.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
     * --------------------使用全局作用域对象封装连接数据库-------------------------
     */
    public static Connection getConnection(HttpServletRequest request) {
        Connection conn= null;
        //获取全局作用域对象
        ServletContext application = request.getServletContext();
        Map map=(Map)application.getAttribute("connection");
        //获取数据库连接对象集合
        Iterator it=map.keySet().iterator();
        while (it.hasNext()){
            Connection connection=(Connection) it.next();
            boolean value=(boolean)map.get(connection);
            if(value){//空闲
                conn=connection;//获取数据库连接
                map.put(connection,false);
            }
        }
        return conn;
    }

    /**
     *
     * @param request 获取全局作用域对象
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn,Statement stmt, ResultSet rs,HttpServletRequest request){
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

        ServletContext application=request.getServletContext();
        Map map =(Map)application.getAttribute("connection");
        if(conn!=null){
            map.put(conn,true);//数据库连接对象变空闲状态
        }

    }
    //---------------------------使用全局作用域对象封装连接数据库------------------------

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
