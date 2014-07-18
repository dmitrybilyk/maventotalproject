<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mod-checkout-summary-delivery accordion-child">

    <h3><spring:theme code="form.summaryDelivery.title" /> <span class="responsive-switcher"></span></h3>
    <c:set var="checkEmail" value="${form.newShipmentEmail}"/>
    <!--
    need to set to country empty string, because it set by default to "Germany". for some product's types country isn't needed
    -->
   <c:choose>
       <c:when test="${not empty checkEmail}">
           <c:set var="country" value="" />
       </c:when>
       <c:otherwise>
           <c:set var="country" value="${form.newShipmentCountry}" />
       </c:otherwise>
   </c:choose>

    <div class="inner slide-block">
		<common:address
            prename="${form.newShipmentFirstName}"
            surname="${form.newShipmentLastName}"
            company="${form.newShipmentCompany}"
            street="${form.newShipmentStreet}"
            houseNumber="${form.newShipmentHouseNumber}"
            additionalStreet="${form.newShipmentAdditionalStreet}"
            zip="${form.newShipmentZip}"
            city="${form.newShipmentCity}"
            country="${country}"
            email="${form.newShipmentEmail}"    />
        <c:if test="${currentstep.stepName == 'summary'}">
            <button type="button" onclick='location.href="${pageContext.request.contextPath}${pathToShipmentPage}"'><spring:theme code="form.summaryDelivery.btnChange" /></button>
        </c:if>
    </div>
</div>

