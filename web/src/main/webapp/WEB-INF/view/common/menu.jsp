<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div style="padding: 5px;">

    <ul>
        <a href="${requestScope['javax.servlet.forward.request_uri']}?locale=ru" style="padding: 5px">RU</a>
        <a href="${requestScope['javax.servlet.forward.request_uri']}?locale=en" style="padding: 5px">EN</a>
        <li><a href="${pageContext.request.contextPath}/"><spring:message code="home"/></a></li>

        <%--       если пользователь не залогинился показать строку меню--%>
<%--        <c:if test="${login = null}">--%>
            <li><a href="${pageContext.request.contextPath}/registration"><spring:message code="registration.heading"/></a></li>
<%--        </c:if>--%>

    </ul>

</div>
