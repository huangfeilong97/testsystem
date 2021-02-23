package com.hfl.controller;

import com.hfl.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求对象参数
        String userId=req.getParameter("userId");
        //2.通过jdbc处理数据
        UserDao userDao=new UserDao();
        int result = userDao.delete(userId);
        //3.索要输出流
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if(result == 1){
            out.print("<font style='color:red;font-size:40'>用户信息删除成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>用户信息删除失败</font>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
