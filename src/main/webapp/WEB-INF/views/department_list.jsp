<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 27/05/2025
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department List</title>
    <style>
        .header-bar {
            display: flex;
            gap: 30px;
            margin-bottom: 15px;
        }
        .btn-basic {
            display: inline-block;
            padding: 2px 6px;
            font-size: 13px;
            background-color: #e0e0e0;
            border: 1px solid #aaa;
            border-radius: 3px;
            text-decoration: none;
        }

        .btn-basic:hover {
            background-color: #d5d5d5;
        }
        .search-bar {
            display: flex;
            gap: 5px;
        }
    </style>
</head>
<body>

<h3>Department List</h3>
<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
<div class="header-bar">
    <a href="${pageContext.request.contextPath}/departments/add" class="btn-basic">Add New</a>

    <form action="${pageContext.request.contextPath}/departments/search" method="get" class="search-bar">
        <input type="text" name="keyword" placeholder="Search by name..."
               value="${keyword}" required/>
        <button class="btn-basic" type="submit">Search</button>
    </form>
</div>

<table border="1">
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <c:choose>
        <c:when test="${empty departments}">
            <tr>
                <td colspan="5" style="text-align: center;">No department found!</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="d" items="${departments}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${d.name}</td>
                    <td>${d.description}</td>
                    <td>${d.status}</td>
                    <td>
                        <div style="display: flex; gap: 5px;">
                            <a class="btn-basic" href="${pageContext.request.contextPath}/departments/edit/${d.id}">Edit</a>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/departments/delete/${d.id}"
                                  onsubmit="return confirm('Are you sure you want to delete this department?')"
                                  style="display:inline;">
                                <button class="btn-basic" type="submit">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
<br>
<a href="employees">employee management</a>
</body>
</html>

