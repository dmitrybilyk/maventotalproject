<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<jsp:useBean id="paymentInfoStep" class="com.cgi.pacoshop.core.checkout.dynamic.impl.PaymentInfoFormDTO"/>

<div class="mod-checkout-summary-payment accordion-child">

    <h3><spring:theme code="form.summaryPayment.title"/> <span class="responsive-switcher"></span></h3>

    <div class="inner slide-block">

        <div class="holder">
            <div class="inner">
                <dl>

                    <dt class="method"><spring:theme code="form.paymentMethods.title"/></dt>
                    <dd class="method"><spring:theme code="form.paymentMethods.creditcard.label"/></dd>

                    <dt class="kto"><spring:theme code="form.summaryPayment.debitcard.ownerName" /></dt>
                    <dd>${card.owner}</dd>
                    <dt class="kto"><spring:theme code="form.paymentMethodCreditCard.cartNo" /></dt>
                    <dd>${card.number}</dd>
                    <dt class="kto"><spring:theme code="form.paymentMethodCreditCard.cartDate" /></dt>
                    <dd>${card.validMonth}/${card.validYear}</dd>

                </dl>
            </div>
        </div>

        <button type="button" onclick='location.href="${pageContext.request.contextPath}${pathToPaymentPage}"'><spring:theme code="form.summaryPayment.btnChange" /></button>

    </div>
</div>