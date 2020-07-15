<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Profile edit</title>
</head>
<body>
<h1>Profile password change</h1>

<form method="POST" action="${pageContext.request.contextPath}/loginedit.jhtml">
    Enter your new password:

    <input type="password" name="newPassword" required
           value="<c:out value="${param.newPassword}"/>"/>

    <br/><br/>

    <button type="submit" name="button">Change password</button>
</form>

</body>
</html>
