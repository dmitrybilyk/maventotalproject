<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-delivery-address-gift-physical">
    <div class="inner">
<p><spring:theme code="form.deliveryAddressGiftPhysical.title"   /></p>
        <ul class="input-list">

            <spring:theme code="form.deliveryAddressGiftPhysical.intro" var="sectionTitle" />
            <formElement:infotext
                    text=""
                    textKey="${sectionTitle}"
                    cssClass="" />

            <formElement:select
                    path="billingSalutation"
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
                    path="billingTitle"
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
                    path="billingFirstName"
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
                    path="billingLastName"
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
                    path="billingCompany"
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
                    path="billingAdditionalStreet"
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
                    path="billingStreet"
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
                    path="billingHouseNumber"
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
                    path="billingZip"
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
                    path="billingCity"
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

            <formElement:email
                    path="billingEmail"
                    id="gift-email"
                    label=""
                    labelKey="form.deliveryAddressGiftPhysical.email.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.deliveryAddressGiftPhysical.email.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.deliveryAddressGiftPhysical.email.tooltip"
                    required="true" />

            <formElement:select
                    path="billingCountry"
                    id="country"
                    label=""
                    labelKey="form.useraddress.country.label"
                    selectMsg=""
                    selectMsgKey="form.useraddress.country.selectMsg"
                    options="${allCountries}"
                    optionValueAttr="isocode"
                    optionLabelAttr="name"
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.useraddress.country.tooltip"
                    required="${not empty mandatoryFields['country']}" />


        </ul>

    </div>
</div>
