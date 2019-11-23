<%--
  Created by IntelliJ IDEA.
  User: fedorg
  Date: 22/11/2019
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello there</title>
</head>
<style>
    body{
        background: dimgray;
    }
    H1 {
        font-size: 140%;
        font-family: Verdana, Helvetica, sans-serif;
        color: white;
    }
</style>
<body>
<h1>Do you have account?</h1>
<br>

<h1>message is ${message}</h1>

<br>
<form method="post">
    <input type="text" name="choice" />
    <input type="submit" name="submit" value="Submit"/>
</form>
</body>
</html>
