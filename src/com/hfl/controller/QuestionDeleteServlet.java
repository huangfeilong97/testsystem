package com.hfl.controller;

import com.hfl.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionId=req.getParameter("questionId");
        QuestionDao questionDao=new QuestionDao();
        int result=questionDao.deleteById(Integer.valueOf(questionId),req);
        if(result==1){
            req.setAttribute("info","试题删除成功");
        }else {
            req.setAttribute("info","试题删除失败");
        }
        req.getRequestDispatcher("/info.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
