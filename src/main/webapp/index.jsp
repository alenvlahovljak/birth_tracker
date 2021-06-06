<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="client" value="${sessionScope.client}" scope="page"/>
<c:set var="parties" value="${requestScope.parties}" scope="page"/>
<%--State--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="views/utils/config.jsp"/>
    <title>Find a perfect party!</title>
</head>
<body>
<jsp:include page="views/UI/header.jsp"/>
<main>
    <jsp:include page="views/UI/jumbotron.jsp">
        <jsp:param name="title" value="Throwing the best parties"/>
        <jsp:param name="info" value="Are you looking for a party? Stop doing that, now! You're at the right place."/>
        <jsp:param name="action" value="parties"/>
        <jsp:param name="link" value="Show me parties!"/>
    </jsp:include>
    <div id="parties" class="album py-5 bg-light">
        <div class="container">
            <c:if test="${parties.size() == 0}">
                <h6 class="text-center display-6">No parties available at the moment!</h6>
            </c:if>
            <c:if test="${parties.size() != 0}">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <c:forEach var="party" items="${parties}">
                        <%--Links--%>
                        <c:url var="partyRoute" value="PartyServlet">
                            <c:param name="command" value="LOAD"/>
                            <c:param name="id" value="${party.id}"/>
                        </c:url>
                        <c:url var="organizationRoute" value="OrganizationServlet">
                            <c:param name="command" value="LOAD"/>
                            <c:param name="id" value="${party.organizationId}"/>
                        </c:url>
                        <%--Links--%>
                        <div class="col">
                            <div class="card shadow-sm">
                                <c:if test="${party.thumbnail.length() > 0}">
                                    <img src="${party.thumbnail}" class="card-img-top"
                                         alt="${party.name} party's thumbnail">
                                </c:if>
                                <c:if test="${party.thumbnail.length() == 0}">
                                    <img src="https://sportingwineclub.com/wp-content/themes/claue/assets/images/placeholder.png"
                                         class="card-img-top"
                                         alt="${party.name} party's thumbnail">
                                </c:if>
                                <div class="card-body">
                                    <h5 class="card-title">${party.name}</h5>
                                    <c:if test="${party.description.length() == 0}">
                                        <p class="card-text">No description has provided!</p>
                                    </c:if>
                                    <c:if test="${party.description.length() > 0}">
                                        <p class="card-text">${party.description}</p>
                                    </c:if>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <small class="text-muted">By: <a href="${organizationRoute}"
                                                                         class="link-danger">${party.organizationAbbreviation}</a></small>
                                    </div>
                                    <div class="card-footer mt-md-5 d-flex justify-content-between align-items-center">
                                        <a class="btn btn-sm btn-outline-secondary" href="${partyRoute}">View</a>
                                        <span class="text-success"><small>$</small>${party.price}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</main>
<jsp:include page="views/UI/footer.jsp"/>
</body>
</html>