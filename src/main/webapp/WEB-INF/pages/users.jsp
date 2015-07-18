<%--
  Author: Dmitry Smorzhok
  Date: 10.07.15
--%>
<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Administration | Help Desk</title>

    <jsp:include page="templates/head.jsp"/>
</head>

<body>
<div class="container">
    <h1>Users</h1>
    <form:form method="post" action="/admin/add" commandName="user" role="form">
        <div class="form-group">
            <form:label path="firstName">First Name:</form:label>
            <form:input path="firstName" class="form-control" placeholder="First Name"/>
        </div>
        <div class="form-group">
            <form:label path="lastName">Last Name:</form:label>
            <form:input path="lastName" class="form-control" placeholder="Last Name"/>
        </div>
        <div class="form-group">
            <form:label path="email">Email:</form:label>
            <form:input path="email" class="form-control" placeholder="Email"/>
        </div>
        <button type="submit" class="btn btn-default">Add User</button>
    </form:form>

    <c:if test="${!empty users}">
        <h3>Users</h3>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.lastName}, ${user.firstName}</td>
                    <td>${user.email}</td>
                    <td>
                        <form:form action="/admin/delete/${user.id}" method="post"><input type="submit"
                                                                                   class="btn btn-danger btn-mini"
                                                                                   value="Delete"/>
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>