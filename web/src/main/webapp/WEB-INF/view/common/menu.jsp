<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="padding: 5px;">

    <ul>

        <li><a href="${pageContext.request.contextPath}/">Домашняя страница</a></li>

        <%--       если пользователь не залогинился скрыть строку меню--%>
                <c:if test="${login != null}">
        <li><a href="${pageContext.request.contextPath}/registration">Регистрация</a></li>
                </c:if>

    </ul>

</div>
