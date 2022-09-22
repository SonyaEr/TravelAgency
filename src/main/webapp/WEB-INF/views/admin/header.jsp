<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<div class="wrapper">
    <div class="page-header clear-filter page-header-v-small"
         filter-color="white">
        <div class="page-header-image2" data-parallax="true"
             style="background-image: url('../img/admin_profile.png');"></div>
        <div class="container">
            <h2 class="title2">
                <c:out value="${userSurName}"/> <c:out value="${userName}"/>
            </h2>
            <div class="content">
                <div class="social-description2">
                    <h7 style="color: #ffffffa3;">
                        <c:out value="${userEmail}"/>
                    </h7>
                </div>
            </div>
        </div>
    </div>
</div>