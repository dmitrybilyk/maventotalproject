<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryAddress" required="true" type="de.hybris.platform.commercefacades.user.data.AddressData" %>
<%@ attribute name="deliveryMode" required="true" type="de.hybris.platform.commercefacades.order.data.DeliveryModeData" %>
<%@ attribute name="paymentInfo" required="true" type="de.hybris.platform.commercefacades.order.data.CCPaymentInfoData" %>
<%@ attribute name="requestSecurityCode" required="true" type="java.lang.Boolean" %>
<%@ attribute name="cartData" required="false" type="de.hybris.platform.commercefacades.order.data.CartData" %>

<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>

<div class="checkout_summary_flow">
	<multi-checkout:summaryFlowDeliveryAddress deliveryAddress="${deliveryAddress}" />
	<multi-checkout:summaryFlowDeliveryMode deliveryMode="${deliveryMode}" cartData="${cartData}" />
    <multi-checkout:summaryFlowPaymentPaymentExt paymentInfo="${paymentInfo}" requestSecurityCode="${requestSecurityCode}"/>
</div>
