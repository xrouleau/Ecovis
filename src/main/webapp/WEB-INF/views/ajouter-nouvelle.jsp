<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Compte</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="hasRole('COMM')">
    <body>
    <div class="container">
        <div class="row justify-content-center  my-5 py-5">
            <div class="col-6 px-5 rounded">
                <a class="btn btn-secondary button mt-5" href="<c:url value="/nouvellesUtilisateur"/>">Retour</a>
                <h1 class="my-5 text-center">Créer une nouvelle</h1>
                <c:set var="postUrl" value="/creerNouvelle" />
                <form:form method="post" action="${postUrl}">
                    <div class="mb-3">
                        <label for="titre" class="form-label">Titre</label>
                        <input type="text" required class="form-control" name="titre" id="titre">
                    </div>
                    <button type="submit" class="btn color1">Créer</button>
                </form:form>
            </div>
        </div>
    </div>
    </body>
</sec:authorize>
</html>
