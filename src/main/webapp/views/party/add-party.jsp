<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="organizations" value="${requestScope.organizations}" scope="page"/>
<%--State--%>

<%--Links--%>
<c:url var="partyListRoute" value="PartyServlet">
    <c:param name="command" value="LIST"/>
</c:url>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title>Create a dream birthday!</title>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main class="container">
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Add new party</h1>
                <p class="lead text-muted">You can add a new top notch party easily.</p>
                <p>
                    <a href="${partyListRoute}" class="btn btn-primary my-2">Get back to parties!</a>
                </p>
            </div>
        </div>
    </section>
    <form class="row" action="PartyServlet" method="post">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="form-group mb-3">
                <label for="name">Party name <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="name"
                       name="name"
                       placeholder="Mary's magical night"
                >
            </div>
            <div class="form-group mb-3">
                <label for="organization">Organization <small class="form-text text-muted">(required)</small></label>
                <select id="organization" name="organization_id" class="form-select mt-2">
                    <c:forEach var="organization" items="${organizations}">
                        <option value="${organization.id}">${organization.name} (abbr. ${organization.abbreviation})
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
                ></textarea>
            </div>
            <div class="form-group mb-3">
                <label for="thumbnail">Thumbnail URL</label>
                <input type="text"
                       class="form-control mt-2"
                       id="thumbnail"
                       name="thumbnail_url"
                       placeholder="https://www.example.com"
                >
            </div>
            <div class="form-group mb-3">
                <label for="price">Price</label>
                <div class="input-group mb-3 mt-2">
                    <span class="input-group-text">$</span>
                    <input type="number" id="price" min="1" max="100" step="1" name="price" class="form-control">
                    <span class="input-group-text">.00</span>
                </div>
            </div>
            <div class="form-group mb-3">
                <label for="maxParticipants">Maximum number of participants</label>
                <input type="range"
                       class="form-range mt-2"
                       id="maxParticipants"
                       name="max_participants"
                       min="10"
                       value="10"
                       max="100"
                       onInput="document.getElementById('range').innerText = document.getElementById('maxParticipants').value"
                >
                <span id="range">10</span>
            </div>
        </div>
        <div class="col-3"></div>
        <div class="col-3"></div>
        <div class="col-9">
            <button type="submit" class="btn btn-primary mt-3">Submit</button>
        </div>
    </form>

</main>
<jsp:include page="../UI/footer.jsp"/>
</body>
</html>
