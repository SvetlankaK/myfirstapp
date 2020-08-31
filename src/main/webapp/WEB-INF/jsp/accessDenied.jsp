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
        <h1><spring:message code="deniedPage.h1"/></h1>
        <h3><spring:message code="deniedPage.errorDefinition"/></h3>
        <h4><spring:message code="deniedPage.errorNumber"/></h4>
        <div class="button-bar">
            <a class="button" href="<c:url value="/welcome"/>"><spring:message code="deniedPage.button"/></a>
        </div>
    </div>
</div>
</body>
</html>