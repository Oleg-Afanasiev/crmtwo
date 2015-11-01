<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/25/15
  Time: 10:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset>
  <legend>Контакты</legend>
  <div class="tabs-right">
    <ul id="tab_contacts" class="nav nav-tabs">
      <c:forEach items="${dealContacts}" var="dealContact" varStatus="loop">
        <li ${mrkActive}>
          <a data-toggle="tab" href="#deal_contact_${dealContact.id}">
              ${dealContact.name}
          </a>
        </li>
      </c:forEach>
    </ul>
    <div class="tab-content">
      <c:forEach items="${dealContacts}" var="dealContact" varStatus="loop">
        <div id="deal_contact_${dealContact.id}" class="tab-pane ${mrkActive}">
          <div class="form-group">
            <label for="add_contact_name_${loop.index + 1}" class="col-sm-3 control-label">Имя Фамилия</label>
            <div class="col-sm-8">
              <input id="add_contact_name_${loop.index + 1}" class="form-control" type="text" name="contact_name_${loop.index + 1}" value="${dealContact.name}" />
            </div>
            <div class="col-sm-3"></div>
            <div class="col-sm-8 text-right">
              <a href="/crm/editContact?id=${dealContact.id}" class="btn btn-xs btn-info" name="contact_edit_${dealContact.id}">Перейти в редактор контакта</a>
              <input class="btn btn-xs btn-info" name="contact_unattached_${dealContact.id}" type="button" value="Открепить" />
              <input class="btn btn-xs btn-info" name="contact_make_primary_${dealContact.id}" type="button" value="Сделать основным" />
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
            <div class="col-sm-3"></div>
            <div class="col-sm-8 text-right">
              <input class="btn btn-xs btn-info" name="company_edit_${company.id}" type="button" value="Перейти в редактор компании" />
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
          <div class="form-group">
            <div class="col-sm-3"></div>
            <div class="col-sm-8 text-left">
              <input id="show_empty_fields_contact_${dealContact.id}" class="btn btn-xs btn-default" type="button" name="show_empty_fields_contact" value="Показать все поля" />
            </div>
          </div>
          <div class="form-group">
            <div class="text-center">
              <input type="submit" name="edit_contact_${dealContact.id}" value="Сохранить" class="btn btn-primary"/>
              <input type="submit" name="cancel_contact_${dealContact.id}" value="Отмена" class="btn btn-default"/>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
  <div id="add_contact_container" hidden>
    <jsp:include page="formContactAdd.jsp" />
  </div>
  <div class="form-group">
    <div class="text-center">
      <input id="add_contact_btn" type="button"  name="add_contact" value="Добавить контакт" class="btn btn-primary"/>
      <input id="cancel_add_contact_btn" type="button" name="cancel_contact" value="Отмена" class="btn btn-default"/>
    </div>
  </div>
</fieldset>
