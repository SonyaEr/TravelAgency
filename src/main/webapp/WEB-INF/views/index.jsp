<%@ page buffer="none" isThreadSafe="true" errorPage="error.jsp"
         language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Silentium</title>
    <link rel="apple-touch-icon" sizes="76x76" href="img/apple-icon.png">
    <link rel="icon" type="image/png" href="img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <meta
            content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
            name='viewport'/>
    <!--     Fonts and icons     -->
    <link
            href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
            rel="stylesheet"/>
    <link rel="stylesheet"
          href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
          crossorigin="anonymous">
    <!-- CSS Files -->
    <link href="css/index.css" rel="stylesheet"/>
    <link href="css/custom.css" rel="stylesheet"/>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/now-ui-kit.css?v=1.3.0" rel="stylesheet"/>
    <link rel="stylesheet" href="css/tour.css"/>
</head>

<body class="landing-page sidebar-collapse">
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <div class="page-header page-header-small">
        <div class="page-header-image" data-parallax="true"
             style="background-image: url('img/video.png');"></div>
        <div class="content-center">
            <div class="container_search">
                <h4 class="title"><spring:message code="text_main1"/></h4>
                <div class="search-right">
                    <h7 class="description"><spring:message code="text_main2"/>
                    </h7>
                </div>
                <form action="/guestsearch" method="POST" class="form_search">
                    <input id="search_text" name="search_text" type="search" placeholder="<spring:message code="search_main"/>" class="search-field"/>
                    <button type="submit" class="search-button">
                        <img src="img/icon-search.png">
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container_countries">
    <c:forEach items="${countres}" var="country">
        <div class="item_countries"
             style="background-image: linear-gradient(rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.3)),
                     url('${pageContext.request.contextPath}/img/country/${country.name}.jpg'),
                     url('${pageContext.request.contextPath}/img/place/none.jpg'); background-size: cover;"
             ;>
            <p_count>${country.name}</p_count>
        </div>
    </c:forEach>
</div>
<div class="section section-about-us">
    <div class="container">
        <div class="row">
            <div class="col-md-8 ml-auto mr-auto text-center">
                <h2 class="title"><spring:message code="whowe"/></h2>
                <h5 class="description"><spring:message code="text1"/></h5>
            </div>
        </div>
        <div class="separator separator-primary"></div>
        <div class="section-story-overview">
            <div class="row">
                <div class="col-md-6">
                    <div class="image-container image-left"
                         style="background-image: url('img/bg4.jpg')">
                        <!-- First image on the left side -->
                        <p class="blockquote blockquote-primary">
                            <spring:message code="text2"/></p>
                    </div>
                    <!-- Second image on the left side of the article -->
                    <div class="image-container"
                         style="background-image: url('img/bg3.jpg')"></div>
                </div>
                <div class="col-md-5">
                    <!-- First image on the right side, above the article -->
                    <div class="image-container image-right"
                         style="background-image: url('img/bg1.jpg')"></div>
                    <h3><spring:message code="text3"/></h3>
                    <p><spring:message code="text4"/></p>
                    <p><spring:message code="text5"/></p>
                    <p><spring:message code="text6"/></p>
                </div>

            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-12">
                <div id="carouselExampleIndicators" class="carousel slide"
                     data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0"
                            class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <img class="d-block" src="img/с3.jpg">
                            <div class="carousel-caption d-none d-md-block">
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img class="d-block" src="img/с1.jpg">
                            <div class="carousel-caption d-none d-md-block">
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img class="d-block" src="img/с3.jpg">
                            <div class="carousel-caption d-none d-md-block">
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img class="d-block" src="img/с1.jpg">
                            <div class="carousel-caption d-none d-md-block">
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev"
                       href="#carouselExampleIndicators" role="button"
                       data-slide="prev"> <i
                            class="now-ui-icons arrows-1_minimal-left"></i>
                    </a> <a class="carousel-control-next"
                            href="#carouselExampleIndicators" role="button"
                            data-slide="next"> <i
                        class="now-ui-icons arrows-1_minimal-right"></i>
                </a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
<script src="js/plugins/bootstrap-switch.js"></script>
<script src="js/plugins/nouislider.min.js" type="text/javascript"></script>
<script src="js/plugins/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<script src="js/now-ui-kit.js?v=1.3.0" type="text/javascript"></script>
<script src="js/core/jquery.min.js" type="text/javascript"></script>
<script src="js/core/popper.min.js" type="text/javascript"></script>
<script src="js/core/bootstrap.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        // the body of this function is in assets/js/now-ui-kit.js
        nowuiKit.initSliders();
    });

    function scrollToDownload() {

        if ($('.section-download').length != 0) {
            $("html, body").animate({
                scrollTop: $('.section-download').offset().top
            }, 1000);
        }
    }
</script>
</body>
</html>