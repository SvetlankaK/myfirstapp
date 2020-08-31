<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="editPasswordPage.title"/></title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/editPassword.css"%>
    </style>
</head>

<body>
<div class="wrapper">
    <form:form action="editPassword" method="post" modelAttribute="editPasswordDto">
        <label><spring:message code="editPasswordPage.current" var="current"/></label>
        <form:input type="password" path="oldPassword" class="form-control" placeholder='${current}'/>
        <label><spring:message code="editPasswordPage.newFirstField" var="first"/></label>

        <form:input type="password" path="newPassword" class="form-control" placeholder='${first}'/>

        <label><spring:message code="editPasswordPage.newSecondField" var="second"/></label>

        <form:input type="password" path="newPasswordRepeat" class="form-control" placeholder='${second}'
                    autocomplete="off"/>
        <form:hidden path="userId"/>
        <input type="submit" value="Send" class="submit"/>
        <c:if test="${wrongOldPassword==true}"><p>Wrong old password</p></c:if>
        <c:if test="${wrongRepeat==true}"><p>Wrong repeat password</p></c:if>
    </form:form>
    <c:if test="${passwordHasChanged==true}"><p>Password has been changed</p></c:if>

</div>
</body>
</html>





