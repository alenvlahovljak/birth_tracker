<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--Links--%>
<c:url var="userListRoute" value="UserServlet">
    <c:param name="command" value="LIST"/>
</c:url>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title>Create new user!</title>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main class="container">
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Add new user</h1>
                <p class="lead text-muted">Add new team member.</p>
                <p>
                    <a href="${userListRoute}" class="btn btn-primary my-2">Get back to managers</a>
                </p>
            </div>
        </div>
    </section>
    <form class="row" action="UserServlet" method="post">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="form-group mb-3">
                <label for="username">Username <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="username"
                       name="username"
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
                       placeholder="Smith"
                >
            </div>
            <div class="form-group mb-3">
                <label for="thumbnail">Avatar URL</label>
                <input type="text"
                       class="form-control mt-2"
                       id="thumbnail"
                       name="avatar_url"
                       placeholder="https://www.example.com"
                >
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
