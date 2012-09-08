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
                <div class="item">
                    <a class="galleryA" href="javascript:alert('${msg}');" style="padding:0">
                        <img style="display:none;" height="500" src="https://lh3.googleusercontent.com/-kkQi8Z1rhB0/TmIzX0rduvI/AAAAAAAAADI/jBJ4xxYPCbc/s720/Eva%252520v%2525C5%2525A1etko%252520019.jpg" alt=""/>
                        <img style="display:none;" height="500" src="https://lh6.googleusercontent.com/-73yQjRKs0MQ/TmI0CwIgOsI/AAAAAAAAAEA/ekSetWN93Yo/s720/Eva%252520v%2525C5%2525A1etko%252520080.jpg" alt=""/>
                    </a>
                </div>
                <div class="item">
                    <a href="javascript:alert('${msg}');">
                        <%--<img src="/images/slider/O.jpg" alt=""/>--%>
                        <img height="500"
                             src="https://lh6.googleusercontent.com/-X2FuwFrdRi4/TmI0bOF7cvI/AAAAAAAAAEg/d7K1ujAUsEs/s720/Eva%252520v%2525C5%2525A1etko%252520094.jpg"
                             alt=""/>
                        <img height="500"
                             src="https://lh6.googleusercontent.com/-qs9IX41wfaw/TmI0ybX6-WI/AAAAAAAAAFI/SxOE8z1l0Ok/s720/Eva%252520v%2525C5%2525A1etko%252520123.jpg"
                             alt=""/>
                    </a>
                </div>
                <div class="item">
                    <a href="javascript:alert('${msg}');">
                        <%--<img src="/images/slider/M.jpg" alt=""/>--%>
                        <img height="500"
                             src="https://lh3.googleusercontent.com/-S31zx_fPJNA/TmI1JnrygLI/AAAAAAAAAFw/KsMVmLY0qyY/s720/Eva%252520v%2525C5%2525A1etko%252520152.jpg"
                             alt=""/>
                        <img height="500"
                             src="https://lh4.googleusercontent.com/-GGW2KE7ZGHg/TmI1T-4O_iI/AAAAAAAAAGE/oaXYeXW21rA/s720/Eva%252520v%2525C5%2525A1etko%252520165.jpg"
                             alt=""/>
                    </a>
                </div>
                <div class="item">
                    <a href="javascript:alert('${msg}');">
                        <%--<img src="/images/slider/A.jpg" alt=""/>--%>
                        <img height="500"
                             src="https://lh6.googleusercontent.com/-V5BkD4VaLhc/TmI2Aj7dtxI/AAAAAAAAAHQ/H9YUCrewdQs/s720/Eva%252520v%2525C5%2525A1etko%252520232.jpg"
                             alt=""/>
                        <img height="500"
                             src="https://lh4.googleusercontent.com/-xuAhqtRRGpI/TmI2KkdTeEI/AAAAAAAAAHk/aw3jzDApFms/s720/Eva%252520v%2525C5%2525A1etko%252520250.jpg"
                             alt=""/>
                    </a>
                </div>
                <div class="item">
                    <a href="javascript:alert('${msg}');">
                        <%--<img src="/images/slider/N.jpg" alt=""/>--%>
                        <img height="500"
                             src="https://lh5.googleusercontent.com/-xTeC99ofO98/TmI1q_gT1GI/AAAAAAAAAGo/B47r-Bsi48w/s720/Eva%252520v%2525C5%2525A1etko%252520192.jpg"
                             alt=""/>
                        <img height="500"
                             src="https://lh4.googleusercontent.com/-cK_jtyH3kos/TmI2YArBUwI/AAAAAAAAAH4/_8GSOq6yYGE/s720/Eva%252520v%2525C5%2525A1etko%252520261.jpg"
                             alt=""/>
                    </a>
                </div>
            </div>
        </div>

    </div>

</div>

