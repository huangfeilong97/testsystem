package com.hfl.controller;

import com.hfl.dao.QuestionDao;
import com.hfl.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionFindByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionId=req.getParameter("questionId");

        QuestionDao questionDao=new QuestionDao();
        Question question =questionDao.findById(questionId,req);

        req.setAttribute("question",question);
        req.getRequestDispatcher("/question_update.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
