<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-payment-method-direct-debit">
    <div class="inner">

        <div class="tabs">
            <ul>
                <li><a href="#bank-bic">iban / BIC</a></li>
                <li><a href="#bank-kto">KTO / BLZ</a></li>
            </ul>
            <div id="bank-bic">
                <ul class="input-list">

                    <formElement:text
                            path="dummyField"
                            id="account-prename"
                            label=""
                            labelKey="form.paymentMethodDirectDebit.accountPrename.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.paymentMethodDirectDebit.accountPrename.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.paymentMethodDirectDebit.accountPrename.tooltip"
                            required="true" />

                    <formElement:text
                            path="dummyField"
                            id="account-surname"
                            label=""
                            labelKey="form.paymentMethodDirectDebit.accountSurname.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.paymentMethodDirectDebit.accountSurname.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.paymentMethodDirectDebit.accountSurname.tooltip"
                            required="true" />

                    <formElement:text
                            path="dummyField"
                            id="account-iban"
                            label=""
                            labelKey="form.paymentMethodDirectDebit.accountIban.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.paymentMethodDirectDebit.accountIban.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.paymentMethodDirectDebit.accountIban.tooltip"
                            required="true" />

                    <formElement:text
                            path="dummyField"
                            id="account-bic"
                            label=""
                            labelKey="form.paymentMethodDirectDebit.accountBic.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.paymentMethodDirectDebit.accountBic.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.paymentMethodDirectDebit.accountBic.tooltip"
                            required="true" />

                    <spring:theme code="form.paymentMethodDirectDebit.moreInformation" var="moreInformation" />
                    <formElement:infotext
                            text=''
                            textKey='${moreInformation}'
                            cssClass="" />

                </ul>
            </div>
            <div id="bank-kto">
                <ul class="input-list">

                    <formElement:text
                            path="dummyField"
                            id="account-prename-kto"
                            label=""
                            labelKey="form.paymentMethodDirectDebit.accountPrenameKTO.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.paymentMethodDirectDebit.accountPrenameKTO.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.paymentMethodDirectDebit.accountPrenameKTO.tooltip"
                            required="true" />

                    <formElement:text
                            path="dummyField"
                            id="account-surname-kto"
                            label=""
                            labelKey="form.paymentMethodDirectDebit.accountSurnameKTO.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.paymentMethodDirectDebit.accountSurnameKTO.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.paymentMethodDirectDebit.accountSurnameKTO.tooltip"
                            required="true" />

                    <formElement:text
                            path="dummyField"
                            id="account-kto"
                            label=""
                            labelKey="form.paymentMethodDirectDebit.accountKTO.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.paymentMethodDirectDebit.accountKTO.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.paymentMethodDirectDebit.accountKTO.tooltip"
                            required="true" />

                    <formElement:text
                            path="dummyField"
                            id="account-blz"
                            label=""
                            labelKey="form.paymentMethodDirectDebit.accountBLZ.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.paymentMethodDirectDebit.accountBLZ.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.paymentMethodDirectDebit.accountBLZ.tooltip"
                            required="true" />

                    <spring:theme code="form.paymentMethodDirectDebit.moreInformationKTO" var="moreInformation" />
                    <formElement:infotext
                            text=''
                            textKey='${moreInformation}'
                            cssClass="" />

                </ul>
            </div>
        </div>

    </div>
</div>