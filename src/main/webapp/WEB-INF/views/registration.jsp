<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76" href="img/apple-icon.png">
    <link rel="icon" type="image/png" href="img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Silentium</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
          name='viewport'/>
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <!-- CSS Files -->
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/now-ui-kit.css?v=1.3.0" rel="stylesheet"/>
</head>

<body class="landing-page sidebar-collapse">
<jsp:include page="header.jsp"/>
<div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image"
         style="background-image: url(img/login.jpg)"></div>
    <div style="margin-top: 6%;" class="content">
        <div class="container">
            <div class="col-md-4 ml-auto mr-auto">
                <div class="card card-login card-plain">
                    <form:form autocomplete="off" action="/registration"
                               modelAttribute="user" method="post" class="form" role="form">
                        <div class="card-header text-center">
                            <div style="filter: invert(1);" class="logo-container">
                                <img style="max-width: 50%;" src="img/samsung.png" alt="">
                            </div>
                        </div>
                        <div class="card-body">
                            <form:errors path="name" cssClass="badge badge-danger"/>
                            <div class="input-group no-border input-lg">

                                <div class="input-group-prepend">

										<span class="input-group-text"> <i
                                                class="now-ui-icons users_circle-08"></i>
										</span>
                                </div>

                                <form:input type="text" path="name" placeholder="FirstName"
                                            class="form-control"/>

                            </div>
                            <form:errors path="surname" cssClass="badge badge-danger"/>
                            <div class="input-group no-border input-lg">
                                <div class="input-group-prepend">
										<span class="input-group-text"> <i
                                                class="now-ui-icons business_badge"></i>
										</span>
                                </div>
                                <form:input type="text" path="surname" placeholder="Surname"
                                            class="form-control"/>
                            </div>
                            <form:errors path="login" cssClass="badge badge-danger"/>
                            <div class="input-group no-border input-lg">
                                <div class="input-group-prepend">
										<span class="input-group-text"> <i
                                                class="now-ui-icons objects_globe"></i>
										</span>
                                </div>
                                <form:input type="text" path="login" placeholder="Login"
                                            class="form-control"/>
                            </div>
                            <form:errors path="password" cssClass="badge badge-danger"/>
                            <div class="input-group no-border input-lg">
                                <div class="input-group-prepend">
										<span class="input-group-text"> <i
                                                class="now-ui-icons ui-1_lock-circle-open"></i>
										</span>
                                </div>
                                <form:input type="password" path="password"
                                            placeholder="Password" class="form-control"/>

                            </div>

                            <c:if test="${successMessage != null}">
                                <div class="alert alert-success" role="alert">
                                    <div class="container">
                                        <div class="alert-icon">
                                            <i class="now-ui-icons ui-2_like"></i>
                                        </div>

                                        <c:out value="${successMessage}"></c:out>

                                        <button type="button" class="close" data-dismiss="alert"
                                                aria-label="Close">
												<span aria-hidden="true"> <i
                                                        class="now-ui-icons ui-1_simple-remove"></i>
												</span>
                                        </button>
                                    </div>
                                </div>
                            </c:if>

                        </div>
                        <div class="card-footer text-center">
                            <button class="btn btn-primary btn-round" type="submit">Зарегистрироваться</button>
                            <div class="card-footer text-center">

                                <div class="pull-right">
                                    <h6>
                                        <a href="/login">Войти</a>
                                    </h6>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--   Core JS Files   -->
<script src="js/core/jquery.min.js" type="text/javascript"></script>
<script src="js/core/popper.min.js" type="text/javascript"></script>
<script src="js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
<script src="js/plugins/bootstrap-datepicker.js" type="text/javascript"></script>
<!--  Google Maps Plugin    -->
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
<script src="js/now-ui-kit.js?v=1.3.0" type="text/javascript"></script>
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
