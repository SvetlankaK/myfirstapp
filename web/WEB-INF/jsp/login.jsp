<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Authorization page</title>
</head>
<body>
<h1>Please, login to continue </h1>
<h3>In case, if you are here for the first time, you can register <a
        href="<c:url value = "${contextPath}/registration.jhtml"/>">here</a>
</h3>
<form method="POST" action="${pageContext.request.contextPath}/login.jhtml">
    Name:
    <input type="text" name="userName"
           value="<c:out value="${param.userName}"/>"/>
    <br/>
    <br/>
    Password:
    <input type="password" name="password"
           value="<c:out value="${param.password}"/>"/>
    <br/>
    <font color="red">
        <c:out value="${errorMessage}"> </c:out>
    </font>
    <br/>
    <button type="submit" name="button">Login</button>
</form>
</body>
</html>