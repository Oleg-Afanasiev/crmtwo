<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fieldset>
  <legend>Компания</legend>
  <div class="form-group">
    <label for="company" class="col-sm-3 control-label">Название компании</label>
    <div class="col-sm-8">
      <select id="company" class="form-control" name="company">
        <option value="0">--Выберите компанию--</option>
        <c:forEach items="${companies}" var="company">
          <option value="${company.id}" ${company.id.toString() == dealCompanyFields.id ? 'selected' : ''}>
              ${company.name}
          </option>
        </c:forEach>
      </select>
    </div>
  </div>

  <div class="form-group">
    <div class="text-center">
      <input id="company_add_button" class="btn btn-primary" type="button" value="Прикрепить компанию" />
      <input id="company_cancel_button" class="btn btn-default" type="button" value="Отмена" />
    </div>
  </div>
</fieldset>
