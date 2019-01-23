<%@ page import="ru.shifu.userstorage.persistent.User" %>
<%@ page import="ru.shifu.userstorage.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>User update</title>
</head>
<body><table>

    <%  String id = request.getParameter("id");
        User user = ValidateService.getInstance().findById(id); %>

<form method="post" action="edit">
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="<%=user.getId()%>">

<tr><td>ID</td><td>
    <%=user.getId()%>
</td></tr>

<tr><td>Name</td><td>
    <input type="text" name="name" value="<%=user.getName()%>">
</td></tr>

<tr><td>Login</td><td>
    <input type="text" name="login" value="<%=user.getLogin()%>">
</td></tr>

<tr><td>Email</td><td>
    <input type="text" name="email" value="<%=user.getEmail()%>">
</td></tr>

<tr align="right"><td colspan="2">
    <input type="submit" value="UPDATE">
</td></tr>

</form></table></body></html>
