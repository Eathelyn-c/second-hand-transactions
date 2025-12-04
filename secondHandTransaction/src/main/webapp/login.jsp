<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eathelyn
  Date: 2025/12/3
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - 用户登录</title>
</head>
<body>
<h1>用户登录</h1>
<form action="login" method="post">
    <label>用户名：</label><input type="text" name="username" required><br>
    <label>密码：</label><input type="password" name="password" required><br>
    <input type="submit" value="登录">
</form>
<a href="register.jsp">没有账号？去注册</a><br>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
</body>
</html>