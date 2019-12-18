<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <title>Customer page</title>
</head>

<sec:authentication var = "user" property="principal"/>
<h1><spring:message code="welcome.privatepage"/>${user.firstName}!</h1>


<h3><spring:message code="catalogue"/></h3>
<table>
    <tr>
        <th><spring:message code="name.author"/></th>
        <th><spring:message code="surname.author"/></th>
        <th><spring:message code="title"/></th>
        <th><spring:message code="genre"/></th>
        <th><spring:message code="quantity"/></th>

    </tr>
    <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.authorFirstName}</td>
                <td>${book.authorLastName}</td>
                <td>${book.title}</td>
                <td>${book.genre}</td>
                <td align = "center">${book.count}</td>
                <c:if test="${book.count>0}">
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/order">
                        <input name="bookToOrder" type="hidden" value="${book.id}">
                        <input type="submit" value=<spring:message code="order"/>>
                    </form>
                </td>
                </c:if>
            </tr>
    </c:forEach>
</table>

<c:if test="${page>0}">
    <a href="${pageContext.request.contextPath}/customerPage?page=${page-1}"><spring:message code="prevPage"/></a>
</c:if>
<c:if test="${page<maxNumber}">
    <a href="${pageContext.request.contextPath}/customerPage?page=${page+1}"><spring:message code="nextPage"/></a>
</c:if>
<br>
<br>
<a href="<spring:url value="/orderPage"/>"><spring:message code="order.heading"/></a>
