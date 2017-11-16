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
<h1>Register</h1>

<%-- Form tag doesn't have an action parameter set. Because of that, when this form is submitted, it will be posted --%>
<%-- back to the same URL path that displayed it. That is, it will be posted back to '/spitters/register'. --%>
<form method="post">

    <label for="firstName">First name:</label>
    <input type="text" id="firstName" name="firstName"/><br/>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName"/><br/>

    <label for="userName">Username:</label>
    <input type="text" id="userName" name="userName"/><br/>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password"/><br/>

    <label for="submit">Password:</label>
    <input type="submit" id="submit" value="Register"/><br/>

</form>

</body>
</html>