<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/07/23
  Time: 下午 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login">

    账户：<input type="text" name="user"><br>
    密码：<input type="password" name="pwd"><br>
    <input type="submit" value="登陆">

</form>
</body>
</html>
