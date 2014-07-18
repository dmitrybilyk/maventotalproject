<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="steps" tagdir="/WEB-INF/tags/desktop/steps" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/desktop/payment" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="sidebar" tagdir="/WEB-INF/tags/desktop/sidebar" %>

<c:set value="${pageContext.request.contextPath}/checkout/payment/add-billing-address" var="summaryPageUrl"/>

<template:pageRegular pageTitle="${pageTitle}" pageIdentifier="checkout-payment">
    <div class="content-main">
        <div class="content-progress-bar">
            <steps:steps/>
        </div>

        <div class="content-heading">
            <spring:theme code="checkout.multi.paymentPage.heading" var="textPageTitle" />
            <common:heading
                    heading="${textPageTitle}" />
        </div>

        <div class="content-messages">
                <%-- TODO: Loop over messages from backend and display them --%>
                <%--<common:message--%>
                <%--msg="Short info message."--%>
                <%--msgType= "info" />--%>
        </div>

        <div class="content-form">

            <%--
                Loading PaymentExtension JavaScript API
            --%>
        <script src="${paymentContainer.jsApiUrl}"></script>

        <div id="globalMessages">
            <common:globalMessages/>
        </div>

        <div id="globalMessages">
            <%-- Additional messages for the user, e.g. that the product is prepaid only --%>
            <common:paymentText />
        </div>

        <form:form id="paymentDetailsForm" method="post" commandName="form" action="formsubmit">

            <p class="intro"><spring:theme code="form.paymentMethods.heading" /></p>

            <formblock:paymentMethods />

        </form:form>

            <%--

                NextPage form - submit button

             --%>

        <form id="nextPageForm" action="${summaryPageUrl}">

            <ul class="input-list main-form-actions">

                <spring:theme code="checkout.multi.paymentPage.mandatory" var="textMandatory"/>
                <formElement:legaltext
                        text=""
                        textKey="${textMandatory}"
                        cssClass=""/>

                <formElement:button
                        name="dummyField"
                        id="payment-api-submit-data"
                        type="submit"
                        text=""
                        textKey="checkout.multi.paymentPage.btnNext.text"
                        cssClass=""
                        msg=""
                        msgType=""
                        tooltip=""
                        tooltipKey="checkout.multi.paymentPage.btnNext.tooltip"
                        label=""
                        labelKey="checkout.multi.paymentPage.btnNext.label" />

            </ul>

        </form>



        </div>

        <div class="content-sidebar">
           <sidebar:serviceSidebar/>
        </div>

    </div>
</template:pageRegular>