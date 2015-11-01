<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
          <option value="${dealStatus.id}" ${dealFields.statusId == dealF.id ? 'selected' : ""}>
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
              <li class="list-group-item">
                <div>
                  <a href="#" class="close" title="Открепить файл">x</a>
                  <span></span>
                  <input type="file"  hidden />
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
        <input id="add_files_btn" class="btn btn-default text-right" type="button" value="Добавить файл"/>
      </div>
    </fieldset>
  </div>
</fieldset>
