<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddUserJSP</title>
</head>
<body>
<form action="/addUser" method="POST">
    <ul>
        <li>
            <label>Username: <input type="text" name="name"></label>
        </li>
        <li>
            <label>Password: <input type="text" name="password"></label>
        </li>
        <li>
            <label>Role: <input type="text" name="role"></label>
        </li>
        <li><input type="submit" align="center" value="Submit"/></li>
    </ul>

</form>
</body>
</html>