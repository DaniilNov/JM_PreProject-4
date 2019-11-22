<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<div align="center">
    <form action="/editUser" method="POST">
        <input type="hidden" name="id" value="${user.id}">
        <ul>
            <li>
                <label>Username: <input type="text" name="name" value="${user.name}"></label>
            </li>
            <li>
                <label>Password: <input type="text" name="password" value="${user.password}"></label>
            </li>
            <li>
                <label>Role: <input type="text" name="role" value="${user.role}"></label>
            </li>

            <li><input type="submit" align="center" value="Submit"/></li>
        </ul>

    </form>
</div>

</body>
</html>
