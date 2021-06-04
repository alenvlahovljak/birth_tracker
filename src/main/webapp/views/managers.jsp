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

    <title>List of all managers | Birth_TrackeR</title>

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
                    <c:url var="addManager" value="ManagerServlet">
                        <c:param name="command" value="ADD"/>
                    </c:url>
                    <ul class="list-unstyled">
                        <li><a href="${addParty}" class="text-white">Add Party</a></li>
                        <li><a href="${addOrganization}" class="text-white">Add Organization</a></li>
                        <li><a href="${addManager}" class="text-white">Add Manager</a></li>
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

<section class="py-5 text-center container">
    <div class="row py-lg-5">
        <div class="col-lg-6 col-md-8 mx-auto">
            <h1 class="fw-light">List of all managers</h1>
            </p>
        </div>
    </div>
</section>

<main>
    <div class="album py-5 bg-light">
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">First name</th>
                    <th scope="col">Last name</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="manager" items="${requestScope.managers}">
                    <c:url var="editManager" value="ManagerServlet">
                        <c:param name="command" value="UPDATE"/>
                        <c:param name="id" value="${manager.id}"/>
                    </c:url>
                    <c:url var="deleteManager" value="ManagerServlet">
                        <c:param name="command" value="DELETE"/>
                        <c:param name="id" value="${manager.id}"/>
                    </c:url>
                    <tr>
                        <th scope="row">${manager.id}</th>
                        <td>${manager.username}</td>
                        <td>${manager.firstName}</td>
                        <td>${manager.lastName}</td>
                        <td>
                            <a href="${editManager}" class="btn btn-outline-warning">Edit</a>
                            <a href="${deleteManager}" class="btn btn-outline-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
