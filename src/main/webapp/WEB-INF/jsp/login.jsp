<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization page</title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/login.css"%>
    </style>
</head>
<body>
<div class="login">
    <h1>Please, login </h1>
    <form method="POST" action="${pageContext.request.contextPath}/login">
        <div class="input">
            <div class="blockinput">
                <input type="text" name="userLogin" placeholder="Login" autocomplete="off" required>
            </div>
            <div class="blockinput">
                <input type="password" name="password" placeholder="Password" required>
            </div>
        </div>
        <button type="submit" name="button">Login</button>
        <p class="regCase">
            Don’t have an account?</p>
        <p class="errorMessage"><c:out value="${requestScope.errorMessage}"/></p>
        <a href="<c:url value="${contextPath}/registration"/>" class="regLink">
            Sign up now
        </a>
    </form>
</div>
</body>
</html>