package com.hfl.dao;

import com.hfl.entity.Question;
import com.hfl.util.JDBCUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    /**
     * 插入方法
     * @param question 需插入的问题记录
     * @return
     */
    public int insert(Question question, HttpServletRequest request) {
        //1.连接数据库
        Connection connection=null;
        PreparedStatement ps=null;
        int result=0;
        connection= JDBCUtil.getConnection(request);
        try {
            //2.获取预编译数据库操作对象
            String sql="insert into question(title,optionA,optionB,optionC,optionD,answer) values(?,?,?,?,?,?)" ;
            ps=connection.prepareStatement(sql);
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());

            //3.执行sql语句
            result=ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //4.释放资源
            JDBCUtil.close(connection,ps,null,request);
        }
        return result;

    }

    /**
     * 获取所有试题
     * @return
     */
    public List<Question> findAll(HttpServletRequest request) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Question> list=new ArrayList<>();
        //获取数据库连接
        connection=JDBCUtil.getConnection(request);

        try {
            //获取预编译数据库操作对象
            String sql="select * from question";
            ps=connection.prepareStatement(sql);
            //执行sql
            rs=ps.executeQuery();
            //处理结果集
            while (rs.next()){
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA=rs.getString("optionA");
                String optionB=rs.getString("optionB");
                String optionC=rs.getString("optionC");
                String optionD=rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question=new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                list.add(question);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection,ps,rs,request);
        }

        return list;

    }

    /**
     * 删除试题
     * @param questionId 需删除的试题编号
     * @return 删除试题数
     */
    public int deleteById(Integer questionId,HttpServletRequest request) {
        Connection conn=null;
        PreparedStatement ps=null;
        int result=0;
        conn=JDBCUtil.getConnection(request);

        try {
            String sql="delete from question where questionId=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,questionId);
            result=ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,null,request);
        }

        return result;

    }

    /**
     * 根据试题编号查找试题信息
     * @param questionId 试题编号
     * @param request 请求对象
     * @return 试题信息
     */
    public Question findById(String questionId, HttpServletRequest request) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Question question=null;

        conn=JDBCUtil.getConnection(request);

        try {
            String sql="select * from question where questionId=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(questionId));

            rs=ps.executeQuery();
            while (rs.next()){
                Integer questionid = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                question=new Question(questionid,title,optionA,optionB,optionC,optionD,answer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs,request);
        }

        return question;

    }

    /**
     * 更新试题
     * @param question 试题信息
     * @param request
     * @return
     */
    public int update(Question question, HttpServletRequest request) {
        Connection conn = null;
        PreparedStatement ps=null;
        int result=0;
        conn=JDBCUtil.getConnection(request);

        try {
            String sql="update question set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=? where questionId=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            ps.setInt(7,question.getQuestionId());

            result = ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,null,request);
        }

        return  result;
    }

    /**
     * 随机出题
     * @return 试题列表
     */
    public List findRandQuestions(HttpServletRequest request) {
        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        conn=JDBCUtil.getConnection(request);
        List questionList=new ArrayList();

        String sql="select * from question ORDER BY RAND() LIMIT 4";
        try {
            ps=conn.prepareStatement(sql);

            rs=ps.executeQuery();

            while (rs.next()){
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA=rs.getString("optionA");
                String optionB=rs.getString("optionB");
                String optionC=rs.getString("optionC");
                String optionD=rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question=new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                questionList.add(question);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs,request);
        }

        return questionList;

    }
}
