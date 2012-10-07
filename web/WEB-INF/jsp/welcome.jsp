<%@ include file="includes.jsp" %>

<%--<script src="/js/plugins/mobilityslider/js/jquery.js" type="text/javascript"></script>--%>

<%--jquery--%>
<script type="text/javascript" src="/js/jquery1.8.24.0/jquery-1.8.2.js"></script>
<script type="text/javascript" src="/js/jquery1.8.24.0/jquery.ui.core.js"></script>
<script type="text/javascript" src="/js/jquery1.8.24.0/jquery.ui.widget.js"></script>

<%--picasa widget--%>
<script type="text/javascript" src="/js/widgets/custom/jquery.picasa.widget.js"></script>

<%--<script type="text/javascript">--%>
    <%--$(document).ready(function() {--%>

        <%--$(function () {--%>
            <%--$('.image').fadeIn(1000);--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<script type="text/javascript">

    $(document).ready(function() {

        $('#welcomeImageFrame').picasa({
            type: 'cover',
            picasaAlbumId: '5667857531879411857'
        });

    });

</script>

<div style="float:left; width:330px; height: 500px;border:1px solid #FFF;" id="welcomeImageFrame">
    <%--<img class="image" style="display:none;" width="330" height="500" src="https://lh5.googleusercontent.com/-4VXDAv1RB5s/TmI2i6WnR4I/AAAAAAAAAIM/B8N9uJvydc8/s720/Eva%252520v%2525C5%2525A1etko%252520277.jpg"  alt="..."/>--%>
</div>

<div style="margin-left: 400px;margin-top: 100px">
    <fmt:message key="welcome.text" />
</div>
