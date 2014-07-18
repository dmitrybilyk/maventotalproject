<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<div class="mod-checkout-summary-delivery accordion-child">

    <h3><spring:theme code="form.summaryCustomerInfo.title" /> <span class="responsive-switcher"></span></h3>
    <div class="inner slide-block">
      <common:customer
                prename="${form.firstName}"
                surname="${form.lastName}"
                email="${form.email}"
              />

        <button type="button" onclick='location.href="${pageContext.request.contextPath}${pathToCustomerPage}"'><spring:theme code="form.summaryDelivery.btnChange" /></button>

    </div>
</div>