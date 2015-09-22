<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRM Authorize Error</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
    <div class="container">
        <div class="row">
            <jsp:include page="fragments/menu.jsp"/>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <div class="page-header">
                    <h1>CRM System</h1>
                </div>
                <label style="color: red;">You don't have permission to access this page!</label>
            </div>
        </div>
    </div>
</body>
</html>
