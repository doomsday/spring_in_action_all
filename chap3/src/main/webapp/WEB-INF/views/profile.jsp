<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>
<h1>Your Profile</h1>

<c:out value="${spitter.userName}"/><br/>
<c:out value="${spitter.firstName}"/>
<c:out value="${spitter.lastName}"/>

</body>
</html>