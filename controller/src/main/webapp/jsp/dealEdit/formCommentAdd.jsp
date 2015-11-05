<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 11/4/15
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset>
  <legend>Добавить примечание</legend>
  <div class="form-group">
    <label for="add_comment" class="col-sm-3 control-label">Примечание к контакту</label>
    <div class="col-sm-8">
      <textarea id="add_comment" class="form-control" name="contact_comment" value=""></textarea>
    </div>
  </div>

  <div class="form-grou">
    <div class="col-sm-3"></div>
    <div class="col-sm-8 text-right">
      <input class="btn btn-default" name="add_file" type="button" value="Добавить файл" />
    </div>
  </div>

  <div class="form-group"></div>

  <div class="form-group">
    <div class="col-sm-3"></div>
    <div class="col-sm-8 text-center">
      <input class="btn  btn-primary" name="contact_comment_add" type="button" value="Готово" />
      <input class="btn  btn-default" name="contact_comment_cancel" type="button" value="Отмена" />
    </div>
  </div>
</fieldset>
