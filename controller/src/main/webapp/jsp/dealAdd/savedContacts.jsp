<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
          <div class="clearfix"></div>
          <input type="hidden" value="${savedContact.contactName}" name="saved_contact_name_${loop.index + 1}"/>


          <div class="col-xs-4 text-muted  text-right">Название компании:</div>
          <div class="col-xs-8 text-primary">${savedContact.companyName}</div>
          <div class="clearfix"></div>
          <input type="hidden" value="${savedContact.companyId}" name="saved_contact_companyId_${loop.index + 1}"/>
          <input type="hidden" value="${savedContact.companyName}" name="saved_contact_companyName_${loop.index + 1}"/>

          <div class="col-xs-4 text-muted  text-right">Название должности:</div>
          <div class="col-xs-8 text-primary">${savedContact.jobPosition}</div>
          <div class="clearfix"></div>
          <input type="hidden" value="${savedContact.jobPosition}" name="saved_contact_jobPosition_${loop.index + 1}"/>

          <div class="col-xs-4 text-muted  text-right">Телефон:</div>
          <div class="col-xs-8 text-primary">${savedContact.phoneTypeName} ${savedContact.phoneNumber}</div>
          <div class="clearfix"></div>
          <input type="hidden" value="${savedContact.phoneTypeId}" name="saved_contact_phoneTypeId_${loop.index + 1}"/>
          <input type="hidden" value="${savedContact.phoneTypeName}" name="saved_contact_phoneTypeName_${loop.index + 1}"/>
          <input type="hidden" value="${savedContact.phoneNumber}" name="saved_contact_phoneNumber_${loop.index + 1}"/>

          <div class="col-xs-4 text-muted  text-right">Email:</div>
          <div class="col-xs-8 text-primary">${savedContact.email}</div>
          <div class="clearfix"></div>
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
