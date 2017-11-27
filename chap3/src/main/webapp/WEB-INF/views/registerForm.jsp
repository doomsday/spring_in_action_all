<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<style>
    div.errors {
        background-color: #ffcccc;
        border: 1px solid red;
    }
    input.error {
        background-color: #ffcccc;
    }
    label.error {
        color: red;
    }
</style>

<h1>Register</h1>

<%-- Form tag doesn't have an action parameter set. Because of that, when this form is submitted, it will be posted --%>
<%-- back to the same URL path that displayed it. That is, it will be posted back to '/spitters/register'. --%>

<%--@elvariable id="spitter" type="org.drpsy.spittr.Spitter"--%>
<sf:form method="POST" commandName="spitter">
    <sf:errors path="*" element="div" cssClass="errors"/>

    <sf:label path="firstName" cssErrorClass="error">First name:</sf:label>
    <sf:input path="firstName" cssErrorClass="error"/>
    <br/>

    <sf:label path="lastName" cssErrorClass="error">Last name:</sf:label>
    <sf:input path="lastName" cssErrorClass="error"/>
    <br/>

    <sf:label path="userName" cssErrorClass="error">User name:</sf:label>
    <sf:input path="userName" cssErrorClass="error"/>
    <br/>

    <sf:label path="email" cssErrorClass="error">Email:</sf:label>
    <sf:input path="email" cssErrorClass="error"/>
    <br/>

    <sf:label path="password" cssErrorClass="error">Password:</sf:label>
    <sf:password path="password" cssErrorClass="error"/>
    <br/>

    <input type="submit" value="Register"/><br/>

</sf:form>