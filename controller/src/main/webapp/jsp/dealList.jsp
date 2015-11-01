<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 9/17/15
  Time: 1:55 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
  <head>
      <title>Список сделок</title>
    <jsp:include page="fragments/headTag.jsp"/>
  </head>
<body>
  <div class="container">
    <div class="row">
      <jsp:include page="fragments/menu.jsp"/>
      <style>
        .link {
          text-decoration: underline;
        }
      </style>
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h3><b>Список сделок</b>
          <a href="/crm/dealadd" class="btn btn-info pull-right" id="deal-add-button" title="Добавить сделку">Добавить сделку</a>
        </h3>
        <c:if test="${empty deals}">
          There are no Deals
        </c:if>
        <c:if test="${not empty deals}">
          <table  class="table table-hover table-bordered table-striped"  border="1" cellpadding="8" cellspacing="0">
            <thead  style="background-color: #dff0d8;">
              <tr>
                <th>Название сделки</th>
                <th>Основной контакт</th>
                <th>Компания контакта</th>
                <th>Этап сделки</th>
                <th>Бюджет</th>
              </tr>
            </thead>
              <c:forEach items="${deals}" var="deal">
                <tr>
                  <td>
                    <a class="link" href="/crm/dealedit?id=${deal.id}" name="deal-edit-id" title="Редактировать сделку">
                      ${deal.name}
                    </a>
                  </td>

                  <c:if test="${not empty deal.contacts}">
                    <c:set var="stop" value="${false}"/>
                    <c:forEach items="${deal.contacts}" var="contact" varStatus="status">
                      <c:if test="${stop ne true}">
                        <td>
                          ${contact.name}
                        </td>
                        <td>
                          ${contact.company.name}
                        </td>
                      </c:if>
                      <c:set var="stop" value="${true}"/>
                    </c:forEach>
                  </c:if>

                  <c:if test="${empty deal.contacts}">
                    <td></td>
                    <td></td>
                  </c:if>

                  <td>
                    ${deal.dealStatus.name}
                  </td>
                  <td>
                    ${deal.budget} USD
                  </td>

                </tr>
              </c:forEach>
          </table>
        </c:if>

      </div>
    </div>
  </div>
</body>
</html>
