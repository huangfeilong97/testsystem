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
    <script type="text/javascript">

        function init(){
            var jsonArray = ${requestScope.questionList}; //得到一个json数组【dept1,dpet2,...】
            var s=document.getElementsByTagName("div")
            s.innerHTML=jsonArray[0];

            for(var i=0;i<jsonArray.length;i++){
                var jsonObj = jsonArray[i]  //{deptNo,dname,loc}
                var trDom = document.createElement("tr"); //<tr></tr>
                var tdDom_1 = document.createElement("td");
                var tdDom_2 = document.createElement("td");
                var tdDom_3 = document.createElement("td");
                tdDom_1.innerHTML = jsonObj.title; //<td>10</td>
                tdDom_2.innerHTML = jsonObj.answer;  //<td>Accounting</td>
                tdDom_3.innerHTML = jsonObj.optionA;    //<td>New York</td>
                trDom.appendChild(tdDom_1)
                trDom.appendChild(tdDom_2)
                trDom.appendChild(tdDom_3)
                document.getElementById("one").appendChild(trDom);
            }
        }
    </script>

</head>
<body>


${requestScope.questionList}
<div></div>

<table border="2" align="center" id="one">
    <tr>
        <td>部门编号</td>
        <td>部门名称</td>
        <td>部门位置</td>
    </tr>



</table>



</body>
</html>
