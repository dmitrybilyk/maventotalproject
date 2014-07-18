<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="mod-payment-method-direct-debit">
    <div class="inner">

        <div class="tabs">

            <%-- TODO: Set this ul after implementation tabs --%>
            <ul style="display: none;">
                <li><a href="#bank-bic">iban / BIC</a></li>
                <li><a href="#bank-kto">KTO / BLZ</a></li>
            </ul>

            <div>

                <ul class="input-list">

                    <formElement:text
                            path="firstName"
                            id="creditcart-account-prename"
                            label=""
                            labelKey="form.creditcart.creditCartFirstName.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.creditcart.creditCartFirstName.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.creditcart.creditCartFirstName.tooltip"
                            required="false" />

                    <formElement:text
                            path="lastName"
                            id="creditcart-account-surname"
                            label=""
                            labelKey="form.creditcart.creditCartLastName.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.creditcart.creditCartLastName.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.creditcart.creditCartLastName.tooltip"
                            required="false" />

                    <formElement:text
                            path="cartNumber"
                            id="creditcart-account-cartnumber"
                            label=""
                            labelKey="form.creditcart.cartNumber.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.creditcart.cartNumber.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.creditcart.cartNumber.tooltip"
                            required="false" />

                    <formElement:text
                            path="validThru"
                            id="creditcart-account-validthru"
                            label=""
                            labelKey="form.creditcart.validThru.label"
                            cssClass=""
                            placeholder=""
                            placeholderKey="form.creditcart.validThru.placeholder"
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey="form.creditcart.validThru.tooltip"
                            required="false" />

                </ul>

            </div>

        </div>

    </div>
</div>