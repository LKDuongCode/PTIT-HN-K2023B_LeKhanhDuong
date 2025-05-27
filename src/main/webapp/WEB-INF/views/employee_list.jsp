<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 27/05/2025
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee List</title>
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
        .pagination {
            margin-top: 10px;
            display: flex;
            align-items: center;
            gap: 10px;
        }
    </style>
</head>
<body>

<h3>Employee List</h3>

<div class="header-bar">
    <a href="${pageContext.request.contextPath}/employees/add" class="btn-basic">Add New</a>

    <form action="${pageContext.request.contextPath}/employees/search" method="get" class="search-bar">
        <input type="text" name="keyword" placeholder="Search by name..." value="${keyword}" />
        <button class="btn-basic" type="submit">Search</button>
    </form>
</div>

<table border="1">
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Status</th>
        <th>Department</th>
        <th>Avatar</th>
        <th>Action</th>
    </tr>

    <c:choose>
        <c:when test="${empty employees}">
            <tr><td colspan="8" style="text-align:center;">No employees found.</td></tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="e" items="${employees}" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${e.name}</td>
                    <td>${e.email}</td>
                    <td>${e.phone}</td>
                    <td>${e.status}</td>
                    <td>${e.departmentName}</td>
                    <td>
                        <img src="${pageContext.request.contextPath}/uploads/${e.avatar}" alt="avatar" width="80"/>
                    </td>
                    <td>
                        <div style="display: flex; gap: 5px;">
                            <a class="btn-basic" href="${pageContext.request.contextPath}/employees/edit/${e.id}">Edit</a>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/employees/delete/${e.id}"
                                  onsubmit="return confirm('Delete this employee?')"
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

<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/employees?page=${currentPage - 1}" class="btn-basic">Prev</a>
    </c:if>

    <span>Page ${currentPage} / ${totalPages}</span>

    <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/employees?page=${currentPage + 1}" class="btn-basic">Next</a>
    </c:if>
</div>

</body>
</html>

