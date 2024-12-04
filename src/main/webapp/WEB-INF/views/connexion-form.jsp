<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Nouvelles</title>
    <%@include file="header.jsp"%>
</head>
<body class="custom-bg-transparent">
<div class="container-fluid">
    <div class="row text-center">
        <h1 class="my-5">Connexion</h1>
    </div>
    <div class="container">
        <div class="row row-cols-1">
            <div class="container-sm col">
                <form method="post" action="connexionSubmit">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Nom d'utilisateur</label>
                        <input type="email" required class="form-control" id="exampleInputEmail1" name="username" aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Mot de passe</label>
                        <input type="password" required class="form-control" name="password" id="exampleInputPassword1">
                    </div>
                    <button type="submit" class="btn btn-primary">Se connecter</button>
                </form>
            </div>

        </div>
    </div>
</div>