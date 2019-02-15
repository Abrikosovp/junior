<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User View</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body style="background-color: #FAEBD7">

<div class="container">
<table class="table">

    <tr>
        <td colspan="9" align="center" style="background-color: #bcb1b9"><c:out value="${result}" default="List of all users"/></td>
    </tr>

    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Login</td>
        <td>Role</td>
        <td>Email</td>
        <td>Country</td>
        <td>City</td>
        <td>Create date</td>
        <td>Actions</td>
    </tr>

    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.country}"/></td>
            <td><c:out value="${user.city}"/></td>
            <td><c:out value="${user.createDate}"/></td>

            <td>
                <c:if test="${sessionScope.role eq 'ADMIN'}">
                    <form action="${pageContext.servletContext.contextPath}/user" method="post">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="hidden" name="role" value="${user.role}"/>
                        <input class="btn btn-danger" type="submit" value="Delete user"/>
                    </form>

                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input class="btn btn-primary" type="submit" value="Update user"/>
                    </form>
                </c:if>

                <c:if test="${sessionScope.role ne 'ADMIN'}">
                    <c:if test="${sessionScope.uid eq user.id}">
                        <form action="${pageContext.servletContext.contextPath}/edit" method="get" style="margin: 1px">
                            <input type="hidden" name="id" value="${sessionScope.uid}"/>
                            <input class="btn btn-primary" type="submit"  value="Update user"/>
                        </form>
                    </c:if>
                </c:if>
            </td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="9" align="left" style="background-color: #bcb1b9">
            <c:if test="${sessionScope.role eq 'ADMIN'}">
                <form action="${pageContext.servletContext.contextPath}/create" method="get" style="margin-bottom: 0">
                    <input type="submit" class="btn btn-primary" value="CREATE NEW USER">
                </form>
            </c:if>
        </td>
    </tr>
    <div class="btn-group">
    <tr>
        <td colspan="8" align="right">
            <form action="${pageContext.servletContext.contextPath}/signout" method="get"
                  style="margin: 2px; display: inline-block">
                <input class="btn btn-warning" type="submit" value="Sign out"/>
            </form>
            <form action="${pageContext.servletContext.contextPath}/json.index" method="post"
                  style="margin: 2px; display: inline-block">
                <input class="btn btn-danger" type="submit" value="Json"/>
            </form>
        </td>
    </tr>
    </div>

</table>
</div>
</body>
</html>
