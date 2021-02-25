package com.hfl.controller;

import com.hfl.dao.QuestionDao;
import com.hfl.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用请求对象获取请求参数
        String title=req.getParameter("title");
        String optionA=req.getParameter("optionA");
        String optionB=req.getParameter("optionB");
        String optionC=req.getParameter("optionC");
        String optionD=req.getParameter("optionD");
        String answer=req.getParameter("answer");

        Question question=new Question(null,title,optionA,optionB,optionC,optionD,answer);
        //2.调用Dao对象将Insert命令推送到数据库，并得到处理结果
        QuestionDao questionDao=new QuestionDao();
        int result=0;
        result=questionDao.insert(question,req);

        //3.通过请求转发，向Tomcat索要info.jsp将处理结果写入到响应体
        if(result==1){
            req.setAttribute("info","试题添加成功");
        }else {
            req.setAttribute("info","试题添加失败");
        }

        req.getRequestDispatcher("/info.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
