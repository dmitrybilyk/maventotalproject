<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mod-useraddress">
    <div class="inner">

    <p><spring:theme code="form.useraddress.heading" /></p>

        <ul class="input-list">
            <formElement:email
                    path="email"
                    id="email"
                    label=""
                    labelKey="form.useraddress.email.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.useraddress.email.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.useraddress.email.tooltip"
                    required="${not empty mandatoryFields['email']}" />


            <c:if test="${!displaymappings['DownloadProductTypeFormElementGroup']}">
                <formElement:phoneFields
                        pathPrefix="phonePrefixHome"
                        pathExtension="phoneExtensionHome"
                        pathNumber="phoneNumberHome"
                        idPrefix="phonePrefixHome"
                        idExtension="phoneExtensionHome"
                        idNumber="phoneNumberHome"
                        labelKey="form.useraddress.homePhone.label"
                        required="${not empty mandatoryFields['phoneNumberHome']}" />

                <formElement:phoneFields
                        pathPrefix="phonePrefixBusiness"
                        pathExtension="phoneExtensionBusiness"
                        pathNumber="phoneNumberBusiness"
                        idPrefix="phonePrefixBusiness"
                        idExtension="phoneExtensionBusiness"
                        idNumber="phoneNumberBusiness"
                        labelKey="form.useraddress.businessPhone.label"
                        required="${not empty mandatoryFields['phoneNumberBusiness']}" />

                <formElement:mobilePhoneFields
                        pathPrefix="mobilePrefix"
                        pathNumber="mobileNumber"
                        idPrefix="mobilePrefix"
                        idNumber="mobileNumber"
                        labelKey="form.useraddress.mobilePhone.label"
                        required="${not empty mandatoryFields['mobileNumber']}" />

            </c:if>
            <formElement:text
                    path="additionalStreet"
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
                    path="street"
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
                    required="${not empty mandatoryFields['street']}" />

            <formElement:text
                    path="houseNumber"
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
                    required="${not empty mandatoryFields['houseNumber']}" />

            <formElement:text
                    path="zip"
                    id="zip"
                    label=""
                    labelKey="form.useraddress.zip.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.useraddress.zip.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.useraddress.zip.tooltip"
                    required="${not empty mandatoryFields['zip']}" />

            <formElement:text
                    path="city"
                    id="city"
                    label=""
                    labelKey="form.useraddress.city.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.useraddress.city.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.useraddress.city.tooltip"
                    required="${not empty mandatoryFields['city']}" />

            <formElement:select
                    path="country"
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
                    required="${not empty mandatoryFields['country']}"
                    disabled="${fn:length(deliveryCountries) == 1}"/>


            <c:if test="${displaymappings['DifferentShipmentAddressFormElementGroup']}">

                <c:set var="key"
                value="${isAllDigital
                ? 'form.useraddress.separateDeliveryAddress.digital' : 'form.useraddress.separateDeliveryAddress.physical_or_kombi'}"/>

                <formElement:checkbox
                        path="differentShipmentAddress"
                        id="dummyField9a"
                        label=""
                        labelKey="${key}.label"
                        value="true"
                        cssClass=""
                        msg=""
                        msgType=""
                        tooltip=""
                        tooltipKey="${key}.tooltip"
                        required="false" />
            </c:if>

            <c:if test="${displaymappings['DifferentInvoiceAddressFormElementGroup'] && !displaymappings['DownloadProductTypeFormElementGroup']}">
                <formElement:checkbox
                        path="differentInvoiceAddress"
                        id="dummyField10a"
                        label=""
                        labelKey="form.useraddress.separateBillingAddress.label"
                        value="true"
                        cssClass=""
                        msg=""
                        msgType=""
                        tooltip=""
                        tooltipKey="form.useraddress.separateBillingAddress.tooltip"
                        required="false" />
            </c:if>

        </ul>

    </div>
</div>