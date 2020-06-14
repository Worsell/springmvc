<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-16">
    <title>admin console</title>
</head>
<body>
<form action="/admin/create" method="POST">
    <button>add user/admin</button>
    <input type="text" name="email" placeholder="email">
    <input name="firstName" type="text" placeholder="firstname">
    <input name="lastName" type="text" placeholder="lastname">
    <input name="password" type="password" placeholder="password">
    <p> <input name="admin" type="checkbox" placeholder="isAdmin"> isAdmin </p>
    <p> <input name="user" type="checkbox" placeholder="isUser"> isUser </p>
</form>
<table border="1">
    <caption>list users in database</caption>
    <tr>
        <th> id</th>
        <th> FirstName</th>
        <th> LastName</th>
        <th> HashPassword</th>
        <th> Role</th>
        <th> Update</th>
        <th> Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <th>${user.id}</th>
            <th>${user.firstName}</th>
            <th>${user.lastName}</th>
            <th>${user.password}</th>
            <th>${user.authorities}</th>
            <th>
                <form action="/admin/update" method="GET">
                    <button name="ok">press</button>
                    <input type="hidden" name="email" placeholder="email">
                    <input name="id" type="hidden" placeholder="firstname" value=${user.id}>
                    <input name="firstName" type="hidden" placeholder="firstname" value=${user.firstName}>
                    <input name="lastName" type="hidden" placeholder="firstname" value=${user.lastName}>
                    <input name="password" type="hidden" placeholder="firstname" value=${user.password}>
                    <input name="role" type="hidden" placeholder="firstname" value=${user.authorities}>
                </form>
            </th>
            <th>
                <form action="/admin/delete" method="POST">
                    <button name="ok">press</button>
                    <input name="id" type="hidden" placeholder="firstname" value=${user.id}>
                </form>
            </th>
        </tr>
    </c:forEach>
</table>
</body>
</html>