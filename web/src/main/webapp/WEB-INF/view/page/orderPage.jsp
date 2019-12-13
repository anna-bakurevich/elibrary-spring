<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>

<head>
    <title>Order Page</title>
</head>

<%--вывести список книг в заказе кнопку подтвердить заказ и удалить из зазказа--%>
<html>
<body>
<h3><fmt:message key="order.title" bundle="${messages}"/></h3>
<table>
    <tr>
        <th><fmt:message key="name.author" bundle="${messages}"/></th>
        <th><fmt:message key="surname.author" bundle="${messages}"/></th>
        <th><fmt:message key="title" bundle="${messages}"/></th>
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
                           value=<fmt:message key="delete" bundle="${messages}"/>>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="${pageContext.request.contextPath}/orderPage">
    <input name="confirm" style="height: 22px; width: 220px;" type="submit" value=<fmt:message key="order.confirm" bundle="${messages}"/>>
</form>
</body>
</html>