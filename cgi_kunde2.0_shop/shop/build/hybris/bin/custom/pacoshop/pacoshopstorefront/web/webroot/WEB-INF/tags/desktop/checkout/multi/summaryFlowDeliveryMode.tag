<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryMode" required="true" type="de.hybris.platform.commercefacades.order.data.DeliveryModeData" %>
<%@ attribute name="cartData" required="false" type="de.hybris.platform.commercefacades.order.data.CartData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="hasShippedItems" value="${cartData.deliveryItemsQuantity > 0}" />

<div class="checkout_summary_flow_b complete" id="checkout_summary_deliverymode_div">
	<div class="item_container_holder">
		<ycommerce:testId code="checkout_deliveryModeData_text">
			<div class="title_holder">
				<h2><spring:theme code="checkout.summary.deliveryMode.header" htmlEscape="false"/><span></span></h2>
			</div>

		<c:if test="${cartData.deliveryItemsQuantity > 0}">
			<div class="item_container">
				<ul>
					<li>${deliveryMode.name} (${deliveryMode.code})</li>
					<li class="deliverymode-description" title="${deliveryMode.description} - ${deliveryMode.deliveryCost.formattedValue}">${deliveryMode.description} - ${deliveryMode.deliveryCost.formattedValue}</li>
				</ul>
			</div>
		</c:if>
		</ycommerce:testId>
		
		<c:if test="${cartData.pickupItemsQuantity > 0}">
			<div class="item_container">
				<ul class="delivery_method-list delivery_method-list-pickup">
					<li><spring:theme code="checkout.pickup.items.to.pickup" arguments="${cartData.pickupItemsQuantity}"/></li>
					<li><spring:theme code="checkout.pickup.store.destinations" arguments="${fn:length(cartData.pickupOrderGroups)}"/></li>
				</ul>
			</div>
		</c:if>
	</div>
	
	<c:if test="${cartData.deliveryItemsQuantity > 0}">
		<ycommerce:testId code="checkout_changeDeliveryMode_element">
			<a href="<c:url value="/checkout/multi/choose-delivery-method"/>" class="edit_complete change_mode_button"><spring:theme code="checkout.summary.deliveryMode.editDeliveryMethod"/></a>
		</ycommerce:testId>
	</c:if>

</div>