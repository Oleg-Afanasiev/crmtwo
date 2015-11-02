<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fieldset>
  <legend>Добавить контакт</legend>

  <c:if test="${dealInputError.name eq 'contact: name is incorrect'}">
    <c:set var="mrkContactNameError" value="has-error" scope="page" />
    <c:set var="msgContactNameError" value="placeholder='Введите корретно Имя Фамилия'" scope="page" />
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
