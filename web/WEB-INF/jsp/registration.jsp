<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration page</title>
</head>
<body>
<h1>Here you can register a new account </h1>
<form method="POST" action="${pageContext.request.contextPath}/registration.jhtml">
    Enter your name:
    <input type="text" name="userName"
           value="<c:out value="${param.userName}"/>"/>
    <br/><br/>
    Enter your password:
    <input type="password" name="password"
           value="<c:out value="${param.password}"/>"/>
    <br/>
    <font color="red">
        <c:out value="${errorMessage}"> </c:out>
    </font>
    <br/>
    <button type="submit" name="button">Register</button>
    <br> <br>
  <a href="<c:url value = "${contextPath}/login.jhtml"/>">Back to authorization page</a>
</form>
</body>
</html>
