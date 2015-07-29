<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <title>
        <spring:message code="mainPage.title" text="Help Desk | главная"/>
    </title>

    <jsp:include page="templates/head.jsp"/>
</head>

<body>

<jsp:include page="templates/navbar.jsp"/>

<div class="container">
    <div class="page-header">
        <h1>Login</h1>
    </div>
    <div>
        <form action="<c:url value="/login"/>" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" class="form-control" name="username"/>
            </div>
            <div class="form-group">
                <label for="pass">Pass</label>
                <input id="pass" type="password" class="form-control" name="password"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-default">Войти</button>
        </form>
    </div>
</div>
</body>
</html>