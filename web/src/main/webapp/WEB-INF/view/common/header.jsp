<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div style="background: #E0E0E0; height: 100px; padding: 5px;">
    <div style="float: left">
        <h1>E-library</h1>
        <a href="${requestScope['javax.servlet.forward.request_uri']}?locale=ru" style="padding: 5px">RU</a>
        <a href="${requestScope['javax.servlet.forward.request_uri']}?locale=en" style="padding: 5px">EN</a>
        </div>
    </div>
</div>