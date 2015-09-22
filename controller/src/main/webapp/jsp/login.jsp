<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>CRM Login page</title>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="<c:url value="../resources/css/signin.css" />">
</head>
<body>
    <div class="container">
        <form class="form-signin" action="login" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>

            <label for="input-login" class="sr-only">Login</label>
            <input type="email" id="input-login" name="input-login" class="form-control" placeholder="Email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\.[a-z]{2,3}$" required autofocus>

            <label for="input-password" class="sr-only">Password</label>
            <input type="password" id="input-password" name="input-password" class="form-control" placeholder="Password" pattern="[A-Za-z_0-9]{4,16}" required>

            <button class="btn btn-lg btn-primary btn-block" id="signin-button" type="submit">Sign in</button>
            <br>
            <label class="col-sm-offset-2 label-error">${errorMessage}</label>
            <br>
            <label class="col-sm-offset-2">Guest: avma@mail.ru / pass</label>
            <label class="col-sm-offset-2">Admin: puva@mail.ru / pass</label>
        </form>
    </div>
</body>
</html>
