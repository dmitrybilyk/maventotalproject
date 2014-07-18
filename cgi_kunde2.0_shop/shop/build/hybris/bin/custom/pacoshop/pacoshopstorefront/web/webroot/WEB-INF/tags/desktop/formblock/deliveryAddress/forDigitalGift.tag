<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-delivery-address-gift-physical">
    <div class="inner">

        <ul class="input-list">

            <spring:theme code="form.deliveryAddressGiftDigital.intro" var="sectionTitle" />
            <formElement:infotext
                    text=""
                    textKey="${sectionTitle}"
                    cssClass="" />

            <formElement:select
                    path="dummyField"
                    id="giftDigital-salutation"
                    label=""
                    labelKey="form.deliveryAddressGiftDigital.salutation.label"
                    selectMsg=""
                    selectMsgKey="form.deliveryAddressGiftDigital.salutation.selectMsg"
                    options="${dummyOptions}"
                    optionValueAttr=""
                    optionLabelAttr=""
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftDigital.salutation.tooltip"
                    required="true" />

            <formElement:text
                    path="dummyField"
                    id="giftDigital-prename"
                    label=""
                    labelKey="form.deliveryAddressGiftDigital.prename.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftDigital.prename.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftDigital.prename.tooltip"
                    required="true" />

            <formElement:text
                    path="dummyField"
                    id="giftDigital-surname"
                    label=""
                    labelKey="form.deliveryAddressGiftDigital.surname.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftDigital.surname.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftDigital.surname.tooltip"
                    required="true" />

            <formElement:email
                    path="dummyField"
                    id="giftDigital-email"
                    label=""
                    labelKey="form.deliveryAddressGiftDigital.email.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftDigital.email.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftDigital.email.tooltip"
                    required="true" />

        </ul>

    </div>
</div>
