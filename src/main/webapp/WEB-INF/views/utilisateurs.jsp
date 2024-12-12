<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Utilisateurs</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="hasRole('ADMIN')">
<body class="custom-bg-transparent">
<div class="container-fluid">
    <div class="row text-center">
        <h1 class="my-5">Utilisateurs</h1>
    </div>

    <div class="container">
        <div class="row justify-content-between">
            <div class="col-6">
                <a class="btn btn-secondary button" href="<c:url value="/nouvelles"/>">Retour</a>
            </div>
            <div class="col-6">
                <a class="btn btn-primary float-end" href="<c:url value="/enregistrer"/> ">Ajouter</a>
            </div>
        </div>
        <div class="row row-cols-1">
            <c:forEach var="user" items="${utilisateurs}">
                <div class="col">
                    <div class="card my-2">
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col ">
                                        <h5 class="card-title">${user.username}</h5>
                                        <p class="mb-0">${user.prenom} ${user.nom}</p>
                                        <p class="mb-0">${user.email}</p>
                                        <c:set var="lenght" value="0"/>
                                        <c:forEach items="${user.roles}">
                                            <c:set var="lenght" value="${lenght + 1}"/>
                                        </c:forEach>
                                        <c:if test="${lenght == 2}">
                                            <p class="mb-0">Communication</p>
                                        </c:if>
                                        <c:if test="${lenght == 3}">
                                            <p class="mb-0">Administrateur</p>
                                        </c:if>
                                    </div>
                                    <div class="col">
                                        <a href="<c:url value="/modifierUtilisateur/${user.id}"/>" class="btn color1 float-end align-middle" data-bs-theme="dark">Modifier</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</div>
</body></sec:authorize>
</html>
