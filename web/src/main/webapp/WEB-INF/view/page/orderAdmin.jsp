<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title>Order administration</title>
</head>

<h3><spring:message code="order.list"/></h3>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:setLocale value="en_US"/>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
<table>
    <tr>
        <th>ID</th>
        <th><spring:message code="name"/></th>
        <th><spring:message code="surname"/></th>
        <th><spring:message code="order.datе"/></th>
        <th><spring:message code="order.returnTo"/></th>
        <th><spring:message code="order.status"/></th>

    </tr>
    <c:forEach items="${orders}" var="o">
        <tr>
            <td>${o.id}</td>
            <td>${o.user.firstName}</td>
            <td>${o.user.lastName}</td>
            <td>${o.orderDate}</td>

            <fmt:parseDate value="${o.returnDate}" var="parsedDate" pattern="yyyy-MM-dd"/>
            <c:if test="${parsedDate < now and o.orderStatus == 'ISSUED'}">
                <td style="color: red">${o.returnDate}</td>
            </c:if>
            <c:if test="${parsedDate >= now or o.orderStatus != 'ISSUED'}">
                <td style="color: green">${o.returnDate}</td>
            </c:if>

            <td>${o.orderStatus}</td>
            <td>
                <form  style="display:inline-block" method="post" action="${pageContext.request.contextPath}/orderDetails">
                    <input name="orderId" type="hidden" value="${o.id}">
                    <input type="submit" value=<spring:message code="order.details"/>>
                </form>
            </td>

        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href="<spring:url value="/librarianPage"/>"><spring:message code="return.private"/></a>

