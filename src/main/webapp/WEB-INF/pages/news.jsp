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

<jsp:include page="templates/navbar.jsp">
    <jsp:param name="active" value="news"/>
</jsp:include>

<div class="container">
    <div class="page-header">
        <h1>News</h1>
    </div>
</div>
</body>
</html>