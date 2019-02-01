<%--
  Enter JSP.
  User: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 03.12.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body style="background-color: #FAEBD7">
<div class="container">
    <h4 class="text-primary">Please enter you action</h4>
    <form action="${pageContext.servletContext.contextPath}/signin" method="get">
        <div class="form-group">
            <input type="submit" value="Authorization" class="btn btn-primary"/>
        </div>
    </form>
    <form action="${pageContext.servletContext.contextPath}/create" method="GET">
        <div class="form-group">
            <input type="submit" value="Registration" class="btn btn-primary"/>
        </div>
    </form>
</div>
</body>
</html>
