<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<h1>Your Profile</h1>
<%--@elvariable id="spitter" type="org.drpsy.spittr.Spitter"--%>
<c:out value="${spitter.userName}"/><br/>
<c:out value="${spitter.firstName}"/>
<c:out value="${spitter.lastName}"/>