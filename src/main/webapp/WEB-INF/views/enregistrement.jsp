<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Enregistrement</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="hasRole('ADMIN')">
    <body class="custom-bg-transparent">
        <div class="container-fluid">
            <div class="row text-center">
                <h1 class="my-5">Enregistrer un nouvel utilisateur</h1>
            </div>
            <div class="container">
                <div class="row row-cols-1">
                    <div class="container-sm col">
                        <div class="col-6">
                            <a class="btn btn-secondary button mb-4" href="<c:url value="/utilisateurs"/>">Retour</a>
                        </div>
                        <c:set var="postUrl" value="/enregistrer" />
                        <form:form method="post" action="${postUrl}" modelAttribute="dto">
                            <div class="mb-3">
                                <form:label path="username" cssClass="form-label">Nom d'utilisateur</form:label>
                                <form:input path="username" type="text" cssClass="form-control"/>
                                <form:errors path="username" cssClass="text-body-danger" />
                            </div>
                            <div class="mb-3">
                                <form:label path="prenom" cssClass="form-label">Prénom</form:label>
                                <form:input path="prenom" type="text" cssClass="form-control"/>
                                <form:errors path="prenom" cssClass="text-body-danger" />
                            </div>
                            <div class="mb-3">
                                <form:label path="nom" cssClass="form-label">Nom</form:label>
                                <form:input path="nom" type="text" cssClass="form-control"/>
                                <form:errors path="nom" cssClass="text-body-danger" />
                            </div>
                            <div class="mb-3">
                                <form:label path="email" cssClass="form-label">Courriel</form:label>
                                <form:input path="email" type="email" cssClass="form-control"/>
                                <form:errors path="email" cssClass="text-body-danger" />
                            </div>
                            <label for="role" >Role</label>
                            <select name="role" class="form-select mb-3" aria-label="Default select example" id="role">
                                <option selected value="3">Utilisateur</option>
                                <option value="2">Communication</option>
                                <option value="1">Administrateur</option>
                            </select>
                            <div class="mb-3">
                                <form:label path="password1" cssClass="form-label">Mot de passe</form:label>
                                <form:input path="password1" type="password" cssClass="form-control"/>
                                <form:errors path="password1" cssClass="text-body-danger" />
                            </div>
                            <div class="mb-3">
                                <form:label path="password2" cssClass="form-label">Confirmer le mot de passe</form:label>
                                <form:input path="password2" type="password" cssClass="form-control"/>
                                <form:errors path="password2" cssClass="text-body-danger" />
                            </div>
                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</sec:authorize>
</html>