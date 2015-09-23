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
    <link rel="stylesheet" href="./resources/css/dealAdd.css">
    <script type="text/javascript" src="./resources/js/dealAdd.js"></script>
</head>
<body>
<h1 class="form-header">Новая сделка</h1>

<div class="wrapper">
  <form id="dealAdd_form" name="dealAdd" action="dealadd" method="POST">
    <table>
      <tbody>
      <tr>
        <td class="td-deal">
          <fieldset class="fieldset-deal">
            <legend>Сделка</legend>

            <div class="text-input-wrapper">
              <label for="name">Название сделки</label>
              <input type="hidden" name="id" value="1">
              <input id="name" name="name" type="text" value=""/><br />
            </div>

            <div class="text-input-wrapper">
              <label for="tags">Теги</label>
              <input id="tags" name="tags" type="text" placeholder="#tags" value=""/><br />
            </div>

            <div class="combo-wrapper">
              <label for="responsibleUser">Ответственный</label>
              <select id="responsibleUser" name="responsibleUser">
                <c:forEach items="${users}" var="user">
                 <option value="${user.getId()}">${user.getFirstName()} ${user.getLastName()}</option>
                </c:forEach>
              </select>
            </div>

            <div class="text-input-wrapper">
              <label for="budget">Бюджет</label>
              <input id="budget" name="budget" type="text" value=""/>
              <span id="currency">USD</span>
              <!-- <span id="space"> </span> -->
            </div>

            <div class="combo-wrapper">
              <label for="status">Этап</label>
              <select id="status" name="status">
                <c:forEach items="${dealStatuses}" var="dealStatus">
                  <option value="${dealStatus.getId()}">${dealStatus.getName()}</option>
                </c:forEach>
              </select>
            </div>

            <div class="text-area-wrapper">
              <label class="comment-label" for="comment">Примечание</label>
              <textarea id="comment" class="input" name="comments" rows="7" cols="30" value="Ответственная сделка"></textarea><br />
            </div>

            <fieldset class="fieldset-files">
              <legend>Файлы</legend>
              <fieldset class="list-files">
                <legend>Список файлов</legend>
                <ul>
                  <li>
                    Файл1.xls
                    <%--<input type="file" name="file[]" id="file_1" multiple="true"/>--%>
                  </li>
                  <li>Файл2.xls</li>
                  <li>Файл3.xls</li>
                </ul>
              </fieldset>
              <div class="button-add-files-wrapper">
                <input class="button" type="button" value="Добавить файлы"/>
              </div>
            </fieldset>
          </fieldset>
        </td>

        <td class="td-list-contacts">
          <fieldset class="fieldset-contacts">
            <legend>Контакты</legend>

            <label for="exist-contact">Имя Фамилия</label>
            <select id="exist-contact" name="exist-contact">
              <option value="0">--Выберите контакт--</option>
              <c:forEach items="${contacts}" var="contact">
                <option value="${contact.getId()}">${contact.getName()}</option>
              </c:forEach>
            </select>

            <div class="buttons-wrapper">
              <input id="add_contact_button" class="button" type="button" value="Добавить контакт" />
              <input id="cancel_contact_button" class="button" type="button" value="Отмена" />
            </div>

            <div id="dock_contacts_container" class="hidden">
              <fieldset class="list-contacts">
                <legend>Прикрепленные контакты</legend>
                <div class="accordion">
                  <ul  id="dock_contacts" >
                  </ul>

                  <div class="total-contacts">Итого: <span id="count_dock_contacts">0</span></div>
                  <input type="hidden" name="count_dock_contacts"/>
                </div>

              </fieldset>
            </div>

            <div id="add_contact_container" class="hidden">
              <div id="sub_add_contact" class="none">
                <fieldset class="add-contact">
                  <legend>Добавить контакт</legend>

                  <label for="add_name">Имя Фамилия</label>
                  <input id="add_name" type="text"/>

                  <label for="add_company_name">Название компании</label>
                  <select id="add_company_name">
                    <option value="0">--Выберите компанию--</option>
                    <c:forEach items="${companies}" var="company">
                      <option value="${company.getId()}">${company.getName()}</option>
                    </c:forEach>
                  </select>

                  <label for="add_position">Название должности</label>
                  <select id="add_position">
                    <option value="0">--Выберите должность--</option>
                    <c:forEach items="${jopPositions}" var="jopPosition">
                      <option value="${jopPosition}">${jopPosition}</option>
                    </c:forEach>
                  </select>

                  <label for="add_contact_phone">Телефон</label>
                  <select id="add_contact_phone" class="phone-type">
                    <option value="1" selected>Рабочий</option>
                    <option value="2">Раб. прямой</option>
                    <option value="3">Мобильный</option>
                    <option value="4">Факс</option>
                    <option value="5">Домашний</option>
                    <option value="6">Другой</option>
                  </select>
                  <input type="text" class="phone"/>

                  <label for="contact_email">Email</label>
                  <input id="contact_email" type="text"/>

                  <label for="contact_skype">Skype</label>
                  <input id="contact_skype" type="text"/>

                  <div class="buttons-wrapper">
                    <input id="add_save_contact_button" class="button" type="button" value="Сохранить контакт" />
                    <input id="add_cancel_contact_button" class="button" type="button" value="Отмена" />
                  </div>
                </fieldset>
              </div>
            </div>
          </fieldset>

          <fieldset class="fieldset-company">
            <legend>Компания</legend>

            <label for="company">Название компании</label>
            <select id="company" name="company">
              <option value="0">--Выберите компанию--</option>
              <c:forEach items="${companies}" var="company">
                <option value="${company.getId()}">${company.getName()}</option>
              </c:forEach>
            </select>

            <div class="buttons-wrapper">
              <input id="add_company_button" class="button" type="button" value="Добавить компанию" />
              <input id="cancel_company_button" class="button" type="button" value="Отмена" />
            </div>

            <div id="add_company_container" class="hidden">
              <div id="sub_add_company" class="none">
                <fieldset class="add-company">
                  <legend>Добавить компанию</legend>

                  <label for="add_company">Название компании</label>
                  <input id="add_company" type="text"/>

                  <label for="add_company_phone">Телефон</label>
                  <select id="add_company_phone" class="phone-type">
                    <option value="1" selected>Рабочий</option>
                    <option value="2">Раб. прямой</option>
                    <option value="3">Мобильный</option>
                    <option value="4">Факс</option>
                    <option value="5">Домашний</option>
                    <option value="6">Другой</option>
                  </select>
                  <input class="phone" type="text" />

                  <label for="company_email">Email</label>
                  <input id="company_email" type="text"/>

                  <label for="company_skype">Skype</label>
                  <input id="company_skype" type="text"/>

                  <label for="add_company_address">Адрес</label>
                  <textarea id="add_company_address" rows="4" cols="30"></textarea>

                  <div class="buttons-wrapper">
                    <input id="add_save_company_button" class="button" type="button" value="Сохранить компанию" />
                    <input id="add_cancel_company_button" class="button" type="button" value="Отмена" />
                  </div>
                </fieldset>
              </div>
            </div>
          </fieldset>
        </td>
      </tr>
      </tbody>
    </table>

    <div class="buttons-wrapper">
      <input id="submit_button" type="submit" value="Сохранить сделку" class="button button-submit"/>
      <input id="cancel_button" type="reset" value="Отмена" class="button button-cancel"/>
    </div>
  </form>
</div>
</body>
</html>
