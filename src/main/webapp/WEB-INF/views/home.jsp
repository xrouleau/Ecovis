<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Home</title>
    <%@include file="header.jsp"%>
</head>
<body>
<div class="container-fluid text-center">
    <div class="row paddingHome my-2">
        <div class="col height position-relative p-0">
            <img src="/images/damian-barczak-s33zR6H33A4-unsplash.jpg" class="rounded image2 position-absolute top-50 start-50 translate-middle" alt="...">
        </div>
        <div class="col height position-relative p-0">
            <div class="container position-absolute top-50 start-50 translate-middle">
                <div class="color2 p-2 mb-2 rounded">
                    <h1 class="p-2">Ecovis</h1>
                </div>
                <div class="color2 p-2 mt-2 rounded">
                    <p class="m-0 p-4">Ecovis est une entreprise dédiée à la protection de l’environnement et à la transition écologique. Notre mission est de proposer des solutions innovantes pour réduire l’impact environnemental des entreprises et des particuliers. Spécialisés dans les énergies renouvelables, la gestion des déchets et la restauration des écosystèmes, nous accompagnons nos partenaires dans leurs projets durables. Chaque initiative est conçue pour favoriser un équilibre entre développement économique et respect de la nature. Grâce à une équipe passionnée et des technologies de pointe, Ecovis s'engage à bâtir un avenir plus vert, une action à la fois. Ensemble, œuvrons pour préserver notre planète.</p>
                </div>
                <div class="color2 p-2 mt-2 rounded d-grid gap-2">
                    <a class="btn color2" href="<c:url value="/nouvelles"/> ">Visiter</a>
                </div>
            </div>
            <img src="/images/ivan-bandura-SqIvpSJ_d9o-unsplash.jpg" class="rounded image1 position-absolute top-50 start-50 translate-middle back" alt="...">
        </div>
        <div class="col height position-relative p-0">
            <img src="/images/ocg-saving-the-ocean-cyxRGU7keTw-unsplash.jpg" class="rounded image2 position-absolute top-50 start-50 translate-middle" alt="...">
        </div>
    </div>
</div>
</body>
</html>