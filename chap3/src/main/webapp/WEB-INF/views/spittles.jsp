<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<h1>Recent Spittles</h1>

<%--@elvariable id="spittleList" type="java.util.List"--%>
<c:forEach items="${spittleList}" var="spittle">
    <li id="spittle_<c:out value="spittle.id"/>">
        <div class="spittleMessage">
            <c:out value="${spittle.message}"/>
        </div>
        <div>
            <span class="spittleTime">
                <c:out value="${spittle.time}"/>
            </span>
            <span class="spittleLocation">
                (<c:out value="${spittle.latitude}"/>,
                <c:out value="${spittle.longitude}"/>)
            </span>
        </div>
    </li>
</c:forEach>
