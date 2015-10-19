<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 9/17/15
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Добавление сделки</title>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="<c:url value="../resources/css/dealAdd.css" />">
    <script type="text/javascript" src="<c:url value="../resources/js/dealAdd.js" />"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="fragments/menu.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row">
                <div>
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Добавление сделки
                            </h3>
                        </div>
                        <div class="panel-body">
                            <form id="dealAdd_form" class="form-horizontal" name="dealAdd" action="/crm/dealadd" method="POST" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <legend>Новая сделка</legend>

                                            <c:if test="${dealInputError.name eq 'name'}">
                                                <c:set var="markNameDealError" value="has-error" scope="page"/>
                                                <c:set var="msgNameDealError" value="placeholder='Введите корректное название'" scope="page" />
                                            </c:if>
                                            <div class="form-group ${markNameDealError}">
                                                <label for="deal-name" class="col-sm-3 control-label">Название сделки</label>
                                                <div class="col-sm-8">
                                                    <input id="deal-name"  class="form-control"  name="name" type="text"  value="${dealFields.name}" ${msgNameDealError}/>
                                                </div>
                                            </div>

                                            <c:set var="msgTagsError" value="#tags" scope="page" />
                                            <c:if test="${dealInputError.name eq 'deal: tags is empty'}">
                                                <c:set var="markTagsError" value="has-error" scope="page"/>
                                                <c:set var="msgTagsError" value="placeholder='Введите тэги'" scope="page" />
                                            </c:if>
                                            <c:if test="${dealInputError.name eq 'deal: tag format error'}">
                                                <c:set var="markTagsError" value="has-error" scope="page"/>
                                                <c:set var="divTagsError" value="<div class=\"text-danger\">Каждый тэг должен начинаться с '#'</div>" scope="page" />
                                            </c:if>

                                            <div class="form-group ${markTagsError}">
                                                <label for="tags" class="col-sm-3 control-label">Теги</label>
                                                <div class="col-sm-8">
                                                    <%--pattern="(#[A-Za-zа-яА-я0-9]+\s*)+"--%>
                                                    <input id="tags" class="form-control" name="tags" type="text" ${msgTagsError} value="${dealFields.tags}"  />
                                                    ${divTagsError}
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="responsibleUser" class="col-sm-3 control-label">Ответственный</label>
                                                <div class="col-sm-8">
                                                    <select id="responsibleUser" class="form-control" name="responsibleUser">
                                                        <c:forEach items="${users}" var="user">
                                                            <option value="${user.id}" ${dealFields.responsibleUserId == user.id ? 'selected' : ''}>
                                                                    ${user.firstName} ${user.lastName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <c:if test="${dealInputError.name eq 'deal: budget is empty'}">
                                                <c:set var="markBudgetError" value="has-error" scope="page"/>
                                                <c:set var="msgBudgetError" value="placeholder='Введите бюджет'" scope="page" />
                                            </c:if>
                                            <c:if test="${dealInputError.name eq 'deal: budget format'}">
                                                <c:set var="markBudgetError" value="has-error" scope="page"/>
                                                <c:set var="divBudgetError" value="Некорректные данные в поле \"Бюджет\"" scope="page" />
                                            </c:if>
                                            <div class="form-group ${markBudgetError}">
                                                <label for="budget" class="col-sm-3 control-label">Бюджет</label>
                                                <div class="col-sm-8">
                                                    <div class="input-group">
                                                        <input id="budget" class="form-control" name="budget" type="text" value="${dealFields.budget}" ${msgBudgetError}/>
                                                        <span id="currency" class="input-group-addon">USD</span>
                                                    </div>
                                                    ${divBudgetError}
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="status" class="col-sm-3 control-label">Этап</label>
                                                <div class="col-sm-8">
                                                    <select id="status" class="form-control" name="status">
                                                        <c:forEach items="${dealStatuses}" var="dealStatus">
                                                            <option value="${dealStatus.id}" ${dealFields.statusId == dealStatus.id ? 'selected' : ""}>
                                                                    ${dealStatus.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label" for="comment">Примечание</label>
                                                <div class="col-sm-8">
                                                    <textarea id="comment" class="form-control"  name="comments" rows="3" cols="30" value="Ответственная сделка">${dealFields.comments}</textarea>
                                                </div>
                                            </div>

                                            <div class="col-sm-11">
                                                <fieldset>
                                                    <legend>Файлы</legend>
                                                    <div>
                                                        <fieldset>
                                                            <legend>Список файлов</legend>
                                                            <div  class="list-files">
                                                                <ul id="attached_file_li_item" class="list-group" hidden>
                                                                    <li class="list-group-item" hidden>
                                                                        <div>
                                                                            <a href="#" class="close" title="Открепить файл">x</a>
                                                                            <span></span>
                                                                            <input type="file" hidden />
                                                                        </div>
                                                                    </li>
                                                                </ul>
                                                                <ul id="attached_files_list" class="list-unstyled">
                                                                </ul>
                                                            </div>
                                                            <input type="hidden" name="n_uploaded_files" value="0" />
                                                        </fieldset>
                                                    </div>
                                                    <div class="text-info">
                                                        <em>Кол-во загруженных: <span id="n_uploaded_files">0</span></em>
                                                    </div>
                                                    <div class="text-right">
                                                        <input id="add_files_btn" class="btn btn-default text-right" type="button" value="Добавить файлы"/>
                                                    </div>
                                                </fieldset>
                                            </div>
                                        </fieldset>

                                        <div id="attached_contacts_container" class="col-sm-11" hidden>
                                            <fieldset>
                                                <legend>Прикрепленные контакты</legend>
                                                <div id="attached_contact_item" hidden>
                                                    <div class="alert alert-info">
                                                        <a href="#" class="close" data-dismiss="alert" title="Открепить контак">x</a>
                                                        <span>Контакт 1</span>
                                                        <input type="hidden" value=""/>
                                                        <input type="hidden" value=""/>
                                                    </div>
                                                </div>
                                                <div id="attached_contacts" class="panel-group">
                                                    <c:forEach items="${dealFields.contacts}" var="contact" >
                                                        <div class="alert alert-info">
                                                            <a href="#" class="close" data-dismiss="alert" title="Открепить контак">x</a>
                                                            <span>${contact.name}</span>
                                                            <input type="hidden" value="${contact.id}"/>
                                                            <input type="hidden" value="${contact.name}"/>
                                                        </div>
                                                    </c:forEach>
                                                </div>

                                                <div class="text-info">
                                                    <em>Итого: <span id="n_attached_contacts">0</span></em>
                                                </div>
                                                <input type="hidden" name="n_attached_contacts" value="0"/>
                                            </fieldset>
                                        </div>

                                        <div id="attached_company_container" class="col-sm-11" hidden>
                                            <fieldset>
                                                <legend>Прикрепленная компания</legend>
                                                <div id="attached_company_item" hidden>
                                                    <div class="alert alert-info">
                                                        <a href="#" class="close" data-dismiss="alert" title="Открепить компанию">x</a>
                                                        <span>Некоторая компания</span>
                                                        <input type="hidden" value="" />
                                                        <input type="hidden" value="" />
                                                    </div>
                                                </div>
                                                <div id="attached_company">
                                                    <c:forEach items="${dealFields.companies}" var="company">
                                                        <div class="alert alert-info">
                                                            <a href="#" class="close" data-dismiss="alert" title="Открепить компанию">x</a>
                                                            <span>${company.name}</span>
                                                            <input type="hidden" name="attached_company_id" value="${company.id}" />
                                                            <input type="hidden" name="attached_company_name" value="${company.name}" />
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </fieldset>
                                        </div>

                                        <div class="form-group"></div>
                                        <div class="form-group"></div>
                                    </div>

                                    <div class="col-md-6">
                                        <div>
                                            <fieldset>
                                                <legend>Контакты</legend>
                                                <div class="form-group">
                                                    <label for="contact" class="col-sm-3 control-label">Имя Фамилия</label>
                                                    <div class="col-sm-5">
                                                        <select id="contact" class="form-control" name="contact"  data-toggle="tooltip">
                                                            <option value="0">--Выберите контакт--</option>
                                                            <c:forEach items="${contacts}" var="contact">
                                                                <option value="${contact.id}">
                                                                        ${contact.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="text-center">
                                                        <input id="contact_add_button" class="btn btn-primary" type="button" value="Добавить контакт" />
                                                        <input id="contact_cancel_button" class="btn btn-default" type="button" value="Отмена" />
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                        <c:if test="${savedContacts.size() > 0}">
                                            <div id="saved_contact_item">
                                                <fieldset>
                                                    <legend>Сохраненные контакты</legend>
                                                    <div class="panel panel-info">
                                                        <c:forEach items="${savedContacts}" var="savedContact" varStatus="loop">
                                                            <div class="panel-heading">
                                                                <h4>
                                                                    <a data-toggle="collapse" data-parent="#accordion_contacts" href="#collapse_contact_${loop.index + 1}">${savedContact.contactName}</a>
                                                                </h4>
                                                            </div>
                                                            <div id="collapse_contact_${loop.index + 1}" class="panel-collapse collapse">
                                                                <div class="panel-body">

                                                                    <div class="col-xs-4 text-muted  text-right">Имя Фамилия:</div>
                                                                    <div class="col-xs-8 text-primary"><a href="" title="Редактировать контакт">${savedContact.contactName}</a></div>
                                                                    <input type="hidden" value="${savedContact.contactName}" name="saved_contact_name_${loop.index + 1}"/>

                                                                    <div class="col-xs-4 text-muted  text-right">Название компании:</div>
                                                                    <div class="col-xs-8 text-primary">${savedContact.companyName}</div>
                                                                    <input type="hidden" value="${savedContact.companyId}" name="saved_contact_companyId_${loop.index + 1}"/>
                                                                    <input type="hidden" value="${savedContact.companyName}" name="saved_contact_companyName_${loop.index + 1}"/>

                                                                    <div class="col-xs-4 text-muted  text-right">Название должности:</div>
                                                                    <div class="col-xs-8 text-primary">${savedContact.jobPosition}</div>
                                                                    <input type="hidden" value="${savedContact.jobPosition}" name="saved_contact_jobPosition_${loop.index + 1}"/>

                                                                    <div class="col-xs-4 text-muted  text-right">Телефон:</div>
                                                                    <div class="col-xs-8 text-primary">${savedContact.phoneTypeName} ${savedContact.phoneNumber}</div>
                                                                    <input type="hidden" value="${savedContact.phoneTypeId}" name="saved_contact_phoneTypeId_${loop.index + 1}"/>
                                                                    <input type="hidden" value="${savedContact.phoneTypeName}" name="saved_contact_phoneTypeName_${loop.index + 1}"/>
                                                                    <input type="hidden" value="${savedContact.phoneNumber}" name="saved_contact_phoneNumber_${loop.index + 1}"/>

                                                                    <div class="col-xs-4 text-muted  text-right">Email:</div>
                                                                    <div class="col-xs-8 text-primary">${savedContact.email}</div>
                                                                    <input type="hidden" value="${savedContact.email}" name="saved_contact_email_${loop.index + 1}"/>

                                                                    <div class="col-xs-4 text-muted  text-right">Skype:</div>
                                                                    <div class="col-xs-8 text-primary">${savedContact.skype}</div>
                                                                    <input type="hidden" value="${savedContact.skype}" name="saved_contact_skype_${loop.index + 1}"/>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="text-info">
                                                        <em>Итого: <span id="n_saved_contacts">${savedContacts.size()}</span></em>
                                                    </div>
                                                    <input type="hidden" name="n_saved_contacts" value="${savedContacts.size()}"/>
                                                </fieldset>
                                            </div>
                                        </c:if>

                                        <div id="add_contact_container">
                                            <fieldset>
                                                <legend>Добавить контакт</legend>

                                                <c:if test="${dealInputError.name eq 'contact: name is empty'}">
                                                    <c:set var="mrkContactNameError" value="has-error" scope="page" />
                                                    <c:set var="msgContactNameError" value="placeholder='Введите Имя Фамилия'" scope="page" />
                                                </c:if>
                                                <div class="form-group ${mrkContactNameError}">
                                                    <label for="add_contact_name" class="col-sm-3 control-label">Имя Фамилия</label>
                                                    <div class="col-sm-8">
                                                        <input id="add_contact_name" class="form-control" type="text" name="contact_name" value="${contactFields.contactName}" ${msgContactNameError}/>
                                                    </div>
                                                </div>

                                                <c:if test="${dealInputError.name eq 'contact: company is not selected'}">
                                                    <c:set var="mrkContactCompanyError" value="has-error" scope="page" />
                                                    <c:set var="divContactNameError" value="<div class='text-danger'>Выберите компанию</div>" scope="page"/>
                                                </c:if>
                                                <div class="form-group ${mrkContactCompanyError}">
                                                    <label for="add_contact_company_name" class="col-sm-3 control-label">Название компании</label>
                                                    <div class="col-sm-8">
                                                        <select id="add_contact_company_name" class="form-control" name="contact_company_id">
                                                            <option value="0">--Выберите компанию--</option>
                                                            <c:forEach items="${companies}" var="company">
                                                                <option value="${company.id}" ${contactFields.companyId == company.id ? 'selected' : ""}>
                                                                        ${company.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                        ${divContactNameError}
                                                    </div>
                                                </div>

                                                <c:if test="${dealInputError.name eq 'contact: job position is empty'}">
                                                    <c:set var="mrkContactJobPositionError" value="has-error" scope="page" />
                                                    <c:set var="msgContactJobPositionError" value="placeholder='Введите должность'" scope="page"/>
                                                </c:if>
                                                <div class="form-group ${mrkContactJobPositionError}">
                                                    <label for="add_contact_job_position" class="col-sm-3 control-label">Название должности</label>
                                                    <div class="col-sm-8">
                                                        <input id="add_contact_job_position" class="form-control" type="text" name="contact_job_position" value="${contactFields.jobPosition}" ${msgContactJobPositionError}/>
                                                    </div>
                                                </div>

                                                <c:if test="${dealInputError.name eq 'contact: phone is empty'}">
                                                    <c:set var="mrkContactPhoneError" value="has-error" scope="page" />
                                                    <c:set var="msgContactPhoneError" value="placeholder='Введите номер'" scope="page"/>
                                                </c:if>
                                                <c:if test="${dealInputError.name eq 'contact: phone is not valid'}">
                                                    <c:set var="divContactPhoneError" value="<div class='text-danger'>Некорректный номер</div>" scope="page"/>
                                                </c:if>
                                                <div class="form-group ${mrkContactPhoneError}">
                                                    <label for="add_contact_phone_type" class="col-sm-3 control-label">Телефон</label>
                                                    <div class="col-sm-4">
                                                        <select id="add_contact_phone_type" class="form-control" name="contact_phone_type_id">
                                                            <c:forEach items="${phoneTypes}" var="phoneType">
                                                                <option value="${phoneType.id}" ${contactFields.phoneTypeId == phoneType.id ? 'selected' : ""}>${phoneType.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <input id="add_contact_phone_number" class="form-control" type="text" name="contact_phone_number" value="${contactFields.phoneNumber}" ${msgContactPhoneError}/>
                                                        ${divContactPhoneError}
                                                    </div>

                                                </div>
                                                <c:if test="${dealInputError.name eq 'contact: email is empty'}">
                                                    <c:set var="mrkContactEmailError" value="has-error" scope="page" />
                                                    <c:set var="msgContactEmailError" value="placeholder='Введите email'" scope="page"/>
                                                </c:if>
                                                <c:if test="${dealInputError.name eq 'contact: email is not valid'}">
                                                    <c:set var="divContactEmailError" value="<div class='text-danger'>Некорректный email</div>" scope="page"/>
                                                </c:if>
                                                <div class="form-group ${mrkContactEmailError}">
                                                    <label for="add_contact_email" class="col-sm-3 control-label">Email</label>
                                                    <div class="col-sm-8">
                                                        <%--pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\.[a-z]{2,3}$"--%>
                                                        <input id="add_contact_email" class="form-control" type="text" name="contact_email" value="${contactFields.email}" ${msgContactEmailError}/>
                                                        ${divContactEmailError}
                                                    </div>
                                                </div>

                                                <c:if test="${dealInputError.name eq 'contact: skype is empty'}">
                                                    <c:set var="mrkContactSkypelError" value="has-error" scope="page" />
                                                    <c:set var="msgContactSkypeError" value="placeholder='Введите skype'" scope="page"/>
                                                </c:if>
                                                <c:if test="${dealInputError.name eq 'contact: skype is not valid'}">
                                                    <c:set var="divContactSkypeError" value="<div class='text-danger'>Некорректный skype</div>" scope="page"/>
                                                </c:if>
                                                <%--pattern="[A-Za-z._0-9]{1,30}"--%>
                                                <div class="form-group ${mrkContactSkypelError}">
                                                    <label for="add_contact_skype" class="col-sm-3 control-label">Skype</label>
                                                    <div class="col-sm-8">
                                                        <input id="add_contact_skype" class="form-control" type="text" name="contact_skype" value="${contactFields.skype}" ${msgContactSkypeError}/>
                                                        ${divContactSkypeError}
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="text-center">
                                                        <input id="add_contact_save_button" class="btn btn-primary" type="submit" name="saveContact" value="Сохранить контакт" />
                                                        <input id="add_contact_cancel_button" class="btn btn-default" type="button" value="Отмена" />
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                        </fieldset>
                                        <div class="form-group"></div>

                                        <div>
                                            <fieldset>
                                                <legend>Компания</legend>
                                                <div class="form-group">
                                                    <label for="company" class="col-sm-3 control-label">Название компании</label>
                                                    <div class="col-sm-8">
                                                        <select id="company" class="form-control" name="company">
                                                            <option value="0">--Выберите компанию--</option>
                                                            <c:forEach items="${companies}" var="company">
                                                                <option value="${company.id}" ${company.id.toString() == dealCompanyFields.id ? 'selected' : ''}>
                                                                        ${company.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="text-center">
                                                        <input id="company_add_button" class="btn btn-primary" type="button" value="Добавить компанию" />
                                                        <input id="company_cancel_button" class="btn btn-default" type="button" value="Отмена" />
                                                    </div>
                                                </div>

                                                <div id="add_company_container">
                                                    <fieldset>
                                                        <legend>Добавить компанию</legend>

                                                        <c:if test="${dealInputError.name eq 'company: name is empty'}">
                                                            <c:set var="mrkCompanyNameError" value="has-error" scope="page" />
                                                            <c:set var="msgCompanyNameError" value="placeholder='Введите название'" scope="page" />
                                                        </c:if>
                                                        <div class="form-group ${mrkCompanyNameError}">
                                                            <label for="add_company_name" class="col-sm-3 control-label">Название компании</label>
                                                            <div class="col-sm-8">
                                                                <input id="add_company_name" class="form-control" type="text" name="company_name" value="${companyFields.companyName}" ${msgCompanyNameError}/>
                                                            </div>
                                                        </div>

                                                        <c:if test="${dealInputError.name eq 'company: number is empty'}">
                                                            <c:set var="mrkCompanyPhoneNumberError" value="has-error" scope="page" />
                                                            <c:set var="msgCompanyPhoneNumberError" value="placeholder='Введите номер'" scope="page" />
                                                        </c:if>
                                                        <c:if test="${dealInputError.name eq 'company: number is not valid'}">
                                                            <c:set var="htmlCompanyPhoneNumberError" value="<div class='text-danger'>Некорректный номер телефона</div>" scope="page" />
                                                        </c:if>
                                                        <div class="form-group ${mrkCompanyPhoneNumberError}">
                                                            <label for="add_company_phone_type" class="col-sm-3 control-label">Телефон</label>
                                                            <div class="col-sm-4">
                                                                <select id="add_company_phone_type"  class="form-control" name="company_phone_type_id">
                                                                    <c:forEach items="${phoneTypes}" var="phoneType">
                                                                        <option value="${phoneType.id}" ${phoneType.id == companyFields.phoneTypeId ? "selected" : ""}>${phoneType.name}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <input id="add_company_phone_number" class="form-control" type="text" name="company_phone_number" value="${companyFields.phoneNumber}" ${msgCompanyPhoneNumberError}/>
                                                                ${htmlCompanyPhoneNumberError}
                                                            </div>
                                                        </div>

                                                        <c:if test="${dealInputError.name eq 'company: email is empty'}">
                                                            <c:set var="mrkCompanyEmailError" value="has-error" scope="page" />
                                                            <c:set var="msgCompanyEmailError" value="placeholder='Введите название'" scope="page" />
                                                        </c:if>
                                                        <c:if test="${dealInputError.name eq 'company: email is not valid'}">
                                                            <c:set var="htmlCompanyEmailError" value="<div class='text-danger'>Некорректный email</div>" scope="page" />
                                                        </c:if>
                                                        <div class="form-group ${mrkCompanyEmailError}">
                                                            <label for="add_company_email" class="col-sm-3 control-label">Email</label>
                                                            <div class="col-sm-8">
                                                                <%--pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\.[a-z]{2,3}$"--%>
                                                                <input id="add_company_email" class="form-control" type="text" name="company_email"  value="${companyFields.email}" ${msgCompanyEmailError}/>
                                                                ${htmlCompanyEmailError}
                                                            </div>
                                                        </div>

                                                        <c:if test="${dealInputError.name eq 'company: url is empty'}">
                                                            <c:set var="mrkCompanyURLError" value="has-error" scope="page" />
                                                            <c:set var="msgCompanyURLError" value="placeholder='Введите Web address'" scope="page" />
                                                        </c:if>
                                                        <c:if test="${dealInputError.name eq 'company: url is not valid'}">
                                                            <c:set var="htmlCompanyURLError" value="<div class='text-danger'>Некорректный Web address</div>" scope="page" />
                                                        </c:if>
                                                        <div class="form-group ${mrkCompanyURLError}">
                                                            <label for="add_company_web_address" class="col-sm-3 control-label">Web address</label>
                                                            <div class="col-sm-8">
                                                                <input id="add_company_web_address" class="form-control" name="company_web_address" type="text" value="${companyFields.webAddress}" ${msgCompanyURLError}/>
                                                                ${htmlCompanyURLError}
                                                            </div>
                                                        </div>

                                                        <c:if test="${dealInputError.name eq 'company: address is empty'}">
                                                            <c:set var="mrkCompanyAddressError" value="has-error" scope="page" />
                                                            <c:set var="msgCompanyAddressError" value="placeholder='Введите адрес'" scope="page" />
                                                        </c:if>
                                                        <div class="form-group ${mrkCompanyAddressError}">
                                                            <label for="add_company_address" class="col-sm-3 control-label">Адрес</label>
                                                            <div class="col-sm-8">
                                                                <textarea id="add_company_address" class="form-control" name="company_address" rows="3" cols="30" ${msgCompanyAddressError}>${companyFields.address}</textarea>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <div class="text-center">
                                                                <input id="add_company_save_button" class="btn btn-primary" type="submit" name="saveCompany" value="Сохранить компанию" />
                                                                <input id="add_company_cancel_button" class="btn btn-default" type="button" value="Отмена" />
                                                            </div>
                                                        </div>
                                                    </fieldset>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group"></div>

                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="submit_button" type="submit" name="saveDeal" value="Сохранить сделку" class="btn btn-primary"/>
                                        <input id="cancel_button" type="reset" value="Отмена" class="btn btn-default"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
