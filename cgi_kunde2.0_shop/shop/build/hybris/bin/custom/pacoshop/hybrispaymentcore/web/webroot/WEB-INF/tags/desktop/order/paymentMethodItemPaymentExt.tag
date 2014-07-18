<%--

    PAYMENTEXTENSION

    CGI Payment Extension
    
    - displays paymentInfo (payment data + billing address) on confirmation page
    
    - replacement for original: /XXXstorefront/web/webroot/WEB-INF/tags/desktop/order/paymentMethodItem.tag
    - to be referenced in:      /XXXstorefront/web/webroot/WEB-INF/views/desktop/pages/checkout/checkoutConfirmationPage.jsp

 --%>

<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute   name="order"        required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<%@ taglib      prefix="c"          uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib      prefix="spring"     uri="http://www.springframework.org/tags" %>
<%@ taglib      prefix="payment"    tagdir="/WEB-INF/tags/desktop/payment" %>

<div class="item_container_holder positive">
    <div class="title_holder">
        <h2><spring:theme code="text.paymentMethod" text="Payment Method"/></h2>
    </div>
    <div class="item_container">
        <div class="left delivery_stages-payment-method">
            <ul class="pad_none">
                <payment:paymentData paymentInfo="${order.paymentInfo}" delimiter="li" />
            </ul>
        </div>
        <div class="left delivery_stages-billingaddress">
            <ul>
                <li><spring:theme code="paymentMethod.billingAddress.header"/></li>
                <payment:billingAddress paymentInfo="${order.paymentInfo}" delimiter="li" />
            </ul>
        </div>
    </div>
</div>
