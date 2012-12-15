<%@ include file="../includes.jsp" %>
<%--<script type="text/javascript" src="<c:url value="/js/jquery1.8.24.0/jquery-1.8.2.js"/>"/>--%>
<%--<script type="text/javascript" src="<c:url value="/js/jquery1.8.24.0/jquery.ui.core.js"/>"/>--%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"/>
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<script>
    $(function () {
        var dialog = $("#dialog").dialog({
            autoOpen:false,
            modal:true,
            width:500,
            title:'<fmt:message key="add.new.record"/>',
            open:function (event, ui) {
                $("#dialog").css({
                    display:'block'
                });
            }
        });

        $('#openDialog').click(function (e) {
            e.preventDefault();

            $("#dialog").find('.inputId').val('');
            $("#dialog").find('.inputName').val('');

            var labelsSize = parseInt('${labelsSize}');
            populateOrderSelect(labelsSize, labelsSize + 1);

            $("#dialog").dialog({ title:"<fmt:message key="new.record"/>" });
            $("#dialog").dialog('open');
        });

        <c:if test="${hasErrors != null}">
        $("#dialog").dialog({ title:"<fmt:message key="form.errors"/>" });
        $("#dialog").dialog('open');
        </c:if>

        $('.edit').click(function (e) {
            var labelRow = $(this).parent('td').parent('tr.labelRow');
            var labelId = labelRow.find('.labelId').html();
            var labelName = labelRow.find('.labelName').html();
            var labelOrder = labelRow.find('.labelOrder').html();

            $("#dialog").find('.inputId').val(labelId);
            $("#dialog").find('.inputName').val(labelName);
            $("#dialog").find('.inputOrder').val(labelOrder);

            var labelsSize = parseInt('${labelsSize}');
            populateOrderSelect(labelsSize - 1, parseInt(labelOrder));

            $("#dialog").dialog({ title:"<fmt:message key="record"/>" });
            $("#dialog").dialog('open');
        });

        $('.delete').click(function (e) {
            var instance = this;
            $(function () {
                $("#dialog-confirm").dialog({
                    resizable:false,
                    height:220,
                    width:400,
                    title:'<fmt:message key="delete.confirm.title"/>',
                    modal:true,
                    open:function (event, ui) {
                        var labelRow = $(instance).parent('td').parent('tr.labelRow');
                        var labelName = labelRow.find('.labelName').html();
                        var content = $(this).find('.dialog-confirm-item');
                        content.html(labelName);

                        $("#dialog-confirm").css({
                            display:'block'
                        });
                    },
                    buttons:{
                        "<fmt:message key="delete"/>":function () {
                            var labelRow = $(instance).parent('td').parent('tr.labelRow');
                            var labelId = labelRow.find('.labelId').html();

                            var url = '<c:url value="/admin/settingDelete.htm"/>';
                            window.location = url + '?id=' + labelId;
                        },
                        "<fmt:message key="cancel"/>":function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });
        });

        function populateOrderSelect(size, selectedIndex) {
            $('#orderSelect option').remove();
            for (var i = 1; i <= size + 1; i++) {
                var selected = i == selectedIndex;
                var option = $('<option></option>');
                option.attr('value', i);
                option.html(i);
                if (selected) {
                    option.attr('selected', 'selected');
                }
                $('#orderSelect').append(option);
            }
        }

        $(function () {
            $("input[type=button], input[type=submit]").button();
        });
    });
</script>

<div id="dialog" title="Basic dialog" style="display: none;">
    <jsp:include page="settingForm.jsp"/>
</div>
<div id="dialog-confirm" title="Empty the recycle bin?" style="display: none;text-align: center;">
    <p>
        <span><fmt:message key="delete.confirm"/></span>
    </p>

    <div class="dialog-confirm-item" style="font-weight: bold;padding-top: 10px;">
    </div>
</div>

<div id="settingsWrapper">

    <div style="text-align: left;margin: 5px;">
        <input type="button" id="openDialog" value="<fmt:message key="add.new.record"/>">
    </div>

    <table id="menuLabelTable">
        <thead>
        <tr>
            <th style="display: none;">id</th>
            <th><fmt:message key="title"/></th>
            <th><fmt:message key="order"/></th>
            <th colspan="2"/>
        </tr>
        </thead>
        <tbody>
        <%--@elvariable id="labels" type="java.util.List<sk.dudas.appengine.robecca.domain.MenuLabel>"--%>
        <c:forEach items="${labels}" var="label">
            <tr class="labelRow">
                <td class="labelId" style="display: none;">${label.id}</td>
                <td class="labelName">${label.name}</td>
                <td class="labelOrder">${label.order}</td>
                <td><input type="button" value="<fmt:message key="edit"/>" class="edit"></td>
                <td><input type="button" value="<fmt:message key="delete"/>" class="delete"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>