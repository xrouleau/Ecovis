<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Nouvelles</title>
    <%@include file="header.jsp"%>
</head>
<sec:authorize access="isAuthenticated()">
<body class="custom-bg-transparent">
<div class="container-fluid mb-5">
    <div class="row text-center">
        <h1 class="my-5">Nouvelles</h1>
    </div>
    <div class="container">
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
                                    <div class="col">
                                        <a href="<c:url value="/lire/${nouvelle.id}"/>" class="btn color1 float-end" data-bs-theme="dark">Lire</a>
                                    </div>
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
