<%@ page buffer="none" isThreadSafe="true" errorPage="error.jsp"
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
    <title>АРМ администратора</title>
    <link rel="apple-touch-icon" sizes="76x76" href="../img/apple-icon.png">
    <link rel="icon" type="image/png" href="../img/favicon.png">
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
    <link rel="stylesheet" href="../css/custom.css"/>
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
    <style>
        select {
            width: 100%;
        }
        textarea{
            width: 100%;
        }
        input{
            width: 100%;
        }
    </style>
</head>
<body class="profile-page sidebar-collapse">
<jsp:include page="../header.jsp"/>
<jsp:include page="header.jsp"/>
<%--@elvariable id="paramEditTourDto" type=""--%>
<%--@elvariable id="new_tourDate" type="TourDate"--%>
<%--@elvariable id="new_tourStructure" type="TourStructure"--%>

    <div class="popular_places_area">
        <div class="container">
            <form:form method="POST" modelAttribute="paramEditTourDto" action="edittour/savetour.do">
            <div class="row">
                <div class="col-lg-4">
                    <div class="filter_result_wrap">
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
                        <div class="filter_bordered">
                            <div class="filter_inner">
                                <div class="row">
                                    <td>Номер тура</td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <form:input readonly="true" path="id"/> <form:errors path="id"
                                                                                           cssClass="error"/>
                                        </div>
                                    </td>
                                    <td>Тип тура</td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <select class="single_select" path="typetour_id" name="typetour_id">
                                                <c:forEach items="${typetours}" var="typetour">
                                                    <option value="${typetour.id}"
                                                        ${typetour.id == paramEditTourDto.typetour_id
                                                                ? 'selected="selected"' : ''}>${typetour.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td>Тип питания</td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <select class="single_select" path="typefood_id" name="typefood_id">
                                                <c:forEach items="${typefoods}" var="typefood">
                                                    <option value="${typefood.id}"
                                                        ${typefood.id == paramEditTourDto.typefood_id
                                                                ? 'selected="selected"' : ''}>${typefood.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td>Тип транспорта</td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <select class="single_select" path="typetransport_id"
                                                    name="typetransport_id">
                                                <c:forEach items="${typetransports}" var="typetransport">
                                                    <option value="${typetransport.id}"
                                                        ${typetransport.id == paramEditTourDto.typetransport_id
                                                                ? 'selected="selected"' : ''}>${typetransport.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td>Туроператор</td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <select class="single_select" path="touroperator_id" name="touroperator_id">
                                                <c:forEach items="${touroperators}" var="touroperator">
                                                    <option value="${touroperator.id}"
                                                        ${touroperator.id == paramEditTourDto.touroperator_id
                                                                ? 'selected="selected"' : ''}>${touroperator.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td>Количество ночей</td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <form:input path="quantity_night"/> <form:errors path="quantity_night"
                                                                                           cssClass="error"/>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td>Описание</td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <form:textarea path="description" rows="10"/> <form:errors path="description"
                                                                                             cssClass="error"/>
                                        </div>
                                    </td>
                                    <p></p>
                                    <td>Название</td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <form:input path="name"/> <form:errors path="name"
                                                                                   cssClass="error"/>
                                        </div>
                                    </td>
                                    <p></p>
                                    <div class="reset_btn">
                                        <button class="boxed-btn3" type="submit"><spring:message
                                                code="save"/></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form:form>
            <div class="jumbotron-tour">
                <div class="container">
                    <h4>Состав тура</h4>
                    <div class="row">
                        <table>
                            <thead>
                            <tr>
                                <td>Город</td>
                                <td>Название</td>
                                <td>Описание</td>
                                <td>Отель</td>
                                <td>Продолжительность</td>
                                <td></td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tourStructures}" var="tourStructure">
                                <tr>
                                    <td><c:out value="${tourStructure.city.name}"></c:out></td>
                                    <td><c:out value="${tourStructure.name}"></c:out></td>
                                    <td><c:out value="${tourStructure.description}"></c:out></td>
                                    <td><c:out value="${tourStructure.hotel.name}"></c:out></td>
                                    <td><c:out value="${tourStructure.getDurationDay()}"></c:out></td>
                                    <td style="vertical-align: middle; text-align: center">
                                        <form:form method="POST" action="edittour/delTourDate.do">
                                            <input type="hidden" name="tourDate_id" value="${tourDate.id}">
                                            <input type="hidden" name="tour_date_count" value="1" id="count_id"/>
                                            <button style="background: #ff3636;" class="boxed-btn35" type="submit">Удалить
                                            </button>
                                        </form:form>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <form:form method="POST" action="tourpage/addTourStructure.do">
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <input path="city" value=" "/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <input path="name" value=" "/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <input path="description" value=" "/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <input path="hotel" value=" "/>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group no-border input-lg">
                                            <input path="duration" value=" "/>
                                        </div>
                                    </td>
                                    <td style="text-align: inherit;" class="align">
                                        <input type="hidden" name="tourDate_id" value="${tourDate.id}">
                                        <button style="padding: 12px 30px" class="boxed-btn35" type="submit">Добавить
                                        </button>
                                    </td>
                                </form:form>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <h4>Актуальные предложения</h4>
                    <div class="row">
                        <table class="table">
                            <thead>
                            <th style="text-align: inherit" scope="col" class="align-middle">Дата выезда</th>
                            <th style="text-align: inherit" scope="col" class="align-middle">Цена, &euro;</th>
                            <th style="text-align: inherit" scope="col" class="align-middle"></th>
                            </thead>
                            <tbody>
                            <c:forEach items="${tourDates}" var="tourDate" varStatus="loop">
                                <tr class="rowTourDate">
                                    <td  style="vertical-align: middle; text-align: center">
                                        <fmt:formatDate pattern="dd.MM.yyyy" value="${tourDate.dateArrival}"/>
                                    </td>
                                    <td style="vertical-align: middle; text-align: center">
                                        <input style="border: none; outline: none; background-color: transparent; text-align: center"
                                               id="price${loop.index}" type="text"
                                               value="${tourDate.price}" size="2" readonly="readonly"/>
                                    </td>
                                    <td style="vertical-align: middle; text-align: center">
                                        <form:form method="POST" action="edittour/delTourDate.do">
                                            <input type="hidden" name="tourDate_id" value="${tourDate.id}">
                                            <input type="hidden" name="tour_date_count" value="1" id="count_id"/>
                                            <button style="background: #ff3636;" class="boxed-btn35" type="submit">Удалить
                                            </button>
                                        </form:form>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <form:form method="POST" action="edittour/addTourDate.do">
                                    <td style="vertical-align: middle; text-align: center">
                                        <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="dateNow"/>
                                        <div class="input-group no-border input-lg">
                                            <input type="date" path="new_dateArrival" name="new_dateArrival"
                                                        cssClass="single_select_search" cssStyle="text-align: center"
                                                        id="new_dateArrival" value="${dateNow}"/>
                                        </div>
                                    </td>
                                    <td style="vertical-align: middle; text-align: center">
                                        <div class="input-group no-border input-lg">
                                            <input path="new_price" name = "new_price" value="0"/>
                                        </div>
                                    </td>
                                    <td style="vertical-align: middle; text-align: center">
                                        <input  type="hidden" path="tour_id" name="tour_id" value="${paramEditTourDto.id}"/>
                                        <button class="boxed-btn35" type="submit">Добавить</button>
                                    </td>
                                </form:form>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="../footer.jsp"/>
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