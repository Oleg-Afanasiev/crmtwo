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
  <jsp:include page="timeLineTaskAndComments.jsp"/>

</fieldset>


