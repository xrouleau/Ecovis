<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Nouvelles</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="hasRole('COMM')">
<body class="custom-bg-transparent">
<div class="container-fluid">
    <div class="row text-center">
        <h1 class="my-5">Mes nouvelles</h1>
    </div>
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-6">
                <a class="btn btn-secondary button" href="<c:url value="/nouvelles"/>">Retour</a>
            </div>
            <div class="col-6">
                <a class="btn btn-primary float-end" href="<c:url value="/ajouterNouvelle"/> ">Ajouter</a>
            </div>
        </div>
        <div class="row row-cols-1">
            <c:forEach var="nouvelle" items="${nouvelles}">
                <div class="col">
                    <div class="card my-2">
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col ">
                                        <h5 class="card-title">${nouvelle.titre}</h5>
                                    </div>
                                    <c:if test="${nouvelle.publie == true}">
                                        <div class="col">
                                            <a href="<c:url value="/retirerNouvelle/${nouvelle.id}"/>" class="btn btn-warning float-end" data-bs-theme="dark">Retirer</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${nouvelle.publie == false}">
                                        <div class="col">
                                            <a href="<c:url value="/publierNouvelle/${nouvelle.id}"/>" class="btn color1 float-end" data-bs-theme="dark">Publier</a>
                                            <a href="<c:url value="/modifierNouvelle/${nouvelle.id}"/>" class="btn btn-primary float-end me-2" data-bs-theme="dark">Modifier</a>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer color2">
                                ${nouvelle.datePublication}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</div>
</body></sec:authorize>
</html>
