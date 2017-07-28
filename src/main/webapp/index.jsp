<%--
  Created by IntelliJ IDEA.
  User: wicker
  Date: 2017/7/27
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>用户管理</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
  </head>
  <body>
  <form action="/jdbcTemplate/userManage/addRow.do" method="get">
    <table>
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="userName" id="userName"/></td>
      </tr>
      <tr>
        <td>年龄：</td>
        <td><input type="text" name="userAge" id="userAge"/></td>
      </tr>
    </table>
    <div id="tipMsg"></div>
    <%--<input type="submit" value="提交"/>--%>
    <input type="button" onclick="addRow()" value="提交"/>
  </form>
  </body>

<script  type="application/javascript">
  function addRow() {
      $.ajax({
          type : "GET",  //提交方式
          url : "${pageContext.request.contextPath}/userManage/addRow.do",//路径
          data : {
              "userName" : $("#userName").val(),
              "userAge" : Number($("#userAge").val())
          },//数据，这里使用的是Json格式进行传输
          success : function(result) {//返回数据根据结果进行相应的处理
              if ( result.success ) {
                  $("#tipMsg").text("成功");
              } else {
                  $("#tipMsg").text("失败");
              }
          }
      });
  }
</script>
</html>
