<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User edit</title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/editUser.css"%>
    </style>
</head>
<body>
<h1>User full information</h1>

<form method="POST" action="${pageContext.request.contextPath}/editUser.jhtml">
    <div class="col-2">
        <label>
            Name
            <input type="text" placeholder="type user name" id="name" name="name" tabindex="1"
                   value="<c:out value="${requestScope.user.name}" />">
        </label>
    </div>
    <div class="col-2">
        <label>
            Surname
            <input type="text" placeholder="type user surname" id="surname" name="surname" tabindex="2"
                   value="<c:out value="${requestScope.user.surname}" />">
        </label>
    </div>

    <div class="col-3">
        <label>
            Salary
            <input type="text" placeholder="type user salary" id="salary" name="salary" tabindex="3"
                   value="<c:out value="${requestScope.user.salary}" />">
        </label>
    </div>
    <div class="col-3">
        <label>
            Email
            <input type="text" placeholder="type user e-mail address" id="email" name="email" tabindex="4"
                   value="<c:out value="${requestScope.user.email}" />">
        </label>
    </div>
    <div class="col-3">
        <label>
            Login
            <input type="text" placeholder="type user login" id="login" name="userLogin" tabindex="5"
                   value="<c:out value="${requestScope.user.userLogin}" />">
        </label>
    </div>

    <div class="col-4">
        <label>
            Password
            <input type="text" placeholder="type user password" id="password" name="password" tabindex="6"
                   value="<c:out value="${requestScope.user.password}" />">
        </label>
    </div>
    <div class="col-4">
        <label>
            Date of birth
            <input type="text" placeholder="06.04.2000" pattern="[0-9]{2}\.0-9]{2}\.[0-9]{4}" id="birth" name="birth"
                   tabindex="7" value="<c:out value="${requestScope.user.dateOfBirth}" />">
        </label>
    </div>
    <div class="col-4">
        <label>
            Access role
            <select tabindex="5" name="access">
                <option>USER</option>
                <option selected>ADMIN</option>
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
        <button class="submitbtn">Save information</button>
    </div>

</form>
</body>
</html>

