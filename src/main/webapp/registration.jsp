<%--
  Created by IntelliJ IDEA.
  User: devel
  Date: 10.09.17
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация </title>
</head>
<body>
<h1>Введите данные для регистрации </h1>

<form action="/registration" method="post" >
<h1>${error_registration}</h1>
    <%--<form action="Finish.jsp" method="post">--%>
Введите логин: <input type="text" value="${login}" name="login_registration" >

Введите пароль:<input type="password" value="${password}" name="password_registration" >

Введите имя:<input type="text" name="name_registration" >

Введите фамилию:<input type="text" name="surname_registration" >

<input type="submit" name="Registration" value="Регистрация ">
</form>
</form>
</body>
</html>
