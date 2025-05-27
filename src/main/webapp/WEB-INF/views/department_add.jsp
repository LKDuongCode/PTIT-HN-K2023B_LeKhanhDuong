<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 27/05/2025
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Add Department</title></head>
<body>
<h3>Add New Department</h3>

<form:form method="post" modelAttribute="createDTO"
           action="${pageContext.request.contextPath}/departments/add">
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

    <br>
    <form:button>Add</form:button>
    <a href="${pageContext.request.contextPath}/departments">Back to list</a>
</form:form>

</body>
</html>
