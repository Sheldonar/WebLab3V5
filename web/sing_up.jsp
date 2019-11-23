<%--
  Created by IntelliJ IDEA.
  User: fedorg
  Date: 22/11/2019
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sing Up</title>
</head>
<style>
    body{
        background: dimgray;
    }
    H1 {
        font-size: 120%;
        font-family: Verdana, Helvetica, sans-serif;
        color: white;
    }
</style>
<body>
<h1>Create login and password (sign up)</h1>
<br>
<br>

<h1>message is ${message}</h1>

<br>
<br>
<form method="POST">
    login : <input type="text" name="login" />
    <br>
    <br>
    password : <input type="password" name="password" />
    <br>
    <br>
    <input type="submit" name="submit" value="Submit"/>
</form>
</body>
</html>
