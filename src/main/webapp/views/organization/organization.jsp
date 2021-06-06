<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="organization" value="${requestScope.organization}" scope="page"/>
<%--State--%>

<%--Links--%>
<c:url var="organizationListRoute" value="OrganizationServlet">
    <c:param name="command" value="LIST"/>
    <c:param name="manager_id"/>
</c:url>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title><c:out value="${organization.name}"/> | Organization</title>
    <style>
        .background {
            background: url("${organization.thumbnail}");
        }

        .info {
            background-color: rgba(12, 12, 12, 0.6);
            color: white;

        }
    </style>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main>
    <div class="container py-4">
        <div class="p-5 mb-4 bg-light rounded-3 background">
            <div class="container-fluid py-5 info">
                <h1 class="display-5 fw-bold">${organization.name}</h1>
                <p class="col-md-8 fs-4">${organization.description}</p>
                <a class="btn btn-primary stretched-link" href="${organizationListRoute}">Check out other
                    organizations</a>
            </div>
        </div>
        <div class="row align-items-md-stretch">
            <div class="col-md-6">
                <div class="h-100 p-5 text-white bg-dark rounded-3">
                    <h2>About Us</h2>
                    <c:if test="${organization.description.length() == 0}">
                        <p>Description is not available!</p>
                    </c:if>
                    <c:if test="${organization.description.length() > 0}">
                        <p>${organization.description}</p>
                    </c:if>
                </div>
            </div>
            <div class="col-md-6">
                <div class="h-100 p-5 bg-light border rounded-3">
                    <h2>How popular we are?</h2>
                    <p>
                        Our organization's rating is ${organization.rating} out of 49 reviews!
                    </p>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="../UI/footer.jsp"/>
</body>
</html>
