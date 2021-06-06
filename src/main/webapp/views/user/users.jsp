<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="users" value="${requestScope.users}" scope="page"/>
<%--state--%>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="../utils/config.jsp"/>
    <title>List of all users | Birth_TrackeR</title>
</head>
<body>
<jsp:include page="../UI/header.jsp"/>
<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">List of all users</h1>
            </div>
        </div>
    </section>
    <div class="album py-5 bg-light">
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">First name</th>
                    <th scope="col">Last name</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <c:url var="editUserRoute" value="UserServlet">
                        <c:param name="command" value="EDIT"/>
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <c:url var="deleteUserRoute" value="UserServlet">
                        <c:param name="command" value="DELETE"/>
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td>${user.username}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>
                            <a href="${editUserRoute}" class="btn btn-outline-warning">Edit</a>
                            <a href="${deleteUserRoute}" class="btn btn-outline-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>
<jsp:include page="../UI/footer.jsp"/>
</body>
</html>
