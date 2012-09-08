<%@ include file="../../includes.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div id="collWrapper">
    <div id="collMenuWrapper">
        <tiles:insertAttribute name="collMenu"/>
    </div>

    <div id="collContentWrapper">
        <tiles:insertAttribute name="collContent"/>
    </div>
</div>
