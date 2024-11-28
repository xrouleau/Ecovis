<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
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
                    <a class="nav-link" href="<c:url value="#"/> ">Nouvelles</a>
                </li>
            </ul>
        </div>
        <div class="float-sm-end color1" id="navbarNav2">
            <ul class="navbar-nav float-end">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="<c:url value="/"/> ">Connexion</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
