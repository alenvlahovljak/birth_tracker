<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@page import="com.bt.servlets.PartyServlet" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Stop looking for the perfect birthday. You are in the right place!">
    <meta name="author" content="Alen Vlahovljak">

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

    <title>Check out our organizations!</title>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


</head>
<body>

<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">About</h4>
                    <p class="text-muted">Add some information about the album below, the author, or any other
                        background context. Make it a few sentences long so folks can pick up some informative tidbits.
                        Then, link them off to some social networking sites or contact information.</p>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <h4 class="text-white mb-4">My company name | User</h4>
                    <button type="button" class="btn btn-outline-secondary">Profile</button>
                    <button type="button" class="btn btn-outline-secondary">Company</button>
                    <c:url var="addParty" value="PartyServlet">
                        <c:param name="command" value="ADD"/>
                    </c:url>
                    <c:url var="addOrganization" value="OrganizationServlet">
                        <c:param name="command" value="ADD"/>
                    </c:url>
                    <ul class="list-unstyled">
                        <li><a href="${addParty}" class="text-white">Add Party</a></li>
                        <li><a href="${addOrganization}" class="text-white">Add Organization</a></li>
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
                <h1 class="fw-light">Here is a list of organizations</h1>
                <p class="lead text-muted">Satisfied with a party?
                    Give it a rating then.</p>
                <p>
                    <a href="#organizations" class="btn btn-primary my-2">Show me the organizations!</a>
                </p>
            </div>
        </div>
    </section>

    <div id="organizations" class="album py-5 bg-light">
        <div class="container">
            <c:if test="${requestScope.organizations.size() == 0}">
                <h6 class="text-center display-6">No organizations available at the moment!</h6>
            </c:if>
            <c:if test="${requestScope.organizations.size() != 0}">
                <div class="row mb-2">
                    <c:forEach var="organization" items="${requestScope.organizations}">
                        <c:url var="read" value="OrganizationServlet">
                            <c:param name="command" value="LOAD"/>
                            <c:param name="id" value="${organization.id}"/>
                        </c:url>
                        <c:url var="edit" value="OrganizationServlet">
                            <c:param name="command" value="EDIT"/>
                            <c:param name="id" value="${organization.id}"/>
                        </c:url>
                        <c:url var="delete" value="OrganizationServlet">
                            <c:param name="command" value="DELETE"/>
                            <c:param name="id" value="${organization.id}"/>
                        </c:url>
                        <div class="col-md-6">
                            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                                <div class="col p-4 d-flex flex-column position-static">
                                    <h3 class="mb-0">${organization.name}</h3>
                                    <div class="mb-1 text-muted">${organization.abbreviation}</div>
                                    <p class="card-text mb-auto">${organization.description}</p>
                                    <div class="btn-group">
                                        <a class="btn btn-sm btn-outline-secondary" href="${read}">View</a>
                                        <a class="btn btn-sm btn-outline-secondary" href="${edit}">Edit</a>
                                        <a class="btn btn-sm btn-outline-danger" href="${delete}">Delete</a>
                                    </div>
                                </div>
                                <div class="col-auto d-none d-lg-block">
                                    <c:if test="${organization.thumbnail.length() > 0}">
                                        <img src="${organization.thumbnail}" class="bd-placeholder-img"
                                             alt="${organization.name}'s thumbnail" width="200" height="250"/>
                                    </c:if>
                                    <c:if test="${organization.thumbnail.length() == 0}">
                                        <img src="https://sportingwineclub.com/wp-content/themes/claue/assets/images/placeholder.png" class="bd-placeholder-img"
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

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
        <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a
                href="../getting-started/introduction/">getting started guide</a>.</p>
    </div>
</footer>
</body>
</html>
