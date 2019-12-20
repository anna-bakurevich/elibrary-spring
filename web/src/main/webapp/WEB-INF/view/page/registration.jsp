<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>

<div class="form">

    <h1><spring:message code="registration.heading"/></h1><br>
    <form method="post" action="${pageContext.request.contextPath}/registration">

        <input type="text" required placeholder=
        <spring:message code="name"/> name="firstName"><br>
        <input type="text" required placeholder=
        <spring:message code="surname"/> name="lastName"><br><br>
        <input type="text" required placeholder=
        <spring:message code="phone"/> name="phone"><br><br>
        <input type="text" required placeholder=
        <spring:message code="login"/> name="login"><br><br>
        <input type="text" required placeholder=
        <spring:message code="password"/> name="password"><br><br>
        <input class="button" type="submit" value=<spring:message code="signup"/>>

    </form>
    <c:if test="${error}">
        <p style="color: red"><spring:message code="error.registration"/></p>
    </c:if>
</div>
</body>
</html>
