<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryAddress" required="true" type="de.hybris.platform.commercefacades.user.data.AddressData" %>
<%@ attribute name="cartData" required="false" type="de.hybris.platform.commercefacades.order.data.CartData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="hasShippedItems" value="${cartData.deliveryItemsQuantity > 0}" />

<div class="checkout_summary_flow_a complete" id="checkout_summary_deliveryaddress_div">
	<div class="item_container_holder">
		<ycommerce:testId code="checkout_deliveryAddressData_text">
			<div class="title_holder">
				<h2><spring:theme code="checkout.summary.deliveryAddress.header" htmlEscape="false"/><span></span></h2>
			</div>

		<div class="item_container">
			<c:if test="${not hasShippedItems}">
				<spring:theme code="checkout.pickup.no.delivery.required"/>
			</c:if>
			<c:if test="${hasShippedItems}">
				<ul>
					<li>${fn:escapeXml(deliveryAddress.title)}&nbsp;${fn:escapeXml(deliveryAddress.firstName)}&nbsp;${fn:escapeXml(deliveryAddress.lastName)}</li>
					<li>${fn:escapeXml(deliveryAddress.line1)}</li>
					<li>${fn:escapeXml(deliveryAddress.line2)}</li>
					<li>${fn:escapeXml(deliveryAddress.town)}</li>
					<li>${fn:escapeXml(deliveryAddress.region.name)}</li>
					<li>${fn:escapeXml(deliveryAddress.postalCode)}</li>
					<li>${fn:escapeXml(deliveryAddress.country.name)}</li>
				</ul>
			</c:if>
		</div>		
		</ycommerce:testId>
	</div>

	<c:if test="${hasShippedItems}">
		<ycommerce:testId code="checkout_changeAddress_element">
		    <c:url value="/checkout/multi/edit-delivery-address" var="editAddressUrl" />
			<a href="${editAddressUrl}/?editAddressCode=${deliveryAddress.id}" class="edit_complete change_address_button">
				<spring:theme code="checkout.summary.deliveryAddress.editDeliveryAddressButton" />
			</a>
		</ycommerce:testId>
	</c:if>
</div>