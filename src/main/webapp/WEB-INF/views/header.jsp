<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value='/css/customstyle.css'/>">
<meta name="viewport" content="width=device-width, initial-scale=1">
<nav class="color1 navbar navbar-expand-lg " data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/"/>">Ecovis</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="<c:url value="/"/> ">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/nouvelles"/> ">Nouvelles</a>
                </li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/utilisateurs"/> ">Utilisateurs</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('COMM')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/nouvellesUtilisateur"/> ">Mes nouvelles</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
        <div class="float-sm-end color1" id="navbarNav2">
            <ul class="navbar-nav float-end">
                <sec:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<c:url value="/connexion"/> ">Connexion</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<c:url value="/compte"/> ">${sessionScope.user.username}</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>