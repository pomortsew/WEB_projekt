<%--
  Created by IntelliJ IDEA.
  User: devel
  Date: 08.09.17
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mypageJsp</title>
</head>

<body>

<h2 color="red">${error_login_verification}</h2>
<form action="mySimple" method="post">
<form action="anwers.jsp" method="post">
    Login:    <input type="text" name="loginMypage"/></br>
    Password: <input type="text" name="passwordMypage"/></br>
    <input type="submit" name="Enter" value="Login"/></br>

<form action="registration.jsp" method="post">

    <input type="submit" name="Registre" value="Registration"/></br>
</form>
</form>
</form>

</body>
</html>
