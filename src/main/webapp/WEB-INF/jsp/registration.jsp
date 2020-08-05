<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <style type="text/css">
        <%@include file="/WEB-INF/css/registration.css"%>

    </style>
    <title>Registration page</title>
</head>
<body>
<h1 class="logo">Here you can register a new account </h1>
<form:form action="registration" method="post" modelAttribute="user">
    <div>
        <!--TODO error message-->
        <!--TODO здесь reqired якобы не рабоатет. но я не удаляю, чтоб помнить, что ра
        бочую его версию замутить таки важно-->
        <p>
            <form:label path="name">Name</form:label>
            <form:input path="name" required placeholder="Dasha"/> <span></span>
        </p>

        <p>
            <form:label path="surname">Surname</form:label>
            <form:input path="surname" required placeholder="Pavlova"/><span></span>
        </p>


        <p>
            <form:label path="email">E-mail</form:label>
            <form:input path="email" requiredplaceholder="perlovla14@gmail.com"/><span></span>
        </p>


        <p>
            <form:label path="dateOfBirth">Date of birth</form:label>
            <form:input path="dateOfBirth" placeholder="06.04.2000" required
                        pattern="[0-9]{2}\.0-9]{2}\.[0-9]{4}"/><br/><span></span>
        </p>

        <fieldset>
            <legend>
                Login data:
            </legend>
            <p>
                <form:label path="userLogin">Login</form:label>
                <form:input path="userLogin" required placeholder="Glasha1O"/><span></span>
            </p>
            <p>
                <form:label path="password">Password</form:label>
                <form:password path="password" required
                               placeholder="Try to be original!"/>
                <span></span>
            </p>
            <form:hidden path="salary" value="100"/>
        </fieldset>
    </div>
    <form:button type="submit" class="registerbtn">Register </form:button>
    <a href="<c:url value = "${contextPath}/login.jhtml"/>" class="button">Back to login page</a>
</form:form>
</body>
</html>