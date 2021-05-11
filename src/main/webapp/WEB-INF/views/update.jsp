<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <title>Редактирование инцидента</title>
</head>
<c:set var="result" value="${accident}" />
<body>
<div class="container">
    <form action="<c:url value='/save?id=${result.id}'/>" method='POST'>
        <div class="form-group">
            <label for="name">Имя </label>
            <input type="text" class="form-control" id="name" name="name" value="${result.name}">
        </div>
        <div class="form-group">
            <label for="text">Описание </label>
            <input type="text" class="form-control" id="text" name="text" value="${result.text}">
        </div>
        <div class="form-group">
            <label for="address">Адрес</label>
            <input type="text" class="form-control" id="address" name="address" value="${result.address}">
        </div>
        <div class="form-group">
            <label for="address">Тип</label>
            <select name="accidentsType">
                <c:forEach items="${accidentsType}" var="accidentType">
                    <c:if test="${accidentType.id == result.accidentType.id}">
                        <option selected value="${accidentType.id}">${accidentType.name}</option>
                    </c:if>
                    <c:if test="${accidentType.id != result.accidentType.id}">
                        <option value="${accidentType.id}">${accidentType.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-dark">Добавить</button>
    </form>
</div>
</body>
</html>