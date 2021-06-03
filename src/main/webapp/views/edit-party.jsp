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

    <title><c:out value="${requestScope.party.name}"/> | Edit</title>

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
                    <h4 class="text-white">Contact</h4>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-white">Follow on Twitter</a></li>
                        <li><a href="#" class="text-white">Like on Facebook</a></li>
                        <li><a href="#" class="text-white">Email me</a></li>
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
<main class="container">
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light"><c:out value="${requestScope.party.name}"/></h1>
                <p class="lead text-muted">Not happy with a paty content?</p>
                <p>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary my-2">Get back to parties!</a>
                </p>
            </div>
        </div>
    </section>
    <form class="row" action="PartyServlet?command=UPDATE" method="post">
        <div class="col-3"></div>
        <div class="col-6">
            <input type="hidden" name="id" value="${requestScope.party.id}"/>
            <div class="form-group mb-3">
                <label for="name">Party name <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="name"
                       name="name"
                       value="${requestScope.party.name}"
                       placeholder="Mary's magical night"
                >
            </div>
            <div class="form-group mb-3">
                <label for="organization">Organization <small class="form-text text-muted">(required)</small></label>
                <select id="organization" name="organization_id" class="form-select mt-2">
                    <c:forEach var="organization" items="${requestScope.organizations}">
                        <option ${organization.id == requestScope.party.organizationId ? 'selected="selected"' : ''}
                                value="${organization.id}">${organization.name} (abbr. ${organization.abbreviation})
                        </option>
                    </c:forEach>
                </select>

            </div>
            <div class="form-group mb-3">
                <label for="description">Description</label>
                <textarea class="form-control mt-2"
                          maxlength="500"
                          rows="5"
                          id="description"
                          name="description"
                          placeholder="Explain your top notch party..."
                >${requestScope.party.description}</textarea>
            </div>
            <div class="form-group mb-3">
                <label for="thumbnail">Thumbnail URL</label>
                <input type="text"
                       class="form-control mt-2"
                       id="thumbnail"
                       name="thumbnail_url"
                       value="${requestScope.party.thumbnail}"
                       placeholder="https://www.example.com"
                >
            </div>
            <div class="form-group mb-3">
                <label for="maxParticipants">Maximum number of participants</label>
                <input type="range"
                       class="form-range mt-2"
                       id="maxParticipants"
                       name="max_participants"
                       min="10"
                       value="${requestScope.party.maxParticipants}"
                       max="100"
                       onInput="document.getElementById('range').innerText = document.getElementById('maxParticipants').value"
                >
                <span id="range">${requestScope.party.maxParticipants}</span>
            </div>
        </div>
        <div class="col-3"></div>
        <div class="col-3"></div>
        <div class="col-9">
            <button type="submit" class="btn btn-primary mt-3">Save</button>
        </div>
    </form>

</main>

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Birth_TrackeR &copy; 2021</p>
    </div>
</footer>
</body>
</html>
