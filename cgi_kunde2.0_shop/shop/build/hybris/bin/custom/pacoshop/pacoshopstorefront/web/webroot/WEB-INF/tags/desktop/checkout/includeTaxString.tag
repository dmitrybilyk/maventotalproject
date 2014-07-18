<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="shippingCost" required="false" rtexprvalue="true" %>
<%@ attribute name="paymentCost" required="false" rtexprvalue="true" %>

<span class="includes-tax">
    <c:set var="paymentText">
        <c:if test="${paymentCost gt 0.0}"><spring:theme code="checkout.orderTotals.tax.withPayment"/></c:if>
    </c:set>

    <c:set var="shippingText">
        <c:if test="${shippingCost gt 0.0}"><spring:theme code="checkout.orderTotals.tax.withShipping"/></c:if>
    </c:set>
    <spring:message code="checkout.orderTotals.tax.fulltext" arguments="${paymentText},${shippingText}" argumentSeparator=","/>
</span>
