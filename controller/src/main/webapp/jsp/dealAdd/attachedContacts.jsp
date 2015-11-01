<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
