<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User creation</title>
</head><body>
<table><form method="POST" action="create">
<input type="hidden" name="action" value="add">
CREATE USER BY ID
<tr><td>Name</td><td><input type="text" name="name"></td></tr>
<tr><td>Login</td><td><input type="text" name="login"></td></tr>
<tr><td>Email</td><td><input type="text" name="email"></td></tr>
<tr align="right"><td colspan="2">
    <input type="submit" value="CREATE"></td></tr>
</form></table>
</body></html>
