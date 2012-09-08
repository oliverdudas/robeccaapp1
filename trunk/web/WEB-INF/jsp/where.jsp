<%@ include file="includes.jsp" %>

<script src="/js/plugins/mobilityslider/js/jquery.js" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function() {

        $(function () {
            $('.image').fadeIn(1000);
        });
    });
</script>

<div style="position: absolute; width:330px; height: 500px;border:1px solid #FFF;float:left;">
    <img class="image" style="display:none;" width="330" height="500" src="/images/hodsvistom/svist1.jpg"  alt="..."/>
</div>

<div style="margin-left: 400px;margin-top: 70px;float:left;">
    <fmt:message key="where.text" />
</div>

<div style="margin-left: 450px;height:300px;margin-top: 35px;float:left;;border:1px solid #FFF">
     <img class="image" style="display:none;" width="400" height="300" src="/images/hodsvistom/svist2.jpg"  alt="..."/>
</div>
<div style="margin-left: 180px;margin-top: 75px;width:450px;height:300px;float:left;border:1px solid #FFF">
     <img class="image" style="display:none;" width="450" height="300" src="/images/hodsvistom/svist3.jpg"  alt="..."/>
</div>
