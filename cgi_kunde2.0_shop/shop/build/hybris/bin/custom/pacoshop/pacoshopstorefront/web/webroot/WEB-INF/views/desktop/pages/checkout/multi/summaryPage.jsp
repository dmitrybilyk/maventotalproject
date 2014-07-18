<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="checkoutMulti" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="steps" tagdir="/WEB-INF/tags/desktop/steps" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sidebar" tagdir="/WEB-INF/tags/desktop/sidebar" %>

<template:pageRegular pageTitle="${pageTitle}" pageIdentifier="checkout-summary">
    <div class="content-main">
        <div class="content-progress-bar">
            <steps:steps/>
        </div>
        <div class="content-heading">
            <spring:theme code="checkout.multi.summaryPage.heading" var="textPageTitle"/>
            <common:heading
                    heading="${textPageTitle}"/>
        </div>


        <div id="globalMessages">
            <common:globalMessages/>
        </div>

        <div class="content-form">

            <form:form method="post" commandName="form" action="${pageContext.request.contextPath}/formsubmit">
                <c:if test="${displayErrors}">
                    <common:errorBlock/>
                </c:if>

                <div id="summary-address" class="content-summary">
                    <c:if test="${not empty form.firstName && displaymappings['CustomerInfoSummaryPageFormElementGroup']}">
                        <checkoutMulti:customerInfo />
                    </c:if>

                    <c:if test="${not empty form.newShipmentFirstName}">
                        <checkoutMulti:summaryDelivery />
                    </c:if>
                    <c:if test="${form.deliveryDateBlock}">
                        <checkoutMulti:summarySubscription />
                    </c:if>

                    <c:if test="${not empty form.billingFirstName}">
                        <checkoutMulti:summaryBillingAddress />
                    </c:if>

                    <c:if test="${cartContentsFragmentDTO.totals.totalPrice > 0}">
                        <checkoutMulti:summaryFlowPaymentPaymentExt paymentInfo="${cartData.paymentInfo}"/>
                    </c:if>

                </div>

                <formblock:termsOfUse/>

                <%@include file="productInOrderFragment.jspf" %>

                <input hidden="true" value="${stepName}" name="stepName" id="stepName"/>
            </form:form>

        </div>

        <%-- NOT ACTIVE--%>
        <%--
        <div class="content-sidebar">
            <sidebar:serviceSidebar/>
        </div>
        --%>

    </div>
</template:pageRegular>


