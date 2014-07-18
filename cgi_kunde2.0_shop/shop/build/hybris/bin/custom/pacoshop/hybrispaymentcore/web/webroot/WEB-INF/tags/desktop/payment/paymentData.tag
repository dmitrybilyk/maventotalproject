<%--

    PAYMENTEXTENSION

    CGI Payment Extension
    
    - displays payment data of given paymentInfo

 --%>
 
 <%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute   name="paymentInfo"    required="true"     type="com.cgi.hybris.payment.core.data.PaymentInfoData" %>
<%@ attribute   name="delimiter"      required="false"    type="java.lang.String" %>

<%@ taglib      prefix="c"            uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib      prefix="spring"       uri="http://www.springframework.org/tags" %>
<%@ taglib      prefix="fn"           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib      prefix="fmt"          uri="http://java.sun.com/jsp/jstl/fmt" %>



<%--
    Choose a delimiter
 --%>
 
<c:choose>
    <c:when test="${delimiter eq 'br' or delimiter eq '' or empty delimiter}">
        <c:set var="tagStart"   value="" />
        <c:set var="tagEnd"     value="<br />" />
    </c:when>
    <c:otherwise>
        <c:set var="tagStart"   value="<${delimiter}>" />
        <c:set var="tagEnd"     value="</${delimiter}>" />
    </c:otherwise>
</c:choose>



<%--
    Display payment data
 --%>
