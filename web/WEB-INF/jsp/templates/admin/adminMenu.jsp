<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"/>
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<script type="text/javascript">

    $(document).ready(function() {

        $('.reset').click(function (e) {
            var instance = this;
            e.preventDefault();
            $(function () {
                $("#dialog-reset").dialog({
                    resizable:false,
                    height:220,
                    width:400,
                    title:'<fmt:message key="reset"/>',
                    modal:true,
                    open:function (event, ui) {
                        $("#dialog-reset").css({
                            display:'block'
                        });
                    },
                    buttons:{
                        "<fmt:message key="yes"/>":function () {
                            window.location = '<c:url value="/reset.htm"/>';
                        },
                        "<fmt:message key="no"/>":function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });
        });

    });


</script>

<div id="dialog-reset" style="display: none;text-align: center;">
    <p>
        <span><fmt:message key="reset.confirm.text"/></span>
    </p>
</div>

<div id="adminMenuWrapper">

    <div id="settingMenuTitle">
        <h2><fmt:message key="settings"/></h2>
    </div>

    <div id="settingMenuWrapper">
        <ul id="settingMenu">
            <li>
                <a <c:if test="${selected == 'ponuka'}">class="selected"</c:if> href="<c:out value="/admin/ponuka.htm"/>"><fmt:message key="nav.collections"/></a>
                <a class="reset" href="<c:out value="#"/>"><fmt:message key="reset"/></a>
            </li>
        </ul>
    </div>

</div>