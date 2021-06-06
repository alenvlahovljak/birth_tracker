<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--State--%>
<c:set var="client" value="${sessionScope.client}" scope="page"/>
<c:set var="party" value="${requestScope.party}" scope="page"/>
<c:set var="discount" value="${requestScope.discount < 30 ? requestScope.discount : 30}" scope="page"/>
<%--State--%>

<%--Links--%>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title><c:out value="${party.name}"/> | Party</title>
    <%--Custom CSS--%>
    <link href="../../resources/style/party.css" rel="stylesheet"/>
</head>
<body>
<main>
    <jsp:include page="../UI/header.jsp"/>
    <div class="container py-4">
        <c:url var="editParty" value="PartyServlet">
            <c:param name="command" value="EDIT"/>
            <c:param name="id" value="${party.id}"/>
        </c:url>
        <c:url var="deleteParty" value="PartyServlet">
            <c:param name="command" value="DELETE"/>
            <c:param name="id" value="${party.id}"/>
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
                <h1 class="display-5 fw-bold">${party.name}</h1>
                <small>by ${party.organizationAbbreviation}</small>
                <p class="col-md-8 fs-4">${party.description}</p>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/">Check out other
                    parties</a>
            </div>
        </div>

        <div class="row align-items-md-stretch">
            <div class="col-md-6">
                <div class="h-100 p-5 text-white bg-dark rounded-3">
                    <h2>Discount</h2>
                    <c:if test="${!party.hasFreeSpots || discount == 0}">
                        <p>Discount store is currently not available!</p>
                    </c:if>
                    <c:if test="${party.hasFreeSpots && discount > 0}">
                        <p>Here you can check your profile's discount</p>
                        <p>You have ${discount}% to apply as discount.</p>
                        <fmt:formatNumber var="discountPrice"
                                          value="${party.price - (party.price * discount) / 100}"
                                          maxFractionDigits="2"/>
                        <div class="d-grid gap-4 d-flex">
                            <img src="https://cdn.iconscout.com/icon/free/png-256/gift-card-1817226-1538096.png"
                                 alt="Coupon icon" height="120px"/>
                            <p class="align-self-center h2">Price: <s
                                    class="text-muted"><small>$</small>${party.price}
                            </s>
                                <strong><small>$</small>${discountPrice}</strong>
                            </p>
                        </div>
                    </c:if>
                    <c:if test="${party.hasFreeSpots && !party.hasDiscount && discount > 0}">
                        <form action="OrderServlet?command=DISCOUNT" method="post">
                            <input type="hidden" name="id" value="${party.orderId}"/>
                            <input type="hidden" name="party_id" value="${party.id}"/>
                            <input type="hidden" name="discount" value="true"/>
                            <button class="btn btn-outline-light" type="submit">Apply coupon</button>
                        </form>
                    </c:if>
                    <c:if test="${party.hasFreeSpots && party.hasDiscount}">
                        <button disabled class="btn btn-secondary" type="submit">Coupon applied</button>
                    </c:if>
                </div>
            </div>
            <div class="col-md-6">
                <div class="h-100 p-5 bg-light border rounded-3">
                    <h2>Can I come?</h2>
                    <c:if test="${party.hasFreeSpots}">
                        <p>
                            Currently it's
                            available ${party.maxParticipants - party.participants} spots!
                        </p>
                        <c:url var="createOrder" value="OrderServlet">
                            <c:param name="command" value="CREATE"/>
                            <c:param name="user_id" value="${client.id}"/>
                            <c:param name="party_id" value="${party.id}"/>
                        </c:url>
                        <c:url var="deleteOrder" value="OrderServlet">
                            <c:param name="command" value="DELETE"/>
                            <c:param name="user_id" value="3"/>
                            <c:param name="party_id" value="${party.id}"/>
                            <c:param name="id" value="${party.orderId}"/>
                        </c:url>
                        <c:if test="${party.orderId == 0}">
                            <form action="${createOrder}" method="post">
                                <button class="btn btn-outline-success">Reserve</button>
                            </form>
                        </c:if>
                        <c:if test="${party.orderId != 0}">
                            <a href="${deleteOrder}" class="btn btn-outline-danger">Cancel reservation</a>
                            <form class="form-group mb-3 mt-5" action="OrderServlet?command=RATING" method="post">
                                <input type="hidden" name="id" value="${party.orderId}"/>
                                <input type="hidden" name="party_id" value="${party.id}"/>
                                <label for="rating">Rate event:</label>
                                <input type="range"
                                       class="form-range mt-2"
                                       id="rating"
                                       name="rating"
                                       min="1"
                                       value="${party.rating}"
                                       max="5"
                                       onInput="document.getElementById('range').innerText = document.getElementById('rating').value; document.getElementById('rate-btn').disabled = false"
                                >
                                <c:if test="${party.rating == 0}">
                                    <span id="range">Not yet rated!</span><br/>
                                </c:if>
                                <c:if test="${party.rating != 0}">
                                    <span id="range">Your rating is ${party.rating}</span>
                                </c:if>
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button disabled id="rate-btn" type="submit" class="btn btn-outline-primary mt-3">
                                        Rate!
                                    </button>
                                </div>
                            </form>
                        </c:if>
                    </c:if>
                    <c:if test="${!party.hasFreeSpots}">
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