<c:if test="${not empty paymentInfo}">  

    <%-- Payment mode name --%>
    ${tagStart}${paymentInfo.paymentModeName}${tagEnd}
    
    <%-- When available: Payment method name --%>
    <c:if test="${not empty paymentInfo.paymentMethodName and paymentInfo.paymentMethodName ne paymentInfo.paymentModeName}">
        ${tagStart}${fn:escapeXml(paymentInfo.paymentMethodName)}${tagEnd}                                    
    </c:if>
    
    <%-- Mode specific data --%>
    <c:choose>

        <%-- Credit card --%>
        <c:when test="${paymentInfo.paymentModeType eq 'creditcard'}">
            ${tagStart}${fn:escapeXml(paymentInfo.creditcard.name)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.creditcard.no)}${tagEnd}
            ${tagStart}<spring:theme code="checkout.summary.paymentMethod.paymentDetails.expires" arguments="${paymentInfo.creditcard.expMonth},${paymentInfo.creditcard.expYear}"/>${tagEnd}
        </c:when>

        <%-- Direct debit --%>
        <c:when test="${paymentInfo.paymentModeType eq 'directdebit'}">
            ${tagStart}${fn:escapeXml(paymentInfo.directdebit.accountOwner)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.directdebit.bankName)} <%-- (${fn:escapeXml(paymentInfo.directdebit.bankCountry)})--%>${tagEnd}
            <%-- 
            ${tagStart}${fn:escapeXml(paymentInfo.directdebit.bankCode)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.directdebit.accountCode)}${tagEnd}
            --%>
            ${tagStart}${fn:escapeXml(paymentInfo.directdebit.iban)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.directdebit.bic)}${tagEnd}
        </c:when>

        <%-- Sofortueberweisung --%>
        <c:when test="${paymentInfo.paymentModeType eq 'sofortueberweisung'}">
            ${tagStart}${fn:escapeXml(paymentInfo.sofortueberweisung.bankName)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.sofortueberweisung.iban)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.sofortueberweisung.bic)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.sofortueberweisung.accountOwner)}${tagEnd}
        </c:when>

        <%-- Giropay --%>
        <c:when test="${paymentInfo.paymentModeType eq 'giropay'}">
            ${tagStart}${fn:escapeXml(paymentInfo.giropay.bankName)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.giropay.iban)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.giropay.bic)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.giropay.accountOwner)}${tagEnd}
        </c:when>

        <%-- iDeal --%>
        <c:when test="${paymentInfo.paymentModeType eq 'ideal'}">
            ${tagStart}${fn:escapeXml(paymentInfo.ideal.bankName)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.ideal.iban)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.ideal.bic)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.ideal.accountOwner)}${tagEnd}
        </c:when>

        <%-- Paypal --%>
        <c:when test="${paymentInfo.paymentModeType eq 'paypal'}">
            ${tagStart}${fn:escapeXml(paymentInfo.paypal.email)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.paypal.firstName)} ${fn:escapeXml(paymentInfo.paypal.lastName)}${tagEnd}
        </c:when>

        <%-- Paybox --%>
        <c:when test="${paymentInfo.paymentModeType eq 'paybox'}">
            ${tagStart}${fn:escapeXml(paymentInfo.paybox.accountCode)}${tagEnd}
        </c:when>

        <%-- Click2Pay --%>
        <c:when test="${paymentInfo.paymentModeType eq 'click2pay'}">
            ${tagStart}${fn:escapeXml(paymentInfo.click2pay.accountCode)}${tagEnd}
            ${tagStart}${fn:escapeXml(paymentInfo.click2pay.username)}${tagEnd}
        </c:when>

        <%-- Installment --%>
        <c:when test="${paymentInfo.paymentModeType eq 'installment'}">       
            <%--
            Mapping RatePay - HybrisPaymentExtension
            
            RatePay                 Description                                                                     HybrisPaymentExtention
            -------------------------------------------------------------------------------------------------------------------------------            
            amount                  Price that has to be financed                                                   amount
            service-charge          Additional costs of financing                                                   serviceFee
            
            annualpercentage-rate   Interest-rate based on service-charge and interest-rate                         annualInterestRate
            interest-rate           Interest-rate which will be used to calculate the annual percentage rate        interestRate
            
            interest-amount         Total of all monthly paid interests                                             interestAmount
            total-amount            Total of amount, interest-amount and servicecharge                              totalAmount
            
            number-of-rates         Number of months; duration of the installment plan                              numberOfInstallments
            monthly-debitinterest   Monthly interest rates based on annualpercentage-rate   
            rate                    Monthly rate to be paid by the customer                                         installmentAmount
            last-rate               Last monthly rate to be paid by the customer xs:decimal M                       lastInstallmentAmount
            
            payment-firstday        Calendar day of the first payment.
            
            --%>            
            
            ${tagStart}Warenwert: <fmt:formatNumber value="${paymentInfo.installment.amount}"/> &euro;${tagEnd}
            ${tagStart}Geb&uuml;hr: <fmt:formatNumber value="${paymentInfo.installment.serviceFee}"/> &euro;${tagEnd}
            ${tagStart}Sollzinssatz (p.a.): <fmt:formatNumber value="${paymentInfo.installment.interestRate}"/>%${tagEnd}
            ${tagStart}Eff. Jahreszins: <fmt:formatNumber value="${paymentInfo.installment.annualInterestRate}"/>%${tagEnd}
            ${tagStart}Zinsbetrag: <fmt:formatNumber value="${paymentInfo.installment.interestAmount}"/> &euro;${tagEnd}
            ${tagStart}Gesamtbetrag: <fmt:formatNumber value="${paymentInfo.installment.totalAmount}"/> &euro;${tagEnd}
            ${tagStart}${tagEnd} 
            ${tagStart}Laufzeit: ${fn:escapeXml(paymentInfo.installment.numberOfInstallments)} Monate${tagEnd}
            ${tagStart}Monatliche Rate: <fmt:formatNumber value="${paymentInfo.installment.installmentAmount}"/> &euro;${tagEnd}
            ${tagStart}Abschlussrate: <fmt:formatNumber value="${paymentInfo.installment.lastInstallmentAmount}"/> &euro;${tagEnd}
            ${tagStart}Zustimmung Kreditauskunft: ${fn:escapeXml(paymentInfo.installment.allowCreditInquiry)}${tagEnd}
            ${tagStart}Zahlung beginnt:
                <c:choose>
                    <c:when test="${paymentInfo.installment.paymentFirstDay eq 'BEGIN'}">
                        Anfang des Monats
                    </c:when>
                    <c:when test="${paymentInfo.installment.paymentFirstDay eq 'MID'}">
                        Mitte des Monats
                    </c:when>
                    <c:when test="${paymentInfo.installment.paymentFirstDay eq 'END'}">
                        Ende des Monats
                    </c:when>
                    <c:otherwise>
                        egal
                    </c:otherwise>
                </c:choose>
            ${tagEnd}
            <c:if test="${paymentInfo.installment.iban ne '' or paymentInfo.installment.accountCode ne ''}">
                ${tagStart}${tagEnd} 
                ${tagStart}${fn:escapeXml(paymentInfo.installment.accountOwner)}${tagEnd}
                ${tagStart}${fn:escapeXml(paymentInfo.installment.bankName)} (${fn:escapeXml(paymentInfo.installment.bankCountry)})${tagEnd}
                ${tagStart}${fn:escapeXml(paymentInfo.installment.bankCode)}${tagEnd}
                ${tagStart}${fn:escapeXml(paymentInfo.installment.accountCode)}${tagEnd}
                ${tagStart}${fn:escapeXml(paymentInfo.installment.iban)}${tagEnd}
                ${tagStart}${fn:escapeXml(paymentInfo.installment.bic)}${tagEnd}
            </c:if>
        </c:when>
 
    </c:choose> 
         
</c:if>