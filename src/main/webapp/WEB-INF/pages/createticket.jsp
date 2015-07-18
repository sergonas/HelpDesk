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

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">
                <img width="20" height="20" alt="HelpDesk" src="<c:url value="/resources/images/logo.png"/>"/>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav">
                <li>
                    <a href="<c:url value="/"/>">
                        <spring:message code="common.nav.main" text="Главная"/>
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li><a href="<c:url value="/news"/>">
                    <spring:message code="common.nav.news" text="Новости сервиса"/>
                </a></li>
                <li><a href="<c:url value="/faq"/>">
                    <spring:message code="common.nav.faq" text="Помощь"/>
                </a></li>
                <li class="active"><a href="<c:url value="/createticket"/>">
                    <spring:message code="common.nav.createTicket" text="Подать заявку"/>
                </a></li>
                <li><a href="<c:url value="/viewtickets"/>">
                    <spring:message code="common.nav.viewTickets" text="Посмотреть заявки"/>
                </a></li>
            </ul>

            <div class="btn-toolbar navbar-right" role="toolbar">
                <div class="btn-group" role="group">
                    <a href="<c:url value="/login"/>">
                        <button type="button" class="btn btn-default navbar-btn">
                            <spring:message code="common.nav.login" text="Войти"/>
                        </button>
                    </a>
                </div>
                <div class="btn-group" role="group">
                    <a href="<c:url value="/signup"/>">
                        <button type="button" class="btn btn-success navbar-btn">
                            <spring:message code="common.nav.signUp" text="Регистрация"/>
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <div class="page-header">
        <h1>News</h1>
    </div>
</div>
</body>
</html>