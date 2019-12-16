<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div style="padding: 5px;">

    <ul>
        <%--       если пользователь не залогинился показать строку меню--%>
        <c:if test="${login = null}">
        </c:if>
        <li><a href="${pageContext.request.contextPath}/registration"><spring:message code="registration.heading"/></a></li>
        <li><a href="${pageContext.request.contextPath}/logout"><spring:message code="logout"/></a></li>
        <li><a href="${pageContext.request.contextPath}/edit"><spring:message code="edit.heading"/></a></li>

    </ul>

</div>
