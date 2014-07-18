<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="formblockDeliveryAddress" tagdir="/WEB-INF/tags/desktop/formblock/deliveryAddress" %>

<div class="mod-userdata">
    <div class="inner">

        <p><spring:theme code="form.userdata.heading" /></p>

        <ul class="input-list">
            <formElement:select
                    path="salutation"
                    id="salutation"
                    label=""
                    labelKey="form.userdata.salutation.label"
                    selectMsg=""
                    selectMsgKey="form.userdata.salutation.selectMsg"
                    options="${titles}"
                    optionValueAttr="code"
                    optionLabelAttr=""
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.userdata.salutation.tooltip"
                    required="${false}" />

            <formElement:text
                    path="firstName"
                    id="prename"
                    label=""
                    labelKey="form.userdata.prename.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.userdata.prename.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.userdata.prename.tooltip"
                    required="${false}" />

            <formElement:text
                    path="lastName"
                    id="surname"
                    label=""
                    labelKey="form.userdata.surname.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.userdata.surname.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.userdata.surname.tooltip"
                    required="${false}" />

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
    </div>
</div>
