<%@ include file="../includes.jsp" %>

<link rel="stylesheet" href="/js/plugins/shadowbox/shadowbox.css" type="text/css"/>
<script type="text/javascript" src="/js/plugins/shadowbox/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/shadowbox/shadowbox.js"></script>
<script type="text/javascript" src="/js/plugins/shadowbox/init.js"></script>

<script type="text/javascript">
    $(document).ready(function() {

        $(function () {
            $('.galleryA').each(function() {
                var targetElem = $(this); // img wrapper
                var srcString = $(this).find('.src').html();

                var img = new Image();
                $(img).load(
                        function () {
                            //$(this).css('display', 'none'); // .hide() doesn't work in Safari when the element isn't on the DOM already
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

<h3><fmt:message key="ladys.collection"/></h3>

<%--@elvariable id="picasaPhotoEntries" type="java.util.List<com.google.gdata.data.photos.PhotoEntry>"--%>
<c:forEach items="${picasaPhotoEntries}" var="photo">
    <%--@elvariable id="photo" type="com.google.gdata.data.photos.PhotoEntry"--%>
    <a class="galleryA" href='${photo.content.uri}'>
        <div class="src" style="display: none;">${photo.mediaThumbnails[1].url}</div>
    </a>
</c:forEach>