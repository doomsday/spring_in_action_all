<%--
  Created by IntelliJ IDEA.
  User: drpsy
  Date: 10-Nov-17
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/style.css" />">
</head>
<body>

<h1>Welcome to Spitter</h1>
<a href="<c:url value = "/spittles"/>">Spittles</a> |
<a href="<c:url value="/spitter/register" />">Register</a>
</body>
</html>
