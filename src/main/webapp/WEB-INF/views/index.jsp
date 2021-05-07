<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<p><c:forEach items="${messages}" var="message">
    <c:out value="${message}"/>
</c:forEach></p>
<p>Hello : Accident</p>
</body>
</html>