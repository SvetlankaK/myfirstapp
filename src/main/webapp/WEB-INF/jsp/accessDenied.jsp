<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="accessDeniedPage.title"/></title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/accessDenied.css"%>
    </style>
</head>
<body>
<div class="error-wall load-error">
    <div class="error-container">
        <h1>oh no...</h1>
        <h3>you do not have permission to view this page</h3>
        <h4>Error 403</h4>
        <div class="button-bar">
            <a class="button" href="<c:url value="/welcome"/>">Go to welcome page</a>
        </div>
    </div>
</div>
</body>
</html>