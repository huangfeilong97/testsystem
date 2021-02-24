package com.hfl.controller;

import com.hfl.dao.UserDao;
import com.hfl.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

public class UserAddServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.【调用请求对象】读取【请求头】参数信息，得到用户的信息信息
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String sex=req.getParameter("sex");
        String email=req.getParameter("email");

        //2.【调用UserDao】将用户信息填充到INSERT命令并借助JDBC规范发送到数据库服务器
        User user=new User(username,password,sex,email);
        UserDao userDao=new UserDao();

        Date startDate=new Date();
        int result=userDao.insert(user,req);
        Date endDate=new Date();
        System.out.println("添加消耗时间 = "+(endDate.getTime() - startDate.getTime())+"毫秒");//20-25 ms -->2-7ms

        //3.【调用响应对象】将【处理结果】以二进制形式写入到响应体
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();//向Tomcat索要输出流

        if(result ==1){
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }

        
    }
    //Tocmat负责销毁【请求对象】和【响应对象】
    //Tomcat负责将Http响应协议包推送到发起请求的浏览器上
    //浏览器根据响应头content-type指定编译器对响应体二进制内容编辑
    //浏览器将编辑后结果在窗口中展示给用户【结束】

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
