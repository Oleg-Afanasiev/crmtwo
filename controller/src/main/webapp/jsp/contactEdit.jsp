<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Редактирование контакта</title>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="<c:url value="../resources/css/contactForm.css" />">
</head>
<body>
    <div class="container">
        <div class="row">
            <jsp:include page="fragments/menu.jsp"/>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Редактирование контакта
                        </h3>
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" id="contact-edit-form" action="/crm/contactedit" method="POST" enctype="multipart/form-data">
                            <fieldset>
                                <legend>Обновить контакт</legend>
                                <div class="form-group">
                                    <label for="contact-name" class="col-sm-3 control-label">Имя Фамилия</label>
                                    <div class="col-sm-8">
                                        <input type="hidden" name="contact-id" value="${contact.id}">
                                        <input type="text" class="form-control" id="contact-name" required = "required" value="${contact.name}" name="contact-name" size="30" pattern="[A-Za-zА-Яа-я\s]{1,30}" autofocus="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="company-id" class="col-sm-3 control-label">Компания</label>
                                    <div class="col-sm-8">
                                        <select id="company-id" class="form-control" name="company-id">
                                            <c:if test="${empty contact.company}"><option value="">Выбрать</option></c:if>
                                            <c:forEach items="${companies}" var="company">
                                                <option value="${company.id}" <c:if test="${company.id eq contact.company.id}">SELECTED</c:if>>${company.name}</option>
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
                                    <label for="responsible-user" class="col-sm-3 control-label">Ответственный</label>
                                    <div class="col-sm-8">
                                    <select id="responsible-user" class="form-control" name="responsible-user-id">
                                        <c:forEach items="${users}" var="respUser">
                                            <option value="${respUser.id}" <c:if test="${respUser.id eq contact.responsibleUser.id}">SELECTED</c:if>>${respUser.userName} (${respUser.role.name})</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="job-position" class="col-sm-3 control-label">Должность</label>
                                    <div class="col-sm-8">
                                        <input id="job-position" required = "required" class="form-control" name="job-position" type="text" value="${contact.jobPosition}" size="30" pattern="[A-Za-zА-Яа-я\s]{1,30}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3">
                                    <select id="phone-type" class="form-control" name="phone-type">
                                        <c:set var="phoneType" value="${contact.phones[0].phoneType}"/>
                                        <option value="1" <c:if test="${phoneType eq 1}">SELECTED</c:if>>Рабочий</option>
                                        <option value="2" <c:if test="${phoneType eq 2}">SELECTED</c:if>>Раб.прямой</option>
                                        <option value="3" <c:if test="${phoneType eq 3}">SELECTED</c:if>>Мобильный</option>
                                        <option value="4" <c:if test="${phoneType eq 4}">SELECTED</c:if>>Факс</option>
                                        <option value="5" <c:if test="${phoneType eq 5}">SELECTED</c:if>>Домашний</option>
                                        <option value="6" <c:if test="${phoneType eq 6}">SELECTED</c:if>>Другой</option>
                                    </select>
                                    </div>
                                    <div class="col-sm-8">
                                        <input id="phone-number" class="form-control" name="phone-number" type="text" value="${contact.phones[0].number}" size="30" pattern="\+[0-9]{12}"/>
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
                                        <input id="skype" required = "required" class="form-control" name="skype" type="text" value="${contact.skype}" size="30" pattern="[A-Za-z._0-9]{1,30}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="contact-comment" class="col-sm-3 control-label">Примечание к контакту</label>
                                    <div class="col-sm-8">
                                        <textarea id="contact-comment" class="form-control" name="contact-comment" rows="3" cols="30">${contact.comments[0].comment}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="files" class="col-sm-3 control-label file-label">Обновить файлы</label>
                                    <div class="col-sm-8">
                                        <c:forEach items="${contact.files}" var="file">
                                            <div id="delete-file-list">
                                                <label><input type="hidden" name="file-id" value="${file.id}">${file.name}</label>
                                                <input id="file-delete-button" type="submit" name="file-delete" value="Удалить" class="btn btn-danger btn-xs pull-right"/>
                                            </div>
                                        </c:forEach>
                                        <input id="files" class="file" type="file" name="files" multiple="multiple"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center contact-delete-buttons">
                                        <input id="contact-submit-button" type="submit" name="edit" value="Готово" class="btn btn-primary"/>
                                        <input id="contact-delete-button" type="submit" name="contact-delete" value="Удалить контакт" class="btn btn-danger"/>
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
