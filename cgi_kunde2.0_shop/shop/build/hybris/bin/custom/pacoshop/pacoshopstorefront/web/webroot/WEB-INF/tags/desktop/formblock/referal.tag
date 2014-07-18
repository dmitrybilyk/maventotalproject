<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${displaymappings['PhysicalBonusWithReferralFormElementGroup']}">
    <div class="mod-referral">
        <div class="inner">
            <p><spring:theme code="form.referal.heading"/></p>

            <ul class="input-list">

                <formElement:select
                        path="referralSalutation"
                        id="referal-salutation"
                        label=""
                        labelKey="form.referal.referalSalutation.label"
                        selectMsg=""
                        selectMsgKey="form.referal.salutation.selectMsg"
                        options="${titles}"
                        optionValueAttr=""
                        optionLabelAttr=""
                        cssClass=""
                        msg=""
                        msgType=""
                        tooltip=""
                        tooltipKey="form.referal.salutation.tooltip"
                        required="${not empty mandatoryFields['referralSalutation']}"/>

                <formElement:select
                        path="referralTitle"
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
                        required="${not empty mandatoryFields['referralTitle']}"/>

                <formElement:text
                        path="referralFirstName"
                        id="referal-prename"
                        label=""
                        labelKey="form.referal.referalPrename.label"
                        cssClass=""
                        placeholder=""
                        placeholderKey="form.referal.referalPrename.placeholder"
                        msg=""
                        msgType=""
                        tooltip=""
                        tooltipKey="form.referal.referalPrename.tooltip"
                        required="${not empty mandatoryFields['referralFirstName']}"/>

                <formElement:text
                        path="referralLastName"
                        id="referal-surname"
                        label=""
                        labelKey="form.referal.referalSurname.label"
                        cssClass=""
                        placeholder=""
                        placeholderKey="form.referal.referalSurname.placeholder"
                        msg=""
                        msgType=""
                        tooltip=""
                        tooltipKey="form.referal.referalSurname.tooltip"
                        required="${not empty mandatoryFields['referralLastName']}"/>

                <formElement:text
                        path="referralAdditionalAddress"
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
                        required="${not empty mandatoryFields['referralAdditionalAddress']}"/>


                <formElement:text
                        path="referralStreet"
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
                        required="${not empty mandatoryFields['referralStreet']}"/>

                <formElement:text
                        path="referralHouseNumber"
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
                        required="${not empty mandatoryFields['referralHouseNumber']}"/>

                <formElement:text
                        path="referralZip"
                        id="referal-zip"
                        label=""
                        labelKey="form.referal.referalZip.label"
                        cssClass=""
                        placeholder=""
                        placeholderKey="form.referal.referalZip.placeholder"
                        msg=""
                        msgType=""
                        tooltip=""
                        tooltipKey="form.referal.referalZip.tooltip"
                        required="${not empty mandatoryFields['referralZip']}"/>

                <formElement:text
                        path="referralCity"
                        id="referal-city"
                        label=""
                        labelKey="form.referal.referalCity.label"
                        cssClass=""
                        placeholder=""
                        placeholderKey="form.referal.referalCity.placeholder"
                        msg=""
                        msgType=""
                        tooltip=""
                        tooltipKey="form.referal.referalCity.tooltip"
                        required="${not empty mandatoryFields['referralCity']}"/>

                <formElement:select
                        path="referralCountry"
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
                        required="${not empty mandatoryFields['referralCountry']}"/>

            </ul>

        </div>
    </div>
</c:if>

