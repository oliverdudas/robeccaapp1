<%@ include file="../includes.jsp" %>

<link rel="stylesheet" href="/js/plugins/shadowbox/shadowbox.css" type="text/css"/>
<script type="text/javascript" src="/js/plugins/shadowbox/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/shadowbox/shadowbox.js"></script>
<script type="text/javascript" src="/js/plugins/shadowbox/init.js"></script>

<style type="text/css">
    .photophrame {
        width: 144px;
        margin: 0 2px;
        display: inline-block;
    }
</style>

<script type="text/javascript">
    $(document).ready(function() {

        $(function () {
            $('.galleryA').each(function() {
                var targetElem = $(this); // img wrapper
                var srcString = $(this).find('.src').html();

                var img = new Image();
                $(img).load(
                        function () {
                            $(this).hide();
                            $(targetElem).append(this);
                            $(this).fadeIn(1000);
                        }).error(
                        function () {
                            alert('Image ' + srcString + 'could not be loaded!');
                        }).attr({'src': srcString}).css({'display': 'inline'});
            });
        });
    });
</script>

<%--@elvariable id="collectionTitleKey" type="java.lang.String>"--%>
<h3><fmt:message key="${collectionTitleKey}"/></h3>

<%--@elvariable id="dtos" type="java.util.List<sk.dudas.appengine.robecca.service.cache.PhotoDto>"--%>
<c:forEach items="${dtos}" var="photo">
    <%--@elvariable id="photo" type="sk.dudas.appengine.robecca.service.cache.PhotoDto"--%>
    <span class="photophrame">
        <a class="galleryA" href='${photo.contentUri}'>
            <div class="src" style="display: none;">${photo.thumbUrl}</div>
        </a>
    </span>
</c:forEach>