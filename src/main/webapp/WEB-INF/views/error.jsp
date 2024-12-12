<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Compte</title>
    <%@include file="header.jsp"%>
<body>
<div class="container">
    <div class="row justify-content-center  my-5 py-5">
        <div class="col-6 px-5 color2 rounded">
            <a class="btn btn-secondary button mt-5" href="<c:url value="/nouvelles"/>">Retour</a>
            <h1 class="mb-5 mt-3 text-center">Erreur</h1>
                <p class="mb-2 text-center">Oups! On dirait qu'il y a eu une erreur...</p>
                <p class="mb-5 text-center">${error}</p>
            </div>
        </div>
    </div>
    </body>
</html>
