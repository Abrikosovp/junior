<%@ page import="java.util.List" %>
<%@ page import="ru.shifu.userstorage.persistent.User" %>
<%@ page import="ru.shifu.userstorage.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1" cellpadding="1" cellspacing="1">

<% String result = (String) request.getAttribute("result");
    List<User> users = ValidateService.getInstance().findAll();
    if (users.size() == 0) { %>

    <tr>
        <td>Storage is empty</td>
    </tr>

    <% } else { %>
    <tr>
    <td colspan="6" align="center"><%=result%></td>
    </tr>

    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
        <td>Create date</td>
        <td>Actions</td>
    </tr>
    <% for (User user : users) { %>

    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>

        <td>
           <form action="user" method="post">
               <input type="hidden" name="action" value="delete">
               <input type="hidden" name="id" value="<%=user.getId()%>">
               <input type="submit" value="Delete user">
           </form>

            <form action="edit" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="submit" value="Update user">
            </form>
        </td>
    </tr>

    <% } %>
    <% } %>

    <tr>
        <td colspan="6" align="center">
            <form action="create" method="get" style="margin-bottom: 0">
                <input type="submit" value="Create new user">
            </form>
        </td>
    </tr>

</table>
</body>
</html>
