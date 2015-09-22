<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Список контктов</title>
        <jsp:include page="fragments/headTag.jsp"/>
        <style>
            .link {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <jsp:include page="fragments/menu.jsp"/>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <H3><b>Список контактов</b>
                        <a href = "/crm/contactadd" class="btn btn-info pull-right" id="contact-add-button" title="Добавить контакт">Добавить контакт</a>
                    </H3>
                    <c:if test="${empty contacts}">
                        There are no Contacts
                    </c:if>
                    <c:if test="${not empty contacts}">
                        <table class="table table-hover table-bordered table-striped"  border="1" cellpadding="8" cellspacing="0">
                            <thead style="background-color: #dff0d8;">
                            <%--<thead>--%>
                                <tr>
                                    <th>Наименование</th>
                                    <th>Компания</th>
                                    <th>Телефон</th>
                                    <th>Email</th>
                                </tr>
                            </thead>
                            <c:forEach items="${contacts}" var="contact">
                                <tr>
                                    <td>
                                        <a class="link" href="/crm/contactedit?id=${contact.id}" name="contact-edit-id" id="contact-edit-id" title="Редактировать контакт">${contact.name}</a>
                                    </td>
                                    <td>
                                        <c:if test="${not empty contact.company}">
                                            <a class="link" href="#<%--companyEditForm?id=${contact.id}--%>" name="company-edit-id" title="Редактировать компанию" style="color:black;">${contact.company.name}
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${not empty contact.phones}">
                                            <c:forEach items="${contact.phones}" var="phone">${phone.number}<br></c:forEach>
                                        </c:if>
                                    </td>
                                    <td>${contact.email}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>

