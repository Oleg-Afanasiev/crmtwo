<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/25/15
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset>
  <legend>Компания</legend>

  <div class="form-group">
    <label for="add_company_name" class="col-sm-3 control-label">Название компании</label>
    <div class="col-sm-8">
      <input id="add_company_name" class="form-control" type="text" name="company_name" value="${dealCompany.name}" />
    </div>
    <div class="col-sm-3"></div>
    <div class="col-sm-8 text-right">
      <a href="/crm/editCompany?id=${dealCompany.id}" class="btn btn-xs btn-info">Перейти в редактор компании</a>
      <input class="btn btn-xs btn-info" name="company_unattached_${dealCompany.id}" type="button" value="Открепить" />
    </div>
  </div>

  <div class="form-group">
    <%--<label for="add_company_phone_type" class="col-sm-3 control-label">Телефон</label>--%>
    <div class="col-sm-3">
      <select id="add_company_phone_type"  class="form-control" name="company_phone_type_id">
        <c:forEach items="${phoneTypes}" var="phoneType">
          <option value="${phoneType.id}" ${phoneType.id == dealCompany.phones.iterator().next().phoneType ? "selected" : ""}>${phoneType.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="col-sm-8">
      <input id="add_company_phone_number" class="form-control" type="text" name="company_phone_number" value="${dealCompany.phones.iterator().next().number}"/>
    </div>
  </div>

  <div class="form-group">
    <label for="add_company_email" class="col-sm-3 control-label">Email</label>
    <div class="col-sm-8">
      <%--pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\.[a-z]{2,3}$"--%>
      <input id="add_company_email" class="form-control" type="text" name="company_email"  value="${dealCompany.email}" />
    </div>
  </div>

  <div class="form-group">
    <label for="add_company_web_address" class="col-sm-3 control-label">Web address</label>
    <div class="col-sm-8">
      <input id="add_company_web_address" class="form-control" name="company_web_address" type="text" value="${dealCompany.webAddress}" />
    </div>
  </div>

  <div class="form-group">
    <label for="add_company_address" class="col-sm-3 control-label">Адрес</label>
    <div class="col-sm-8">
      <textarea id="add_company_address" class="form-control" name="company_address" rows="3" cols="30" >${dealCompany.address}</textarea>
    </div>
  </div>

  <div class="form-group">
    <div class="text-center">
      <input type="submit" name="edit_company_${dealCompany.id}" value="Сохранить" class="btn btn-primary"/>
      <input type="submit" name="cancel_company_${dealCompany.id}" value="Отмена" class="btn btn-default"/>
    </div>
  </div>
</fieldset>

