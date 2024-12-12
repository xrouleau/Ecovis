<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>${nouvelle.titre}</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="isAuthenticated()">
<body>
<div class="container">
    <div class="row justify-content-center  my-3 py-5">
        <div class="col px-5 color2 rounded">
            <a class="btn btn-secondary button mt-5" href="<c:url value="/nouvelles"/>">Retour</a>
            <h1 class="mb-4 mt-5 text-center">${nouvelle.titre}</h1>
            <p class="mb-5">${nouvelle.contenu}</p>
            <p class="mb-1">Auteurs:</p>
            <c:forEach var="auteur" items="${nouvelle.utilisateurs}">
                <p class="mb-1">${auteur.prenom} ${auteur.nom}</p>
            </c:forEach>
            <p class="float-end date">${nouvelle.datePublication.toLocalDateTime().getDayOfMonth()}/${nouvelle.datePublication.toLocalDateTime().getMonthValue()}/${nouvelle.datePublication.toLocalDateTime().getYear()}</p>
        </div>
    </div>
</div>
</body></sec:authorize>
</html>