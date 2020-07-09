<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <style type="text/css">
        <%@include file="/WEB-INF/css/registration.css"%>

    </style>
    <title>Registration page</title>
</head>
<body>
<h1 class="logo">Here you can register a new account </h1>
<form method="POST" action="${pageContext.request.contextPath}/registration.jhtml">
    <div>

        <p>
            <label for="name">Name</label>
            <input type="text" name="name" id="name" required placeholder="Dasha"><span></span>
        </p>

        <p>
            <label for="surname">Surname</label>
            <input type="text" name="surname" id="surname" required placeholder="Pavlova"><span></span>
        </p>


        <p>
            <label for="Email">Email</label>
            <input type="Email" name="email" id="Email" required placeholder="perlovla14@gmail.com"><span></span>
        </p>


        <p>
            <label for="birth">Date of birth</label>
            <input type="text" placeholder="06.04.2000" pattern="[0-9]{2}\.0-9]{2}\.[0-9]{4}" name="birth" id="birth"
                   required><span></span>
        </p>

        <fieldset>
            <legend>
                Login data:
            </legend>
            <p>
                <label for="login">Login</label>
                <input type="text" name="userLogin" id="login" required placeholder="Glasha1O"><span></span>
            </p>
            <p>
                <label for="Password">Password</label>
                <input type="password" name="password" id="Password" required
                       placeholder="Try to be original!"><span></span>
            </p>
            <input name="salary" type="hidden" value="100"/>
        </fieldset>
    </div>
    <button type="submit" class="registerbtn">Register</button>
    <a href="<c:url value = "${contextPath}/login.jhtml"/>" class="button">Back to login page</a>
</form>
</body>
</html>