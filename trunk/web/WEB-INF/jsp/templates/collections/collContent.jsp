<%@ include file="../../includes.jsp" %>

<link href="/js/plugins/mobilityslider/css/mobilityslider.css" rel="stylesheet" type="text/css"/>
<script src="/js/plugins/mobilityslider/js/jquery.js" type="text/javascript"></script>
<script src="/js/plugins/mobilityslider/js/mobilyslider.js" type="text/javascript"></script>
<script src="/js/plugins/mobilityslider/js/init2.js" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function() {

        $(function () {
            $('.galleryA img').fadeIn(1000);
        });
    });
</script>

<style type="text/css">
    #mobilitySliderContent, .slider, .sliderContent, .sliderContent .item {
        width: 400px !important;
    }
</style>

<div id="mobilitySliderContent">
    <fmt:message key='robecca.wellcome.msg' var="msg"/>

    <div class="slider">
        <div class="sliderContent">
            <div class="item">
                <a class="galleryA" href="javascript:alert('${msg}');">
                    <img height="450"
                         style="display: none;"
                         src="https://lh3.googleusercontent.com/-kkQi8Z1rhB0/TmIzX0rduvI/AAAAAAAAADI/jBJ4xxYPCbc/s720/Eva%252520v%2525C5%2525A1etko%252520019.jpg"
                         alt=""/>
                </a>
            </div>
            <div class="item">
                <a href="javascript:alert('${msg}');">
                    <img height="450"
                         src="https://lh6.googleusercontent.com/-ySTtqCcE67s/TmIz2t-9HAI/AAAAAAAAADs/sShqyHiRK80/s720/Eva%252520v%2525C5%2525A1etko%252520070.jpg"
                         alt=""/>
                </a>
            </div>
            <div class="item">
                <a href="javascript:alert('${msg}');">
                    <img height="450"
                         src="https://lh3.googleusercontent.com/-S31zx_fPJNA/TmI1JnrygLI/AAAAAAAAAFw/KsMVmLY0qyY/s720/Eva%252520v%2525C5%2525A1etko%252520152.jpg"
                         alt=""/>
                </a>
            </div>
            <div class="item">
                <a href="javascript:alert('${msg}');">
                    <img height="450"
                         src="https://lh4.googleusercontent.com/-xuAhqtRRGpI/TmI2KkdTeEI/AAAAAAAAAHk/aw3jzDApFms/s720/Eva%252520v%2525C5%2525A1etko%252520250.jpg"
                         alt=""/>
                </a>
            </div>
            <div class="item">
                <a href="javascript:alert('${msg}');">
                    <img height="450"
                         src="https://lh3.googleusercontent.com/-S31zx_fPJNA/TmI1JnrygLI/AAAAAAAAAFw/KsMVmLY0qyY/s720/Eva%252520v%2525C5%2525A1etko%252520152.jpg"
                         alt=""/>
                </a>
            </div>
        </div>
    </div>

</div>