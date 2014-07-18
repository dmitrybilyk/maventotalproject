<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<div class="mod-checkout-summary-billing-address  accordion-child">

    <h3><spring:theme code="form.summaryBillingAddress.title" /> <span class="responsive-switcher"></span></h3>
    <div class="inner slide-block">
        <common:address
                prename="${form.billingFirstName}"
                surname="${form.billingLastName}"
                street="${form.billingStreet}"
                houseNumber="${form.billingHouseNumber}"
                additionalStreet="${form.billingAdditionalStreet}"
                zip="${form.billingZip}"
                city="${form.billingCity}"
                email="${form.billingEmail}"
                country="${form.billingCountry}" />

        <c:if test="${currentstep.stepName == 'summary'}">
            <button type="button" onclick='location.href="${pageContext.request.contextPath}${pathToShipmentPage}"'><spring:theme code="form.summaryBillingAddress.btnChange" /></button>
        </c:if>
    </div>

</div>