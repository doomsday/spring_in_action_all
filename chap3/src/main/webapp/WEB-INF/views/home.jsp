<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>

<h1><s:message code="spittr.welcome"/></h1>
<a href="<c:url value="/spittles"/>">Spittles</a> |
<a href="<c:url value="/spitter/register" />">Register</a>