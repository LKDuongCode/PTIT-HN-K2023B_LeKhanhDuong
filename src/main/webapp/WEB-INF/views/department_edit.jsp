<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 27/05/2025
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Edit Department</title></head>
<body>
<h3>Edit Department</h3>

<form:form method="post" modelAttribute="updateDTO"
           action="${pageContext.request.contextPath}/departments/edit">
    <form:hidden path="id"/>

    <div>
        <p>Name:</p>
        <form:input path="name"/>
        <form:errors path="name" cssStyle="color:red"/>
    </div>

    <div>
        <p>Description:</p>
        <form:textarea path="description" rows="4" cols="50"/>
        <form:errors path="description" cssStyle="color:red"/>
    </div>

    <div>
        <p>Status:</p>
        <form:select path="status">
            <form:option value="ACTIVE">ACTIVE</form:option>
            <form:option value="INACTIVE">INACTIVE</form:option>
        </form:select>
        <form:errors path="status" cssStyle="color:red"/>
    </div>

    <br>
    <form:button>Update</form:button>
    <a href="${pageContext.request.contextPath}/departments">Back to list</a>
</form:form>

</body>
</html>

