<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome page</title>
</head>
<h1>Hello, <c:out value="${user}"> </c:out></h1>

</br>
</br><p><font color="green"><c:out value="${PasswordChange}"> </c:out></font></p>
Now your password is <strong><c:out value="${password}"> </c:out></strong>, you can change it, if you
<a href="<c:url value = "${contextPath}/loginedit.jhtml"/>">click here</a>
</br>
</br>
<a href="<c:url value = "${contextPath}/logout.jhtml"/>">Click here to logout</a>
</body>
</html>