<c:if test="${displaymappings['MonetaryBonusNoReferralFormElementGroup'] || displaymappings['MonetaryBonusReferralFormElementGroup']}">
    <div class="mod-referral">
        <div class="inner">

            <p><spring:theme code="${displaymappings['MonetaryBonusReferralFormElementGroup'] ? 'form.referal.indicateAccount' : 'form.no_referal.indicateAccount'}"/></p>

            <div class="tabs">
                <ul>
                    <c:choose>
                        <c:when test="${form.tabIbanBic}">
                            <li class="ui-tabs-active"><a href="#bank-bic"><spring:theme code="form.referal.referalTabs.IBAN_BIC" /></a></li>
                            <li><a href="#bank-kto"><spring:theme code="form.referal.referalTabs.Kontonummer_BLZ" /></a></li>
                        </c:when>
                        <c:when test="${form.tabKontonummerBlz}">
                            <li><a href="#bank-bic"><spring:theme code="form.referal.referalTabs.IBAN_BIC" /></a></li>
                            <li class="ui-tabs-active"><a href="#bank-kto"><spring:theme code="form.referal.referalTabs.Kontonummer_BLZ" /></a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="ui-tabs-active"><a href="#bank-bic"><spring:theme code="form.referal.referalTabs.IBAN_BIC" /></a></li>
                            <li><a href="#bank-kto"><spring:theme code="form.referal.referalTabs.Kontonummer_BLZ" /></a></li>
                        </c:otherwise>
                    </c:choose>



                </ul>
                <div id="bank-bic">
                    <ul class="input-list">

                        <formElement:text
                                path="ibanBACfirstName"
                                id="referal-account-prename"
                                label=""
                                labelKey="form.referal.referalAccountPrename.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.referal.referalAccountPrename.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.referal.referalAccountPrename.tooltip"
                                required="true"/>

                        <formElement:text
                                path="ibanBAClastName"
                                id="referal-account-surname"
                                label=""
                                labelKey="form.referal.referalAccountSurname.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.referal.referalAccountSurname.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.referal.referalAccountSurname.tooltip"
                                required="true"/>

                        <formElement:text
                                path="iban"
                                id="referal-account-iban"
                                label=""
                                labelKey="form.referal.referalAccountIban.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.referal.referalAccountIban.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.referal.referalAccountIban.tooltip"
                                required="true"/>

                        <formElement:text
                                path="bic"
                                id="referal-account-bic"
                                label=""
                                labelKey="form.referal.referalAccountBic.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.referal.referalAccountBic.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.referal.referalAccountBic.tooltip"
                                required="true"/>

                        <spring:theme code="form.referal.moreInformation" var="moreInformation"/>
                        <formElement:infotext
                                text=''
                                textKey='${moreInformation}'
                                cssClass=""/>

                    </ul>
                </div>
                <div id="bank-kto">
                    <ul class="input-list">

                        <formElement:text
                                path="kontonummerBLZfirstName"
                                id="referal-account-prename-kto"
                                label=""
                                labelKey="form.referal.referalAccountPrenameKTO.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.referal.referalAccountPrenameKTO.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.referal.referalAccountPrenameKTO.tooltip"
                                required="true"/>

                        <formElement:text
                                path="kontonummerBLZlastName"
                                id="referal-account-surname-kto"
                                label=""
                                labelKey="form.referal.referalAccountSurnameKTO.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.referal.referalAccountSurnameKTO.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.referal.referalAccountSurnameKTO.tooltip"
                                required="true"/>

                        <formElement:text
                                path="accountNumber"
                                id="referal-account-kto"
                                label=""
                                labelKey="form.referal.referalAccountKTO.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.referal.referalAccountKTO.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.referal.referalAccountKTO.tooltip"
                                required="true"/>

                        <formElement:text
                                path="banIdNumber"
                                id="referal-account-blz"
                                label=""
                                labelKey="form.referal.referalAccountBLZ.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.referal.referalAccountBLZ.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.referal.referalAccountBLZ.tooltip"
                                required="true"/>

                        <spring:theme code="form.referal.moreInformationKTO" var="moreInformation"/>
                        <formElement:infotext
                                text=''
                                textKey='${moreInformation}'
                                cssClass=""/>

                    </ul>
                </div>
            </div>

        </div>
    </div>
</c:if>