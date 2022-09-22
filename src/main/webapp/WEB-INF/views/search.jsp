<%@ page buffer="none" isThreadSafe="true" errorPage="error.jsp"
         language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="now" class="java.util.Date" scope="page"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Silentium</title>
    <link rel="apple-touch-icon" sizes="76x76" href="../img/apple-icon.png">
    <link rel="icon" type="image/png" href="../img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link href="../css/index.css" rel="stylesheet"/>
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
    <link rel="stylesheet" href="../css/owl.carousel.min.css">
    <link rel="stylesheet" href="../css/magnific-popup.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/themify-icons.css">
    <link rel="stylesheet" href="../css/nice-select.css">
    <link rel="stylesheet" href="../css/flaticon.css">
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <link rel="stylesheet" href="../css/gijgo.css">
    <link rel="stylesheet" href="../css/animate.css">
    <link rel="stylesheet" href="../css/slick.css">
    <link rel="stylesheet" href="../css/slicknav.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/now-ui-kit.css?v=1.3.0"/>
    <link rel="stylesheet" href="../css/tour.css"/>
</head>
<body class="profile-page sidebar-collapse">
<meta charset="UTF-8">
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <div class="page-header page-header-small2">
        <div class="page-header-image" data-parallax="true"
             style="background-image: url('img/video.png');"></div>
    </div>
</div>
<div class="popular_places_area">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <div class="filter_result_wrap">
                    <div class="filter_bordered">
                        <div class="filter_inner">
                            <div class="row">
                                <%--@elvariable id="paramSearchTourDto" type=""--%>
                                <form:form method="POST" modelAttribute="paramSearchTourDto" action="search">
                                    <td>Найти</td>
                                    <td>
                                        <div>
                                            <form:input path="search_text" cssClass="single_select_search"/>
                                            <form:errors path="search_text" cssClass="error"/>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td><spring:message code="typetour"/></td>
                                    <td>
                                        <div>
                                            <select class="single_select_search" path="typetour_id" name="typetour_id">
                                                <option value="-1"></option>
                                                <c:forEach items="${typetours}" var="typetour">
                                                    <option value="${typetour.id}"
                                                        ${typetour.id == paramSearchTourDto.typetour_id
                                                                ? 'selected="selected"' : ''}>${typetour.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td><spring:message code="food"/></td>
                                    <td>
                                        <div>
                                            <select class="single_select_search" path="typefood_id" name="typefood_id">
                                                <option value="-1"></option>
                                                <c:forEach items="${typefoods}" var="typefood">
                                                    <option value="${typefood.id}"
                                                        ${typefood.id == paramSearchTourDto.typefood_id
                                                                ? 'selected="selected"' : ''}>${typefood.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td><spring:message code="transport"/></td>
                                    <td>
                                        <div >
                                            <select class="single_select_search" path="typetransport_id" name="typetransport_id">
                                                <option value="-1"></option>
                                                <c:forEach items="${typetransports}" var="typetransport">
                                                    <option value="${typetransport.id}"
                                                        ${typetransport.id == paramSearchTourDto.typetransport_id
                                                                ? 'selected="selected"' : ''}>${typetransport.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td><spring:message code="touroperator"/></td>
                                    <td>
                                        <div >
                                            <select class="single_select_search" path="touroperator_id" name="touroperator_id" >
                                                <option value="-1"></option>
                                                <c:forEach items="${touroperators}" var="touroperator">
                                                    <option value="${touroperator.id}"
                                                        ${touroperator.id == paramSearchTourDto.touroperator_id
                                                                ? 'selected="selected"' : ''}>${touroperator.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td><spring:message code="planneddate"/></td>
                                    <td>
                                        <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="dateNow"/><br>
                                        <div class="input-group no-border input-lg">
                                            <form:input type="date" path="date_travel" name="date_travel"
                                                        cssClass="single_select_search" cssStyle="text-align: center"
                                                        id="date_travel" value="${date_travel}" min="${dateNow}"/>
                                            <form:errors
                                                    path="date_travel" cssClass="error"/>
                                        </div>
                                    </td>
                                    <td><spring:message code="quantitynight"/></td>
                                    <td>
                                        <div>
                                            <form:input path="quantity_day" cssClass="single_select_search" cssStyle="text-align: center"/>
                                            <form:errors path="quantity_day" cssClass="error"/>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td><spring:message code="price"/>, &euro;</td>
                                    <td>
                                        <div class="filter-block-price">
                                            <span>от</span>
                                            <form:input path="price_min" autocomplete="false" size="9" cssStyle="text-align: center"/>
                                            <form:errors path="price_min" cssClass="error"/>
                                                <%--                                        <input id="price-min" type="number" class="fillter-number" name="fp1" autocomplete="off" placeholder="3132">--%>
                                            <span>до</span>
                                            <span class="narrow-screen"></span>
                                            <form:input path="price_max" autocomplete="false" size="9" cssStyle="text-align: center"/>
                                            <form:errors path="price_max" cssClass="error"/>
                                                <%--                                        <input id="price-max" type="number" class="fillter-number" name="fp2" autocomplete="off" placeholder="375401">--%>
                                        </div>
                                    </td>
                                    <p></p>
                                    <div class="reset_btn">
                                        <button class="boxed-btn3" type="submit"><spring:message
                                                code="search"/></button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="row">
                    <c:forEach items="${toursSearch}" var="tourDate">
                        <div class="col-lg-6 col-md-6">
                            <div class="single_place">
                                <div class="thumb">
                                    <img src="${pageContext.request.contextPath}/img/place/${tourDate.tour.id}.jpg"
                                         alt=""
                                         onError="this.src='${pageContext.request.contextPath}/img/place/none.jpg'">
                                    <a class="prise">&euro;<c:out value="${tourDate.price}"></c:out></a>
                                </div>
                                <div class="place_info">
                                    <a><h3><c:out value="${tourDate.tour.name}"></c:out></h3></a>
                                    <p><c:out value="${tourDate.tour.description}"></c:out></p>
                                    <div class="rating_days d-flex justify-content-between">
												<span class="d-flex justify-content-center align-items-center">
													 <i class="fa fa-star"></i>
													 <i class="fa fa-star"></i>
													 <i class="fa fa-star"></i>
													 <i class="fa fa-star"></i>
													 <i class="fa fa-star"></i>
													 <a>(20 Review)</a>
												</span>
                                        <div class="days">
                                            <i class="fa fa-clock-o"></i>
                                            <a><c:out value="${tourDate.tour.quantityNight}"></c:out></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="reset_btn" align="center">
                                    <form:form style="text-align: end; margin-bottom: 20px; margin-right: 20px;" method="POST" action="search/selectTour.do">
                                        <input type="hidden" name="tourDate_id" value="${tourDate.tour.id}">
                                        <button
                                                class="boxed-btn35" type="submit"><spring:message code="select"/>
                                        </button>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<!--   Core JS Files   -->
<script src="../js/core/jquery.min.js" type="text/javascript"></script>
<script src="../js/core/popper.min.js" type="text/javascript"></script>
<script src="../js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="../js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="../js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
<script src="../js/plugins/bootstrap-datepicker.js"
        type="text/javascript"></script>
<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
<script src="../js/now-ui-kit.js?v=1.3.0" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        nowuiKit.initContactUsMap();
    });
</script>
</body>
</html>