<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/admin/update">
    <input type="text" name="email" placeholder="email" value=${param.get("email")}>
    <input type="text" name="firstName" placeholder="input firstname" value=${param.get("firstName")}>
    <input type="text" name="lastName" placeholder="input lastname" value=${param.get("lastName")}>
    <select name="role">
        <c:if test = "${param.get(\"role\").equals(\"admin\")}">
            <option selected="selected" value="admin">admin</option>
            <option value="user">user</option>
        </c:if>
        <c:if test = "${param.get(\"role\").equals(\"user\")}">
            <option value="admin">admin</option>
            <option selected="selected" value="user">user</option>
        </c:if>
    </select>
    <input type="password" name="password" placeholder="password" value=${param.get("password")}>
    <input type="hidden" name="id" placeholder="password" value=${param.get("id")}>
    <button>ok</button>
</form>
</body>
</html>
