<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

        <c:url var="editParty" value="PartyServlet">
            <c:param name="command" value="EDIT"/>
            <c:param name="id" value="${requestScope.party.id}"/>
        </c:url>
        <c:url var="deleteParty" value="PartyServlet">
            <c:param name="command" value="DELETE"/>
            <c:param name="id" value="${requestScope.party.id}"/>
        </c:url>

        <%--                            To DO only list of organization where this manager is--%>
        <%--                            To DO when admin he can add any org--%>
<%--        TO DO trim white space on all fileds--%>

        <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-5">
            <a class="btn btn-warning me-md-2" href="${editParty}">Edit</a>
            <a class="btn btn-danger" href="${deleteParty}">Delete</a>
        </div>

        <div class="p-5 mb-4 bg-light rounded-3 background">
            <div class="container-fluid py-5 info">
                <h1 class="display-5 fw-bold">${requestScope.party.name}</h1>
                <small>by ${requestScope.party.organizationAbbreviation}</small>
                <p class="col-md-8 fs-4">${requestScope.party.description}</p>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/">Check out other
                    parties</a>
            </div>
        </div>

        <div class="row align-items-md-stretch">
            <div class="col-md-6">
                <div class="h-100 p-5 text-white bg-dark rounded-3">
                    <c:set var="discount" scope="page"
                           value="${requestScope.discount < 30 ? requestScope.discount : 30}"/>

                    <h2>Discount</h2>
                    <c:if test="${!requestScope.party.hasFreeSpots || discount == 0}">
                        <p>Discount store is currently not available!</p>
                    </c:if>
                    <c:if test="${requestScope.party.hasFreeSpots && discount > 0}">
                        <p>Here you can check your profile's discount</p>
                        <p>You have ${discount}% to apply as discount.</p>
                        <fmt:formatNumber var="discountPrice"
                                          value="${requestScope.party.price - (requestScope.party.price * discount) / 100}"
                                          maxFractionDigits="2"/>
                        <div class="d-grid gap-4 d-flex">
                            <img src="https://cdn.iconscout.com/icon/free/png-256/gift-card-1817226-1538096.png"
                                 alt="Coupon icon" height="120px"/>
                            <p class="align-self-center h2">Price: <s
                                    class="text-muted"><small>$</small>${requestScope.party.price}
                            </s>
                                <strong><small>$</small>${discountPrice}</strong>
                            </p>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.party.hasFreeSpots && !requestScope.party.hasDiscount && discount > 0}">
                        <form action="OrderServlet?command=DISCOUNT" method="post">
                            <input type="hidden" name="id" value="${requestScope.party.orderId}"/>
                            <input type="hidden" name="party_id" value="${requestScope.party.id}"/>
                            <input type="hidden" name="discount" value="true"/>
                            <button class="btn btn-outline-light" type="submit">Apply coupon</button>
                        </form>
                    </c:if>
                    <c:if test="${requestScope.party.hasFreeSpots && requestScope.party.hasDiscount}">
                        <button disabled class="btn btn-secondary" type="submit">Coupon applied</button>
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
                        <c:url var="createOrder" value="OrderServlet">
                            <c:param name="command" value="CREATE"/>
                            <c:param name="user_id" value="3"/>
                            <c:param name="party_id" value="${requestScope.party.id}"/>
                        </c:url>
                        <c:url var="deleteOrder" value="OrderServlet">
                            <c:param name="command" value="DELETE"/>
                            <c:param name="user_id" value="3"/>
                            <c:param name="party_id" value="${requestScope.party.id}"/>
                            <c:param name="id" value="${requestScope.party.orderId}"/>
                        </c:url>
                        <c:if test="${requestScope.party.orderId == 0}">
                            <form action="${createOrder}" method="post">
                                <button class="btn btn-outline-success">Reserve</button>
                            </form>
                        </c:if>
                        <c:if test="${requestScope.party.orderId != 0}">
                            <a href="${deleteOrder}" class="btn btn-outline-danger">Cancel reservation</a>
                            <form class="form-group mb-3 mt-5" action="OrderServlet?command=RATING" method="post">
                                <input type="hidden" name="id" value="${requestScope.party.orderId}"/>
                                <input type="hidden" name="party_id" value="${requestScope.party.id}"/>
                                <label for="rating">Rate event:</label>
                                <input type="range"
                                       class="form-range mt-2"
                                       id="rating"
                                       name="rating"
                                       min="1"
                                       value="${requestScope.party.rating}"
                                       max="5"
                                       onInput="document.getElementById('range').innerText = document.getElementById('rating').value; document.getElementById('rate-btn').disabled = false"
                                >
                                <c:if test="${requestScope.party.rating == 0}">
                                    <span id="range">Not yet rated!</span><br/>
                                </c:if>
                                <c:if test="${requestScope.party.rating != 0}">
                                    <span id="range">Your rating is ${requestScope.party.rating}</span>
                                </c:if>
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button disabled id="rate-btn" type="submit" class="btn btn-outline-primary mt-3">
                                        Rate!
                                    </button>
                                </div>
                            </form>
                        </c:if>
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
