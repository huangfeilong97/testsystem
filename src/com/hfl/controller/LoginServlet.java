package com.hfl.controller;

import com.hfl.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //调用请求对象对请求体使用utf-8字符集进行重新编辑
        req.setCharacterEncoding("utf-8");
        //调用请求对象从请求体中读取请求参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        //调用DAO将登录验证信息推送到数据库服务器
        UserDao userDao=new UserDao();
        boolean flag=userDao.login(username,password);
        //获取输出流
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //调用响应对象，根据验证结果将不同资源文件地址写入到响应头，交给浏览器
        if(flag){
            //在判定来访用户身份合法后，通过请求对象向Tomcat申请为当前用户申请一个HttpSession
            HttpSession session=req.getSession();
            resp.sendRedirect("/myWeb/index.html");
        }else {
            resp.sendRedirect("/myWeb/login_error.html");
        }


    }
}
