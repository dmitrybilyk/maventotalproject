<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<div class="mod-checkout-summary-delivery ert accordion-child">

    <h3><spring:theme code="form.summaryCustomerInfo.title" /> <span class="responsive-switcher"></span></h3>
    <div class="inner slide-block">
      <common:customer
                prename="${form.firstName}"
                surname="${form.lastName}"
                company="${form.company}"
                street="${form.street}"
                houseNumber="${form.houseNumber}"
                additionalStreet="${form.additionalStreet}"
                zip="${form.zip}"
                city="${form.city}"
                country="${form.country}"
                homePhone="${form.phonePrefixHome}${form.phoneExtensionHome}${form.phoneNumberHome}"
                businessPhone="${form.phonePrefixBusiness}${form.phoneExtensionBusiness}${form.phoneNumberBusiness}"
                mobile="${form.mobilePrefix}${form.mobileNumber}"
                email="${form.email}"
              />

        <c:if test="${currentstep.stepName == 'summary'}">
            <div class="button-holder">
                <span class="tooltip"><spring:theme code="form.summaryCustomerInfo.tooltip" /></span>
                <button type="button" onclick='location.href="${pageContext.request.contextPath}${pathToCustomerPage}"'><spring:theme code="form.summaryDelivery.btnChange" /></button>
            </div>
        </c:if>
    </div>
</div>
