<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users update page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript"><%@include file="/WEB-INF/script/validator.js"%></script>
    <script type="text/javascript"><%@include file="/WEB-INF/script/jsonUpdate.js"%></script>
</head>

<body style="background-color: #FAEBD7" class="center">
    <div class="container" style="width: 350px">
        <h2 align="middle">UPDATE USER BY ID</h2><br/>

    <form class="form-horizontal"  method="post" action="${pageContext.servletContext.contextPath}/edit" onsubmit="return validate()">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">

        <div class="form-group">
            <label class="control-label col-sm-2" for="id">ID:</label>
            <div class="col-sm-10">
                <input id="id" name="id" value="<c:out value="${user.id}"/>" readonly="readonly"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" value="<c:out value="${user.name}"/>">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="login" name="login" placeholder="Enter login" value="<c:out value="${user.login}"/>">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password" value="<c:out value="${user.password}"/>">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="email" name="email" placeholder="Enter email" value="<c:out value="${user.email}"/>">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Country:</label>
            <div class="col-sm-10">
                <select class="form-control" id="country" name="country" onchange="getCityByCountry()" required>
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

        <c:choose>
            <c:when test="${sessionScope.role eq 'ADMIN'}">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="role">Role:</label>
                    <div class="col-sm-10">
                        <select id="role" name="role">
                            <option value="ADMIN">admin</option>
                            <option selected value="USER">user</option>
                        </select>
                    </div>
                </div>
            </c:when>

            <c:otherwise>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="roleNonAdmin">Role:</label>
                    <div class="col-sm-10">
                        <select id="roleNonAdmin" name="role">
                            <option selected value="USER">user</option>
                        </select>
                    </div>
                </div>
                <input type="hidden" name="role" value="<c:out value="${user.role}"/>"/>
            </c:otherwise>
        </c:choose>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>

    </form>
</div>
</body>
</html>
