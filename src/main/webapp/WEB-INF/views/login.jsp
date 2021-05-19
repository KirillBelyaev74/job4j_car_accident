<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <title>Авторизация</title>
</head>
<body>
<br>
<br>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Авторизация</div>
                <div class="card-body">
                    <form name='login' action="<c:url value='/login'/>" method='POST'>
                        <c:if test="${not empty errorMessage}">
                        <div style="color:red; font-weight: bold; margin: 30px 0px;">
                                ${errorMessage}
                        </div>
                        </c:if>
                        <div class="form-group">
                            <label for="username" class="cols-sm-2 control-label">Имя</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="username" id="username"
                                           placeholder="Введите имя"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="cols-sm-2 control-label">Пароль</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" name="password" id="password"
                                           placeholder="Введите пароль"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" float="right" class="btn btn-dark">
                                Войти
                            </button>
                            <input type="button" onclick="location.href='<c:url value='/registration'/>'"
                                   class="btn btn-dark" value="Зарегистироваться">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>