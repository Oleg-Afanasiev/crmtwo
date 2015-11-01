<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 9:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
