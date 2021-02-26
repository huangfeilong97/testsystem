package com.hfl.controller;

import com.hfl.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 阅卷
 */
public class ExamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        List<Question> list=(List)session.getAttribute("questionList");

        int score=0;
        for (Question question :list) {
            Integer questionId=question.getQuestionId();
            String answer=question.getAnswer();
            String userAnswer = req.getParameter("answer_"+questionId);
            if(answer.equals(userAnswer)){
                score+=25;
            }

        }

        req.setAttribute("info","本次考试成绩："+score);
        req.getRequestDispatcher("/info.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
