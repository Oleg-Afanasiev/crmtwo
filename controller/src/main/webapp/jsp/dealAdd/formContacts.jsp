<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <input id="contact_add_button" class="btn btn-primary" type="button" value="Прикрепить контакт" />
      <input id="contact_cancel_button" class="btn btn-default" type="button" value="Отмена" />
    </div>
  </div>
</fieldset>
