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
    <link rel="stylesheet" href="../css/custom.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/now-ui-kit.css?v=1.3.0"/>
    <!-- CSS Files -->
    <link href="../css/tour.css" rel="stylesheet"/>
    <style type="text/css">

        table, table tr td {
            border: 1px solid #000000;
            text-align: left;
        }

        table, table tr th {
            border: 1px solid #000000;
            text-align: center;
        }

        table {
            width: 100%;
            text-align: center;
            border-collapse: collapse;
            table-layout: fixed;
        }

        .col_150 {
            width: 150px;
        }

        .col_250 {
            width: 250px;
        }
        .col_200 {
            width: 200px;
        }
        .col_50 {
            width: 50px;
        }

        h2 {
            text-align: center;
            font-size: 22px;
        }

        h3 {
            text-align: left;
            font-size: 16px;
        }

        h4 {
            text-align: center;
            font-size: 16px;
        }
    </style>
</head>

<body class="profile-page sidebar-collapse">
<jsp:include page="../header.jsp"/>
<jsp:include page="header.jsp"/>
<div style="padding: 0px 300px;">
    <div class="container">
        <h5 style="text-align: center; margin-top: 15px">Путевка</h5>
        <%--@elvariable id="voucher" type="com.silentium.model.Voucher"--%>
        <form:form method="POST" modelAttribute="voucher" action="voucherpage/updateVoucher.do">
            <table class="tourcart_table" style="font-size: 0.8em">
                <col class="col_150">
                <col class="col_250">
                <tbody style="font-family: Montserrat, Helvetica Neue, Arial, sans-serif;">
                <tr style="font-family:Montserrat, Helvetica Neue, Arial, sans-serif; font-size: 0.9em ">
                <tr style="background-color: #ffffff;">
                    <td><h5>Номер путевки</h5></td>
                    <td><h5 style="text-align: center">
                        <form:input
                                cssStyle="text-align: center; border: none; outline: none; background-color: transparent"
                                id="id" path="id" value="${vvoucher.id}"
                                readonly="true"/>
                        <form:errors path="id" cssClass="error"/>
                    </h5></td>
                </tr>
                <tr style="background-color: #ffffff;">
                    <td><h5>Дата путевки</h5></td>
                    <td>
                        <h5 style="text-align: center">
                            <fmt:formatDate pattern="dd.MM.yyyy" value="${voucher.voucherDate}"/>
                        </h5>
                    </td>

                </tr>
                <tr style="background-color: #ffffff;">
                    <td><h5>Клиент</h5></td>
                    <td><h5 style="text-align: center"><c:out value="${voucher.order.client.getFIO()}"></c:out></h5>
                    </td>
                </tr>

                <tr style="background-color: #ffffff;">
                    <td rowspan="2"><h5>Способ связи с клиентом</h5></td>


                    <td style="background-color: #f2f2f2; border: 1px solid #f2f2f2">
                        <h5 style="text-align: center; "> Телефон: <c:out
                                value="${voucher.order.client.getTelNum()}"></c:out></h5>
                    </td>

                </tr>
                <td style="background-color: #f2f2f2;">
                    <h5 style="text-align: center; "> Почта: <c:out
                            value="${voucher.order.client.getEmail()}"></c:out></h5>
                </td>

                <tr style="background-color: #ffffff;">
                    <td><h5>Комментарий к путевке</h5></td>
                    <td style="text-align: center">
                        <form:textarea rows="10" cols="60" path="comment" value="${voucher.comment}"/>
                        <form:errors path="comment" cssClass="error"/>
                    </td>
                <tr style="background-color: #ffffff;">
                    <td><h5>Тур</h5></td>
                    <td><h5 style="text-align: center"><c:out value="${voucher.order.tourDate.tour.name}"></c:out></h5>
                    </td>
                </tr>
                <tr style="background-color: #ffffff;">
                    <td><h5>Цена</h5></td>
                    <td style="vertical-align: middle; text-align: center">
                        <h5>
                            <input style="text-align: center; border: none; outline: none; background-color: transparent"
                                   id="price" type="number"
                                   value="${voucher.order.tourDate.price}" readonly="readonly"/>
                        </h5>
                    </td>
                </tr>
                <tr style="background-color: #ffffff;">
                    <td><h5>Количество</h5></td>
                    <td>
                        <div class="quantity_wr_2" style="text-align: center">
                            <div class="quantity_inner">
                                <button type="button" class="bt_minus">
                                    <svg viewBox="0 0 24 24">
                                        <line x1="5" y1="12" x2="19" y2="12"></line>
                                    </svg>
                                </button>
                                <form:input id="count_id" path="count" value="${voucher.count}"
                                            type="text" name="count_id" data-max-count="20"
                                            size="2" class="quantity"/>
