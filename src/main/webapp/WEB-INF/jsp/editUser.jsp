<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>User edit</title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/editUser.css"%>
    </style>
</head>
<body>
<h1>User full information</h1>
<form:form action="editUser" method="post" modelAttribute="userView">
    <div class="col-2">
        <label>
            Name
            <form:input path="name" tabindex="1" placeholder="type user name"/> <span></span>
            <!-- todo value должно быть внутри input, но с ебаными формами это не работает
            пока почему-то-->
            value="<c:out value="${user.name}"/>">
        </label>
    </div>
    <div class="col-2">
        <label>
            Surname
            <form:input path="surname" placeholder="type user surname" tabindex="2"/>
            value="<c:out value="${user.surname}"/>">
        </label>
    </div>

    <div class="col-3">
        <label>
            Salary
            <form:input path="salary" placeholder="type user salary" tabindex="3"/>
            value="<c:out value="${user.salary}"/>">
        </label>
    </div>
    <div class="col-3">
        <label>
            Email
            <form:input path="email" placeholder="type user e-mail address" tabindex="4"/>
            value="<c:out value="${user.email}"/>">
        </label>
    </div>
    <div class="col-3">
        <label>
            Login
            <form:input path="userLogin" placeholder="type user login" tabindex="5"/>
            value="<c:out value="${user.userLogin}"/>">
        </label>
    </div>

    <div class="col-4">
        <label>
            Password
            <form:input path="password" placeholder="type user password" tabindex="6"/>
            value="<c:out value="${user.password}"/>">
        </label>
    </div>
    <div class="col-4">
        <label>
            Date of birth
            <form:input path="dateOfBirth" placeholder="06.04.2000" pattern="[0-9]{2}\.0-9]{2}\.[0-9]{4}" tabindex="7"/>
            value="<c:out value="${user.dateOfBirth}"/>">
        </label>
    </div>
    <div class="col-4">
        <label>
            Access role
            <select tabindex="5" name="access" multiple size="${roles.size()}">
                <!-- todo примерный вариант работы form:select, но пока не ебу как сделать-->
                    <%--    <form:select path="role">--%>
                    <%--        <form:option value="-" label="--Please Select--"/>--%>
                    <%--        <form:options items="${role.roleName}"/>--%>
                    <%--    </form:select>--%>
                <c:forEach items="${roles}" var="role">

                    <option value=${role.id}>${role.roleName}</option>
                </c:forEach>
            </select>
        </label>

    </div>
    <div class="col-5">
        <label>
            Note:
            <p class="notification"> Check info once again before saving</p>
        </label>


    </div>

    <div class="col-submit">
        <form:button class="submitbtn">Save information</form:button>
    </div>

</form:form>
</body>
</html>

