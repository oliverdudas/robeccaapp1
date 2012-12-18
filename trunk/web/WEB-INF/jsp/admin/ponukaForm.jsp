<%@ include file="../includes.jsp" %>

<div id="settingsWrapper">

    <form:form commandName="command" action="/admin/ponukaForm.htm">

        <table style="width: 450px; height: 100px;">
            <tr style="display: none;">
                <td>id<span class="required">*</span>:</td>
                <td><form:input path="id" cssClass="inputId"/></td>
                <td class="error"><form:errors path="name"/></td>
            </tr>
            <tr>
                <td><fmt:message key="title"/><span class="required">*</span>:</td>
                <td><form:input path="name" cssClass="inputName"/></td>
                <td class="error"><form:errors path="name"/></td>
            </tr>
            <tr>
                <td><fmt:message key="album"/><span class="required">*</span>:</td>
                <td>
                    <%--@elvariable id="webAlbum" type="sk.dudas.appengine.robecca.service.cache.WebAlbumDto"--%>
                    <form:select path="album" cssClass="inputAlbum" id="albumSelect">
                        <form:option value=""></form:option>
                        <c:forEach items="${webAlbum.albums}" var="album">
                            <form:option value="${album.idAndTitle}">${album.title}</form:option>
                        </c:forEach>
                    </form:select>
                </td>
                <td class="error"><form:errors path="album"/></td>
            </tr>
            <tr>
                <td><fmt:message key="order"/><span class="required">*</span>:</td>
                <td>
                    <%--@elvariable id="labelsSizeItems" type="java.util.List<Long>"--%>
                    <form:select path="order" id="orderSelect" items="${labelsSizeItems}" cssClass="inputOrder"/>
                </td>
                <td class="error"><form:errors path="order"/></td>
            </tr>
        </table>

        <div>
            <input type="submit" name="newLabelSubmit" value="<fmt:message key="save"/>">
        </div>

    </form:form>

</div>