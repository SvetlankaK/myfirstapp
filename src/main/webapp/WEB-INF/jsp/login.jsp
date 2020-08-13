<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="loginPage.title"/></title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/login.css"%>
    </style>
</head>
<body>
<div class="login">
    <h1><spring:message code="loginPage.formStarter"/></h1>
    <div class="dropdown">
        <div class="dropbtn">en</div>
        <div class="dropdown-content">
            <a href="?lang=en"><spring:message code="app.lang.en"/></a>
            <a href="?lang=ru"><spring:message code="app.lang.ru"/></a>
        </div>
    </div>
    <form:form action="login" method="post" modelAttribute="user">
        <div class="input">
            <div class="blockinput">
                <spring:message code="loginPage.login" var="placeholderLogin"/>
                <form:input path="userLogin" required="true" placeholder='${placeholderLogin}' autocomplete="off"/>
            </div>
            <div class="blockinput">
                <spring:message code="loginPage.password" var="placeholderPassword"/>
                <form:password path="password" required="true" placeholder='${placeholderPassword}'/>
            </div>
        </div>
        <form:button type="submit"><spring:message code="loginPage.button"/></form:button>
        <p class="regCase"><spring:message code="loginPage.accountQuestion"/>
        </p>
        <p class="errorMessage"><c:out value="${errorMessage}"/></p>
        <a href="<c:url value="/registration"/>" class="regLink">
            <spring:message code="loginPage.signUp"/>
        </a>
    </form:form>
</div>
</body>
</html>
