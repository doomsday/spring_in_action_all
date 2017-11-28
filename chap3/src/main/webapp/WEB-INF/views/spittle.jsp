<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<h1>Recent Spittles</h1>

<div class="spittleView">
    <div class="spittleMessage"><%--@elvariable id="spittle" type="sun.security.krb5.KrbSafe"--%>
    <c:out value="${spittle.message}"/>
    </div>
    <div>
        <span class="spittleTime"><c:out value="${spittle.time}"/></span>
    </div>
</div>
