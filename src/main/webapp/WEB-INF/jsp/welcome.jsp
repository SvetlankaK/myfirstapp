<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title><spring:message code="welcomePage.title"/></title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/welcome.css"%>
    </style>
</head>
<body>
<div class="container">
    <header class="header">
        <h1 class="logo"><spring:message code="page.logo"/></h1>
        <ul class="menu-main">
            <li><a href="<c:url value = "${contextPath}/welcome"/>" class="current"><spring:message
                    code="mainPageLink"/></a></li>
            <c:forEach items="#{roles}" var="role">
                <c:if test="${role.roleName=='admin'}">
                    <li><a href="<c:url value = "${contextPath}/users"/>"><spring:message code="usersLink"/></a></li>
                </c:if>
            </c:forEach>
        </ul>
        <a href="<c:url value = "${contextPath}/logout"/>" class="logoutLink"><spring:message code="logoutLink"/></a>
    </header>
    <div class="content-body">
        <main class="content">
            <h2 class="greeting"><spring:message code="welcomePage.hello"/> <c:out
                    value="${userLogin}"> </c:out></h2>
        </main>
        <nav class="sidenav"></nav>
        <aside class="ads"></aside>
    </div>
    <footer class="footer">
        <div class="footerInfo"><spring:message code="page.footerInfo"/></div>
    </footer>
</div>
</body>
</html>