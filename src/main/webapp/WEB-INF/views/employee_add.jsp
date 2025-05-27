<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 27/05/2025
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>

<h3>Add New Employee</h3>

<form:form method="post"
           modelAttribute="createDTO"
           action="${pageContext.request.contextPath}/employees/add"
           enctype="multipart/form-data">

    <div>
        <p>Name:</p>
        <form:input path="name"/>
        <form:errors path="name" cssStyle="color:red"/>
    </div>

    <div>
        <p>Email:</p>
        <form:input path="email"/>
        <form:errors path="email" cssStyle="color:red"/>
    </div>

    <div>
        <p>Phone:</p>
        <form:input path="phone"/>
        <form:errors path="phone" cssStyle="color:red"/>
    </div>

    <div>
        <p>Status:</p>
        <form:select path="status">
            <form:option value="ACTIVE">ACTIVE</form:option>
            <form:option value="INACTIVE">INACTIVE</form:option>
        </form:select>
        <form:errors path="status" cssStyle="color:red"/>
    </div>

    <div>
        <p>Avatar:</p>
        <form:input path="imageFile" type="file"/>
        <form:errors path="imageFile" cssStyle="color:red"/>
    </div>

    <div>
        <p>Department:</p>
        <form:select path="department_id">
            <form:options items="${departments}" itemValue="id" itemLabel="name"/>
        </form:select>
        <form:errors path="department_id" cssStyle="color:red"/>
    </div>

    <br>
    <form:button>Add</form:button>
    <a href="${pageContext.request.contextPath}/employees">Back to list</a>
</form:form>

</body>
</html>
