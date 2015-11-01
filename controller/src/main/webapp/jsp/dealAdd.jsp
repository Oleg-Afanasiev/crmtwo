<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 9/17/15
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Добавление сделки</title>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="<c:url value="../resources/css/dealAdd.css" />">
    <script type="text/javascript" src="<c:url value="../resources/js/dealAdd.js" />"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="fragments/menu.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row">
                <div>
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Добавление сделки
                            </h3>
                        </div>
                        <div class="panel-body">
                            <form id="dealAdd_form" class="form-horizontal" name="dealAdd" action="/crm/dealadd" method="POST" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-md-6">

                                        <jsp:include page="dealAdd/formDeal.jsp"/>
                                        <div id="attached_contacts_container" class="col-sm-11" hidden>
                                            <jsp:include page="dealAdd/attachedContacts.jsp"/>
                                        </div>

                                        <div id="attached_company_container" class="col-sm-11" hidden>
                                            <jsp:include page="dealAdd/attachedCompany.jsp"/>
                                        </div>

                                        <div class="form-group"></div>
                                        <div class="form-group"></div>
                                    </div>

                                    <div class="col-md-6">
                                        <div>
                                            <jsp:include page="dealAdd/formContacts.jsp"/>
                                        </div>
                                        <c:if test="${savedContacts.size() > 0}">
                                            <div id="saved_contact_item">
                                                <jsp:include page="dealAdd/savedContacts.jsp"/>
                                            </div>
                                        </c:if>

                                        <div id="add_contact_container">
                                            <jsp:include page="dealAdd/formContactAdd.jsp" />
                                        </div>
                                        </fieldset>
                                        <div class="form-group"></div>

                                        <div>
                                            <jsp:include page="dealAdd/formCompany.jsp"/>
                                            <div id="add_company_container">
                                                <jsp:include page="dealAdd/formCompanyAdd.jsp" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group"></div>

                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="submit_button" type="submit" name="saveDeal" value="Сохранить сделку" class="btn btn-primary"/>
                                        <input id="cancel_button" type="reset" value="Отмена" class="btn btn-default"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
