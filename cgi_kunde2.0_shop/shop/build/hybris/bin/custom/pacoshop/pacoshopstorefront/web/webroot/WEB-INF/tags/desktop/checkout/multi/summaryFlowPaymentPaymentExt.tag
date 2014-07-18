<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="paymentInfo" required="true" type="de.hybris.platform.commercefacades.order.data.CCPaymentInfoData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mod-checkout-summary-payment accordion-child">

    <h3><spring:theme code="form.summaryPayment.title"/> <span class="responsive-switcher"></span></h3>

    <div class="inner slide-block">

        <div class="holder">
            <div class="inner">

                <c:if test="${not empty paymentInfo}">

                        <%-- Mode specific data --%>
                        <c:choose>

                            <%-- Credit card --%>
                            <c:when test="${paymentInfo.paymentModeType eq 'creditcard'}">
                                <p>
                                    <spring:theme code="form.summaryPayment.paymentMethod.title"/>&nbsp;
                                    ${paymentInfo.paymentModeName}&nbsp;
                                    <c:if test="${not empty paymentInfo.paymentMethodName and paymentInfo.paymentMethodName ne paymentInfo.paymentModeName}">
                                        ${fn:escapeXml(paymentInfo.paymentMethodName)}
                                    </c:if>
                                </p>
                                <p>
                                    <spring:theme code="form.summaryPayment.paymentMethod.name"/>&nbsp;
                                    ${fn:escapeXml(paymentInfo.creditcard.name)}
                                </p>
                                <p>
                                    <spring:theme code="form.summaryPayment.paymentMethod.number"/>&nbsp;
                                    ${fn:escapeXml(paymentInfo.creditcard.no)}
                                </p>
                                <p>
                                    <spring:theme code="form.summaryPayment.paymentMethod.date"/>&nbsp;
                                    ${paymentInfo.creditcard.expMonth}/${paymentInfo.creditcard.expYear}
                                </p>
                            </c:when>

                            <%-- Direct debit --%>
                            <c:when test="${paymentInfo.paymentModeType eq 'directdebit'}">
                                <p>
                                    <spring:theme code="form.summaryPayment.paymentMethod.title"/>&nbsp;
                                    ${paymentInfo.paymentModeName}&nbsp;
                                    <c:if test="${not empty paymentInfo.paymentMethodName and paymentInfo.paymentMethodName ne paymentInfo.paymentModeName}">
                                        ${fn:escapeXml(paymentInfo.paymentMethodName)}
                                    </c:if>
                                </p>
                                <p>
                                    <spring:theme code="form.summaryPayment.debitcard.owner"/>&nbsp;
                                        ${fn:escapeXml(paymentInfo.directdebit.accountOwner)}
                                </p>
                                <c:choose>
                                    <c:when test="${'WC_SEPA-DD' eq paymentInfo.paymentMethodCode}">
                                        <p>
                                            <spring:theme code="form.summaryPayment.debitcard.iban"/>&nbsp;
                                            ${fn:escapeXml(paymentInfo.directdebit.iban)}
                                        </p>
                                        <p>
                                            <spring:theme code="form.summaryPayment.debitcard.bic"/>&nbsp;
                                            ${fn:escapeXml(paymentInfo.directdebit.bic)}
                                        </p>
                                    </c:when>

                                    <c:when test="${'WC_ELV' eq paymentInfo.paymentMethodCode}">
                                        <p>
                                            <spring:theme code="form.summaryPayment.debitcard.landBank"/>&nbsp;
                                                ${fn:escapeXml(fn:toUpperCase(paymentInfo.directdebit.bankCountry))}
                                        </p>
                                        <p>
                                            <spring:theme code="form.summaryPayment.debitcard.bankCode"/>&nbsp;
                                                ${fn:escapeXml(paymentInfo.directdebit.bankCode)}
                                        </p>
                                        <p>
                                            <spring:theme code="form.summaryPayment.debitcard.accountNumber"/>&nbsp;
                                                ${fn:escapeXml(paymentInfo.directdebit.accountCode)}
                                        </p>
                                    </c:when>

                                    <c:when test="${'internal_directdebit' eq paymentInfo.paymentMethodCode}">
                                        <c:choose>
                                            <c:when test="${not empty paymentInfo.directdebit.iban}">
                                                <p>
                                                    <spring:theme code="form.summaryPayment.debitcard.bank"/>&nbsp;
                                                        ${fn:escapeXml(paymentInfo.directdebit.bankName)}
                                                </p>
                                                <p>
                                                    <spring:theme code="form.summaryPayment.debitcard.iban"/>&nbsp;
                                                        ${fn:escapeXml(paymentInfo.directdebit.iban)}
                                                </p>
                                                <p>
                                                    <spring:theme code="form.summaryPayment.debitcard.bic"/>&nbsp;
                                                        ${fn:escapeXml(paymentInfo.directdebit.bic)}
                                                </p>
                                            </c:when>
                                            <c:when test="${not empty paymentInfo.directdebit.bankCode}">
                                                <p>
                                                    <spring:theme code="form.summaryPayment.debitcard.bank"/>&nbsp;
                                                        ${fn:escapeXml(paymentInfo.directdebit.bankName)}
                                                </p>
                                                <p>
                                                    <spring:theme code="form.summaryPayment.debitcard.bankCode"/>&nbsp;
                                                        ${fn:escapeXml(paymentInfo.directdebit.bankCode)}
                                                </p>
                                                <p>
                                                    <spring:theme code="form.summaryPayment.debitcard.accountNumber"/>&nbsp;
                                                        ${fn:escapeXml(paymentInfo.directdebit.accountCode)}
                                                </p>
                                            </c:when>
                                        </c:choose>


                                    </c:when>
                                </c:choose>
                            </c:when>

                            <%-- Sofortuberweisung --%>
                            <c:when test="${paymentInfo.paymentModeType eq 'sofortueberweisung'}">
                                <p>
                                    ${paymentInfo.paymentModeName}
                                    <span class="tooltip"><spring:theme code="form.summaryPayment.paymentMethod.sofortueberweisung.tooltip"/></span>
                                </p>
                                <p>${fn:escapeXml(paymentInfo.sofortueberweisung.bankName)}</p>
                                <p>${fn:escapeXml(paymentInfo.sofortueberweisung.iban)}</p>
                                <p>${fn:escapeXml(paymentInfo.sofortueberweisung.bic)}</p>
                                <p>${fn:escapeXml(paymentInfo.sofortueberweisung.accountOwner)}</p>
                            </c:when>


                            <%-- Paypal --%>
                            <c:when test="${paymentInfo.paymentModeType eq 'paypal'}">
                                <p>
                                    ${paymentInfo.paymentModeName}
                                    <span class="tooltip"><spring:theme code="form.summaryPayment.paymentMethod.paypal.tooltip"/></span>
                                </p>
                                <p>${fn:escapeXml(paymentInfo.paypal.email)}</p>
                                <p>${fn:escapeXml(paymentInfo.paypal.firstName)} ${fn:escapeXml(paymentInfo.paypal.lastName)}</p>
                            </c:when>

                            <%-- payondelivery --%>
                            <c:when test="${paymentInfo.paymentModeType eq 'payondelivery'}">
                                <p>
                                        ${paymentInfo.paymentModeName}
                                    <span class="tooltip"><spring:theme code="form.summaryPayment.paymentMethod.payondelivery.tooltip"/></span>
                                </p>
                            </c:when>

                            <%-- invoice --%>
                            <c:when test="${paymentInfo.paymentModeType eq 'invoice'}">
                                <p>
                                        ${paymentInfo.paymentModeName}
                                    <span class="tooltip"><spring:theme code="form.summaryPayment.paymentMethod.invoice.tooltip"/></span>
                                </p>
                            </c:when>

                        </c:choose>

                </c:if>

            </div>
        </div>

        <c:if test="${currentstep.stepName == 'summary'}">
            <button type="button" onclick='location.href="${pageContext.request.contextPath}${pathToPaymentPage}"'><spring:theme code="form.summaryPayment.btnChange" /></button>
        </c:if>
    </div>
</div>