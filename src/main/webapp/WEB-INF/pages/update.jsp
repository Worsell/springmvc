<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/admin/update">
    <input type="text" name="email" placeholder="email" value=${email}>
    <input type="text" name="firstName" placeholder="input firstname" value=${firstName}>
    <input type="text" name="lastName" placeholder="input lastname" value=${lastName}>
    <select multiple = "multiple" name="nroles">
        <c:forEach var = "role" items="${roles}">
            <c:if test="${urole.contains(role)}">
                <option selected value="${role.authority}">${role.authority}</option>
            </c:if>
            <c:if test="${!urole.contains(role)}">
                <option value="${role.authority}">${role.authority}</option>
            </c:if>
        </c:forEach>
    </select>
    <input type="password" name="password" placeholder="password" value=${password}>
    <input type="text" name="id" placeholder="id" value="${id}">
    <button>ok</button>
</form>
</body>
</html>
