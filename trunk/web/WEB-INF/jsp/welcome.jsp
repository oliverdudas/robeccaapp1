<%@ include file="includes.jsp" %>

<script src="/js/plugins/mobilityslider/js/jquery.js" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function() {

        $(function () {
            $('.image').fadeIn(1000);
        });
    });
</script>

<div style="float:left; width:330px; height: 500px;border:1px solid #FFF;">
    <img class="image" style="display:none;" width="330" height="500" src="${welcomePictureUrl}"  alt="..."/>
</div>

<div style="margin-left: 400px;margin-top: 100px">
    <fmt:message key="welcome.text" />
</div>