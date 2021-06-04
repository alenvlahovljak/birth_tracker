<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.83.1">

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous"
    >

    <title><c:out value="${requestScope.party.name}"/> | Party</title>

    <style>
        .background {
            background: url("${requestScope.party.thumbnail}");
        }

        .info {
            background-color: rgba(12, 12, 12, 0.6);
            color: white;

        }

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
<main>
    <div class="container py-4">
        <header class="pb-3 mb-4 border-bottom">
            <a href="/" class="d-flex align-items-center text-dark text-decoration-none">
                <span class="fs-4">Birth_TrackeR</span>
            </a>
        </header>

        <div class="p-5 mb-4 bg-light rounded-3 background">
            <div class="container-fluid py-5 info">
                <h1 class="display-5 fw-bold">${requestScope.party.name}</h1>
                <small>by ${requestScope.party.organizationAbbreviation}</small>
                <p class="col-md-8 fs-4">${requestScope.party.description}</p>
                <a class="btn btn-primary stretched-link" href="${pageContext.request.contextPath}/">Check out other
                    parties</a>
            </div>
        </div>

        <div class="row align-items-md-stretch">
            <div class="col-md-6">
                <div class="h-100 p-5 text-white bg-dark rounded-3">
                    <h2>Discount</h2>
                    <c:if test="${!requestScope.party.hasFreeSpots}">
                        <p>This event is no more available!</p>
                    </c:if>
                    <c:if test="${requestScope.party.hasFreeSpots}">
                        <p>Here you can check your profile's discount</p>
                        <p>You have 0% to apply as discount.</p>
                    </c:if>
                    <c:if test="${requestScope.party.hasFreeSpots}">
                        <button class="btn btn-outline-light" type="button">Apply coupon</button>
                    </c:if>
                </div>
            </div>
            <div class="col-md-6">
                <div class="h-100 p-5 bg-light border rounded-3">
                    <h2>Can I come?</h2>
                    <c:if test="${requestScope.party.hasFreeSpots}">
                        <p>
                            Currently it's
                            available ${requestScope.party.maxParticipants - requestScope.party.participants} spots!
                        </p>
                        <button class="btn btn-outline-secondary" type="button">Reserve</button>
                    </c:if>
                    <c:if test="${!requestScope.party.hasFreeSpots}">
                        <p>Sorry, no free spots available!</p>
                        <button disabled class="btn btn-outline-secondary" type="button">Out of stock</button>
                    </c:if>
                </div>
            </div>
        </div>

        <footer class="pt-3 mt-4 text-muted border-top">
            &copy; 2021 Birth_TrackeR
        </footer>
    </div>
</main>
</body>
</html>
