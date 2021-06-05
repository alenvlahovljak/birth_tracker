<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="client" value="${sessionScope.client}" scope="page"/>
<c:set var="parties" value="${requestScope.parties}" scope="page"/>
<%--state--%>

<%--Links--%>
<c:url var="authAdminRoute" value="AuthServlet">
    <c:param name="role" value="admin"/>
</c:url>
<c:url var="authManagerRoute" value="AuthServlet">
    <c:param name="role" value="manager"/>
</c:url>
<c:url var="authUserRoute" value="AuthServlet">
    <c:param name="role" value="user"/>
</c:url>

<c:url var="addManager" value="ManagerServlet">
    <c:param name="command" value="ADD"/>
</c:url>
<c:url var="managerListRoute" value="ManagerServlet">
    <c:param name="command" value="LIST"/>
</c:url>

<c:url var="addOrganization" value="OrganizationServlet">
    <c:param name="command" value="ADD"/>
</c:url>
<c:url var="organizationListRoute" value="OrganizationServlet">
    <c:param name="command" value="LIST"/>
</c:url>

<c:url var="addUser" value="UserServlet">
    <c:param name="command" value="ADD"/>
</c:url>
<c:url var="userListRoute" value="UserServlet">
    <c:param name="command" value="LIST"/>
</c:url>

<c:url var="addParty" value="PartyServlet">
    <c:param name="command" value="ADD"/>
</c:url>
<c:url var="partyListRoute" value="PartyServlet">
    <c:param name="command" value="LIST"/>
</c:url>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Stop looking for the perfect birthday. You are in the right place!">
    <meta name="author" content="Alen Vlahovljak">

    <title>Find a perfect party!</title>

    <%-- Favicon --%>
    <link rel="apple-touch-icon" sizes="180x180" href="./resources/apple-touch-icon.png?">
    <link rel="icon" type="image/x-icon" sizes="32x32" href="./resources/favicon-32x32.png?">
    <link rel="icon" type="image/x-icon" sizes="16x16" href="./resources/favicon-16x16.png?">
    <link rel="manifest" href="./resources/site.webmanifest">

    <!-- Bootstrap core CSS and JavaScript -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous"
    >
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous">
    </script>
</head>
<body>
<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">About Us</h4>
                    <p class="text-muted">We're assisting in providing the best birthday parties in town!</p>
                    <c:if test="${client.role == 1}">
                        <a href="${partyListRoute}"
                           class="btn btn-outline-secondary">Parties</a>
                        <a href="${organizationListRoute}"
                           class="btn btn-outline-secondary">Organizations</a>
                        <a href="${managerListRoute}"
                           class="btn btn-outline-secondary">Managers</a>
                        <a href="${userListRoute}"
                           class="btn btn-outline-secondary">Users</a>
                    </c:if>
                    <c:if test="${client.role != 1 && client != null}">
                        <a href="${partyListRoute}"
                           class="btn btn-outline-secondary">Parties</a>
                        <a href="${organizationListRoute}"
                           class="btn btn-outline-secondary">Organizations</a>
                    </c:if>
                    <c:if test="${client == null}">
                        <a href="${partyListRoute}"
                           class="btn btn-outline-secondary">Parties</a>
                    </c:if>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <c:if test="${client.role == 1}">
                        <h4 class="text-white mb-4">${client.username} | Admin</h4>
                    </c:if>
                    <c:if test="${client.role == 2}">
                        <h4 class="text-white mb-4">${client.firstName} ${client.lastName} |
                            Manager</h4>
                    </c:if>
                    <c:if test="${client.role == 3}">
                        <h4 class="text-white mb-4">${client.firstName} ${client.lastName} |
                            User</h4>
                    </c:if>
                    <ul class="list-unstyled">
                        <c:if test="${client.role == 1}">
                            <li><a href="${addManager}" class="text-white">Add Manager</a></li>
                            <li><a href="${addUser}" class="text-white">Add User</a></li>
                            <li><a href="${addOrganization}" class="text-white">Add Organization</a></li>
                            <li><a href="${addParty}" class="text-white">Add Party</a></li>
                        </c:if>
                        <c:if test="${client.role == 2}">
                            <li><a href="${addOrganization}" class="text-white">Add Organization</a></li>
                            <li><a href="${addParty}" class="text-white">Add Party</a></li>
                        </c:if>
                        <c:if test="${client != null}">
                            <li><a href="${authAdminRoute}">Login out</a></li>
                        </c:if>
                        <c:if test="${client == null}">
                            <div class="d-grid gap-2">
                                <a class="btn btn-primary" href="${authAdminRoute}">Login as admin</a>
                                <a class="btn btn-primary" href="${authManagerRoute}">Login as manager</a>
                                <a class="btn btn-primary" href="${authUserRoute}">Login as user</a>
                            </div>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container">
            <a href="#" class="navbar-brand d-flex align-items-center">
                <strong>Birth_TrackeR</strong>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader"
                    aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>
<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Throwing the best parties</h1>
                <p class="lead text-muted">Are you looking for a party?
                    Stop doing that, now! You're at the right place.</p>
                <p>
                    <a href="#parties" class="btn btn-primary my-2">Show me the parties!</a>
                </p>
            </div>
        </div>
    </section>

    <div id="parties" class="album py-5 bg-light">
        <div class="container">
            <c:if test="${parties.size() == 0}">
                <h6 class="text-center display-6">No parties available at the moment!</h6>
            </c:if>
            <c:if test="${parties.size() != 0}">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <c:forEach var="party" items="${parties}">
                        <c:url var="readParty" value="PartyServlet">
                            <c:param name="command" value="LOAD"/>
                            <c:param name="id" value="${party.id}"/>
                        </c:url>
                        <c:url var="readOrganization" value="OrganizationServlet">
                            <c:param name="command" value="LOAD"/>
                            <c:param name="id" value="${party.organizationId}"/>
                        </c:url>
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
                                        <small class="text-muted">By: <a href="${readOrganization}"
                                                                         class="link-danger">${party.organizationAbbreviation}</a></small>
                                    </div>
                                    <div class="card-footer mt-md-5 d-flex justify-content-between">
                                        <div class="btn-group">
                                            <a class="btn btn-sm btn-outline-secondary" href="${readParty}">View</a>
                                        </div>
                                        <p class="text-success"><small>$</small>${party.price}</p>
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
<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Copyright&copy; 2021 | Birth_TrackeR</p>
    </div>
</footer>
</body>
</html>
