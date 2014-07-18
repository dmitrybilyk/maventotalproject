<%--

    PAYMENTEXTENSION

    CGI Payment Extension
    
    - displays paymentInfo (payment data + billing address) on review page
    
    - replacement for original: /XXXstorefront/web/webroot/WEB-INF/tags/desktop/checkout/multi/summaryFlowPayment.tag
    - to be referenced in:      /XXXstorefront/web/webroot/WEB-INF/tags/desktop/checkout/multi/summaryFlow.tag

 --%>
 
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute   name="requestSecurityCode"  required="true" type="java.lang.Boolean" %>
<%@ attribute   name="paymentInfo"          required="true" type="de.hybris.platform.commercefacades.order.data.CCPaymentInfoData" %>

<%@ taglib      prefix="c"                  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib      prefix="spring"             uri="http://www.springframework.org/tags" %>
<%@ taglib      prefix="ycommerce"          uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib      prefix="payment"            tagdir="/WEB-INF/tags/desktop/payment" %>

<div class="checkout_summary_flow_c complete" id="checkout_summary_payment_div">

    <div class="item_container_holder">
    
        <ycommerce:testId code="checkout_paymentDetails_text">
        
            <div class="title_holder">
                <h2><spring:theme code="checkout.summary.paymentMethod.header" htmlEscape="false"/><span></span></h2>
            </div>

            <div class="item_container">            
                <div class="left">
                    <ul>
                        <payment:paymentData paymentInfo="${paymentInfo}" delimiter="li" />
                    </ul>
                </div>                
                <div class="left">
                    <ul>
                        <li><spring:theme code="checkout.summary.paymentMethod.billingAddress.header"/></li>
                        <payment:billingAddress paymentInfo="${paymentInfo}" delimiter="li" />
                    </ul>
                </div>                
            </div>
            
        </ycommerce:testId>
        
    </div>

    <ycommerce:testId code="checkout_changePayment_element">
        <c:url value="/checkout/multi/add-payment-method" var="addPaymentMethodUrl"/>
        <a href="${addPaymentMethodUrl}" class="edit_complete change_payment_method_button">
            <spring:theme code="checkout.summary.paymentMethod.editPaymentMethod"/>
        </a>
    </ycommerce:testId>
    
</div>
