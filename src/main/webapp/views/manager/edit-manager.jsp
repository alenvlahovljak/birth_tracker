<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="manager" value="${requestScope.manager}" scope="page"/>
<%--state--%>

<%--Links--%>
<c:url var="managerListRoute" value="UserServlet">
    <c:param name="command" value="LIST"/>
</c:url>
<%--Links--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title><c:out value="${manager.username}"/> | Edit</title>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main class="container">
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light"><c:out value="${manager.username}"/></h1>
                <p class="lead text-muted">Not happy with a manager content?</p>
                <p>
                    <a href="${managerListRoute}" class="btn btn-primary my-2">Get back to parties!</a>
                </p>
            </div>
        </div>
    </section>
    <form class="row" action="ManagerServlet?command=UPDATE" method="post">
        <div class="col-3"></div>
        <div class="col-6">
            <input type="hidden" name="id" value="${manager.id}"/>
            <div class="form-group mb-3">
                <label for="username">Username <small class="form-text text-muted">(required)</small></label>
                <input type="text"
                       required
                       class="form-control mt-2"
                       id="username"
                       name="username"
                       value="${manager.username}"
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
                       value="${manager.firstName}"
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
                       value="${manager.lastName}"
                       placeholder="Smith"
                >
            </div>
            <div class="form-group mb-3">
                <label for="thumbnail">Avatar URL</label>
                <input type="text"
                       class="form-control mt-2"
                       id="thumbnail"
                       name="avatar_url"
                       value="${manager.avatar}"
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
