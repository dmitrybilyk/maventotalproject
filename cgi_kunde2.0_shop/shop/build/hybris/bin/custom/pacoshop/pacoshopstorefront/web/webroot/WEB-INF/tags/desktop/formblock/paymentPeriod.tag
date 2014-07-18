<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-payment-period">
    <div class="inner">

        <p><spring:theme code="form.paymentPeriod.title"/></p>

        <ul class="input-list">
            <spring:theme code="form.paymentPeriod.heading" var="paymentPeriodHeading" />
            <formElement:infotext
                    text=""
                    textKey="${paymentPeriodHeading}"
                    cssClass="" />

            <formElement:radiobutton
                    path="dummyField2"
                    id="dummyField2a"
                    label=""
                    labelKey="form.paymentPeriod.yearly.label"
                    value="yearly"
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.paymentPeriod.yearly.tooltip"
                    required="false" />

            <formElement:radiobutton
                    path="dummyField2"
                    id="dummyField2b"
                    label=""
                    labelKey="form.paymentPeriod.quarterly.label"
                    value="quarterly"
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.paymentPeriod.quarterly.tooltip"
                    required="false" />
            <formElement:radiobutton
                    path="dummyField2"
                    id="dummyField2c"
                    label=""
                    labelKey="form.paymentPeriod.monthly.label"
                    value="monthly"
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.paymentPeriod.quarterly.tooltip"
                    required="false" />

        </ul>

    </div>
</div>