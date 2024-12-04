<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Nouvelles</title>
    <%@include file="header.jsp"%>
</head>
<body class="custom-bg-transparent">
<div class="container-fluid">
    <div class="row text-center">
        <h1 class="my-5">Enregistrer un nouvel utilisateur</h1>
    </div>
    <div class="container">
        <div class="row row-cols-1">
            <div class="container-sm col">
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
                    <form:select path="roles" cssClass="form-select" aria-label="Default select example">
                        <form:option selected="true" value="1">Utilisateur</form:option>
                        <form:option value="2">Communication</form:option>
                        <form:option value="3">Administrateur</form:option>
                    </form:select>
                    <select class="form-select" aria-label="Default select example" id="roles">
                        <option value="1" selected>Utilisateur</option>
                        <option value="2">Communication</option>
                        <option value="3">Administrateur</option>
                    </select>
                    <div class="mb-3">
                        <label for="password1" class="form-label">Mot de passe</label>
                        <input type="password" required class="form-control" name="password1" id="password1">
                    </div>
                    <div class="mb-3">
                        <label for="password2" class="form-label">Confirmer le mot de passe</label>
                        <input type="password" required class="form-control" name="password2" id="password2">
                    </div>
                    <button type="submit" class="btn btn-primary">Se connecter</button>
                </form:form>
            </div>

        </div>
    </div>
</div>