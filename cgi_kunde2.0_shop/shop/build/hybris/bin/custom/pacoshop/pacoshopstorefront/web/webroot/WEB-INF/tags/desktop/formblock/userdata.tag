<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                    required="${not empty mandatoryFields['salutation']}" />

            <formElement:select
                    path="title"
                    id="title"
                    label=""
                    labelKey="form.userdata.title.label"
                    selectMsg=""
                    selectMsgKey="form.userdata.title.selectMsg"
                    options="${titles2}"
                    optionValueAttr="code"
                    optionLabelAttr=""
                    cssClass=""
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.userdata.title.tooltip"
                    required="false" />

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
                    required="${not empty mandatoryFields['firstName']}" />

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
                    required="${not empty mandatoryFields['lastName']}" />

           <c:if test="${!displaymappings['DownloadProductTypeFormElementGroup']}">
            <formElement:dateFields
                    pathDay="birthDateDay"
                    pathMonth="birthDateMonth"
                    pathYear="birthDateYear"
                    idDay="birthDay"
                    idMonth="birthMonth"
                    idYear="birthYear"
                    label=""
                    labelKey="form.userdata.birthdate.label"
                    cssClass=""
                    placeholderDay=""
                    placeholderDayKey="form.userdata.placeholderDay"
                    placeholderMonth=""
                    placeholderMonthKey="form.userdata.placeholderMonth"
                    placeholderYear=""
                    placeholderYearKey="form.userdata.placeholderYear"
                    msg=""
                    msgType=""
                    required="false" />

           </c:if>
        </ul>

    </div>
</div>