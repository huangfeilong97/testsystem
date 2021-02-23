package com.hfl.controller;

import com.hfl.dao.UserDao;
import com.hfl.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao=new UserDao();
        //获取用户列表
        List<User> userList = userDao.findAll();
        //设置响应数据编码方式
        resp.setContentType("text/html;charset=utf-8");
        //向Tomcat索要输出流
        PrintWriter out=resp.getWriter();
        //输出成表格形式
        out.print("<table  border='2' align='center'>");

        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户密码</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td>操作</td>");
        out.print("</tr>");
        for (User user :userList) {
            out.print("<tr>");
            out.print("<td>"+user.getUserId()+"</td>");
            out.print("<td>"+user.getUsername()+"</td>");
            out.print("<td>"+user.getPassword()+"</td>");
            out.print("<td>"+user.getSex()+"</td>");
            out.print("<td>"+user.getEmail()+"</td>");
            out.print("<td><a href='/myWeb/user/delete?userId="+user.getUserId()+"'>删除</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
