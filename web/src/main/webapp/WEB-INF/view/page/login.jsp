<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--<fmt:setLocale value="${locale}"/>--%>
<%--<fmt:setBundle basename="translations" var="messages"/>--%>

<html>
<body>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%--<h1><fmt:message key="login.heading" bundle="${messages}"/></h1><br>--%>
<h1><spring:message code="login.heading"/></></h1><br>

<form method="post" action="${pageContext.request.contextPath}/login">


    <input type="text" required placeholder="login" name="login"><br>
    <input type="text" required placeholder="password" name="password"><br><br>
    <input class="button" type="submit" value=<spring:message code="button.login"/>>

</form>
</body>
</html>