<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/07/23
  Time: 下午 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
欢迎您登陆，${user.username}

<shiro:hasPermission name="/insert">
    <a href="${pageContext.request.contextPath}/insert">新增</a>
</shiro:hasPermission>

<shiro:hasPermission name="/update">
    <a href="${pageContext.request.contextPath}/update">修改</a>
</shiro:hasPermission>

<shiro:hasPermission name="/delete">
    <a href="${pageContext.request.contextPath}/delete">删除</a>
</shiro:hasPermission>

<shiro:hasPermission name="/select">
    <a href="${pageContext.request.contextPath}/select">查询</a>
</shiro:hasPermission>

<a href="${pageContext.request.contextPath}/logout">登出</a>
</body>
</html>
