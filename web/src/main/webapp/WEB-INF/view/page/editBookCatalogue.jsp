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
                <form method="post" action="${pageContext.request.contextPath}/bookDelete">
                    <input name="bookDelete" type="hidden" value="${book.id}">
                    <input type="submit" style="height: 22px; width: 75px;"
                           value=<spring:message code="delete"/>>
                    <input name="countDelete" type="number" value=0 min=0 style="width: 40px">
                </form>
                <form method="post" action="${pageContext.request.contextPath}/bookAdd">
                    <input name="bookAdd" type="hidden" value="${book.id}">
                    <input type="submit" style="height: 22px; width: 75px;"
                           value=<spring:message code="add"/>>
                    <input name="countAdd" type="number" value=0 min=0 style="width: 40px">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/editBookCatalogue?page=${page+1}">next</a>
<a href="${pageContext.request.contextPath}/editBookCatalogue?page=${page-1}">previous</a>

<%--<form method="post" action="${pageContext.request.contextPath}/editBookCatalogue">--%>
<%--&lt;%&ndash;    <input name="pageNumber" type="hidden" value="${pageNumber}">&ndash;%&gt;--%>

<%--    <c:if test="${page>0}">--%>
<%--        <input name="prevPage?page=${page-1}" type="submit" value=<spring:message code="button.prev"/>>--%>
<%--    </c:if>--%>

<%--    <c:if test="${pageNumber<maxNumber}">--%>
<%--        <input name="nextPage?page=${page+1}" type="submit" value=<spring:message code="button.next"/>>--%>
<%--    </c:if>--%>
<%--</form>--%>

<br>
<br>
<a href="<spring:url value="/librarianPage"/>"><spring:message code="return.private"/></a>