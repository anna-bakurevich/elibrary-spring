<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html xmlns:jsp="http://java.sun.com/JSP/Page"
      xmlns:c="http://java.sun.com/jsp/jstl/core"
      xmlns:spring="http://www.springframework.org/tags"
      xmlns:tiles="http://tiles.apache.org/tags-tiles">


<head>
    <title>E-LIBRARY</title>
</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="menu"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>
</body>
</html>