<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="usersPage.title"/></title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/users.css"%>
    </style>
</head>
<body>
<div class="container">
    <header class="header">
        <!--todo не работает лого тут и на welocome-->
        <h1 class="logo"><spring:message code="page.logo"/></h1>
        <ul class="menu-main">
            <li><a href="<c:url value = "${contextPath}/welcome"/>" class="current"><spring:message
                    code="mainPageLink"/></a></li>
            <li><a href="<c:url value = "${contextPath}/users"/>"><spring:message code="usersLink"/></a></li>
        </ul>
        <a href="<c:url value = "${contextPath}/logout"/>" class="logoutLink"><spring:message
                code="logoutLink"/></a>
    </header>
    <div class="content-body">
        <main class="content">
            <div class="responsive-table">
                <table>
                    <tr class="tr-header">
                        <th><spring:message code="usersPage.fullName"/></th>
                        <th><spring:message code="usersPage.role"/></th>
                        <th><spring:message code="usersPage.email"/></th>
                        <th><spring:message code="usersPage.salary"/></th>
                        <th><spring:message code="usersPage.birthday"/></th>
                        <th><spring:message code="usersPage.editUserLink"/></th>
                        <th>
                            <a href="<c:url value = "${contextPath}/registration"/>">+</a>
                        </th>
                    </tr>
                    <c:forEach items="#{requestScope.users}" var="user">
                        <td class="table-text-left">${user.surname} ${user.name}</td>
                        <td><select class="selection-handle">
                            <c:forEach items="#{user.role}" var="role">
                                <option>${role.roleName} </option>
                            </c:forEach>
                            ></select></td>
                        <td>${user.email}</td>
                        <td>${user.salary}</td>
                        <td>${user.dateOfBirth}</td>
                        <td>
                            <a href="<c:url value = "${contextPath}/editUser?user=${user.userLogin}"/>">&#9998;</a>
                        </td>
                        <td>
                            <a href="<c:url value = "${contextPath}/delete?user=${user.userLogin}"/>">&#x2716;</a>
                        </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
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