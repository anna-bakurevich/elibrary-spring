<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Edit</title>
</head>
<body>

<div class="form">

    <h1><spring:message code="edit.heading"/></h1><br>
    <form method="post" action="${pageContext.request.contextPath}/edit">

        <input type="text" required placeholder="Имя" name="firstName" value="${login.firstName}"><br>
        <input type="text" required placeholder="Фамилия" name="lastName" value="${login.lastName}"><br><br>
        <input type="text" required placeholder="Номер телефона" name="phone" value="${login.phone}"><br><br>
        <input class="button" type="submit" value=<spring:message code="button.save"/>>

    </form>
</div>
</body>
