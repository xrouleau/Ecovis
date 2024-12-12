<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>${nouvelle.titre}</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="hasRole('COMM')">
<body>
<div class="container">
    <div class="row justify-content-center  my-3 py-5">
        <div class="col px-5 color2 rounded">
            <a class="btn btn-secondary button mt-5" href="<c:url value="/nouvellesUtilisateur"/>">Retour</a>
            <h1 class="my-5 text-center">Modifier la nouvelle</h1>
            <c:url var="URLModifierNouvelle" value="/modifierNouvelle/${nouvelle.id}"/>
            <form:form method="post" modelAttribute="nouvelle" action="${URLModifierNouvelle}">
                <div class="mb-3">
                    <form:label path="titre" cssClass="form-label">Titre</form:label>
                    <form:input path="titre" type="text" cssClass="form-control" value="${nouvelle.titre}"/>
                </div>
                <div class="mb-3">
                    <form:label path="contenu" cssClass="form-label">Contenu</form:label>
                    <form:textarea path="contenu" value="${nouvelle.contenu}" type="text" cssClass="form-control contenu"/>
                </div>
                <button type="submit" class="btn btn-primary">Sauvegarder</button>
            </form:form>
            <p>Auteurs: </p>
            <div class="container">
                <div class="row row-cols-5">
                    <c:forEach var="auteur" items="${nouvelle.utilisateurs}">
                        <div class="col ps-0">
                            <div class="card my-2">
                                <div class="card-body">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col p-0">
                                                <h5 class="mt-2 p-auto">${auteur.username}</h5>
                                            </div>
                                            <div class="col p-0">
                                                <a href="<c:url value="/retirerUtilisateurNouvelle/${auteur.id}/${nouvelle.id}"/>" class="btn btn-danger float-end" data-bs-theme="dark">Retirer</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <p>Ajouter un auteur: </p>
            <c:url var="URLAjouterUtilisateur" value="/ajouterUtilisateurNouvelle/${nouvelle.id}"/>
            <form:form method="post" action="${URLAjouterUtilisateur}">
                <div class="mb-2">
                    <label for="username" class="form-label">Nom d'utilisateur</label>
                    <input type="text" required class="form-control" name="username" id="username">
                </div>
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </form:form>
            <a class="btn btn-danger button mb-5 float-end" href="<c:url value="/supprimerNouvelle/${nouvelle.id}"/>">Supprimer</a>
        </div>
    </div>
</div>
</body></sec:authorize>
</html>