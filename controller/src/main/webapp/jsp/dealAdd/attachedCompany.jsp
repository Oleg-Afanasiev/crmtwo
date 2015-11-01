<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fieldset>
  <legend>Прикрепленная компания</legend>
  <div id="attached_company_item" hidden>
    <div class="alert alert-info">
      <a href="#" class="close" data-dismiss="alert" title="Открепить компанию">x</a>
      <span>Некоторая компания</span>
      <input type="hidden" value="" />
      <input type="hidden" value="" />
    </div>
  </div>
  <div id="attached_company">
    <c:forEach items="${dealFields.companies}" var="company">
      <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert" title="Открепить компанию">x</a>
        <span>${company.name}</span>
        <input type="hidden" name="attached_company_id" value="${company.id}" />
        <input type="hidden" name="attached_company_name" value="${company.name}" />
      </div>
    </c:forEach>
  </div>
</fieldset>
