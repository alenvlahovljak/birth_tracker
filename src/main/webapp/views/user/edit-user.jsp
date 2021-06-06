<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="user" value="${requestScope.user}" scope="page"/>
<%--state--%>

<%--Links--%>
<c:url var="userListRoute" value="UserServlet">
    <c:param name="command" value="LIST"/>
</c:url>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title><c:out value="${user.username}"/> | Edit</title>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main class="container">
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light"><c:out value="${user.username}"/></h1>
                <p class="lead text-muted">Not happy with a user content?</p>
                <p>
                    <a href="${userListRoute}" class="btn btn-primary my-2">Get back to users!</a>
                </p>
            </div>
        </div>
    </section>
    <form class="row" action="UserServlet?command=UPDATE" method="post">
        <div class="col-3"></div>
        <div class="col-6">
            <input type="hidden" name="id" value="${user.id}"/>
            <div class="form-group mb-3">
                <label for="username">Username <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="username"
                       name="username"
                       value="${user.username}"
                       placeholder="JonnY"
                >
            </div>
            <div class="form-group mb-3">
                <label for="first_name">First name <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="first_name"
                       name="first_name"
                       value="${user.firstName}"
                       placeholder="John"
                >
            </div>
            <div class="form-group mb-3">
                <label for="last_name">Last name <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="last_name"
                       name="last_name"
                       value="${user.lastName}"
                       placeholder="Smith"
                >
            </div>
            <div class="form-group mb-3">
                <label for="thumbnail">Avatar URL</label>
                <input type="text"
                       class="form-control mt-2"
                       id="thumbnail"
                       name="avatar_url"
                       value="${user.avatar}"
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
