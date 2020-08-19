<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="editUserPage.title"/></title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/editUser.css"%>
    </style>
</head>
<body>
<h1><spring:message code="editUserPage.formName"/></h1>
<form:form action="editUser" method="post" modelAttribute="user">
    <div class="col-2">
        <label>
            <spring:message code="editUserPage.name"/>
            <form:input path="name" tabindex="1" placeholder="type user name"/>
            <form:errors path="name" class="error"/><span></span>
        </label>
    </div>
    <div class="col-2">
        <label>
            <spring:message code="editUserPage.surname"/>
            <form:input path="surname" placeholder="type user surname" tabindex="2"/>
            <form:errors path="surname" class="error"/>
        </label>
    </div>
    <div class="col-3">
        <label>
            <spring:message code="editUserPage.salary"/>

            <form:input path="salary" placeholder="type user salary" tabindex="3"/>
            <form:errors path="salary" class="error"/>
        </label>
    </div>
    <div class="col-3">
        <label>
            <spring:message code="editUserPage.email"/>
            <form:input path="email" placeholder="type user e-mail address" tabindex="4"/>
            <form:errors path="email" class="error"/>
        </label>
    </div>
    <div class="col-3">
        <label>
            <spring:message code="editUserPage.login"/>
            <form:input path="userLogin" placeholder="type user login" tabindex="5"/>
            <form:errors path="userLogin" class="error"/>
        </label>
    </div>
    <div class="col-4">
        <label>
            <spring:message code="editUserPage.password"/>
            <form:input path="password" placeholder="type user password" tabindex="6"/>
            <form:errors path="password" class="error"/>
        </label>
    </div>
    <div class="col-4">
        <label>
            <spring:message code="editUserPage.birthday"/>
            <form:input path="dateOfBirth" placeholder="06.04.2000" pattern="[0-9]{2}\.0-9]{2}\.[0-9]{4}" tabindex="7"/>
            <form:errors path="dateOfBirth" class="error"/>
        </label>
    </div>
    <div class="col-4">
        <label>
            <spring:message code="editUserPage.access"/>

            <form:select multiple="true" path="role">
                <c:forEach items="${allRoles}" var="role">
                    <c:set var="isSelected" value="false"/>

                    <c:forEach items="#{user.role}" var="roleU">
                        <c:if test="${roleU.getId()==role.id}">
                            <c:set var="isSelected" value="true"/>
                        </c:if>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${isSelected}">
                            <form:option value="${role.id}" selected="selected">${role.roleName}</form:option>
                        </c:when>
                        <c:otherwise>
                            <form:option value="${role.id}">${role.roleName}</form:option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </form:select>

        </label>
    </div>
    <div class="col-5">
        <label>
            <spring:message code="editUserPage.noteLabel"/>
            <p class="notification"><spring:message code="editUserPage.noteText"/></p>
        </label>
    </div>
    <div class="col-submit">
        <form:button class="submitbtn"><spring:message code="editUserPage.button"/></form:button>
    </div>
</form:form>
</body>
</html>