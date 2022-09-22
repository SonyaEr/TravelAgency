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
    <link rel="apple-touch-icon" sizes="76x76" href="../img/apple-icon.png">
    <link rel="icon" type="image/png" href="../img/favicon.png">
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
    <link rel="stylesheet" href="../css/index.css"/>
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
</head>
<body class="profile-page sidebar-collapse">
<meta charset="UTF-8">
<jsp:include page="../header.jsp"/>
<jsp:include page="header.jsp"/>

<ul class="nav nav-tabs" id="myTabs" style="padding: 20px 210px;">
    <li class="nav-item"><a class="nav-link" href="#Tab7" data-toggle="tab"><spring:message code="freeorders"/> </a>
    </li>
    <li class="nav-item"><a class="nav-link" href="#Tab2" data-toggle="tab"><spring:message code="orders"/> </a></li>
    <li class="nav-item"><a class="nav-link" href="#Tab3" data-toggle="tab"><spring:message code="vouchers"/> </a></li>
    <li class="nav-item"><a class="nav-link" href="#Tab1" data-toggle="tab"><spring:message code="clients"/></a></li>
    <li class="nav-item"><a class="nav-link" href="#Tab6" data-toggle="tab"><spring:message code="tours"/></a></li>
    <li class="nav-item"><a class="nav-link" href="#Tab5" data-toggle="tab"><spring:message code="others"/></a></li>

