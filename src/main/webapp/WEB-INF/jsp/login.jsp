<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form action="login" method="post" modelAttribute="userView">
        <div class="input">
            <div class="blockinput">
                <form:input path="userLogin" required placeholder="Login" autocomplete="off"/>
            </div>
            <div class="blockinput">
                <form:password path="password" required
                               placeholder="Password"/>
            </div>
        </div>
        <form:button type="submit">Login</form:button>
        <p class="regCase">
            Don’t have an account?</p>
        <p class="errorMessage"><c:out value="${errorMessage}"/></p>
        <a href="<c:url value="/registration"/>" class="regLink">
            Sign up now
        </a>
    </form:form>
</div>
</body>
</html>
