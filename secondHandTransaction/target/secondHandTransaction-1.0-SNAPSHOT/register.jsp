<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eathelyn
  Date: 2025/12/3
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP - 用户注册</title>
</head>
<body>
<h1>用户注册</h1>
<form action="register" method="post">
    <label>用户名：</label><input type="text" name="username" required><br>
    <label>密码：</label><input type="password" name="password" required><br>
    <label>确认密码：</label><input type="password" name="confirmPassword" required><br>
    <input type="submit" value="注册">
</form>
<a href="login.jsp">已有账号？去登录</a><br>
<c:if test="${error != null }">
    <p style="color:red;">${error}</p>
</c:if>
</body>
</html>
