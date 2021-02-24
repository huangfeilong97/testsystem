package com.hfl.dao;

import com.hfl.entity.User;
import com.hfl.util.JDBCUtil;
import jdk.nashorn.internal.scripts.JD;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.*;

public class UserDao {
    Connection conn=null;
    /**
     * 登录验证
     * @return true登录成功；false登录失败
     */
    public boolean login(String username,String password){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            conn=JDBCUtil.getConnection();

            String sql="select * from user where username=? and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);

            rs=ps.executeQuery();

            if(rs.next()){
                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }

        return false;


    }

    /**
     * 用户插入方法
     * //JDBC规范中，Connection创建与销毁最浪费时间
     * @return
     */
    public int insert(User user){
        Connection conn=null;
        PreparedStatement ps=null;
        int insert=0;
        try {
            //获取数据库连接
            conn=JDBCUtil.getConnection();
            //获取预编译的数据库操作对象
            String sql="insert into user(username,password,sex,email) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            //执行sql语句
            insert= ps.executeUpdate();

            return insert;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源
            JDBCUtil.close(conn,ps,null);
        }
        return insert;

    }

    /**
     * 用户插入方法(模拟数据库连接池)
     * //JDBC规范中，Connection创建与销毁最浪费时间
     * @return
     */
    public int insert(User user, HttpServletRequest request){
        Connection conn=null;
        PreparedStatement ps=null;
        int insert=0;

        try {
            //获取数据库连接
            conn=JDBCUtil.getConnection(request);
            //获取预编译的数据库操作对象
            String sql="insert into user(username,password,sex,email) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            //执行sql语句
            insert= ps.executeUpdate();

            return insert;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源
            JDBCUtil.close(conn,ps,null,request);
        }
        return insert;

    }

    /**
     * 查找所有用户信息
     * @return
     */
    public List findAll(){

        List<User> userList=new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //1.获取数据库连接
            conn=JDBCUtil.getConnection();
            //2.获取预编译的数据库操作对象
            String sql="select * from user";
            ps=conn.prepareStatement(sql);
            //3.执行sql
            rs=ps.executeQuery();
            //4.处理结果集
            while (rs.next()){
                Integer userId = rs.getInt("userId");
                String username=rs.getString("username");
                String password=rs.getString("password");
                String sex=rs.getString("sex");
                String email=rs.getString("email");
                User user=new User(userId,username,password,sex,email);
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }

        return userList;
    }

    /**
     * 根据用户编号删除用户信息
     * @param 
     * @return
     */
    public int delete(String userId){
        Connection conn=null;
        PreparedStatement ps=null;
        int delete=0;

        try {
            conn=JDBCUtil.getConnection();

            String sql="delete from user where userId = ?";
            ps=conn.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(userId));
            delete= ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,null);
        }
        return delete;


    }

}
