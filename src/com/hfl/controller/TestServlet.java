package com.hfl.controller;

import com.hfl.dao.QuestionDao;
import com.hfl.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List questionList=new ArrayList();
        //获取保存试题的会话作用域
        HttpSession session=req.getSession(false);
        //1.调用Dao对象随机从question表拿出4道题目
        QuestionDao dao=new QuestionDao();
        questionList=dao.findRandQuestions(req);
        //2.将4道题目添加到session作为共享数据
        session.setAttribute("questionList",questionList);

        //3.请求转发，申请掉exam.jsp将4道题目写入到响应体
        req.getRequestDispatcher("/exam.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
