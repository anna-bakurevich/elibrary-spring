<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Order details</title>
</head>

<body>
<h3><spring:message code="order.title"/> ${orderId}</h3>
<table>
    <tr>
        <th>ISBN</th>
        <th><spring:message code="name.author"/></th>
        <th><spring:message code="surname.author"/></th>
        <th><spring:message code="title"/></th>
        <th><spring:message code="order.datе"/></th>
        <th><spring:message code="order.returnTo"/></th>
    </tr>
    <c:forEach items="${books}" var="b">
        <tr>
            <td>${b.isbn}</td>
            <td>${b.authorFirstName}</td>
            <td>${b.authorLastName}</td>
            <td>${b.title}</td>
            <td>${orderDate}</td>

            <c:set var="st" value="${status}"/>
            <c:if test="${st eq 'RETURNED'}">
                <td><spring:message code="order.returned"/></td>
            </c:if>
            <c:if test="${st != 'RETURNED'}">
                <td>${returnDate}</td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href="<spring:url value="/orderAdmin"/>"><spring:message code="return.orderAdmin"/></a>

</body>
</html>

