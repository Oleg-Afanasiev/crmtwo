<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавление контакта</title>
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
                            Добавление контакта
                        </h3>
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" id="contact-add-form" action="/crm/contactadd" method="POST" enctype="multipart/form-data">
                            <fieldset>
                                <legend>Новый контакт</legend>
                                <div class="form-group">
                                    <label for="contact-name" class="col-sm-3 control-label">Имя Фамилия</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="contact-name" required = "required"  name="contact-name" value="" size="30" pattern="[A-Za-zА-Яа-я\s]{1,30}" autofocus="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="company-id" class="col-sm-3 control-label">Компания</label>
                                    <div class="col-sm-8">
                                        <select id="company-id" class="form-control" name="company-id">
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
                                        <input id="tags" class="form-control" name="tags" type="text" value="" size="30" placeholder="#tags" pattern="(#[A-Za-zа-яА-я0-9]+\s*)+"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="responsible-user" class="col-sm-3 control-label">Ответственный</label>
                                    <div class="col-sm-8">
                                        <select id="responsible-user" class="form-control" name="responsible-user-id">
                                            <c:forEach items="${users}" var="respUser">
                                                <option value="${respUser.id}" <c:if test="${activeUser.id eq respUser.id}">SELECTED</c:if>>${respUser.userName} (${respUser.role.name})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="job-position" class="col-sm-3 control-label">Должность</label>
                                    <div class="col-sm-8">
                                        <input id="job-position" required = "required" class="form-control" name="job-position" type="text" value="" size="30" pattern="[A-Za-zА-Яа-я\s]{1,30}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <select id="phone-type" class="form-control" name="phone-type">
                                            <option value="1">Рабочий</option>
                                            <option value="2">Раб.прямой</option>
                                            <option value="3">Мобильный</option>
                                            <option value="4">Факс</option>
                                            <option value="5">Домашний</option>
                                            <option value="6">Другой</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-8">
                                        <input id="phone-number" class="form-control" name="phone-number" type="text" value="" size="30" pattern="\+[0-9]{12}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-3 control-label">Email</label>
                                    <div class="col-sm-8">
                                        <input id="email" required = "required" class="form-control" name="email" type="email" value="" size="30" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\.[a-z]{2,3}$"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="skype" class="col-sm-3 control-label">Skype</label>
                                    <div class="col-sm-8">
                                        <input id="skype" required = "required" class="form-control" name="skype" type="text" value="" size="30" pattern="[A-Za-z._0-9]{1,30}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="contact-comment" class="col-sm-3 control-label">Примечание к контакту</label>
                                    <div class="col-sm-8">
                                        <textarea id="contact-comment" class="form-control" name="contact-comment" rows="3" cols="30"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="files" class="col-sm-3 control-label file-label">Добавить файлы</label>
                                    <div class="col-sm-8">
                                        <input id="files" class="file" type="file" name="files" multiple="multiple"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="contact-submit-button" type="submit" value="Сохранить" class="btn btn-primary"/>
                                        <input id="contact-cancel-button" type="reset" value="Отмена" class="btn btn-default"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                        <form class="form-horizontal" id="taskForm" action="#" method="POST">
                            <fieldset>
                            <legend>Новая задача</legend>
                                <div class="form-group">
                                    <label for="taskPeriod" class="col-sm-3 control-label">Период</label>
                                    <div class="col-sm-8">
                                        <select id="taskPeriod" class="form-control" name="taskPeriod">
                                            <option value="">Выбрать</option>
                                            <%--<c:forEach items="${companies}" var="company">--%>
                                                <%--<option value="${company.id}">${company.name}</option>--%>
                                            <%--</c:forEach>--%>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="responsibleUserTask" class="col-sm-3 control-label">Ответственный</label>
                                    <div class="col-sm-8">
                                        <select id="responsibleUserTask" class="form-control" name="responsibleUserIdTask">
                                            <c:forEach items="${users}" var="user">
                                                <option value="${user.id}">${user.userName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="taskType" class="col-sm-3 control-label">Тип задачи </label>
                                    <div class="col-sm-8">
                                        <select id="taskType" class="form-control" name="TaskType">
                                            <option value="">Выбрать</option>
                                            <%--<c:forEach items="${users}" var="user">--%>
                                            <%--<option value="${user.id}">${user.userName}</option>--%>
                                            <%--</c:forEach>--%>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="taskComment" class="col-sm-3 control-label">Текст задачи</label>
                                    <div class="col-sm-8">
                                        <textarea id="taskComment" class="form-control" name="taskComment" rows="3" cols="30"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="task-submit-button" type="submit" value="Сохранить" class="btn btn-primary"/>
                                        <input id="task-cancel-button" type="reset" value="Отмена" class="btn btn-default"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                        <fieldset>
                            <legend>Сделки</legend>
                            <div>
                                <label class="control-label col-md-3 col-md-offset-3">Общее кол-во:</label>
                                <label class="control-label col-sm-5 text-right"><%--5 на 100 000--%> USD</label>
                            </div>
                            <div>
                                <label for="allDeals" class="control-label control-label col-sm-3  text-right">Сделки</label>
                                <div class="col-sm-8">
                                    <select id="allDeals" class="form-control scroll-bar" name="allDeals" size="3">
                                        <%--<option value="">Сделка 1 | Переговоры | 20 000 | USD</option>--%>
                                        <%--<option value="">Сделка 2 | Согласова... | 30 000 | USD</option>--%>
                                    </select>
                                </div>
                            </div>
                        </fieldset>
                    <br/>
                        <form class="form-horizontal" id="dealForm" action="#" method="POST">
                            <fieldset>
                                <legend>Быстрое добавление сделки</legend>
                                <div class="form-group">
                                    <label for="dealName" class="col-sm-3 control-label">Название сделки</label>
                                    <div class="col-sm-8">
                                        <input id="dealName" class="form-control" name="dealName" type="text" value="" size="30" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dealStatus" class="col-sm-3 control-label">Этап</label>
                                    <div class="col-sm-8">
                                        <select id="dealStatus" class="form-control" name="dealStatus">
                                            <option>Выбрать</option>
                                            <%-- <c:forEach items="${users}" var="user">
                                            <option value="${user.id}">${user.userName}</option>
                                            </c:forEach>--%>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dealBudget" class="col-sm-3 control-label">Бюджет</label>
                                    <div class="col-sm-7">
                                        <input id="dealBudget" class="form-control" name="dealBudget" type="text" value="" size="30" />
                                    </div>
                                    <label class="col-sm-1 control-label">USD</label>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="deal-submit-button" type="submit" value="Сохранить" class="btn btn-primary"/>
                                        <input id="deal-cancel-button" type="reset" value="Отмена" class="btn btn-default"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                        <form class="form-horizontal" id="companyForm" action="#" method="POST">
                            <fieldset>
                                <legend>Добавить новую компанию</legend>
                                <div class="form-group">
                                    <label for="companyName" class="col-sm-3 control-label">Название компании</label>
                                    <div class="col-sm-8">
                                        <input id="companyName" class="form-control" name="companyName" type="text" value="" size="30"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="companyPhone" class="col-sm-3 control-label">Номер телефона</label>
                                    <div class="col-sm-8">
                                        <input id="companyPhone" class="form-control" name="companyPhone" type="text" value="" size="30"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="webAddress" class="col-sm-3 control-label">Web-адрес</label>
                                    <div class="col-sm-8">
                                        <input id="webAddress" class="form-control" name="webAddress" type="text" value="" size="30"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="companyAddress" class="col-sm-3 control-label">Адрес</label>
                                    <div class="col-sm-8">
                                        <input id="companyAddress" class="form-control" name="companyAddress" type="text" value="" size="30"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="company-submit-button" type="submit" value="Сохранить" class="btn btn-primary"/>
                                        <input id="company-cancel-button" type="reset" value="Отмена" class="btn btn-default"/>
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
