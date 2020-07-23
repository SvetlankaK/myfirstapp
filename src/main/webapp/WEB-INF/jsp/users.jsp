<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users view</title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/users.css"%>
    </style>
</head>
<body>
<div class="container">
    <header class="header">
        <h1 class="logo">Useless application</h1>
        <ul class="menu-main">
            <li><a href="<c:url value = "${contextPath}/welcome.jhtml"/>" class="current">Main page</a></li>
            <li><a href="<c:url value = "${contextPath}/users.jhtml"/>">Users</a></li>
        </ul>
        <a href="<c:url value = "${contextPath}/logout.jhtml"/>" class="logoutLink">Logout</a>
    </header>
    <div class="content-body">
        <main class="content">
            <div class="responsive-table">
                <table>
                    <tr class="tr-header">
                        <th>Name, surname</th>
                        <th>Role</th>
                        <th>Email</th>
                        <th>Salary, $</th>
                        <th>Date of birth</th>
                        <th>Edit user</th>
                        <th>
                            <a href="<c:url value = "${contextPath}/registration.jhtml"/>">+</a>
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
                            <a href="<c:url value = "${contextPath}/editUser.jhtml?action=edit;user=${user.userLogin}"/>">&#9998;</a>
                        </td>
                        <td>
                            <a href="<c:url value = "${contextPath}/delete.jhtml?user=${user.userLogin}"/>">&#x2716;</a>
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
        <div class="footerInfo">made by Sveta Kvetko, 2020</div>
    </footer>
</div>
</body>
</html>
