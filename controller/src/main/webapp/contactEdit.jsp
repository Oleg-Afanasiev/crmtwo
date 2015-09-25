<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Contact Edit Form</title>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="<c:url value="resources/css/contactForm.css" />">
</head>
<body>
    <br>
    <div class="container">
        <div class="row">
            <jsp:include page="fragments/menu.jsp"/>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Добавление контакта
                        </h3>
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" id="contactForm" action="contactedit" method="POST">
                            <fieldset>
                                <legend>Обновить контакт</legend>
                                <div class="form-group">
                                    <label for="contactName" class="col-sm-3 control-label">Имя Фамилия</label>
                                    <div class="col-sm-8">
                                        <input type="hidden" name="id" value="${contact.id}">
                                        <input type="text" class="form-control" id="contactName" required = "required" value="${contact.name}" name="contactName" size="30" pattern="[A-Za-zА-Яа-я\s]{1,30}" autofocus="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="companyId" class="col-sm-3 control-label">Компания</label>
                                    <div class="col-sm-8">
                                        <select id="companyId" class="form-control" name="companyId">
                                            <option value="${contact.company.id}">${contact.company.name}</option>
                                            <option value="">Выбрать</option>
                                            <c:forEach items="${companies}" var="company">
                                                <option value="${company.id}">${company.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="tags" class="col-sm-3 control-label">Теги</label>
                                    <div class="col-sm-8">
                                        <input id="tags" class="form-control" name="tags" type="text" value="${tags}" size="30" placeholder="#tags" pattern="(#[A-Za-zа-яА-я0-9]+\s*)+"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="responsibleUser" class="col-sm-3 control-label">Ответственный</label>
                                    <div class="col-sm-8">
                                    <select id="responsibleUser" class="form-control" name="responsibleUserId">
                                        <option value="${contact.responsibleUser.id}">${contact.responsibleUser.userName}</option>
                                        <c:forEach items="${users}" var="user">
                                            <option value="${user.id}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="jobPosition" class="col-sm-3 control-label">Должность</label>
                                    <div class="col-sm-8">
                                        <input id="jobPosition" required = "required" class="form-control" name="jobPosition" type="text" value="${contact.jobPosition}" size="30" pattern="[A-Za-zА-Яа-я\s._]{1,30}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3">
                                    <select id="phoneType" class="form-control" name="phoneType">
                                        <c:set var="contPhType" value="${contact.phones[0].phoneType}"/>
                                        <%--TODO selected doesn't work--%>
                                        <option value="1" <c:if test="${contPhType} == 1">selected</c:if>>Рабочий</option>
                                        <option value="2" <c:if test="${contPhType} == 2">selected</c:if>>Раб.прямой</option>
                                        <option value="3" <c:if test="${contPhType} == 3">selected</c:if>>Мобильный</option>
                                        <option value="4" <c:if test="${contPhType} == 4">selected</c:if>>Факс</option>
                                        <option value="5" <c:if test="${contPhType} == 5">selected</c:if>>Домашний</option>
                                        <option value="6" <c:if test="${contPhType} == 6">selected</c:if>>Другой</option>
                                    </select>
                                    </div>
                                    <div class="col-sm-8">
                                        <input id="phoneNumber" class="form-control" name="phoneNumber" type="text" value="${contact.phones[0].number}" size="30" pattern="[\+0-9]{1,30}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-3 control-label">Email</label>
                                    <div class="col-sm-8">
                                        <input id="email" required = "required" class="form-control" name="email" type="email" value="${contact.email}" size="30" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\.[a-z]{2,3}$"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="skype" class="col-sm-3 control-label">Skype</label>
                                    <div class="col-sm-8">
                                        <input id="skype" required = "required" class="form-control" name="skype" type="text" value="${contact.skype}" size="30" pattern="[A-Za-z._]{1,30}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="contactComment" class="col-sm-3 control-label">Примечание к контакту</label>
                                    <div class="col-sm-8">
                                        <textarea id="contactComment" class="form-control" name="contactComment" rows="3" cols="30">${contact.comments[0].comment}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="files" class="col-sm-3 control-label file-label">Добавить файлы</label>
                                    <%--TODO files doesn't work--%>
                                    <div class="col-sm-8">
                                        <input id="files" class="file" type="file" name="files"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="contactSubmitButton" type="submit" name="edit" value="Обновить" class="btn btn-primary"/>
                                        <input id="contactCancelButton" type="submit" name="delete" value="Удалить" class="btn btn-danger"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
