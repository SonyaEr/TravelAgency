<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent "
     color-on-scroll="10">
    <div class="container">
        <div class="dropdown button-dropdown">
            <a href="#pablo" class="dropdown-toggle" id="navbarDropdown" data-toggle="dropdown">
                <span class="button-bar"></span>
                <span class="button-bar"></span>
                <span class="button-bar"></span>
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                <c:if test="${role=='NONE'}">
                    <a class="dropdown-item" href="/login"><spring:message code="login"/></a>
                    <a class="dropdown-item" href="/registration"><spring:message code="registration"/></a>
                    <a class="dropdown-item" href="/search"> <spring:message code="tours"/></a>
                </c:if>
                <c:if test="${role!='NONE'}">
                    <a class="dropdown-item" href="/login"><spring:message code="logout"/></a>
                    <a class="dropdown-item" href="/search"> <spring:message code="tours"/></a>
                    <c:if test="${role=='ADMIN'}">
                        <a class="dropdown-item" href="/home"> <spring:message code="profile"/></a>
                    </c:if>
                    <c:if test="${role!='ADMIN'}">
                        <a class="dropdown-item" href="/home"> <spring:message code="profile"/></a>
                    </c:if>
                </c:if>

            </div>
        </div>
        <div class="navbar-translate">
            <a class="navbar-brand" href="/"
               title="Designed by Eroshenko S.. Coded by Eroshenko S."> Silentium </a>
            <button class="navbar-toggler navbar-toggler" type="button"
                    data-toggle="collapse" data-target="#navigation"
                    aria-controls="navigation-index" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-bar top-bar"></span> <span
                    class="navbar-toggler-bar middle-bar"></span> <span
                    class="navbar-toggler-bar bottom-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse justify-content-end"
             id="navigation" data-nav-image="/img/blurred-image-1.jpg">
            <ul class="navbar-nav">
                <c:if test="${role=='NONE'}">
                    <li class="nav-item"><a class="nav-link" href="/login"><spring:message code="login"/></a></li>
                    <li class="nav-item"><a class="nav-link" href="/registration"><spring:message
                            code="registration"/></a></li>
                    <li class="nav-item"><a class="nav-link" href="/search"><spring:message code="tours"/></a></li>
                </c:if>
                <c:if test="${role!='NONE'}">
                    <li class="nav-item"><a class="nav-link" href="/logout"><spring:message code="logout"/></a></li>
                    <li class="nav-item"><a class="nav-link" href="/search"><spring:message code="tours"/></a></li>
                    <c:if test="${role=='ADMIN'}">
                        <li class="nav-item"><a class="nav-link" href="/home"><spring:message code="profile"/></a>
                        </li>
                    </c:if>
                    <c:if test="${role!='ADMIN'}">
                        <li class="nav-item"><a class="nav-link" href="/home"><spring:message code="profile"/></a>
                        </li>
                    </c:if>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<!-- End Navbar -->
