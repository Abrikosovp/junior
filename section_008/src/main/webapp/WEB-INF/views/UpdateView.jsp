<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>User update</title>
</head>
<body><table>

<form method="post" action="${pageContext.servletContext.contextPath}/edit">
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="<c:out value="${user.id}"/>">

<tr><td>ID</td><td>
    <c:out value="${user.id}"/>
</td></tr>

<tr><td>Name</td><td>
    <input type="text" name="name" value="<c:out value="${user.name}"/>">
</td></tr>

<tr><td>Login</td><td>
    <input type="text" name="login" value="<c:out value="${user.login}"/>">
</td></tr>

<tr><td>Email</td><td>
    <input type="text" name="email" value="<c:out value="${user.email}"/>">
</td></tr>

<tr align="right"><td colspan="2">
    <input type="submit" value="UPDATE">
</td></tr>

</form></table></body></html>
