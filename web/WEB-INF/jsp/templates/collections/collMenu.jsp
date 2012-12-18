<%@ include file="../../includes.jsp" %>

<ul class="collNav">

    <%--@elvariable id="menuLabels" type="java.util.List<sk.dudas.appengine.robecca.domain.MenuLabel>"--%>
    <c:forEach items="${menuLabels}" var="label">
        <li>
            <c:url var="albumURL" value="/collections/album.htm">
                <c:param name="id" value="${label.id}"/>
            </c:url>
            <a href="${albumURL}">${label.name}</a>
        </li>
    </c:forEach>

</ul>