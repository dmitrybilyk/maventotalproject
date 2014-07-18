<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="mod-order-totals">

    <c:if test="${cartContentsFragmentDTO.totals.subTotal eq 0}" var="isZeroSubtotal"/>
    <c:if test="${cartContentsFragmentDTO.totals.deliveryCost eq 0}" var="isZeroDeliveryCosts"/>
    <c:if test="${cartContentsFragmentDTO.totals.paymentCost eq 0}" var="isZeroPaymentCosts"/>
    <c:if test="${displaymappings['NewsletterFormElementGroup'] or (cartContentsFragmentDTO.totals.totalPrice eq 0)}" var="isFreeOfCharge"/>

    <div class="inner">

        <h3><spring:theme code="checkout.orderTotals.title" /></h3>

        <dl>
            <dt class="net"><spring:theme code="checkout.orderTotals.netPrice"/></dt>
            <dd class="net">
                <c:choose>
                    <c:when test="${isZeroSubtotal}">
                        <spring:theme code="checkout.orderTotals.kostenlos"/>
                    </c:when>
                    <c:otherwise>
                        ${cartContentsFragmentDTO.totals.subTotalForShowing}
                    </c:otherwise>
                </c:choose>
            </dd>

            <c:forEach items="${cartContentsFragmentDTO.totals.totalTaxValues}" var="taxValue">
                <c:if test="${taxValue.appliedValue gt 0}">
                    <dt class="tax"><spring:message code="checkout.orderTotals.TaxTitle" arguments="${taxValue.value}" htmlEscape="false"/>
                    <dd class="tax"> ${taxValue.appliedValueForShowing}</dd>
                </c:if>
            </c:forEach>

            <dt class="shipping"><spring:theme code="checkout.orderTotals.returnTitle"/></dt>
            <dd class="shipping">
                <c:choose>
                    <c:when test="${isZeroDeliveryCosts}">
                        <spring:theme code="checkout.orderTotals.keine"/>
                    </c:when>
                    <c:otherwise>
                        ${cartContentsFragmentDTO.totals.deliveryCostForShowing}
                    </c:otherwise>
                </c:choose>
            </dd>

            <c:if test="${!isZeroPaymentCosts}">
                <dt class="payment"><spring:theme code="checkout.orderTotals.fees"/></dt>
                <dd class="payment">${cartContentsFragmentDTO.totals.paymentCostForShowing}</dd>
            </c:if>

            <dt class="total"><spring:theme code="checkout.orderTotals.totalTitle"/>
                <checkout:includeTaxString paymentCost="${cartContentsFragmentDTO.totals.paymentCost}" shippingCost="${cartContentsFragmentDTO.totals.deliveryCost}"/>
            </dt>
            <dd class="total">${cartContentsFragmentDTO.totals.totalPriceForShowing}</dd>

        </dl>
    </div>

    <c:choose>
        <c:when test="${isFreeOfCharge}">
            <formblock:buyButton labelKey="form.buyButton.btnBuy.free"/>
        </c:when>
        <c:otherwise>
            <formblock:buyButton labelKey="form.buyButton.btnBuy"/>
        </c:otherwise>
    </c:choose>

</div>
