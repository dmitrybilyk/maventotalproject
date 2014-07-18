<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="formblockDeliveryAddress" tagdir="/WEB-INF/tags/desktop/formblock/deliveryAddress" %>

<div class="mod-delivery-address-gift-physical">
    <div class="inner">


        <p><spring:theme code="form.deliveryAddress.newAddress.title" /></p>

        <ul class="input-list">

            <formElement:select
                    path="newShipmentSalutation"
                    id="gift-salutation"
                    label="Anrede"
                    labelKey="form.deliveryAddressGiftPhysical.salutation.label"
                    selectMsg=""
                    selectMsgKey="form.deliveryAddressGiftPhysical.salutation.selectMsg"
                    options="${titles}"
                    optionValueAttr=""
                    optionLabelAttr=""
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftPhysical.salutation.tooltip"
                    required="true" />

            <formElement:text
                    path="newShipmentFirstName"
                    id="gift-prename"
                    label=""
                    labelKey="form.deliveryAddressGiftPhysical.prename.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftPhysical.prename.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftPhysical.prename.tooltip"
                    required="true" />

            <formElement:text
                    path="newShipmentLastName"
                    id="gift-surname"
                    label=""
                    labelKey="form.deliveryAddressGiftPhysical.surname.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftPhysical.surname.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftPhysical.surname.tooltip"
                    required="true" />

            <formElement:email
                    path="newShipmentEmail"
                    id="gift-email"
                    label=""
                    labelKey="form.differentDeliveryAddressDigital.email.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.differentDeliveryAddressDigital.email.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.differentDeliveryAddressDigital.email.tooltip"
                    required="true" />

        </ul>

    </div>
</div>
