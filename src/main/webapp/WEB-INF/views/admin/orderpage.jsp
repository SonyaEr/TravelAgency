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
        <h5 style="text-align: center; margin-top: 15px">Заявка на путевку</h5>
        <table class="tourcart_table" style="font-size: 0.8em">
            <form:form method="POST" action="orderpage/updateOrder.do" id="form_order">
            <col class="col_150">
            <col class="col_250">
            <tbody style="font-family: Montserrat, Helvetica Neue, Arial, sans-serif;">
            <tr style="font-family:Montserrat, Helvetica Neue, Arial, sans-serif; font-size: 0.9em ">
            <tr style="background-color: #ffffff;">
                <td><h5>Номер заказа</h5></td>
                <td><h5 style="text-align: center"><c:out value="${order.id}"></c:out></h5></td>
            </tr>
            <tr style="background-color: #ffffff;">
                <td><h5>Дата заказа</h5></td>
                <td><h5 style="text-align: center"><fmt:formatDate pattern="dd.MM.yyyy"
                                                                   value="${order.orderDate}"/></h5></td>
            </tr>
            <tr style="background-color: #ffffff;">
                <td><h5>Клиент</h5></td>
                <td><h5 style="text-align: center"><c:out value="${order.client.getFIO()}"></c:out></h5></td>
            </tr>

            <tr style="background-color: #ffffff;">
                <td rowspan="2"><h5>Способ связи с клиентом</h5></td>


                <td style="background-color: #f2f2f2; border: 1px solid #f2f2f2">
                    <h5 style="text-align: center; "> Телефон: <c:out value="${order.client.getTelNum()}"></c:out></h5>
                </td>

            </tr>
            <td style="background-color: #f2f2f2;">
                <h5 style="text-align: center; "> Почта: <c:out value="${order.client.getEmail()}"></c:out></h5></td>

            <tr style="background-color: #ffffff;">
                <td><h5>Комментарий к заказу</h5></td>
                <td style="text-align: center">
                        <textarea readonly rows="10" cols="60"
                                  name="comment_order">${order.comment}</textarea>
                </td>
            <tr style="background-color: #ffffff;">
                <td><h5>Тур</h5></td>
                <td><h5 style="text-align: center"><c:out value="${order.tourDate.tour.name}"></c:out></h5></td>
            </tr>
            <tr style="background-color: #ffffff;">
                <td><h5>Цена</h5></td>
                <td style="vertical-align: middle; text-align: center">
                    <h5>
                        <input style="text-align: center; border: none; outline: none; background-color: transparent"
                               id="price" type="number"
                               value="${order.tourDate.price}" readonly="readonly"/>
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
                            <input type="text" value="${order.count}" size="2" class="quantity"
                                   name="tour_date_count" data-max-count="20"
                                   id="tour_date_count"/>
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
                        <input style="text-align: center; border: none; outline: none; background-color: transparent"
                               id="summa" type="number"
                               value="${order.tourDate.price*order.count}" readonly="readonly"/>
                    </h4>
                </td>
            </tr>
            <tr style="background-color: #ffffff;">
                <td><h5>Статус заказа</h5></td>
                <td style="text-align: center">
                    <h5>
                        <select class="single_select_search" path="status_order" name="status_order">
                            <c:forEach items="${statusorders}" var="statusorder">
                                <option value="${statusorder.id}"
                                    ${statusorder.id == order.statusOrder.id
                                            ? 'selected="selected"' : ''}>${statusorder.name}</option>
                            </c:forEach>
                        </select>
                    </h5>
                </td>
            </tr>
            <tr style="background-color: #ffffff;">
                <td><h5>Примечание</h5></td>
                <td style="text-align: center">
                    <textarea rows="4" cols="60" name="note_order" maxlength="100">${order.note}</textarea>
                </td>
            </tr>
            </form:form>
            <tr style="background-color: #ffffff;">
                <td></td>
                <td style="text-align: center; border: 0px solid #f9f9ff; display: flex; column-gap: 20px; justify-content: center;">
                    <input form="form_order" type="hidden" name="order_id" value="${order.id}">
                    <button form="form_order" class="input_home" style=" background: #0b4f5f;"
                            name="saveOrder" type="submit"><spring:message code="saveorder"/>
                    </button>
                    <c:if test="${order.statusOrder.getId()==4}">
                        <form:form method="POST" action="home/createVoucher.do">
                            <input type="hidden" name="order_id" value="${order.id}">
                            <button class="input_home"
                                    name="createvoucher" type="submit"><spring:message code="createvoucher"/>
                            </button>
                        </form:form>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
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