</ul>
<div class="tab-content">
    <!-- Свободные заказы -->
    <div class="tab-pane fade" id="Tab7" style="padding: 0px 210px;">

        <div>
            <table>
                <thead>
                <tr>
                    <td>Номер заявки</td>
                    <td>Дата заявки</td>
                    <td>Клиент</td>
                    <td>Тур</td>
                    <td>Цена</td>
                    <td>Статус заявки</td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${freeorders}" var="order">
                    <tr>
                        <td><c:out value="${order.id}"></c:out></td>
                        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${order.orderDate}"/></td>
                        <td><c:out value="${order.client.getFIO()}"></c:out></td>
                        <td><c:out value="${order.tourDate.tour.name}"></c:out></td>
                        <td><fmt:formatNumber pattern="0.00" value="${order.tourDate.price*order.count}"/></td>
                        <td><c:out value="${order.statusOrder.name}"></c:out></td>
                        <td>
                            <form:form method="POST" action="home/editOrder.do">
                                <input type="hidden" name="order_id" value="${order.id}">
                                <input type="submit" class="input_home" value="<spring:message code="edit" />"/>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- Мои зявки -->
    <div class="tab-pane fade" id="Tab2" style="padding: 0px 210px;">
        <div>
            <table>
                <thead>
                <tr>
                    <td>Номер заявки</td>
                    <td>Дата заявки</td>
                    <td>Клиент</td>
                    <td>Тур</td>
                    <td>Цена</td>
                    <td>Статус заявки</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td><c:out value="${order.id}"></c:out></td>
                        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${order.orderDate}"/></td>
                        <td><c:out value="${order.client.getFIO()}"></c:out></td>
                        <td><c:out value="${order.tourDate.tour.name}"></c:out></td>
                        <td><fmt:formatNumber pattern="0.00" value="${order.tourDate.price*order.count}"/></td>
                        <td><c:out value="${order.statusOrder.name}"></c:out></td>
                        <td>
                            <c:if test="${order.statusOrder.getId()==4}">
                                <form:form method="POST" action="home/createVoucher.do">
                                    <input type="hidden" name="order_id" value="${order.id}">
                                    <input type="submit" class="input_home"
                                           value="<spring:message code="createvoucher" />"/>
                                </form:form>
                            </c:if>
                        </td>
                        <td>
                            <form:form method="POST" action="home/editOrder.do">
                                <input type="hidden" name="order_id" value="${order.id}">
                                <input type="submit" class="input_home" value="<spring:message code="edit" />"/>
                            </form:form>
                        </td>
                        <td>
                            <c:if test="${order.statusOrder.getId()==2}">
                                <form:form method="POST" action="home/Invoice">
                                    <input type="hidden" name="order_id" value="${order.id}">
                                    <input type="submit" class="input_home" value="<spring:message code="invoice" />"/>
                                </form:form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- Путевки -->
    <div class="tab-pane fade" id="Tab3" style="padding: 0px 210px;">
        <div>
            <table>
                <thead>
                <tr>
                    <td>Номер путевки</td>
                    <td>Клиент</td>
                    <td>Даты тура</td>
                    <td>Стоимость</td>
                    <td>Номер заявки</td>
                    <td>Тур</td>
                    <td>Дата путевки</td>
                    <td>Дата обновления путевки</td>
                    <td>Статус</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${vouchers}" var="voucher">
                    <tr>
                        <td><c:out value="${voucher.id}"></c:out></td>
                        <td><c:out value="${voucher.order.client.getFIO()}"></c:out></td>
                        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${voucher.tourDate.dateArrival}"/> -
                            <fmt:formatDate pattern="dd.MM.yyyy" value="${voucher.tourDate.getDateDeparture()}"/></td>
                        <td><fmt:formatNumber pattern="0.00" value="${voucher.amount}"/></td>
                        <td><c:out value="${voucher.order.id}"></c:out></td>
                        <td><c:out value="${voucher.tourDate.tour.name}"></c:out></td>
                        <fmt:parseDate pattern="yyyy-MM-dd" type="both"
                                       value="${voucher.voucherDate}" var="voucherDate"/>
                        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${voucherDate}"/></td>
                        <fmt:parseDate pattern="yyyy-MM-dd'T'HH:mm" type="both"
                                       value="${voucher.voucherUpdateDate}" var="voucherUpdateDate"/>
                        <td><fmt:formatDate pattern="dd.MM.yyyy hh:mm:ss" value="${voucherUpdateDate}"/></td>
                        <td><c:out value="${voucher.statusVoucher.name}"></c:out></td>
                        <td>
                            <form:form method="POST" action="home/editVoucher.do">
                                <input type="hidden" name="voucher_id" value="${voucher.id}">
                                <input type="submit" class="input_home"
                                       value="<spring:message code="edit" />"/>
                            </form:form>
                        </td>
                        <td>
                            <c:if test="${voucher.statusVoucher.getId()==2 || voucher.statusVoucher.getId()==5
                                      || voucher.statusVoucher.getId()==3}">
                                <form:form method="POST" action="home/Contract">
                                    <input type="hidden" name="voucher_id" value="${voucher.id}">
                                    <input type="submit" class="input_home" value="<spring:message code="contract" />"/>
                                </form:form>
                            </c:if>
                        </td>
                        <td>
                            <form:form method="POST" action="home/Memo">
                                <input type="hidden" name="tour_id" value="${voucher.tourDate.tour.id}">
                                <input type="submit" class="input_home" value="<spring:message code="memo" />"/>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Клиенты -->
    <div class="tab-pane fade" id="Tab1" style="padding: 0px 210px;">
        <div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <td>Id</td>
                        <td>Фамилия</td>
                        <td>Имя</td>
                        <td>Отчество</td>
                        <td>Дата рождения</td>
                        <td>Вид документа</td>
                        <td>Документ</td>
                        <td>Адрес</td>
                        <td>Телефонный номер</td>
                        <td>Почта</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${clients}" var="client">
                        <tr>
                            <td><c:out value="${client.id}"></c:out></td>
                            <td><c:out value="${client.surname}"></c:out></td>
                            <td><c:out value="${client.name}"></c:out></td>
                            <td><c:out value="${client.patronymic}"></c:out></td>
                            <td><fmt:formatDate pattern="dd.MM.yyyy" type="date" value="${client.birthDate}"/></td>
                            <td><c:out value="${client.documentType}"></c:out></td>
                            <td><c:out value="${client.document}"></c:out></td>
                            <td><c:out value="${client.address}"></c:out></td>
                            <td><c:out value="${client.telNum}"></c:out></td>
                            <td><c:out value="${client.email}"></c:out></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- Прочее -->
    <div class="tab-pane fade" id="Tab5" style="padding: 0px 210px;">
        <div>
            <h3 style="margin-bottom: 5px">
                <spring:message code="citys"/>
            </h3>
            <div>
                <table>
                    <thead>
                    <tr>
                        <td>Id</td>
                        <td>Название</td>
                        <td>Страна</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${citys}" var="city">
                        <tr>
                            <td><c:out value="${city.id}"></c:out></td>
                            <td><c:out value="${city.name}"></c:out></td>
                            <td><c:out value="${city.country.getName()}"></c:out></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <form:form method="POST" modelAttribute="citySave" action="saveCity.do">
                            <td></td>
                            <td><form:input path="name"/> <form:errors path="name" cssClass="error"/></td>
                            <td>
                                <select path="country_id" onchange="">
                                    <option value="-1">Выберите страну</option>
                                    <c:forEach items="${countrys}" var="country">
                                        <option value="${country.id}">${country.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="submit" class="input_home" value="<spring:message code="save"/>"/></td>
                        </form:form>
                    </tr>
                    </tbody>
                </table>
            </div>
            <h3 style="margin-bottom: 5px">
                <spring:message code="countrys"/>
            </h3>
            <div>
                <table>
                    <thead>
                    <tr>
                        <td>Id</td>
                        <td>Название</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${countrys}" var="country">
                        <tr>
                            <td><c:out value="${country.id}"></c:out></td>
                            <td><c:out value="${country.name}"></c:out></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <form:form method="POST" modelAttribute="countrySave" action="saveСountry.do">
                            <td></td>
                            <td><form:input path="name"/> <form:errors path="name" cssClass="error"/></td>
                            </td>
                            <td><input type="submit" class="input_home" value="<spring:message code="save"/>"/></td>
                        </form:form>
                    </tr>
                    </tbody>
                </table>
            </div>
            <h3 style="margin-bottom: 5px">
                <spring:message code="touroperators"/>
            </h3>
            <div>
                <table>
                    <thead>
                    <tr>
                        <td>Id</td>
                        <td>Название</td>
                        <td>Имя контакта</td>
                        <td>Email контакта</td>
                        <td>Телефон контакта</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tourOperators}" var="tourOperator">
                        <tr>
                            <td><c:out value="${tourOperator.id}"></c:out></td>
                            <td><c:out value="${tourOperator.name}"></c:out></td>
                            <td><c:out value="${tourOperator.contactName}"></c:out></td>
                            <td><c:out value="${tourOperator.contactEmail}"></c:out></td>
                            <td><c:out value="${tourOperator.contactTelnum}"></c:out></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- Туры -->
    <div class="tab-pane fade" id="Tab6" style="padding: 0px 210px;">
        <div>
            <table>
                <thead>
                <tr>
                    <td>Id</td>
                    <td>Назва</td>
                    <td>Количество ночей</td>
                    <td>Туроператор</td>
                    <td>Питания</td>
                    <td>Транспорт</td>
                    <td>Тип</td>
                    <td></td>
                    <td scope="col">
                        <div class=" text-center">
                            <form:form method="POST" action="home/addTour">
                                <input type="submit" class="input_home" value="<spring:message code="add" />"/>
                            </form:form>
                        </div>
                    </td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tours}" var="tour">
                    <tr>
                        <td><c:out value="${tour.id}"></c:out></td>
                        <td><c:out value="${tour.name}"></c:out></td>
                        <td><c:out value="${tour.quantityNight}"></c:out></td>
                        <td><c:out value="${tour.getTourOperator().name}"></c:out></td>
                        <td><c:out value="${tour.getTypeFood().name}"></c:out></td>
                        <td><c:out value="${tour.getTypeTransport().name}"></c:out></td>
                        <td><c:out value="${tour.getTypeTour().name}"></c:out></td>
                        <td>
                            <form:form method="POST" action="home/editTour">
                                <input type="hidden" name="tour_id" value="${tour.id}">
                                <input type="submit" class="input_home" value="<spring:message code="edit" />"/>
                            </form:form>
                        </td>
                        <td>
                            <form:form method="POST" action="home/deleteTour">
                                <input type="hidden" name="tour_id" value="${tour.id}">
                                <input type="submit" style="background-color: #ff3636;" class="input_home"
                                       value="<spring:message code="delete" />"/>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
    $(function () {
        $('[data-toggle="tab"]').on('shown.bs.tab', function () {
            // сохраним последнюю вкладку
            localStorage.setItem('lastTab', $(this).attr('href'));
        });
        //перейти к последней вкладки, если она существует:
        var lastTab = localStorage.getItem('lastTab');
        if (lastTab) {
            $('[href="' + lastTab + '"]').tab('show');
        } else {
            // установить первую вкладку активной если lasttab не существует
            $('[data-toggle="tab"]:first').tab('show');
        }
    });
</script>

<script src="../js/vendor/modernizr-3.5.0.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/owl.carousel.min.js"></script>
<script src="../js/isotope.pkgd.min.js"></script>
<script src="../js/ajax-form.js"></script>
<script src="../js/waypoints.min.js"></script>
<script src="../js/imagesloaded.pkgd.min.js"></script>
<script src="../js/scrollIt.js"></script>
<script src="../js/wow.min.js"></script>
<script src="../js/nice-select.min.js"></script>
<script src="../js/plugins.js"></script>
<script src="../js/range.js"></script>
<script src="../js/slick.min.js"></script>

</body>
</html>