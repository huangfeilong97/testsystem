<%@ page import="com.hfl.entity.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 黄飞龙
  Date: 2021/2/25
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        List<Question> questionList =(List)request.getAttribute("questionList");
        //第五阶段： JSTL有效弥补EL不能遍历集合输出问题
    %>
</head>
<body>
    <table  border="2" align="center">
        <tr align="center">
            <td>试题编号</td>
            <td>题目信息</td>
            <td>A选项</td>
            <td>B选项</td>
            <td>C选项</td>
            <td>D选项</td>
            <td>正确答案</td>
            <td colspan="2"></td>
        </tr>
        <%
            for(Question question:questionList){
        %>
        <tr>
            <td><%=question.getQuestionId()%></td>
            <td><%=question.getTitle()%></td>
            <td><%=question.getOptionA()%></td>
            <td><%=question.getOptionB()%></td>
            <td><%=question.getOptionC()%></td>
            <td><%=question.getOptionD()%></td>
            <td><%=question.getAnswer()%></td>
            <td><a href="/myWeb/question/delete?questionId=<%=question.getQuestionId()%>">删除试题</a></td>
            <td><a href="/myWeb/question/findById?questionId=<%=question.getQuestionId()%>">更新试题</a></td>
        </tr>
        <%
            }
        %>
    </table>

</body>
</html>
