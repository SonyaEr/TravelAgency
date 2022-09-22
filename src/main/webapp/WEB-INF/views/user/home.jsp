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
	<link rel="stylesheet" href="../css/custom.css">
	<link rel="stylesheet" href="../css/index.css" />
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

	<li class="nav-item"><a class="nav-link" href="#Tab2" data-toggle="tab"><spring:message code="orders"/> </a></li>
	<li class="nav-item"><a class="nav-link" href="#Tab3" data-toggle="tab"><spring:message code="vouchers"/> </a></li>
	<li class="nav-item"><a class="nav-link" href="#Tab1" data-toggle="tab"><spring:message code="profile"/> </a></li>

</ul>
<div class="tab-content" style="padding: 20px 210px;">
	<!-- Личные данные -->
	<div class="tab-pane fade" id="Tab1">

		<div>
			<div>
				<%--@elvariable id="person" type="com.silentium.model.Person"--%>
				<form:form method="POST" modelAttribute="person" action="saveClient.do">
					<table>
						<tbody style="font-family: Montserrat, Helvetica Neue, Arial, sans-serif; font-size: 0.8em;">
						<tr>
							<td><h5><spring:message code="login"/></h5></td>
							<td>
								<h5><c:out value="${person.email}"/></h5>
							</td>
						</tr>
						<tr>
							<td><h5><spring:message code="surname"/></h5></td>
							<td>
								<h5><form:input path="surname" value="${person.surname}"/>
								</h5> <form:errors path="surname" cssClass="error"/>
							</td>
						</tr>
						<tr>
							<td><h5><spring:message code="name"/></h5></td>
							<td>
								<h5><form:input path="name" value="${person.name}"/>
								</h5> <form:errors path="name" cssClass="error"/>
							</td>
						</tr>
						<tr>
							<td><h5><spring:message code="patronymic"/></h5></td>
							<td>
								<h5>
									<form:input path="patronymic" value="${person.patronymic}"/>
									<form:errors path="patronymic" cssClass="error"/>
								</h5>
							</td>
						</tr>
						<tr>
							<td><h5><spring:message code="birthdate"/></h5></td>
							<td>
								<h5>
									<fmt:formatDate value="${person.birthDate}" pattern="yyyy-MM-dd" var="birthDate_format"/>
									<form:input type="date" path="birthDate" value = "${birthDate_format}"/>
									<form:errors path="birthDate" cssClass="error"/>
								</h5>
							</td>
						</tr>
						<tr>
							<td><h5><spring:message code="typedocument"/></h5></td>
							<td>
								<h5>
									<form:input path="documentType" value="${person.documentType}"/>
									<form:errors path="documentType" cssClass="error"/>
								</h5>
							</td>
						</tr>
						<tr>
							<td><h5><spring:message code="document"/></h5></td>
							<td>
								<h5>
									<form:textarea rows="3" cols="50" path="document" value="${person.document}"/>
									<form:errors path="document" cssClass="error"/>
								</h5>
							</td>
						</tr>
						<tr>
							<td><h5><spring:message code="address"/></h5></td>
							<td>
								<h5>
									<form:textarea rows="4" cols="50" path="address" value="${person.address}"/>
									<form:errors path="address" cssClass="error"/>
								</h5>
							</td>
						</tr>
						<tr>
							<td><h5><spring:message code="telnum"/></h5></td>
							<td>
								<h5>
									<form:input path="telNum" value="${person.telNum}"/>
									<form:errors path="telNum" cssClass="error"/>
								</h5>
							</td>
						</tr>
						<input type="hidden" name="id" value="${person.id}">
						<tbody style="font-family: Montserrat, Helvetica Neue, Arial, sans-serif;">
					</table>
					<div align="center">
						<button
								class="boxed-btn3" type="submit"><spring:message code="save" />
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Заказы -->
	<div class="tab-pane fade" id="Tab2">

		<div>
			<table>
				<thead>
				<td><spring:message code="numorder"/></td>
				<td><spring:message code="dataorder"/></td>
				<td><spring:message code="client"/></td>
				<td><spring:message code="tour"/></td>
				<td><spring:message code="summa"/></td>
				<td><spring:message code="statusorder"/></td>
				<td></td>
				<td></td>
				</thead>
				<tbody>
				<c:forEach items="${orders}" var="order">
					<tr>
						<td><c:out value="${order.id}"></c:out></td>
						<td><fmt:formatDate pattern="dd.MM.yyyy" value="${order.orderDate}"/></td>
						<td><c:out value="${order.client.getFIO()}"></c:out></td>
						<td><c:out value="${order.tourDate.tour.name}"></c:out></td>
						<td><fmt:formatNumber pattern="0.00"
											  value="${order.tourDate.price*order.count}"/></td>
						<td><c:out value="${order.statusOrder.name}"></c:out></td>
						<td>
							<form:form method="POST" action="home/editOrder.do">
								<input type="hidden" name="order_id" value="${order.id}">
								<input type="submit" class="input_home"
									   value="<spring:message code="edit"/>"/>
							</form:form>
						</td>
						<td>
							<c:if test="${order.statusOrder.getId()==2}">
								<form:form method="POST" action="home/Invoice">
									<input type="hidden" name="order_id" value="${order.id}">
									<input type="submit" class="input_home" value="<spring:message code="invoice"/>"/>
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
	<div class="tab-pane fade" id="Tab3">

		<div>
			<table>
				<thead>
				<tr>
					<td>Номер путевки</td>
					<td>Статус</td>
					<td>Дата путевки</td>
					<td>Клиент</td>
					<td>Дата выезда</td>
					<td>Дата окончания</td>
					<td>Стоимость</td>
					<td>Номер заказа</td>
					<td>Тур</td>
					<td>Менеджер</td>
					<td></td>

					<td></td>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${vouchers}" var="voucher">
					<tr>
						<td><c:out value="${voucher.id}"></c:out></td>
						<td><c:out value="${voucher.statusVoucher.name}"></c:out></td>
						<fmt:parseDate pattern="yyyy-MM-dd" type="date"
									   value="${voucher.voucherDate}" var="voucherDate"/>
						<td><fmt:formatDate pattern="dd.MM.yyyy" value="${voucherDate}"/></td>
						<td><c:out value="${voucher.order.client.getFIO()}"></c:out></td>
						<td><fmt:formatDate pattern="dd.MM.yyyy" value="${voucher.tourDate.dateArrival}"/></td>
						<td><fmt:formatDate pattern="dd.MM.yyyy" value="${voucher.tourDate.getDateDeparture()}"/></td>
						<td><fmt:formatNumber pattern="0.00" value="${voucher.amount}"/></td>
						<td><c:out value="${voucher.order.id}"></c:out></td>
						<td><c:out value="${voucher.tourDate.tour.name}"></c:out></td>
						<td><c:out value="${voucher.manager.getFIO()}"></c:out></td>
						<td>
							<form:form method="POST" action="home/editVoucher.do">
								<input type="hidden" name="voucher_id" value="${voucher.id}">
								<input type="submit" class="input_home"
									   value="<spring:message code="view" />"/>
							</form:form>
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
	$(document).ready(function() {
		nowuiKit.initContactUsMap();
	});
</script>
<script>
	$(function() {
		$('[data-toggle="tab"]').on('shown.bs.tab', function () {
			// сохраним последнюю вкладку
			localStorage.setItem('lastTab', $(this).attr('href'));
		});
		//перейти к последней вкладки, если она существует:
		var lastTab = localStorage.getItem('lastTab');
		if (lastTab) {
			$('[href="' + lastTab + '"]').tab('show');
		}
		else
		{
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
<!-- <script src="js/gijgo.min.js"></script> -->
<script src="../js/slick.min.js"></script>

</body>
</html>