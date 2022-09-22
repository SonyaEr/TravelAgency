<%@ page buffer="none" isThreadSafe="true" errorPage="admin/error.jsp"
         language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<% String contextPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Silentium</title>
    <link rel="apple-touch-icon" sizes="76x76" href="img/apple-icon.png">
    <link rel="icon" type="image/png" href="img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" href="../css/index.css"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
          name='viewport'/>
    <!--     Fonts and icons     -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
          crossorigin="anonymous">
    <!-- CSS Files -->
    <link rel="stylesheet" href="../css/custom.css" />
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
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <div class="page-header page-header-small2">
        <div class="page-header-image" data-parallax="true"
             style="background-image: url('img/video.png');"></div>
    </div>
</div>
<div class="popular_places_area1">
    <div class="wrapper">
        <div class="popular_places_area1">
            <div class="container">
                <section id="gallery">
                    <div class="row">
                        <div class="col-md-6">
                            <div style="margin-bottom: 10px" id="carousel-example-1z"
                                 class="carousel slide carousel-fade carousel-fade"
                                 data-ride="carousel">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" style="border-radius: 24px; "
                                         src="${pageContext.request.contextPath}/img/place/${tour.id}.jpg"
                                         alt="First slide"
                                         onError="this.src='${pageContext.request.contextPath}/img/place/none.jpg'">
                                </div>
                            </div>
                            <table class="tourcart_table">
                                <tbody>
                                <td class="tourcart_icon" style="background-color: #F7FAFD;">
                                    <img style="max-width: 20px; text-align: center; background-color: #F7FAFD; margin-right: 10px;"
                                         src="${pageContext.request.contextPath}/img/download.png">
                                    <h7>Начни свое путешествие:</h7>
                                </td>
                                <td style="background-color: #F7FAFD;">
                                    <form:form style="text-align: end;" method="POST" action="/home/memo">
                                        <input type="hidden" name="tour_id" value="${tour.id}">
                                        <input class="tourcart" type="submit" value="<spring:message code="memo" />"/>
                                    </form:form>
                                </td>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <h5 class="pb-1">
                                <strong>
                                    <c:out value="${tour.name}"></c:out> </strong>
                            </h5>
                            <p style="font-weight: 400;"> Описание: <c:out value="${tour.description}"></c:out></p>
                            <p>Количество ночей: <c:out value="${tour.quantityNight}"></c:out></p>
                            <p>Туроператор: <c:out value="${tour.tourOperator.getName()}"></c:out></p>
                            <p>Питание: <c:out value="${tour.typeFood.getName()}"></c:out></p>
                            <p>Транспорт:
                                <c:out value="${tour.typeTransport.getName()}"></c:out></p>
                            <p>Тип: <c:out value="${tour.typeTour.getName()}"></c:out></p>

                        </div>
                    </div>
                </section>
            </div>

            <div class="jumbotron-tour">
                <div class="container">
                    <h4>Состав тура</h4>
                    <div class="row">

                        <table>
                            <thead>
                            <tr>
                                <td>Страна</td>
                                <td>Название</td>
                                <td>Описание</td>
                                <td>Отель</td>
                                <td>Продолжительность</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tourStructures}" var="tourStructure">
                                <tr>
                                    <td><c:out value="${tourStructure.city.country.name}"></c:out></td>
                                    <td><c:out value="${tourStructure.name}"></c:out></td>
                                    <td><c:out value="${tourStructure.description}"></c:out></td>
                                    <td><c:out value="${tourStructure.hotel.name}"></c:out></td>
                                    <td><c:out value="${tourStructure.getDurationDay()}"></c:out></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <h4>Актуальные предложения</h4>
                    <div class="row">
                        <table class="table">
                            <thead>
                            <th scope="col" class="align-middle">Дата выезда</th>
                            <th scope="col" class="align-middle">Цена, &euro;</th>
                            <th scope="col" class="align-middle">Сумма, &euro;</th>
                            <td>
                                <div class="quantity_wr_2">
                                    <div class="quantity_inner">
                                        <button class="bt_minus">
                                            <svg viewBox="0 0 24 24">
                                                <line x1="5" y1="12" x2="19" y2="12"></line>
                                            </svg>
                                        </button>
                                        <input type="text" value="1" size="2" class="quantity"
                                               name="tour_date_count" data-max-count="20"
                                               id="tour_date_count"/>
                                        <button class="bt_plus">
                                            <svg viewBox="0 0 24 24">
                                                <line x1="12" y1="5" x2="12" y2="19"></line>
                                                <line x1="5" y1="12" x2="19" y2="12"></line>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                            </td>
                            </thead>
                            <tbody>
                            <c:forEach items="${tourDates}" var="tourDate" varStatus="loop">
                                <tr class="rowTourDate">
                                    <td style="vertical-align: middle; text-align: center">
                                        <fmt:formatDate pattern="dd.MM.yyyy" value="${tourDate.dateArrival}"/>
                                    </td>
                                    <td style="vertical-align: middle; text-align: center">
                                        <input style="border: none; outline: none; background-color: transparent"
                                               id="price${loop.index}" type="text"
                                               value="${tourDate.price}" size="2" readonly="readonly"/>
                                    </td>
                                    <td style="vertical-align: middle; text-align: right">
                                        <input style="border: none; outline: none; background-color: transparent"
                                               id="summa${loop.index}" type="number"
                                               value="${tourDate.price}" size="2" readonly="readonly"/>
                                    </td>
                                    <td class="align">
                                        <form:form method="POST" action="tourpage/createOrder.do">
                                            <input type="hidden" name="tourDate_id" value="${tourDate.id}">
                                            <input type="hidden" name="tour_date_count" value="1" id="count_id"/>
                                            <button class="boxed-btn35" type="submit"><spring:message
                                                    code="createorder"/>
                                            </button>
                                        </form:form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
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
<script>
    // Убавляем кол-во по клику
    $('.quantity_inner .bt_minus')
        .click(
            function () {
                let $input = $(this).parent().find(
                    '.quantity');
                let count = parseInt($input.val()) - 1;
                count = count < 1 ? 1 : count;
                $input.val(count);
                var size = document
                    .getElementsByClassName('rowTourDate').length;
                for (var i = 0; i < size; i++) {
                    document.getElementById('summa' + i).value = (parseFloat(document
                            .getElementById('price' + i).value)
                        * count).toFixed(1);
                }
                document.getElementById('count_id').value = count;
            });
    // Прибавляем кол-во по клику
    $('.quantity_inner .bt_plus')
        .click(
            function () {
                let $input = $(this).parent().find(
                    '.quantity');
                let count = parseInt($input.val()) + 1;
                count = count > parseInt($input
                    .data('max-count')) ? parseInt($input
                        .data('max-count'))
                    : count;
                $input.val(parseInt(count));
                var size = document
                    .getElementsByClassName('rowTourDate').length;
                for (var i = 0; i < size; i++) {
                    document.getElementById('summa' + i).value = (parseFloat(document.getElementById('price' + i).value)
                        * count).toFixed(1);
                }
                document.getElementById('count_id').value = count;
            });
    // Убираем все лишнее и невозможное при изменении поля
    $('.quantity_inner .quantity')
        .bind(
            "change keyup input click",
            function () {
                if (this.value.match(/[^0-9]/g)) {
                    this.value = this.value.replace(
                        /[^0-9]/g, '');
                }
                if (this.value == "") {
                    this.value = 1;
                }
                if (this.value > parseInt($(this).data(
                    'max-count'))) {
                    this.value = parseInt($(this).data(
                        'max-count'));
                }
                var size = document
                    .getElementsByClassName('rowTourDate').length;
                for (var i = 0; i < size; i++) {
                    document.getElementById('summa' + i).value = parseInt(document
                            .getElementById('price' + i).value)
                        * this.value;
                }
                document.getElementById('count_id').value = this.value;
            });
</script>
</body>
</html>