<%--                                <input type="text" value="${voucher.count}" size="2" class="quantity"--%>
<%--                                       name="tour_date_count" data-max-count="20"--%>
<%--                                       id="tour_date_count"/>--%>
                                <button type="button" class="bt_plus">
                                    <svg viewBox="0 0 24 24">
                                        <line x1="12" y1="5" x2="12" y2="19"></line>
                                        <line x1="5" y1="12" x2="19" y2="12"></line>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr style="background-color: #ffffff;">
                    <td><h5>Сумма</h5></td>
                    <td style="vertical-align: middle; text-align: center">
                        <h4>
                            <form:input cssStyle="text-align: center; border: none; outline: none"
                                        id="summa" path="amount" value="${voucher.amount}"/>
                            <form:errors path="amount" cssClass="error"/>
                        </h4>
                    </td>
                </tr>
                <tr style="background-color: #ffffff;">
                    <td><h5>Статус путевки</h5></td>
                    <td style="text-align: center">
                        <h5>
                            <select class="single_select_search" path="status_voucher" name="status_voucher">
                                <c:forEach items="${statusvouchers}" var="statusvoucher">
                                    <option value="${statusvoucher.id}"
                                        ${statusvoucher.id == voucher.statusVoucher.id
                                                ? 'selected="selected"' : ''}>${statusvoucher.name}</option>
                                </c:forEach>
                            </select>
                        </h5>
                    </td>
                </tr>
                <tr style="background-color: #ffffff;">
                    <td><h5>Место создания</h5></td>
                    <td style="text-align: center">
                        <form:input cssStyle="text-align: center"
                                    path="place_issue" value="${voucher.place_issue}"/>
                        <form:errors path="place_issue" cssClass="error"/>
                    </td>
                </tr>
                </tbody>
            </table>
            <table class="tourcart_table" style="font-size: 0.8em">
                <col class="col_150">
                <col class="col_200">
                <col class="col_50">
                <tbody style="font-family: Montserrat, Helvetica Neue, Arial, sans-serif;">
                <tr style="font-family:Montserrat, Helvetica Neue, Arial, sans-serif; font-size: 0.9em; background-color: #ffffff ">
                    <th style="font-family:Montserrat, Helvetica Neue, Arial, sans-serif; font-size: 0.9em">
                        <h5>Туристы</h5>
                    </th>
                    <td style="background-color: #ffffff">
                        <button class="input_home" style=" background: #0b4f5f;"
                                name="addoTurist" type="submit"><spring:message code="add"/>
                        </button>
                    </td>
                    <td style="border-right: solid #f9f9ff; background-color: #ffffff"></td>
                </tr>
                <c:forEach items="${voucherclients}" var="voucherclient">
                    <tr style="font-family:Montserrat, Helvetica Neue, Arial, sans-serif; font-size: 0.9em; background-color: #ffffff ">
                        <td style="border-top: 0px solid #f9f9ff; border-bottom: 0px solid #f9f9ff; background-color: #ffffff"></td>
                        <td style="text-align: center; border: 0px solid #f9f9ff; display: flex;
                        column-gap: 20px; row-gap: 20px; flex-wrap: wrap; align-content: center">
                            <h5 style="text-align: left; ">
                                <c:out value="${voucherclient.getFIO()}"></c:out>
                            </h5>
                        </td>
                        <td style="border-right: solid #f9f9ff; background-color: #ffffff">
                            <button style="background:#ff3636;; padding: 10px 15px;" class="input_home"
                                    style=" background: #0b4f5f;"
                                    name="deleteTourist" type="submit">Х
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <tr style="background-color: #ffffff;">
                <td></td>
                <td style="text-align: center; border: 0px solid #f9f9ff; display: flex; column-gap: 20px; justify-content: center;">
                    <button class="input_home" style=" background: #0b4f5f;"
                            name="saveVoucher" type="submit"><spring:message code="save"/>
                    </button>
                </td>
            </tr>
        </form:form>
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
                let $input = $(this).parent().find('.quantity');
                let count = parseInt($input.val()) - 1;
                count = count < 1 ? 1 : count;
                $input.val(count);
                document.getElementById('summa').value = (parseFloat(document.getElementById('price').value)
                    * count).toFixed(1);
                document.getElementById('count_id').value = count;
            });
    // Прибавляем кол-во по клику
    $('.quantity_inner .bt_plus')
        .click(
            function () {
                let $input = $(this).parent().find('.quantity');
                let count = parseInt($input.val()) + 1;
                count = count > parseInt($input.data('max-count')) ? parseInt($input.data('max-count')) : count;
                $input.val(parseInt(count));
                document.getElementById('summa').value = (parseFloat(document.getElementById('price').value)
                    * count).toFixed(1);
                document.getElementById('count_id').value = count;
            });
    // Убираем все лишнее и невозможное при изменении поля
    $('.quantity_inner .quantity')
        .bind(
            "change keyup input click",
            function () {
                if (this.value.match(/[^0-9]/g)) {
                    this.value = this.value.replace(/[^0-9]/g, '');
                }
                if (this.value == "") {
                    this.value = 1;
                }
                if (this.value > parseInt($(this).data('max-count'))) {
                    this.value = parseInt($(this).data('max-count'));
                }
                document.getElementById('summa').value = parseInt(document.getElementById('price').value)
                    * this.value;

                document.getElementById('count_id').value = this.value;
            });
</script>

</body>
</html>