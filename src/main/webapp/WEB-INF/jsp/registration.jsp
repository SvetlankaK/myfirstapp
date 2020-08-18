<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <style type="text/css">
        <%@include file="/WEB-INF/css/registration.css"%>
    </style>
    <title><spring:message code="registrationPage.title"/></title>
</head>
<body>
<h1 class="logo"><spring:message code="registrationPage.formName"/></h1>
<form:form action="registration" method="post" modelAttribute="user">
    <div>
        <p>
            <form:label path="name"><spring:message code="registrationPage.name"/></form:label>
            <form:input path="name" placeholder="Dasha"/>
            <form:errors path="name"/><span></span>

        </p>
        <p>
            <form:label path="surname"><spring:message code="registrationPage.surname"/></form:label>
            <form:input path="surname" required="true" placeholder="Pavlova"/>
            <form:errors path="surname"/><span></span>

        </p>
        <p>
            <form:label path="email"><spring:message code="registrationPage.email"/></form:label>
            <form:input path="email" required="true" placeholder="perlovla14@gmail.com"/>
            <form:errors path="email"/><span></span>

        </p>
        <p>
            <form:label path="dateOfBirth"><spring:message code="registrationPage.birthday"/></form:label>
            <form:input path="dateOfBirth" placeholder="06.04.2000" pattern="[0-9]{2}\.0-9]{2}\.[0-9]{4}"
                        required="true"/>
                         <form:errors path="dateOfBirth" />
            <br/><span></span>
        </p>
        <fieldset>
            <legend>
                <spring:message code="registrationPage.legendName"/>
            </legend>
            <p>
                <form:label path="userLogin"><spring:message code="registrationPage.login"/></form:label>
                <form:input path="userLogin" required="true" placeholder="Glasha1O"/>
                <form:errors path="userLogin"/><span></span>
            </p>
            <span class="errorMessage"><c:out value="${errorMessage}"/></span>
            <p>
                <form:label path="password"><spring:message code="registrationPage.password"/></form:label>
                <form:password path="password" required="true"
                               placeholder="Try to be original!"/>
                <form:errors path="password"/>
                <span></span>
            </p>
            <form:hidden path="salary" value="100"/>
        </fieldset>
    </div>
    <form:button type="submit" class="registerbtn"><spring:message code="registrationPage.button"/></form:button>
    <a href="<c:url value = "${contextPath}/login"/>" class="button"><spring:message
            code="registrationPage.loginRedirect"/></a>
</form:form>
</body>
</html>