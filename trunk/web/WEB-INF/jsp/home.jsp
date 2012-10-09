<%@ include file="includes.jsp" %>
<%--mobilityslider--%>
<link href="/js/plugins/mobilityslider/css/mobilityslider.css" rel="stylesheet" type="text/css"/>
<script src="/js/plugins/mobilityslider/js/jquery.js" type="text/javascript"></script>
<script src="/js/plugins/mobilityslider/js/mobilyslider.js" type="text/javascript"></script>
<script src="/js/plugins/mobilityslider/js/init.js" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function() {

        $(function () {
            $('.galleryA img').fadeIn(1000);
        });
    });
</script>

<div id="window">

    <fmt:message key='robecca.wellcome.msg' var="msg"/>

    <div id="mobilitySliderContent">

        <div class="slider">
            <div class="sliderContent">


                <%--@elvariable id="homePictureUrls" type="java.util.List<sk.dudas.appengine.robecca.service.cache.PhotoDto>"--%>
                <c:forEach items="${homePictureUrls}" var="item" varStatus="status" step="2">

                    <div class="item">
                        <a
                                <c:if test="${status.index == 0}">class="galleryA"</c:if>
                                href="javascript:alert('${msg}');"
                                style="padding:0">
                            <img
                                    <c:if test="${status.index == 0}">style="display:none;"</c:if> height="500"
                                    src="${homePictureUrls[status.index].contentUri}"
                                    alt=""/>
                            <img
                                    <c:if test="${status.index == 0}">style="display:none;"</c:if> height="500"
                                    src="${homePictureUrls[status.index + 1].contentUri}"
                                    alt=""/>
                        </a>
                    </div>

                </c:forEach>

            </div>
        </div>

    </div>

</div>

