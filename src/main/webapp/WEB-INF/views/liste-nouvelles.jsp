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
        <h1 class="my-5">Nouvelles</h1>
    </div>
    <div class="container">
        <div class="row row-cols-3">
            <c:forEach var="nouvelle" items="${nouvelles}">
                <div class="col">
                    <div class="card my-2">
                        <div class="card-body">
                            <h5 class="card-title">${nouvelle.titre}</h5>
                            <a href="#" class="btn color1">Lire</a>
                        </div>
                        <div class="card-footer text-body-secondary">
                                ${nouvelle.datePublication}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</div>
</body>
</html>
