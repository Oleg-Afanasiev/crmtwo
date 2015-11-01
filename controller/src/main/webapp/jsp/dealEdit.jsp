<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/21/15
  Time: 8:21 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Редактирование сделки</title>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="<c:url value="../resources/css/dealAdd.css" />">
    <%--<link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.vertical-tabs.css" />">--%>
    <%--<link rel="stylesheet" href="<c:url value="../resources/css/awesome-bootstrap-checkbox.css" />">--%>
    <link rel="stylesheet" href="<c:url value="../resources/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/build.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/signin.css"/>">
    <link rel="stylesheet" href="<c:url value="../resources/css/timeLineTaskComments.css"/>">
    <script type="text/javascript" src="<c:url value="../resources/js/dealEdit.js" />"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="fragments/menu.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Редактирование сделки (Work in progress!)
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" id="deal-edit-form" action="/crm/dealedit" method="POST"
                          enctype="multipart/form-data">

                        <div class="row">
                            <div class="col-md-6">
                                <jsp:include page="dealEdit/formDeal.jsp"/>
                            </div>
                            <div class="col-md-6">
                                <jsp:include page="dealEdit/formCompany.jsp" />
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-md-6">
                                <jsp:include page="dealEdit/formContacts.jsp" />
                            </div>
                            <div class="col-md-6">
                                <jsp:include page="dealEdit/formTasksAndComments.jsp" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
