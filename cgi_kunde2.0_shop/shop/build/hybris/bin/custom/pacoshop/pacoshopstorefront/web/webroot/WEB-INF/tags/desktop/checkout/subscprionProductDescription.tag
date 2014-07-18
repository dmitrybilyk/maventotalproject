<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="product" required="true" rtexprvalue="true" type="com.cgi.pacoshop.core.model.ProductDTO" %>

<%--TODO formatted price and currency should come from DefaultPriceDataFactory. Not possible to do now--%>

<c:if test="${product.termsOfServiceRenewal}">
    <spring:message code="checkout.subscriptionproducts.termofservice.renewal"/> &#32
</c:if>


<%--*******************************PRODUCT UNIT**********************************************************--%>
<c:if test="${fn:toLowerCase(product.unit) == 'month'}">
    <c:set var="productUnit">
        <spring:message code="checkout.subscriptionproducts.unit.month"/>
    </c:set>
</c:if>

<c:if test="${fn:toLowerCase(product.unit) == 'week'}">
    <c:set var="productUnit">
        <spring:message code="checkout.subscriptionproducts.unit.week"/>
    </c:set>
</c:if>

<c:if test="${fn:toLowerCase(product.unit) == 'edition'}">
    <c:set var="productUnit">
        <spring:message code="checkout.subscriptionproducts.unit.edition"/>
    </c:set>
</c:if>
<%--*****************************************************************************************--%>
<c:if test="${fn:toLowerCase(product.billingFrequencyUnit) == 'month'}">
    <c:set var="billingFrequencyUnit">
        <spring:message code="checkout.subscriptionproducts.unit.month"/>
    </c:set>
</c:if>

<c:if test="${fn:toLowerCase(product.billingFrequencyUnit) == 'week'}">
    <c:set var="billingFrequencyUnit">
        <spring:message code="checkout.subscriptionproducts.unit.week"/>
    </c:set>
</c:if>

<c:if test="${fn:toLowerCase(product.billingFrequencyUnit) == 'edition'}">
    <c:set var="billingFrequencyUnit">
        <spring:message code="checkout.subscriptionproducts.unit.edition"/>
    </c:set>
</c:if>
<%--*****************************************************************************************--%>


<c:if test="${fn:toLowerCase(product.actualBilledUnit) == 'month'}">
    <c:set var="productActualBilledUnit">
        <spring:message code="checkout.subscriptionproducts.unit.month"/>
    </c:set>
</c:if>

<c:if test="${fn:toLowerCase(product.actualBilledUnit) == 'week'}">
    <c:set var="productActualBilledUnit">
        <spring:message code="checkout.subscriptionproducts.unit.week"/>
    </c:set>
</c:if>

<c:if test="${fn:toLowerCase(product.actualBilledUnit) == 'edition'}">
    <c:set var="productActualBilledUnit">
        <spring:message code="checkout.subscriptionproducts.unit.edition"/>
    </c:set>
</c:if>
<%--*****************************************************************************************--%>
<%--&#32 - space symbol--%>
<c:set var="displayedDeliveryPeriod">
    ${product.deliveryPeriod}&#32${productUnit}
</c:set>
<c:set var="displayedPricePerUnit">
    ${product.pricePerUnitWithCurrency}
</c:set>
<c:set var="displayedUnit">
    ${product.billingFrequency}&#32${billingFrequencyUnit}
</c:set>
<c:set var="displayedActualBilledPrice">
   ${product.actualBilledPriceWithCurrency}
</c:set>
<c:set var="displayedActualBilledUnit">
    ${product.actualBilledBillingFrequency}&#32${productActualBilledUnit}
</c:set>


<%--*****************************************************************************************--%>
<%--For thankYou page only--%>
<c:if test="${not empty requestScope['toShowOnlyDeliveryPeriodInDescription']}">
    ${displayedDeliveryPeriod}
</c:if>

<c:if test="${empty requestScope['toShowOnlyDeliveryPeriodInDescription']}">
    <spring:message code="checkout.subscriptionproducts.description"
                arguments=" ${displayedDeliveryPeriod};
                            ${displayedPricePerUnit};
                            ${displayedUnit}"
                htmlEscape="false"
                argumentSeparator=";"/>
    <c:if test="${not empty product.actualBilledUnit and not empty product.actualBilledPrice}">

        (${product.actualBilledPriceWithCurrency}
        <spring:message code="checkout.subscriptionproducts.description.actual"
                    arguments="${product.actualBilledBillingFrequency};
                               ${productActualBilledUnit}"
                    htmlEscape="false"
                    argumentSeparator=";"/>)

    </c:if>
</c:if>
