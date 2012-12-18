<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ include file="../../includes.jsp" %>

<div id="footer">
    <span>
        <a href="<c:url value="/home.htm"/>"><fmt:message key="footer.title"/></a>
    </span>
    <%
        UserService userService = UserServiceFactory.getUserService();
    %>
    <c:set var="isUserAdmin" value="<%=userService.isUserLoggedIn() && userService.isUserAdmin()%>"/>


    <c:if test="${isUserAdmin}">
        <span>
            <a href="<c:out value="/admin/ponuka.htm"/>"><fmt:message key="settings"/></a>
        </span>
    </c:if>
</div>