<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User creation</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript"><%@include file="/WEB-INF/script/validator.js"%></script>
    <script type="text/javascript"><%@include file="/WEB-INF/script/jsonCreate.js"%></script>
</head>

<body style="background-color: #FAEBD7" class="center">
<div class="container-fluid" style="width: 350px">
    <h2 align="middle">CREATE USER BY ID</h2><br/>

    <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/create" onsubmit="return validate()">
        <input type="hidden" name="action" value="add">

        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="login" placeholder="Enter login" name="login">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="email" placeholder="Enter email" name="email">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Country:</label>
            <div class="col-sm-10">
                <select class="form-control" id="country" name="country" onchange="getCity()" required>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="city">City:</label>
            <div class="col-sm-10">
                <select class="form-control" id="city" name="city" required>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="role">Role:</label>
            <div class="col-sm-10">
                <select id="role" name="role">
                    <option value="ADMIN">admin</option>
                    <option selected value="USER">user</option>
                </select>
            </div>
        </div>

        <div align="right" class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">CREATE</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
