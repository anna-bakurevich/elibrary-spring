<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <title>Librarian page</title>
</head>

<sec:authentication var = "librarian" property="principal"/>
<h1><spring:message code="welcome.privatepage"/>${librarian.firstName}!</h1>

<h3><spring:message code="user.list"/></h3>
<table>
    <tr>
        <th><spring:message code="name"/></th>
        <th><spring:message code="surname"/></th>
        <th><spring:message code="phone"/></th>

    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.phone}</td>
            <td>
                <c:if test="${librarian.id != user.id}">
                    <form method="post" action="${pageContext.request.contextPath}/librarianPage">
                        <input name="deleteId" type="hidden" value="${user.id}">
                        <input type="submit" value=<spring:message code="delete"/>>
                    </form>
                </c:if>
            </td>

        </tr>
    </c:forEach>
</table>

<c:if test="${error}">
    <p style="color: red"><spring:message code="error.deleteUser"/></p>
    <br>
    <br>
    <a href="<spring:url value="/librarianPage"/>"><spring:message code="return.private"/></a>
</c:if>



