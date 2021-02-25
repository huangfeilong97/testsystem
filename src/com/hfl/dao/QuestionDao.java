package com.hfl.dao;

import com.hfl.entity.Question;
import com.hfl.util.JDBCUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
