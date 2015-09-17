<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 9/17/15
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новая сделка</title>
    <link rel="stylesheet" href="./dealAdd.css">
</head>
<body>
  <h1 class="form-header">Новая сделка</h1>

  <div class="wrapper">
    <form id="dealAdd_form" action="dealAdd" method="POST">

      <fieldset>
        <legend>Сделка</legend>

        <div class="text-input-wrapper">
          <label for="name">Название сделки</label>
          <input type="hidden" name="id" value="1">
          <input id="name" name="name" type="text" value=""/><br />
        </div>

        <div class="text-input-wrapper">
          <label for="tags">Теги</label>
          <input id="tags" name="tags" type="text" value=""/><br />
        </div>

        <div class="combo-wrapper">
          <label for="responsibleUser">Ответственный</label>
          <select id="responsibleUser" name="responsibleUser">
            <c:forEach items="${users}" var="user">
              <option value="${user.id}">${user.name}</option>
            </c:forEach>
          </select>
        </div>

        <div class="text-input-wrapper">
          <label for="budget">Бюджет</label>
          <input id="budget" name="budget" type="text" value="30000,34"/>
          <br />
        </div>

        <div class="combo-wrapper">
          <label for="status">Ответственный</label>
          <select id="status" name="status">
            <option value="1">идут переговоры</option>
            <option value="2">сделали предложение</option>
          </select>
        </div>

        <div class="text-area-wrapper">
          <label for="comments">Примечание</label>
          <textarea id="comments" class="input" name="comments" rows="7" cols="30" value="Ответственная сделка"></textarea><br />
        </div>
      </fieldset>

      <div class="buttons-wrapper">
        <input id="submit_button" type="submit" value="Сохранить" class="button button-submit"/>
        <input id="cancel_button" type="reset" value="Сохранить" class="button button-cancel"/>
      </div>
    </form>
  </div>
</body>
</html>
