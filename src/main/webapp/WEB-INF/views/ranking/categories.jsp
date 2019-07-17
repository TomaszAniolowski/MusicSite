<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>musicsite - albums</title>
    <!-- Bootstrap -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!--FONT AWESOME-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf" %>

<section class="ens-page">
    <h1 class="categories">Categories</h1>

    <div class="category-selection">

        <form:form method="post" modelAttribute="categorySelector">

            <h5 class="categories">Select:</h5>
            <hr class="horizontal-line-select">
            <div class="categories-checkboxes-list">
                <input type="submit" value="Show" class="categories-show-content">
                <div class="categories-checkboxes">
                    <form:checkboxes path="categoryList"
                                     items="${categories}"
                                     itemLabel="name"
                                     itemValue="id"
                                     cssClass="category-checkbox"/>
                </div>
            </div>
        </form:form>
    </div>

    <c:if test="${not empty categorySelector.categoryList}">

        <div class="categories-content">

            <c:if test="${not empty performers}">
                <div class="categories-performers">
                    <h1 class="ens-type">Performers</h1>
                    <%@include file="../fragments/performers-table.jspf"%>
                </div>
            </c:if>
            <c:if test="${not empty albums}">
                <div class="categories-albums">
                    <h1 class="ens-type">Albums</h1>

                    <%@include file="../fragments/albums-table.jspf"%>

                </div>
            </c:if>
            <c:if test="${not empty tracks}">
                <div class="categories-tracks">
                    <h1 class="ens-type">Tracks</h1>
                    <%@include file="../fragments/tracks-table.jspf"%>
                </div>
            </c:if>

        </div>
    </c:if>


</section>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script type="text/javascript" src="/resources/js/Ens-Pages.js"></script>
</body>
</html>