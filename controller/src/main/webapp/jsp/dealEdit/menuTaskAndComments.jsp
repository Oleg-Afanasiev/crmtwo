<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/1/15
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
  <div class="col-sm-6">
    <input type="button" class="btn btn-default" id="add_comment" value="Добавить примечание">
  </div>

  <div class="col-sm-6">
    <div id="block_company_comments" class="checkbox checkbox-primary">
      <input id="comments_for_company"  type="checkbox" checked="checked">
      <label for="comments_for_company">Примечания компании</label>
    </div>
  </div>

  <div id="block_crearfix"class="clearfix"></div>
  <div id="block_add_task" class="col-sm-6">
    <input type="button" class="btn btn-default" id="add_task" value="Добавить задачу">
  </div>

  <div class="col-sm-6">
    <div class="checkbox checkbox-primary">
      <input id="comments_for_contact"  type="checkbox"/>
      <label for="comments_for_contact">Примечания контакта</label>
    </div>
  </div>
</div>
