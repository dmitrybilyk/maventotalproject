<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="mod-errors">
    <div class="inner">

        <p class="title"><spring:theme code="checkout.multi.userdataPage.invalid.data" /></p>

        <ul>

            <c:forEach items="${requestScope['org.springframework.validation.BindingResult.form'].allErrors}" var="error">
                <li><spring:theme code="${error.code}"/></li>
            </c:forEach>

        </ul>

    </div>
</div>
