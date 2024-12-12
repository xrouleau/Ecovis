<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Compte</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="isAuthenticated()">
<body>
<div class="container">
    <div class="row justify-content-center  my-5 py-5">
        <div class="col-6 px-5 color2 rounded">
            <a class="btn btn-secondary button mt-5" href="<c:url value="/nouvelles"/>">Retour</a>
            <h1 class="my-5 text-center">Compte</h1>
            <p>${sessionScope.user.username}</p>
            <p>${sessionScope.user.prenom}</p>
            <p>${sessionScope.user.nom}</p>
            <p>${sessionScope.user.email}</p>
            <c:set var="lenght" value="0"/>
            <c:forEach items="${sessionScope.user.roles}">
                <c:set var="lenght" value="${lenght + 1}"/>
            </c:forEach>
            <c:if test="${lenght == 2}">
                <p>Communication</p>
            </c:if>
            <c:if test="${lenght == 3}">
                <p>Administrateur</p>
            </c:if>
            <c:url var="URLModifierMDP" value="/modifierMDPCompte"/>
            <form:form method="post" action="${URLModifierMDP}">
                <div class="mb-3">
                    <label for="oldPassword" class="form-label">Mot de passe</label>
                    <input type="password" required class="form-control" name="oldPassword" id="oldPassword">
                </div>
                <div class="mb-3">
                    <label for="password1" class="form-label">Nouveau mot de passe</label>
                    <input type="password" required class="form-control" name="password1" id="password1">
                </div>
                <div class="mb-3">
                    <label for="password2" class="form-label">Confirmer le nouveau mot de passe</label>
                    <input type="password" required class="form-control" name="password2" id="password2">
                </div>
                <div class="container mb-5">
                    <div class="row justify-content-between">
                        <div class="col-4 p-0">
                            <button type="submit" class="btn btn-primary">Modifier</button>
                        </div>
                        <div class="col-3 p-0">
                            <a href="<c:url value="/deconnexion"/>" class="btn btn-danger float-end">Se déconnecter</a>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</sec:authorize>
</html>
