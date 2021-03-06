<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--State--%>
<c:set var="client" value="${sessionScope.client}" scope="page"/>
<c:set var="party" value="${requestScope.party}" scope="page"/>
<c:set var="organizations" value="${requestScope.organizations}" scope="page"/>
<%--State--%>

<%--Functions--%>
<fmt:formatNumber var="price" value="${party.price}" maxFractionDigits="0"/>
<%--Functions--%>

<%--Links--%>
<c:url var="partyListRoute" value="PartyServlet">
    <c:param name="command" value="LIST"/>
</c:url>

<c:url var="updatePartyRoute" value="PartyServlet">
    <c:param name="command" value="UPDATE"/>
    <c:param name="manager_id" value="${client.role == 2 ? client.id : 0}}"/>
</c:url>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title><c:out value="${party.name}"/> | Edit</title>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main class="container">
    <jsp:include page="../UI/jumbotron.jsp">
        <jsp:param name="title" value="${party.name}"/>
        <jsp:param name="info" value="Not happy with a party content?"/>
        <jsp:param name="action" value="${partyListRoute}"/>
        <jsp:param name="link" value="Get back to parties!"/>
    </jsp:include>
    <form class="row"
          action="${updatePartyRoute}"
          method="post">
        <div class="col-3"></div>
        <div class="col-6">
            <input type="hidden" name="id" value="${party.id}"/>
            <div class="form-group mb-3">
                <label for="name">Party name <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="name"
                       name="name"
                       value="${party.name}"
                       placeholder="Mary's magical night"
                >
            </div>
            <div class="form-group mb-3">
                <label for="organization">Organization <small class="form-text text-muted">(required)</small></label>
                <select id="organization" name="organization_id" class="form-select mt-2">
                    <c:forEach var="organization" items="${organizations}">
                        <option ${organization.id == party.organizationId ? 'selected="selected"' : ''}
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
                >${party.description}</textarea>
            </div>
            <div class="form-group mb-3">
                <label for="thumbnail">Thumbnail URL</label>
                <input type="text"
                       class="form-control mt-2"
                       id="thumbnail"
                       name="thumbnail_url"
                       value="${party.thumbnail}"
                       placeholder="https://www.example.com"
                >
            </div>
            <div class="form-group mb-3">
                <label for="price">Price</label>
                <div class="input-group mb-3 mt-2">
                    <span class="input-group-text">$</span>
                    <input type="number" id="price" min="1" max="100" value="${price}"
                           step="1"
                           name="price" class="form-control">
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
                       value="${party.maxParticipants}"
                       max="100"
                       onInput="document.getElementById('range').innerText = document.getElementById('maxParticipants').value"
                >
                <span id="range">${party.maxParticipants}</span>
            </div>
        </div>
        <div class="col-3"></div>
        <div class="col-3"></div>
        <div class="col-9">
            <button type="submit" class="btn btn-primary mt-3">Save</button>
        </div>
    </form>
</main>
<jsp:include page="../UI/footer.jsp"/>
</body>
</html>