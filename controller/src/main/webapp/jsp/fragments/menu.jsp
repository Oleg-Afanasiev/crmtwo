<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<div class="col-sm-3 col-md-2 sidebar">
    <div id="sidebar-content">
        <ul class="nav nav-sidebar">
            <li class="active"><a href="/">CRM System<span class="sr-only">(current)</span></a></li>
            <li><a href="/crm/contactlist">Список контактов</a></li>
            <li><a href="/crm/deallist">Список сделок</a></li>
        </ul>
        <ul id="logout-sidebar-button" class="nav nav-sidebar">
            <li>
                <form action="/logout" method="post">
                    <div class="form-group">
                        <label class="control-label col-xs-offset-2">
                            ${activeUser.userName} (${activeUser.role.name})
                        </label>
                        <input type="submit" class="col-xs-offset-2 btn btn-primary" name="logout" value="Logout"/>
                    </div>
                </form>
            </li>
        </ul>
    </div>
</div>