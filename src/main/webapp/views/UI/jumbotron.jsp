<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="py-5 text-center container">
    <div class="row py-lg-5">
        <div class="col-lg-6 col-md-8 mx-auto">
            <h1 class="fw-light"><%= request.getParameter("title")%></h1>
            <p class="lead text-muted"><%= request.getParameter("info")%></p>
            <p>
                <a href="<%= request.getParameter("action")%>" class="btn btn-primary my-2"><%= request.getParameter("link")%></a>
            </p>
        </div>
    </div>
</section>