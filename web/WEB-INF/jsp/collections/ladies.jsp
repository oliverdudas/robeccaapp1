<%@ include file="../includes.jsp" %>

<%--jquery--%>
<script type="text/javascript" src="/js/jquery1.8.24.0/jquery-1.8.2.js"></script>
<script type="text/javascript" src="/js/jquery1.8.24.0/jquery.ui.core.js"></script>
<script type="text/javascript" src="/js/jquery1.8.24.0/jquery.ui.widget.js"></script>

<%--shadowbox--%>
<link rel="stylesheet" href="/js/plugins/shadowbox/shadowbox.css" type="text/css"/>
<script type="text/javascript" src="/js/plugins/shadowbox/shadowbox.js"></script>

<%--picasa widget--%>
<script type="text/javascript" src="/js/widgets/custom/jquery.picasa.widget.js"></script>

<script type="text/javascript">

    $(document).ready(function() {

        function setupDemos() {
            Shadowbox.setup("a.galleryA", {
                gallery: "mustang",
                continuous: true,
                counterType: "skip"
            });
        }

        $('#album').picasa({
            end: function() {
                $('.albumLoader').slideUp(1000);
                $('#album').slideDown(1000);
                Shadowbox.init({
                    overlayOpacity: 0.8
                }, setupDemos);
            }
        });

    });

</script>

<h3><fmt:message key="ladys.collection"/></h3>

<div class="albumLoader"><fmt:message key="loading"/></div>

<div id="album">
</div>