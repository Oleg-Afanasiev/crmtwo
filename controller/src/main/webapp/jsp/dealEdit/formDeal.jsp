<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/25/15
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fieldset>
  <legend>Редактирование сделки</legend>
  <div class="form-group">
    <label for="deal-name" class="col-sm-3 control-label">Название сделки</label>

    <div class="col-sm-8">
      <input id="deal-name" class="form-control" name="name" type="text"
             value="${deal.name}"/>
    </div>
  </div>

  <div class="form-group">
    <label for="tags" class="col-sm-3 control-label">Теги</label>

    <div class="col-sm-8">
      <%--pattern="(#[A-Za-zа-яА-я0-9]+\s*)+"--%>
      <input id="tags" class="form-control" name="tags" type="text" value="${dealTags}"
             placeholder="#tags"/>
    </div>
  </div>

  <div class="form-group">
    <label for="responsibleUser"
           class="col-sm-3 control-label">Ответственный</label>

    <div class="col-sm-8">
      <select id="responsibleUser" class="form-control" name="responsibleUser">
        <c:forEach items="${users}" var="user">
          <option value="${user.id}" ${deal.responsibleUser.id == user.id ? 'selected' : ''}>
              ${user.firstName} ${user.lastName}
          </option>
        </c:forEach>
      </select>
    </div>
  </div>

  <div class="form-group">
    <label for="budget" class="col-sm-3 control-label">Бюджет</label>

    <div class="col-sm-8">
      <div class="input-group">
        <input id="budget" class="form-control" name="budget" type="text"
               value="${deal.budget}"/>
        <span id="currency" class="input-group-addon">USD</span>
      </div>
    </div>
  </div>

  <div class="form-group">
    <label for="status" class="col-sm-3 control-label">Этап</label>

    <div class="col-sm-8">
      <select id="status" class="form-control" name="status">
        <c:forEach items="${dealStatuses}" var="dealStatus">
          <option value="${dealStatus.id}" ${deal.dealStatus.id == dealF.id ? 'selected' : ""}>
              ${dealStatus.name}
          </option>
        </c:forEach>
      </select>
    </div>
  </div>

  <div class="form-group">
    <div class="text-center">
      <input id="submit_button" type="submit" name="saveDeal" value="Обновить сделку" class="btn btn-primary"/>
      <input id="cancel_button" type="submit" name="deleteDeal" value="Удалить" class="btn btn-danger"/>
    </div>
  </div>
</fieldset>
