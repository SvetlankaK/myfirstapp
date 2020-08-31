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
    <form:form action="editPassword" method="post" modelAttribute="user">
        <label><spring:message code="editPasswordPage.current"/></label>
        <input type="password" class="form-control" placeholder="Current Password">
        <label><spring:message code="editPasswordPage.newFirstField"/></label>

        <input type="password" class="form-control" placeholder="New Password">

        <label><spring:message code="editPasswordPage.newSecondField"/></label>

        <form:input path="password" class="form-control" autocomplete="off"/>

        <%--        <input type="password" value="faisal.khan@123" class="form-control" placeholder="Confirm Password">--%>
        <input type="submit" value="Send" class="submit"/>
    </form:form>

</div>
</body>
</html>





