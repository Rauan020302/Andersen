<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color: ${color}">Users count - ${userList.size()}</h1>
<table id="accounts_table">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
        </tr>
    </c:forEach>
</table>
<div>
    <input id="firstName" placeholder="Enter First Name">
    <input id="lastName" placeholder="Enter Last Name">
    <input id="age" placeholder="Enter Age">
    <button onclick="addUser(
        document.getElementById('firstName').value,
        document.getElementById('lastName').value,
        document.getElementById('age').value)">
        Add
    </button>
</div>
</body>
</html>