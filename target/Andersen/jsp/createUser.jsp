<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color: ${color}">Users count - ${userList.size()}</h1>

<table id="users_table">
    <tr>

        <th>FirstName</th>
        <th>LastName</th>
        <th>Age</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>

            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.age}"/></td>
        </tr>
    </c:forEach>
</table>
<h1></h1>
<form action="/Andersen.war/create" method="post">
    <%--<div>--%>
    <input id="firstName" name="firstName" placeholder="Enter First Name">
    <input id="lastName" name="lastName" placeholder="Enter Last Name">
    <input id="age" name ="age" placeholder="Enter Age">
    <input type="submit" value="Add!!!!!">
        <button onclick="addUser(
            document.getElementById('firstName').value,
            document.getElementById('lastName').value,
            document.getElementById('age').value)">
            Add
        </button>

</form>
<%--</div>--%>
</body>
</html>
