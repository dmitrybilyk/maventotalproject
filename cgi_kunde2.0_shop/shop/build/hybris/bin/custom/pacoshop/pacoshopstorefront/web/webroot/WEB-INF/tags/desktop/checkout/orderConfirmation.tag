<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>

<div class="mod-order-confirmation">
    <div class="inner">

        <div class="item-description">
            <checkout:orderItems />
        </div>

        <div class="item-order-numder">

            <p><spring:theme code="checkout.orderconfirmation.heading"/></p>
            <span class="number">${cartContentsFragmentDTO.orderCode}</span>
            <p class="print-info" id="print-label"><spring:theme code="checkout.orderconfirmation.printinfo.label" /></p>
            <button class="btn" id="btn-print"><spring:theme code="checkout.orderconfirmation.printinfo.button.label"/> </button>
        </div>

    </div>
</div>