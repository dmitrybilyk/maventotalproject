<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="formblockDeliveryAddress" tagdir="/WEB-INF/tags/desktop/formblock/deliveryAddress" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="steps" tagdir="/WEB-INF/tags/desktop/steps" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sidebar" tagdir="/WEB-INF/tags/desktop/sidebar" %>


<template:pageRegular pageTitle="${pageTitle}" pageIdentifier="checkout-delivery">
    <div class="content-main">
        <div class="content-progress-bar">
            <steps:steps/>
        </div>
        <div class="content-heading">
            <spring:theme code="checkout.multi.deliveryPage.heading" var="textPageTitle" />
            <common:heading
                    heading="${textPageTitle}" />
        </div>

        <div class="content-form">

            <form:form method="post" commandName="form" action="${pageContext.request.contextPath}/formsubmit">
                <c:if test="${displayErrors}">
                    <common:errorBlock />
                </c:if>
                <input hidden="true" value="${stepName}" name="stepName" id="stepName"/>

                <c:if test="${displaymappings['GoodPrintDigitalDifferentInvoceAddressFormElementGroup']}">
                    <formblockDeliveryAddress:forPhysicalGift />
                </c:if>


                <c:if test="${displaymappings['ShipmentPagePhysicalDifferentShipmentAddressFormElementGroup'] && !displaymappings['ShipmentPageDigitalDifferentShipmentAddressFormElementGroup']}">
                    <formblockDeliveryAddress:existingAddresses />
                    <formblockDeliveryAddress:forPhysicalProduct />
                </c:if>

                <c:if test="${displaymappings['ShipmentPageDigitalDifferentShipmentAddressFormElementGroup'] && !displaymappings['ShipmentPagePhysicalDifferentShipmentAddressFormElementGroup']}">
                    <formblockDeliveryAddress:forDigitalProduct />
                </c:if>

                <c:if test="${displaymappings['ShipmentPageDigitalDifferentShipmentAddressFormElementGroup'] && displaymappings['ShipmentPagePhysicalDifferentShipmentAddressFormElementGroup']}">
                    <formblockDeliveryAddress:existingAddresses />
                    <formblockDeliveryAddress:forPhysicalAndDigitalProducts />
                </c:if>
                <c:if test="${displaymappings['DeliveryStartFormElementGroup']}">
                    <formblock:subscriptionStart />
                </c:if>

                <ul class="input-list main-form-actions">

                    <spring:theme code="checkout.multi.deliveryPage.mandatory" var="textMandatory"/>
                    <formElement:legaltext
                            text=""
                            textKey="${textMandatory}"
                            cssClass=""/>

                    <formElement:button
                            name="dummyField"
                            id="dummyField"
                            type="submit"
                            text=""
                            textKey="checkout.multi.deliveryPage.btnNext.text"
                            cssClass=""
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="checkout.multi.deliveryPage.btnNext.tooltip"
                            label=""
                            labelKey="checkout.multi.deliveryPage.btnNext.label" />

                </ul>

            </form:form>

        </div>
        <div class="content-sidebar">
            <sidebar:serviceSidebar/>
        </div>
    </div>

</template:pageRegular>