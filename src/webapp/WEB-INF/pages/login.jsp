<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login in application</title>
</head>
<body>
<form action="/login" method="POST">
    <input type="text" name="email" placeholder="email">
    <input type="text" name="firstname" placeholder="firstname">
    <input type="text" name="lastname" placeholder="lastname">
    <input type="password" name="password" placeholder="password">
    <button>press to login</button>
</form>
<form action="/logout" method="GET">
    <button>press to login out</button>
</form>
</body>
</html>
