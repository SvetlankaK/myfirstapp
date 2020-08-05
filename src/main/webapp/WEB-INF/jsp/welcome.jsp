<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Welcome page</title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/welcome.css"%>
    </style>
</head>

<div class="container">
    <header class="header">
        <h1 class="logo">Useless application</h1>
        <ul class="menu-main">
            <li><a href="<c:url value = "${contextPath}/welcome"/>" class="current">Main page</a></li>
            <c:forEach items="#{requestScope.roles}" var="role">
                <c:if test="${role.roleName=='admin'}">
                    <li><a href="<c:url value = "${contextPath}/users"/>">Users</a></li>
                </c:if>
            </c:forEach>
        </ul>
        <a href="<c:url value = "${contextPath}/logout.jhtml"/>" class="logoutLink">Logout</a>
    </header>
    <div class="content-body">
        <main class="content">
            <h2 class="greeting">Hello, <c:out value="${userLogin}"> </c:out></h2>
        </main>
        <nav class="sidenav"></nav>
        <aside class="ads"></aside>
    </div>
    <footer class="footer">
        <div class="footerInfo">made by Sveta Kvetko, 2020</div>
    </footer>
</div>
</body>
</html>