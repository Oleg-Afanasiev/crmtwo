<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10/25/15
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset>
  <legend>Задачи и примечания</legend>
  <jsp:include page="menuTaskAndComments.jsp" />
  <div id="add_contact_comment_container" hidden>
    <jsp:include page="formCommentAdd.jsp" />
  </div>

  <div id="add_task_container">
    <jsp:include page="formTaskAdd.jsp" />
  </div>
  <jsp:include page="timeLineTaskAndComments.jsp"/>

</fieldset>


