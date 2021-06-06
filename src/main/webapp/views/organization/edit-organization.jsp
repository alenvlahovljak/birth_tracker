<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="organization" value="${requestScope.organization}" scope="page"/>
<%--State--%>

<%--Links--%>
<c:url var="organizationListRoute" value="OrganizationServlet">
    <c:param name="command" value="LIST"/>
    <c:param name="manager_id" value="${organization.managerId}"/>
</c:url>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title><c:out value="${organization.name}"/> | Edit</title>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main class="container">
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light"><c:out value="${organization.name}"/></h1>
                <p class="lead text-muted">Not happy with a organization content?</p>
                <p>
                    <a href="${organizationListRoute}" class="btn btn-primary my-2">Get back to parties!</a>
                </p>
            </div>
        </div>
    </section>
    <form class="row" action="OrganizationServlet?command=UPDATE" method="post">
        <div class="col-3"></div>
        <div class="col-6">
            <input type="hidden" name="id" value="${organization.id}"/>
            <div class="form-group mb-3">
                <label for="name">Organization name <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="name"
                       name="name"
                       value="${organization.name}"
                       placeholder="Awsome organization"
                >
            </div>
            <div class="form-group mb-3">
                <label for="abbreviation">Abbreviation <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       maxlength="10"
                       class="form-control mt-2"
                       id="abbreviation"
                       name="abbreviation"
                       value="${organization.abbreviation}"
                       placeholder="AWsome"
                >
            </div>
            <div class="form-group mb-3">
                <label for="description">Description</label>
                <textarea class="form-control mt-2"
                          maxlength="500"
                          rows="5"
                          id="description"
                          name="description"
                          placeholder="Tell something about your organization..."
                >${organization.description}</textarea>
            </div>
            <div class="form-group mb-3">
                <label for="thumbnail">Thumbnail URL</label>
                <input type="text"
                       class="form-control mt-2"
                       id="thumbnail"
                       name="thumbnail_url"
                       value="${organization.thumbnail}"
                       placeholder="https://www.example.com"
                >
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
