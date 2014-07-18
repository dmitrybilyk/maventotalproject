<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-delivery-address-gift-physical">
    <div class="inner">
        <p><spring:theme code="form.subscriptionStart.title"   /></p>
        <ul class="input-list">

            <spring:theme code="form.subscriptionStart.intro" var="sectionTitle" />
            <formElement:infotext
                    text=""
                    textKey="${sectionTitle}"
                    cssClass="" />

            <formElement:checkbox
                    path="deliveryStart"
                    id="deliveryStart"
                    label=""
                    labelKey="form.subscriptionStart.immediatelySubscription.label"
                    value="${form.deliveryStart}"
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.subscriptionStart.immediatelySubscription.tooltip"
                    required="false"
                    checked="${form.deliveryStart ? 'checked' : ''}"
                    />

            <formElement:date
                    path="deliveryStartDateStr"
                    id="deliveryStartDateStr"
                    label=""
                    labelKey="form.subscriptionStart.date.label"
                    cssClass="date"
                    placeholder=""
                    placeholderKey="form.subscriptionStart.date.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.subscriptionStart.date.tooltip"
                    required="false" />

        </ul>

    </div>
</div>
