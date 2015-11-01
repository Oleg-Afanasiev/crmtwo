<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/25/15
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset>
  <legend>Быстрое добавление контакта</legend>
  <div class="form-group">
    <label for="add_contact_name_${loop.index + 1}" class="col-sm-3 control-label">Имя Фамилия</label>
    <div class="col-sm-8">
      <input id="add_contact_name_${loop.index + 1}" class="form-control" type="text" name="contact_name_${loop.index + 1}" value="${dealContact.name}" />
    </div>
  </div>
  <div class="form-group">
    <label for="add_contact_company_name_${loop.index + 1}" class="col-sm-3 control-label">Название компании</label>
    <div class="col-sm-8">
      <select id="add_contact_company_name_${loop.index + 1}" class="form-control" name="contact_company_id_${loop.index + 1}">
        <option value="0">--Выберите компанию--</option>
        <c:forEach items="${companies}" var="company">
          <option value="${company.id}" ${dealContact.company.id == company.id ? 'selected' : ""}>
              ${company.name}
          </option>
        </c:forEach>
      </select>
    </div>
  </div>
  <div class="form-group">
    <label for="add_contact_job_position_${loop.index + 1}" class="col-sm-3 control-label">Название должности</label>
    <div class="col-sm-8">
      <input id="add_contact_job_position_${loop.index + 1}" class="form-control" type="text" name="contact_job_position_${loop.index + 1}" value="${dealContact.jobPosition}"/>
    </div>
  </div>
  <div class="form-group">
    <%--<label for="add_contact_phone_type" class="col-sm-3 control-label">Телефон</label>--%>
    <div class="col-sm-3">
      <select id="add_contact_phone_type_${loop.index + 1}" class="form-control" name="contact_phone_type_id_${loop.index + 1}">
        <c:forEach items="${phoneTypes}" var="phoneType">
          <option value="${phoneType.id}" ${dealContact.phones.iterator().next().phoneType == phoneType.id ? 'selected' : ""}>${phoneType.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="col-sm-8">
      <input id="add_contact_phone_number_${loop.index + 1}" class="form-control" type="text" name="contact_phone_number_${loop.index + 1}" value="${dealContact.phones.iterator().next().number}"/>
    </div>
  </div>
  <div class="form-group">
    <label for="add_contact_email_${loop.index + 1}" class="col-sm-3 control-label">Email</label>
    <div class="col-sm-8">
      <%--pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\.[a-z]{2,3}$"--%>
      <input id="add_contact_email_${loop.index + 1}" class="form-control" type="text" name="contact_email_${loop.index + 1}" value="${dealContact.email}"/>
    </div>
  </div>
  <div class="form-group">
    <label for="add_contact_skype_${loop.index + 1}" class="col-sm-3 control-label">Skype</label>
    <div class="col-sm-8">
      <input id="add_contact_skype_${loop.index + 1}" class="form-control" type="text" name="contact_skype_${loop.index + 1}" value="${dealContact.skype}" />
    </div>
  </div>
</fieldset>
