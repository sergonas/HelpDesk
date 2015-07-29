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
<spring:url var = "signup" value='/signup'/>

<div class="container">
    <div class="page-header">
        <h1>News</h1>
    </div>
    <div>
        <form:form action="${signup}" method="post" commandName="user" role="form">
            <div class="form-group">
                <form:label path="username" for="exampleInputEmail1">Email</form:label>
                <form:input path="username" class="form-control"/>
            </div>
            <div class="form-group">
                <form:label path="passwordHash" for="exampleInputPassword1">Пароль</form:label>
                <form:input path="passwordHash" type="password" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-default">Зарегистрироваться</button>
        </form:form>
    </div>
</div>
</body>
</html>