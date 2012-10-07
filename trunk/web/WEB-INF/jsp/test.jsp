<%@ include file="includes.jsp" %>

<script type="text/javascript" src="/js/jquery1.8.24.0/jquery-1.8.2.js"></script>
<script type="text/javascript" src="/js/jquery1.8.24.0/jquery.ui.core.js"></script>
<script type="text/javascript" src="/js/jquery1.8.24.0/jquery.ui.widget.js"></script>

<link rel="stylesheet" href="/js/plugins/shadowbox/shadowbox.css" type="text/css"/>
<%--<script type="text/javascript" src="/js/plugins/shadowbox/jquery.js"></script>--%>
<script type="text/javascript" src="/js/plugins/shadowbox/shadowbox.js"></script>
<script type="text/javascript" src="/js/plugins/shadowbox/init.js"></script>

<script type="text/javascript" src="/js/widgets/custom/jquery.picasa.widget.js"></script>

<script type="text/javascript">

    $(document).ready(function() {
        $('#album').picasa();

    });

</script>

<div>

    <div id="album">

    </div>

</div>