<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <title>Order Page</title>
</head>

<%--вывести список книг в заказе кнопку подтвердить заказ и удалить из зазказа--%>
<html>
<body>
<h3><spring:message code="order.title"/></h3>
<table>
    <tr>
        <th><spring:message code="name.author"/></th>
        <th><spring:message code="surname.author"/></th>
        <th><spring:message code="title"/></th>
    </tr>
    <c:forEach items="${booksInOrder}" var="b">
        <tr>
            <td>${b.authorFirstName}</td>
            <td>${b.authorLastName}</td>
            <td>${b.title}</td>

            <td>
                <form method="post" action="${pageContext.request.contextPath}/orderPage">
                    <input name="bookDelete" type="hidden" value="${b.id}">
                    <input type="submit" style="height: 22px; width: 75px;"
                           value=<spring:message code="delete"/>>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="${pageContext.request.contextPath}/confirm">
    <input name="confirm" style="height: 22px; width: 220px;" type="submit" value=<spring:message code="order.confirm"/>>
</form>

</body>
</html>