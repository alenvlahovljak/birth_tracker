<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--State--%>
<c:set var="client" value="${sessionScope.client}" scope="page"/>
<%--State--%>

<%--Links--%>
<c:url var="logInAdminRoute" value="AuthServlet">
    <c:param name="role" value="admin"/>
</c:url>
<c:url var="logInManagerRoute" value="AuthServlet">
    <c:param name="role" value="manager"/>
</c:url>
<c:url var="logInUserRoute" value="AuthServlet">
    <c:param name="role" value="user"/>
</c:url>
<c:url var="logOutRoute" value="AuthServlet">
    <c:param name="role" value="destroy"/>
</c:url>

<c:url var="addManager" value="ManagerServlet">
    <c:param name="command" value="ADD"/>
</c:url>
<c:url var="managerListRoute" value="ManagerServlet">
    <c:param name="command" value="LIST"/>
</c:url>

<c:url var="addOrganization" value="OrganizationServlet">
    <c:param name="command" value="ADD"/>
</c:url>
<c:url var="organizationListRoute" value="OrganizationServlet">
    <c:param name="command" value="LIST"/>
</c:url>

<c:url var="addUser" value="UserServlet">
    <c:param name="command" value="ADD"/>
</c:url>
<c:url var="userListRoute" value="UserServlet">
    <c:param name="command" value="LIST"/>
</c:url>

<c:url var="addParty" value="PartyServlet">
    <c:param name="command" value="ADD"/>
    <c:param name="manager_id" value="${client.role == 2 ? client.id : 0}"/>
</c:url>
<c:url var="partyListRoute" value="PartyServlet">
    <c:param name="command" value="LIST"/>
</c:url>
<%--Links--%>

<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">About Us</h4>
                    <p class="text-muted">We're assisting in providing the best birthday parties in town!</p>
                    <c:if test="${client.role == 1}">
                        <a href="${partyListRoute}"
                           class="btn btn-outline-secondary">Parties</a>
                        <a href="${organizationListRoute}"
                           class="btn btn-outline-secondary">Organizations</a>
                        <a href="${managerListRoute}"
                           class="btn btn-outline-secondary">Managers</a>
                        <a href="${userListRoute}"
                           class="btn btn-outline-secondary">Users</a>
                    </c:if>
                    <c:if test="${client.role != 1 && client != null}">
                        <a href="${partyListRoute}"
                           class="btn btn-outline-secondary">Parties</a>
                        <a href="${organizationListRoute}"
                           class="btn btn-outline-secondary">Organizations</a>
                    </c:if>
                    <c:if test="${client == null}">
                        <a href="${partyListRoute}"
                           class="btn btn-outline-secondary">Parties</a>
                    </c:if>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <c:if test="${client.role == 1}">
                        <h4 class="text-white mb-4">${client.username} | Admin</h4>
                    </c:if>
                    <c:if test="${client.role == 2}">
                        <h4 class="text-white mb-4">${client.firstName} ${client.lastName} |
                            Manager</h4>
                    </c:if>
                    <c:if test="${client.role == 3}">
                        <h4 class="text-white mb-4">${client.firstName} ${client.lastName} |
                            User</h4>
                    </c:if>
                    <ul class="list-unstyled">
                        <c:if test="${client.role == 1}">
                            <li><a href="${addManager}" class="text-white">Add Manager</a></li>
                            <li><a href="${addUser}" class="text-white">Add User</a></li>
                            <li><a href="${addOrganization}" class="text-white">Add Organization</a></li>
                            <li><a href="${addParty}" class="text-white">Add Party</a></li>
                        </c:if>
                        <c:if test="${client.role == 2}">
                            <li><a href="${addOrganization}" class="text-white">Add Organization</a></li>
                            <li><a href="${addParty}" class="text-white">Add Party</a></li>
                        </c:if>
                        <c:if test="${client != null}">
                            <li><a class="text-white" href="${logOutRoute}">Login out</a></li>
                        </c:if>
                        <c:if test="${client == null}">
                            <div class="d-grid gap-2">
                                <a class="btn btn-primary" href="${logInAdminRoute}">Login as admin</a>
                                <a class="btn btn-primary" href="${logInManagerRoute}">Login as manager</a>
                                <a class="btn btn-primary" href="${logInUserRoute}">Login as user</a>
                            </div>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container">
            <a href="#" class="navbar-brand d-flex align-items-center">
                <strong>Birth_TrackeR</strong>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader"
                    aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>