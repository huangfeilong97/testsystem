<%@ page import="java.util.List" %>
<%@ page import="com.hfl.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: 黄飞龙
  Date: 2021/2/26
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<center>

    <form action="/myWeb/exam" method="get">

        <table border="2">
            <%
                List<Question> questionList=(List) session.getAttribute("questionList");
                for (Question question :questionList) {
            %>
            <tr>
                <td>题目:</td>
                <td><input type="text" name="title" value="<%=question.getTitle()%>"></td>
            </tr>
            <tr>
                <td>A:</td>
                <td><input type="text" name="optionA" value="<%=question.getOptionA()%>"></td>
            </tr>
            <tr>
                <td>B:</td>
                <td><input type="text" name="optionB" value="<%=question.getOptionB()%>"></td>
            </tr>
            <tr>
                <td>C:</td>
                <td><input type="text" name="optionC" value="<%=question.getOptionC()%>"></td>
            </tr>
            <tr>
                <td>D:</td>
                <td><input type="text" name="optionD" value="<%=question.getOptionD()%>"></td>
            </tr>
            <tr>
                <td>答案:</td>
                <td>
                    <input type="radio" name="answer_<%=question.getQuestionId()%>" value="A">A
                    <input type="radio" name="answer_<%=question.getQuestionId()%>" value="B">B
                    <input type="radio" name="answer_<%=question.getQuestionId()%>" value="C">C
                    <input type="radio" name="answer_<%=question.getQuestionId()%>" value="D">D
                </td>
            </tr>
            <tr></tr>

            <%
                }
            %>
            <tr>
                <td><input type="submit" value="提交试卷"/></td>
                <td><input type="reset" value="重做"></td>
            </tr>
        </table>
    </form>

</center>

</body>
</html>
