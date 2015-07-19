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
    <c:choose>
        <c:when test="${!empty newsList}">
            <c:forEach items="${newsList}" var="news">
                <h3>${news.header}</h3>

                <div class="small">
                        ${news.author} posted on ${news.date}
                </div>
                <div class="multiline-text">
                        ${news.text}
                </div>
                <br>
            </c:forEach>
            <nav>
                <ul class="pagination">
                    <li>
                        <a href="${currentIndex - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach var="i" begin="1" end="${pages}">
                        <c:url var="pageUrl" value="${i}"/>
                        <c:choose>
                            <c:when test="${i == currentIndex}">
                                <li class="active"><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <li>
                        <a href="${currentIndex + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </c:when>
        <c:otherwise>
            No news yet
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>