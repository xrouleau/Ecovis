<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Modifier Utilisateur</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="hasRole('ADMIN')">
<body>
<div class="container">
    <div class="row justify-content-center  my-5 py-5">
        <div class="col-6 px-5 color2 rounded">
            <a class="btn btn-secondary button mt-5" href="<c:url value="/utilisateurs"/>">Retour</a>
            <h1 class="my-5 text-center">Modifier l'utilisateur</h1>
            <p>${utilisateur.username}</p>
            <p>${utilisateur.prenom}</p>
            <p>${utilisateur.nom}</p>
            <p>${utilisateur.email}</p>
            <c:url var="URLModifierMDP" value="/modifierMDP"/>
            <form:form method="post" action="${URLModifierMDP}">
                <div class="mb-3">
                    <label for="password1" class="form-label">Changer le mot de passe</label>
                    <input type="password" required class="form-control" name="password1" id="password1">
                </div>
                <div class="mb-3">
                    <label for="password2" class="form-label">Confirmer le nouveau mot de passe</label>
                    <input type="password" required class="form-control" name="password2" id="password2">
                </div>
                <div class="container mb-3">
                    <div class="row justify-content-between">
                        <div class="col-4 p-0">
                            <button type="submit" class="btn btn-primary">Modifier</button>
                        </div>
                    </div>
                </div>
            </form:form>
            <c:set var="lenght" value="0"/>
            <c:forEach items="${utilisateur.roles}">
                <c:set var="lenght" value="${lenght + 1}"/>
            </c:forEach>
            <c:url var="URLModifierRole" value="/modifierRole"/>
            <form:form method="post" action="${URLModifierRole}">
                <select name="role" class="form-select mb-3" aria-label="Default select example" id="role">
                    <option <c:if test="${lenght == 1}"> selected </c:if> value="3">Utilisateur</option>
                    <option <c:if test="${lenght == 2}"> selected </c:if> value="2">Communication</option>
                    <option <c:if test="${lenght == 3}"> selected </c:if> value="1">Administrateur</option>
                </select>
                <div class="col p-0 mb-2">
                    <button type="submit" class="btn btn-primary">Modifier le role</button>
                </div>
            </form:form>
            <a class="btn btn-danger button mb-5 float-end" href="<c:url value="/supprimerUtilisateur/${utilisateur.id}"/>">Supprimer</a>
        </div>
    </div>
</div>
</body></sec:authorize>
</html>