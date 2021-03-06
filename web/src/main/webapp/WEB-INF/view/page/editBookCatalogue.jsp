<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<head>
    <title>Edit book catalogue</title>
</head>

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
            <td align="center">${book.count}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/bookDelete?page=${page}">
                    <input name="bookDelete" type="hidden" value="${book.id}">
                    <input type="submit" style="height: 22px; width: 75px;"
                           value=<spring:message code="delete"/>>
                    <input name="countDelete" type="number" value=0 min=0 style="width: 40px">
                </form>
                <form method="post" action="${pageContext.request.contextPath}/bookAdd?page=${page}">
                    <input name="bookAdd" type="hidden" value="${book.id}">
                    <input type="submit" style="height: 22px; width: 75px;"
                           value=<spring:message code="add"/>>
                    <input name="countAdd" type="number" value=0 min=0 style="width: 40px">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${page>0}">
    <a href="${pageContext.request.contextPath}/editBookCatalogue?page=${page-1}"><spring:message code="prevPage"/></a>
</c:if>
<c:if test="${page<maxNumber}">
<a href="${pageContext.request.contextPath}/editBookCatalogue?page=${page+1}"><spring:message code="nextPage"/></a>
</c:if>
<c:if test="${error}">
    <p style="color: red"><spring:message code="error.deleteBook"/></p>
    <br>
    <br>
    <a href="<spring:url value="/editBookCatalogue"/>"><spring:message code="return.catalogue"/></a>
</c:if>

<br>
<br>
<a href="<spring:url value="/librarianPage"/>"><spring:message code="return.private"/></a>