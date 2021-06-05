<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="client" value="${sessionScope.client}" scope="page"/>
<c:set var="organizations" value="${requestScope.organizations}" scope="page"/>
<%--state--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title>Check out our organizations!</title>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main>
    <jsp:include page="../UI/jumbotron.jsp">
        <jsp:param name="title" value="Here's a list of organizations!"/>
        <jsp:param name="info" value="Satisfied with a party? Give it a rating then."/>
        <jsp:param name="action" value="organizations"/>
        <jsp:param name="link" value="Show me organizations!"/>
    </jsp:include>
    <div id="organizations" class="album py-5 bg-light">
        <div class="container">
            <c:if test="${organizations.size() == 0}">
                <h6 class="text-center display-6">No organizations available at the moment!</h6>
            </c:if>
            <c:if test="${organizations.size() != 0}">
                <div class="row mb-2">
                    <c:forEach var="organization" items="${organizations}">
                        <%--Links--%>
                        <c:url var="organizationRoute" value="OrganizationServlet">
                            <c:param name="command" value="LOAD"/>
                            <c:param name="id" value="${organization.id}"/>
                        </c:url>
                        <c:url var="editOrganizationRoute" value="OrganizationServlet">
                            <c:param name="command" value="EDIT"/>
                            <c:param name="id" value="${organization.id}"/>
                        </c:url>
                        <c:url var="deleteOrganizationRoute" value="OrganizationServlet">
                            <c:param name="command" value="DELETE"/>
                            <c:param name="id" value="${organization.id}"/>
                        </c:url>
                        <%--Links--%>
                        <div class="col-md-6">
                            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                                <div class="col p-4 d-flex flex-column position-static">
                                    <h3 class="mb-0">${organization.name}</h3>
                                    <div class="mb-1 text-muted">${organization.abbreviation}</div>
                                    <p class="card-text mb-auto">${organization.description}</p>
                                    <div class="btn-group">
                                        <a class="btn btn-sm btn-outline-secondary" href="${organizationRoute}">View</a>
                                        <a class="btn btn-sm btn-outline-secondary"
                                           href="${editOrganizationRoute}">Edit</a>
                                        <a class="btn btn-sm btn-outline-danger" href="${deleteOrganizationRoute}">Delete</a>
                                    </div>
                                </div>
                                <div class="col-auto d-none d-lg-block">
                                    <c:if test="${organization.thumbnail.length() > 0}">
                                        <img src="${organization.thumbnail}" class="bd-placeholder-img"
                                             alt="${organization.name}'s thumbnail" width="200" height="250"/>
                                    </c:if>
                                    <c:if test="${organization.thumbnail.length() == 0}">
                                        <img src="https://sportingwineclub.com/wp-content/themes/claue/assets/images/placeholder.png"
                                             class="bd-placeholder-img"
                                             alt="${organization.name}'s thumbnail" width="200" height="250"/>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</main>
<jsp:include page="../UI/footer.jsp"/>
</body>
</html>
