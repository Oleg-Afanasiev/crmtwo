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
    <script type="text/javascript" src="./resources/js/fileUpload.js"></script>
    <meta charset="utf-8">
</head>
<body>
<h1 class="form-header">Новая сделка</h1>

<div class="wrapper">
  <form id="dealAdd_form" name="dealAdd" action="dealadd" method="POST" enctype="multipart/form-data">
    <table>
      <tbody>
      <tr>
        <td class="td-deal">
          <fieldset class="fieldset-deal">
            <legend>Сделка</legend>

            <div class="text-input-wrapper">
              <label for="name">Название сделки</label>
              <input type="hidden" name="id" value="1">
              <input id="name" name="name" type="text" value="${nameInput}"/><br />
              <c:if test="${dealInputError.getName() eq 'name'}">
                <div class="error">"Поле "Название сделки" должно быть заполнено"</div>
              </c:if>

            </div>

            <div class="text-input-wrapper">
              <label for="tags">Теги</label>
              <input id="tags" name="tags" type="text" placeholder="#tags" value="${tagsInput}"/><br />
              <c:if test="${dealInputError.getName() eq 'tags empty'}">
                <div class="error">Поле "Тэги" должно быть заполнено</div>
              </c:if>

              <c:if test="${dealInputError.getName() eq 'tags #'}">
                <div class="error">Каждый тэг должен начинаться с '#'</div>
              </c:if>
            </div>

            <div class="combo-wrapper">
              <label for="responsibleUser">Ответственный</label>
              <select id="responsibleUser" name="responsibleUser">
                <c:forEach items="${users}" var="user">
                 <option value="${user.getId()}" ${responsibleUserIdInput == user.getId() ? 'selected' : ''}>
                  ${user.getFirstName()} ${user.getLastName()}
                 </option>
                </c:forEach>
              </select>

              <c:if test="${dealInputError.getName() eq 'responsible user'}">
                <div class="error">Выберите из списка "Ответственный"</div>
              </c:if>
            </div>

            <div class="text-input-wrapper">
              <label for="budget">Бюджет</label>
              <input id="budget" name="budget" type="text" value="${budget}"/>
              <span id="currency">USD</span>

              <c:if test="${dealInputError.getName() eq 'budget empty'}">
                <div class="error">Необходимо заполнить поле "Бюджет"</div>
              </c:if>

              <c:if test="${dealInputError.getName() eq 'budget format'}">
                <div class="error">Некорректные данные в поле "Бюджет"</div>
              </c:if>
              <!-- <span id="space"> </span> -->
            </div>

            <div class="combo-wrapper">
              <label for="status">Этап</label>
              <select id="status" name="status">
                <c:forEach items="${dealStatuses}" var="dealStatus">
                  <option value="${dealStatus.getId()}" ${statusId == dealStatus.getId() ? 'selected' : ""}>
                    ${dealStatus.getName()}
                  </option>
                </c:forEach>
              </select>

              <c:if test="${dealInputError.getName() eq 'status'}">
                <div class="error">Выберите из списка "Этап"</div>
              </c:if>
            </div>

            <div class="text-area-wrapper">
              <label class="comment-label" for="comment">Примечание</label>
              <textarea id="comment" class="input" name="comments" rows="7" cols="30" value="Ответственная сделка">${comments}</textarea><br />
            </div>

            <fieldset class="fieldset-files">
              <legend>Файлы</legend>
              <fieldset class="list-files">
                <legend>Список файлов</legend>
                <ul id="uploaded_files_list">

                </ul>

                <div id="uploaded_files_container" class="hidden">

                </div>

                <input type="hidden" name="count_uploaded_files" value="0" />

              </fieldset>

              <div id="total_uploaded_files" class="hidden total">Кол-во загруженных: <span id="count_uploaded_files">0</span></div>
              <div class="button-add-files-wrapper">
                <%--<input id="file_input" class="hidden" type="file" name="file" />--%>
                <input id="add_files_btn" class="button" type="button" value="Добавить файлы"/>
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
                <option value="${contact.getId()}">
                  ${contact.getName()}
                </option>
              </c:forEach>

              <c:forEach items="${newContactsFields}" var="newContactFields">
                <option value="${newContactFields.getContactId()}">
                  ${newContactFields.getContactName()}
                </option>
              </c:forEach>
            </select>

            <div class="buttons-wrapper">
              <input id="add_contact_button" class="button" type="button" value="Добавить контакт" />
              <input id="cancel_contact_button" class="button" type="button" value="Отмена" />
            </div>

            <div id="dock_contacts_container" class="hidden"}>
              <fieldset class="list-contacts">
                <legend>Прикрепленные контакты</legend>
                <div class="accordion">
                  <ul  id="dock_contacts" >
                    <c:forEach items="${dealContactsFields}" var="contactFields">
                      <li class="li-dock-contact">
                        <section id="id_contact_${contactFields.getContactId()}">
                          <a class="a-undock" title="Открепить контакт" href="#">x</a>
                          <a class="a-contact" href="#">${contactFields.getContactName()}</a>
                          <input name="dock_contact_${contactFields.getContactId()}"  type="hidden" value="${contactFields.getContactId()}">
                        </section>
                      </li>
                    </c:forEach>
                  </ul>

                  <div class="total">Итого: <span id="count_dock_contacts">0</span></div>
                  <input type="hidden" name="count_dock_contacts" value="0"/>
                </div>

              </fieldset>
            </div>

            <div id="add_contact_container" class="hidden">
              <div id="sub_add_contact" class="none">
                <fieldset class="add-contact">
                  <legend>Добавить контакт</legend>

                  <label for="add_contact_name">Имя Фамилия</label>
                  <input id="add_contact_name" type="text"/>

                  <label for="add_contact_company_name">Название компании</label>
                  <select id="add_contact_company_name">
                    <option value="0">--Выберите компанию--</option>
                    <c:forEach items="${companies}" var="company">
                      <option value="${company.getId()}">
                        ${company.getName()}
                      </option>
                    </c:forEach>

                    <c:forEach items="${newCompaniesFields}" var="newCompanyFields">
                      <option value="${newCompanyFields.getCompanyId()}">
                        ${newCompanyFields.getCompanyName()}
                      </option>
                    </c:forEach>
                  </select>

                  <label for="add_contact_job_position">Название должности</label>
                  <input id="add_contact_job_position" type="text" />

                  <label for="add_contact_phone_type">Телефон</label>
                  <select id="add_contact_phone_type" class="phone-type">
                    <c:forEach items="${phoneTypes}" var="phoneType">
                      <option value="${phoneType.getPhoneType()}">${phoneType.getName()}</option>
                    </c:forEach>
                  </select>
                  <input id="add_contact_phone_number" type="text" class="phone"/>

                  <label for="add_contact_email">Email</label>
                  <input id="add_contact_email" type="text"/>

                  <label for="add_contact_skype">Skype</label>
                  <input id="add_contact_skype" type="text"/>

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
                <option value="${company.getId()}" ${company.getId().toString() == dealCompanyFields.getCompanyId() ? 'selected' : ''}>
                  ${company.getName()}
                </option>
              </c:forEach>

              <c:forEach items="${newCompaniesFields}" var="newCompanyFields">
                <option value="${newCompanyFields.getCompanyId()}" ${newCompanyFields.getCompanyId() == dealCompanyFields.getCompanyId() ? 'selected' : ""}>
                     ${newCompanyFields.getCompanyName()}
                </option>
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

                  <label for="add_company_name">Название компании</label>
                  <input id="add_company_name" type="text"/>

                  <label for="add_company_phone_type">Телефон</label>
                  <select id="add_company_phone_type" class="phone-type">
                    <c:forEach items="${phoneTypes}" var="phoneType">
                      <option value="${phoneType.getPhoneType()}">${phoneType.getName()}</option>
                    </c:forEach>
                  </select>
                  <input id="add_company_phone_number" class="phone" type="text" />

                  <label for="add_company_email">Email</label>
                  <input id="add_company_email" type="text"/>

                  <label for="add_company_web_address">Web address</label>
                  <input id="add_company_web_address" type="text"/>

                  <label for="add_company_address">Адрес</label>
                  <textarea id="add_company_address" rows="4" cols="30"></textarea>

                  <div class="buttons-wrapper">
                    <input id="add_save_company_button" class="button" type="button" value="Сохранить компанию" />
                    <input id="add_cancel_company_button" class="button" type="button" value="Отмена" />
                  </div>
                </fieldset>
              </div>
            </div>
            <div class="hidden">
              <input id="count_new_contacts" name="count_new_contacts" type="hidden" value="0" />
              <ul id="added_contact_list">
                <c:forEach items="${newContactsFields}" var="contactFields" varStatus="counter">
                  <li>
                    <input type="hidden" name="contact_name_${counter.count}" value="${contactFields.getContactName()}">
                    <input type="hidden" name="contact_companyId_${counter.count}" value="${contactFields.getCompanyId()}">
                    <input type="hidden" name="contact_jobPosition_${counter.count}" value="${contactFields.getJobPosition()}">
                    <input type="hidden" name="contact_phoneTypeId_${counter.count}" value="${contactFields.getPhoneTypeId()}">
                    <input type="hidden" name="contact_phoneNumber_${counter.count}" value="${contactFields.getPhoneNumber()}">
                    <input type="hidden" name="contact_email_${counter.count}" value="${contactFields.getEmail()}">
                    <input type="hidden" name="contact_skype_${counter.count}" value="${contactFields.getSkype()}">
                    <input type="hidden" name="contact_pseudoId_${counter.count}" value="${contactFields.getContactId()}">
                  </li>
                </c:forEach>
              </ul>
            </div>

            <div class="hidden">
              <input id="count_new_companies" name="count_new_companies" type="hidden" value="0" />
              <ul id="added_company_list">
                <c:forEach items="${newCompaniesFields}" var="newCompanyFields" varStatus="counter">
                  <li>
                    <input type="hidden" name="company_name_${counter.count}" value="${newCompanyFields.getCompanyName()}">
                    <input type="hidden" name="company_phoneTypeId_${counter.count}" value="${newCompanyFields.getPhoneTypeId()}">
                    <input type="hidden" name="company_phoneNumber_${counter.count}" value="${newCompanyFields.getPhoneNumber()}">
                    <input type="hidden" name="company_email_${counter.count}" value="${newCompanyFields.getEmail()}">
                    <input type="hidden" name="company_webAddress_${counter.count}" value="${newCompanyFields.getWebAddress()}">
                    <input type="hidden" name="company_address_${counter.count}" value="${newCompanyFields.getAddress()}">
                    <input type="hidden" name="company_pseudoId_${counter.count}" value="${newCompanyFields.getCompanyId()}">
                  </li>
                </c:forEach>
              </ul>
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
