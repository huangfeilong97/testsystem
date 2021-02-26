package com.hfl.controller;

import com.hfl.dao.QuestionDao;
import com.hfl.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title,optionA,optionB,optionC,optionD,answer,questionId;
        QuestionDao dao = new QuestionDao();
        Question question=null;
        int result = 0;
        //1.调用请求对象读取请求头参数信息
        title= request.getParameter("title");
        optionA= request.getParameter("optionA");
        optionB= request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer  = request.getParameter("answer");
        questionId = request.getParameter("questionId");
        question=new Question(Integer.valueOf(questionId),title,optionA,optionB,optionC,optionD,answer);

        result = dao.update(question,request);

        if(result==1){
            request.setAttribute("info","试题更新成功");
        }else {
            request.setAttribute("info","试题更新失败");
        }

        request.getRequestDispatcher("/info.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
