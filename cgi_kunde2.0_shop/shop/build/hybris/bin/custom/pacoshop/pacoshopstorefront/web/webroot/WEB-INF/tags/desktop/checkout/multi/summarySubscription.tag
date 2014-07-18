<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="mod-checkout-summary-subscription accordion-child">

    <h3><spring:theme code="form.summarySubscription.title" /> <span class="responsive-switcher"></span></h3>

    <div class="slide-block inner">

	    <div class="holder">
	        <div class="inner">
	            <c:choose>
		            <c:when test="${empty form.deliveryStartDate}">
		                <p><spring:theme code="form.summarySubscription.deliveryASAP" /> </p>
		            </c:when>
		            <c:otherwise>
		                <p> <fmt:formatDate pattern="dd.MM.yyyy" value="${form.deliveryStartDate}" /></p>
		            </c:otherwise>
		        </c:choose>
	        </div>
	    </div>
        <c:if test="${currentstep.stepName == 'summary'}">
	        <button type="button" onclick='location.href="${pageContext.request.contextPath}${pathToShipmentPage}"'><spring:theme code="form.summarySubscription.btnChange" /></button>
        </c:if>
    </div>

</div>