<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div align="center">
    <form action="/login" method="POST">

            <div class="field">
                <label for="uName">Username:</label>
                <input type="text" id="uName" name="name" />
            </div>
            <div class="field">
                <label for="uPass">Password:</label>
                <input type="text" id="uPass" name="password" />
            </div>
            <div class="submit">
                <input type="submit" align="center" value="name"/>
            </div>


        </form>
    </div>

</div>
</body>
</html>