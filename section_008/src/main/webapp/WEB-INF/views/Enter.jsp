<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <style>
        div.container1 {
            height: 10em;
            position: relative }

        div.container1 {
            margin: 0;
            position: absolute;
            top: 30%;
            left: 50%;
            margin-right: -50%;
            transform: translate(-50%, -50%) }
    </style>
</head>
<body style="background-color: #FAEBD7">
<div class="container1">
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
