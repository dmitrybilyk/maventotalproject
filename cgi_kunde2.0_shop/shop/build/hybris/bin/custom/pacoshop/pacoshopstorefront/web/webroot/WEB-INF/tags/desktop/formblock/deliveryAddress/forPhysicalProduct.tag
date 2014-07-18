<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formblockDeliveryAddress" tagdir="/WEB-INF/tags/desktop/formblock/deliveryAddress" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mod-delivery-address-gift-physical" id="new-address-form">
    <div class="inner">
        <p>
            <spring:theme code="form.deliveryAddress.newAddress.title" /></p>

        <ul class="input-list">
            <spring:theme code="form.deliveryNewAddressPhysical.intro" var="sectionTitle" />
            <formElement:infotext
                    text=""
                    textKey="${sectionTitle}"
                    cssClass="" />

            <formElement:select
                    path="newShipmentSalutation"
                    id="gift-salutation"
                    label=""
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

            <formElement:select
                    path="newShipmentTitle"
                    id="title"
                    label=""
                    labelKey="form.userdata.title.label"
                    selectMsg=""
                    selectMsgKey="form.userdata.title.selectMsg"
                    options="${titles2}"
                    optionValueAttr=""
                    optionLabelAttr=""
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.userdata.title.tooltip"
                    required="false" />

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


            <formElement:text
                    path="newShipmentCompany"
                    id="gift-company"
                    label=""
                    labelKey="form.deliveryAddressGiftPhysical.company.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftPhysical.company.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftPhysical.company.tooltip"
                    required="false" />


            <formElement:text
                    path="newShipmentAdditionalStreet"
                    id="address"
                    label=""
                    labelKey="form.useraddress.address.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.useraddress.address.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.useraddress.address.tooltip"
                    required="false" />

            <formElement:text
                    path="newShipmentStreet"
                    id="street"
                    label=""
                    labelKey="form.useraddress.street.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.useraddress.street.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.useraddress.street.tooltip"
                    required="true" />

            <formElement:text
                    path="newShipmentHouseNumber"
                    id="houseNumber"
                    label=""
                    labelKey="form.useraddress.houseNumber.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.useraddress.houseNumber.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.useraddress.houseNumber.tooltip"
                    required="true" />

            <formElement:text
                    path="newShipmentZip"
                    id="gift-zip"
                    label=""
                    labelKey="form.deliveryAddressGiftPhysical.zip.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftPhysical.zip.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftPhysical.zip.tooltip"
                    required="true" />

            <formElement:text
                    path="newShipmentCity"
                    id="gift-city"
                    label=""
                    labelKey="form.deliveryAddressGiftPhysical.city.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftPhysical.city.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftPhysical.city.tooltip"
                    required="true" />


            <formElement:select
                    path="newShipmentCountry"
                    id="country"
                    label=""
                    labelKey="form.useraddress.country.label"
                    selectMsg=""
                    selectMsgKey="form.useraddress.country.selectMsg"
                    options="${deliveryCountries}"
                    optionValueAttr="isocode"
                    optionLabelAttr="name"
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.useraddress.country.tooltip"
                    required="${not empty mandatoryFields['newShipmentCountry']}"
                    disabled="${fn:length(deliveryCountries) == 1}" />



        </ul>

    </div>
</div>
