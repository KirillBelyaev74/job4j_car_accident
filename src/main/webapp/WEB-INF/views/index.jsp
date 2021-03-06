<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
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

    <title>Accident</title>
</head>
<body>
<div class="navbar navbar-dark bg-dark box-shadow">
    <div class="container d-flex justify-content-between">
        <a class="navbar-brand d-flex align-items-center" style="color:#ffffff">
            <strong>${user.username}</strong>
        </a>
        <c:if test="${not empty user}">
            <input type="button"
                   onclick="location.href='<c:url value='/logout'/>'"
                   class="btn btn-dark" value="Выйти">
        </c:if>
    </div>
</div>
<div class="container">
    <br/><input type="button" onclick="location.href='<c:url value='/create'/>'"
                class="btn btn-dark" value="Добавить"><br/><br/>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Имя</th>
            <th scope="col">Описание</th>
            <th scope="col">Адрес</th>
            <th scope="col">Тип</th>
            <th scope="col">Роли</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accidents}" var="accident">
            <tr>
                <td>${accident.name}</td>
                <td>${accident.text}</td>
                <td>${accident.address}</td>
                <td>${accident.accidentType.name}</td>
                <td>
                    <c:forEach items="${accident.rules}" var="rule">
                        ${rule.name}<br>
                    </c:forEach>
                </td>
                <td><input type="button" onclick="location.href='<c:url value='/update?id=${accident.id}'/>'"
                           class="btn btn-dark" value="Редактировать"></td>
                <td><input type="button" onclick="location.href='<c:url value='/delete?id=${accident.id}'/>'"
                           class="btn btn-dark" value="Удалить"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>