<%@ include file="includes.jsp" %>

<script type="text/javascript"
        src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyBhB8tijEFgrhCsgw3wVCQrM1lf-HrMvp8"></script>
<script type="text/javascript" src="/js/plugins/shadowbox/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/gmap/jquery.gmap-1.1.0.js"></script>


<script type="text/javascript">
    $(document).ready(function() {

        $("#mapCanvas1").gMap({
            markers: [{
                latitude: 48.176346,
                longitude: 17.064790
            }], zoom: 15 });

    });
</script>

<div id="shopsWrapper">
    <div>
        <div class="sl">
            <p><fmt:message key="shop1"/></p>
        </div>
        <div class="sr">
            <div id="mapCanvas1"></div>
        </div>
    </div>
</div>