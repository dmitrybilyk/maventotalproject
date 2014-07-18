<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="sidebar" tagdir="/WEB-INF/tags/desktop/sidebar" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="steps" tagdir="/WEB-INF/tags/desktop/steps" %>

<template:pageRegular pageTitle="${pageTitle}" pageIdentifier="checkout-userdata">
    <div class="content-main">
        <div class="content-progress-bar">
            <steps:steps/>
        </div>

        <div class="content-heading">
            <spring:theme code="checkout.multi.userdataPage.heading" var="textPageTitle"/>
            <common:heading
                    heading="${textPageTitle}"/>
        </div>



        <div class="content-form">

            <form:form method="post" commandName="form" action="${pageContext.request.contextPath}/formsubmit">
                <c:if test="${displayErrors}">
                    <common:errorBlock />
                </c:if>

                <input hidden="true" value="${stepName}" name="stepName" id="stepName"/>
                <ul class="input-list">

                    <spring:theme code="checkout.multi.userdataPage.intro" var="textPageIntro"/>
                    <formElement:infotext
                            text="${textPageIntro}"
                            cssClass=""/>

                </ul>


                <c:if test="${displaymappings['NewsletterProductTypeFormElementGroup']}">
                    <formblock:forNewsletterProduct />
                </c:if>

                <c:if test="${displaymappings['DownloadProductTypeFormElementGroup']}">
                    <ul class="input-list">
                        <formElement:checkbox
                                path="invoiceWanted"
                                id="dummyField8a"
                                label=""
                                labelKey="form.userdata.separateInvoice.label"
                                value="true"
                                tooltip=""
                                tooltipKey="form.userdata.separateInvoice.tooltip"
                                cssClass=" has-field-sendInvoice"
                                msg=""
                                msgType=""
                                required="false" />

                    </ul>
                </c:if>

                <c:if test="${displaymappings['StudentOfferFormElementGroup']}">
                    <formblock:student/>
                </c:if>

                <c:if test="${displaymappings['AboOfferFormElementGroup']}">
                    <formblock:abonumber/>
                </c:if>

                <c:if test="${displaymappings['MilesAndMoreBonusFormElementGroup']}">
                    <formblock:milesandmore/>
                </c:if>

                <c:if test="${displaymappings['GoodPrintDigitalProductTypeFormElementGroup'] || displaymappings['DownloadProductTypeFormElementGroup']}">
                    <formblock:userdata />
                    <c:if test="${!displaymappings['DownloadProductTypeFormElementGroup']}">
                        <formblock:usercompany />
                    </c:if>
                    <formblock:useraddress />
                </c:if>
                <formblock:referal/>

                <ul class="input-list main-form-actions">

                    <spring:theme code="checkout.multi.userdataPage.mandatory" var="textMandatory"/>
                    <formElement:legaltext
                            text=""
                            textKey="${textMandatory}"
                            cssClass=""/>

                    <formElement:button
                            name="dummyField"
                            id="dummyField"
                            type="submit"
                            text=""
                            textKey="checkout.multi.userdataPage.btnNext.text"
                            cssClass=""
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="checkout.multi.userdataPage.btnNext.tooltip"
                            label=""
                            labelKey="checkout.multi.userdataPage.btnNext.label"/>

                </ul>


            </form:form>
        </div>
        <div class="content-sidebar">
            <sidebar:serviceSidebar/>
        </div>
    </div>
</template:pageRegular>
