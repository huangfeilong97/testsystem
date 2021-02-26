package com.hfl.controller;

import com.hfl.dao.QuestionDao;
import com.hfl.entity.Question;
import com.hfl.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用Dao获取试题集合
        QuestionDao questionDao=new QuestionDao();
        List<Question> list = questionDao.findAll(req);

        //req.setAttribute("questionList",list);

        String str=JSONUtil.jsonArray(list);

        req.setAttribute("questionList",list);
        //req.setAttribute("questionList",str);
        //请求转发索要资源写入响应体
        req.getRequestDispatcher("/question.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
