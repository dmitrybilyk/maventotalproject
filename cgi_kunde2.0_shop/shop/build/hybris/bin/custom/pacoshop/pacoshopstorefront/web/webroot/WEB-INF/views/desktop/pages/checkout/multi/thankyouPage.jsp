<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="checkoutMulti" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<template:pageRegular pageTitle="${pageTitle}" pageIdentifier="checkout-confirmation">
    <div class="content-main">

        <div class="content-heading">
            <spring:theme code="checkout.multi.thankyouPage.heading" var="textPageTitle"/>
            <common:heading
                    heading="${textPageTitle}"/>
        </div>

        <div class="content-messages">
            <%--<common:message--%>
                    <%--msg="Short info message."--%>
                    <%--msgType="info"/>--%>
        </div>

        <div class="content-form">
            <c:set var="form" value="${cartContentsFragmentDTO.form}" scope="request" />

            <div id="summary-address" class="content-summary">

                <c:if test="${not empty form.firstName && cartContentsFragmentDTO.customerInfoDisplayed}">
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
                    <checkoutMulti:summaryFlowPaymentPaymentExt paymentInfo="${cartContentsFragmentDTO.paymentInfo}"/>
                </c:if>
            </div>

            <checkout:orderConfirmation/>

            <c:if test="${cartContentsFragmentDTO.downloadButtonDisplayed}" >
                <%--
                https://jira.symmetrics.de/browse/KS-2547:
                If it has not been specified in those cases where it's optional, do not display the "Zum Download" button at all.
                --%>

                <form action='${cartContentsFragmentDTO.redirectUrl}'>
                    <ul class="input-list main-form-actions">
                        <spring:theme code="checkout.multi.thankyouPage.downloadText" var="downloadText"/>
                        <formElement:legaltext
                                text=""
                                textKey="${downloadText}"
                                cssClass=""/>

                        <formElement:button
                                name=""
                                id="dummyField"
                                type="submit"
                                text=""
                                textKey="checkout.multi.thankyouPage.btnDownload.text"
                                cssClass=""
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="checkout.multi.thankyouPage.btnDownload.tooltip"
                                label=""
                                labelKey="checkout.multi.thankyouPage.btnDownload.label"/>

                    </ul>
                </form>
                </c:if>


            <checkout:orderBanners/>

            <c:if test="${not cartContentsFragmentDTO.downloadButtonDisplayed}" >
                <div class="mod-button-back-home">
                    <div class="inner">
                        <ul class="input-list">
                            <spring:theme code="checkout.multi.thankyouPage.discoverMore" var="redirectrlDescConst"/>
                            <formElement:link
                                    text="${cartContentsFragmentDTO.redirectUrlDescription != ''? cartContentsFragmentDTO.redirectUrlDescription : redirectrlDescConst}"
                                    textKey=""
                                    url="${cartContentsFragmentDTO.redirectUrl}"
                                    title=""
                                    titleKey="checkout.recommendedItems.linkHome.title"
                                    cssClass="button-back-home" />

                        </ul>
                    </div>
                </div>
            </c:if>

        </div>

        <div class="content-sidebar">
            <input hidden="true" value="${stepName}" name="stepName" id="stepName"/>
        </div>


    <%--    <%@include file="productInOrderFragment.jsp" %>
--%>

    </div>

    <cms:globalSlot uid="SSORegisterLightBoxSlot" var="feature" limit="1">
        <cms:component component="${feature}" />
    </cms:globalSlot>

</template:pageRegular>
