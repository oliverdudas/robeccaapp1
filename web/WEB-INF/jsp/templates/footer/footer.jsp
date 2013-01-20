<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ include file="../../includes.jsp" %>

<div id="footer">
    <span>
        <a href="<c:url value="/admin/ponuka.htm"/>"><fmt:message key="footer.title"/></a>
    </span>
    <%
        UserService userService = UserServiceFactory.getUserService();
        if (userService.isUserLoggedIn()) {

    %>
    <span>
        <a href="<%=userService.createLogoutURL("/")%>"><fmt:message key="logout"/></a>
    </span>
    <%
    } %>
</div>