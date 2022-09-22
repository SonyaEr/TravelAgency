<%@ page buffer="none" isThreadSafe="true" errorPage="admin/error.jsp"
         language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<footer class="footer footer-default">
    <div class="container">
        <nav>
            <ul>
                <c:if test="${role=='NONE'}">
                    <li><a href="/login"> <spring:message code="login"/></a></li>
                    <li><a href="/registration"> <spring:message code="registration"/></a></li>
                    <li><a href="/search"> <spring:message code="tours"/></a></li>
                </c:if>
                <c:if test="${role!='NONE'}">
                    <li><a href="/logout"> <spring:message code="logout"/></a></li>
                    <li><a href="/search"> <spring:message code="tours"/></a></li>
                    <li><a href="/home"> <spring:message code="profile"/></a></li>
                </c:if>
                <select id="locales">
                    <option value="ru"><spring:message code="rus"/></option>
                    <option value="en"><spring:message code="eng"/></option>
                    <option value="uk"><spring:message code="ukr"/></option>
                </select>
            </ul>
        </nav>
        <div class="copyright" id="copyright">
            &copy;
            <script>
                document.getElementById('copyright').appendChild(
                    document.createTextNode(new Date().getFullYear()))
            </script>
            , Created by <a>Yeroshenko S.</a>
        </div>
    </div>
    <script type="text/javascript">
        var params = getURLWithoutVar('lang');
        $(document).ready(function () {
            var selItem = localStorage.getItem("locales");
            $('#locales').val(selItem ? selItem : 'en');
            $("#locales").change(function () {
                var selectedOption = $('#locales').val();
                if (selectedOption) {
                    window.location.replace('?lang=' + selectedOption + params);
                    localStorage.setItem("locales", selectedOption);
                }
            });
        });

        function getURLWithoutVar(key) {
            var params = '';
            location.search.substr(1).split('&')
                .reduce(function (res, a) {
                    var t = a.split('=');
                    if (key != decodeURIComponent(t[0]) && decodeURIComponent(t[0]) != '') params += '&' + a;
                },{});
            return params;
        }
    </script>
</footer>



