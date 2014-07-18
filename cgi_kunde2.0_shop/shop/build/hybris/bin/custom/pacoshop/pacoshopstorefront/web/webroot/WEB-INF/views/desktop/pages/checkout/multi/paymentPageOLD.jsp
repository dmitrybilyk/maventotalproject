<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="steps" tagdir="/WEB-INF/tags/desktop/steps" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:set value="/saveBillingAddress" var="addBillingAddressUrl"/>
<c:set value="/checkout/multi/summary" var="summaryPageUrl"/>

somw text


<%--
    IMPORTANT: CSS only for demo purpose, remove when doing real implementation!!!
--%>
<style>
    #paymentDetailsForm fieldset {
        padding-top: 0;
        padding-bottom: 0;
        border: none;
    }
    #paymentDetailsForm fieldset.selected {
        border: 1px solid #CCCCCC;
    }
    #paymentDetailsForm #paymentApiSpinner {
    }

    html.paymentApiLoading body,
    html.paymentApiLoading #billingAddressForm button,
    html.paymentApiLoading #paymentDetailsForm input,
    html.paymentApiLoading #paymentDetailsForm select {
        cursor: wait;
    }
    html.paymentApiLoading #billingAddressForm button[type='submit']:before {
        content: "";
        width: 32px;
        height: 32px;
        background-image: url(${commonResourcePath}/images/spinner.gif);
        position: fixed;
        top: 50%;
        left: 50%;
    }
    #billingAddressForm .save_payment_details {
        padding-top: 30px;
        padding-bottom: 20px;
    }
</style>


<script type="text/javascript" src="${commonResourcePath}/js/jquery-1.7.2.min.js"></script>
<%--
    Loading PaymentExtension JavaScript API
--%>
<script src="${paymentContainer.jsApiUrl}"></script>

<%--
    Using PaymentExtension JavaScript API...
--%>
<script type="text/javascript">

    /*<![CDATA[*/
    $(document).ready(function(){



        /**
         * Payment form animations & sync state
         *
         */
        $("#paymentDetailsForm").each(function() {

            /**
             * Shiny accordeon animation (hide form fields for unselected payment modes)
             *
             */
            $("fieldset input[name='paymentMode']").bind("change", function() {
                $("fieldset input[name='paymentMode']:not(:checked)").parents("fieldset").removeClass("selected").find("div.payment-api-fields").hide();
                $("fieldset input[name='paymentMode']:checked").parents("fieldset").addClass("selected").find("div.payment-api-fields").slideDown();
            }).trigger("change");



            /**
             * Special for Maestro card: Show issue number and date.
             *
             */
            $("fieldset #payment-api-creditcard-methodCode").bind("change", function() {
                if ($(this).find("option:selected").attr("data-mode-type") == "maestro") {
                    $(this).parents("fieldset").find("div.issNo, div.issueDate").show();
                } else {
                    $(this).parents("fieldset").find("div.issNo, div.issueDate").hide();
                }
            }).trigger("change");



            /**
             * Track whether fields are in sync with backend.
             *
             */
            $(this).bind("change", function() {
                $(this).attr("data-synced-with-backend", "");
            });

        });



        /**
         * Using the Payment API:
         *
         *   - Catch billing address form submission event
         *   - Store payment information PCI-compliant via PaymentAPI JavaScript API
         *   - Submit address form || show error
         *
         */
        $("#billingAddressForm button[type='submit']").bind("click", function() {



            // Error message if API init failed
            if (window.paymentApi == undefined) {
                $("#globalMessages").html('<div class="information_message negative"><span class="single"></span><p>API init failed - payment is currently not possible.</p></div>');
                $("html, body").animate({scrollTop: 0}, 500);
                return false;
            }

            // Directly submit if payment data not changed
            if ($("#paymentDetailsForm").attr("data-synced-with-backend") == "true") {
                $("#billingAddressForm").submit();
                return false;
            }



            // Gather user input
            var mode = $("input[name='paymentMode']:checked").val();
            var methodCode = "";
            var data = {};

            switch(mode) {
                case 'creditcard':
                    methodCode = $("#payment-api-creditcard-methodCode").val();
                    data = {
                        "name"          : $("#payment-api-creditcard-name").val(),
                        "no"            : $("#payment-api-creditcard-no").val(),
                        "cvv"           : $("#payment-api-creditcard-cvv").val(),
                        "issNo"         : $("#payment-api-creditcard-issNo").val(),
                        "expMonth"      : $("#payment-api-creditcard-expMonth").val(),
                        "expYear"       : $("#payment-api-creditcard-expYear").val(),
                        "issMonth"      : $("#payment-api-creditcard-issMonth").val(),
                        "issYear"       : $("#payment-api-creditcard-issYear").val()
                    };
                    break;
                case 'directdebit':
                    methodCode = "";
                    data = {
                        "bankName"      : $("#payment-api-directdebit-bankName").val(),
                        "accountOwner"  : $("#payment-api-directdebit-accountOwner").val(),
                        "iban"          : $("#payment-api-directdebit-iban").val(),
                        "bic"           : $("#payment-api-directdebit-bic").val()
                    };
                    break;
                case 'giropay':
                    methodCode = "";
                    data = {
                        "accountOwner"  : $("#payment-api-giropay-accountOwner").val(),
                        "bankCode"      : $("#payment-api-giropay-bankCode").val(),
                        "accountCode"   : $("#payment-api-giropay-accountCode").val()
                    };
                    break;
                case 'paybox':
                    methodCode = "";
                    data = {
                        "accountCode"   : $("#payment-api-paybox-accountCode").val()
                    };
                    break;
                case 'click2pay':
                    methodCode = "";
                    data = {
                        "username"      : $("#payment-api-click2pay-username").val(),
                        "accountCode"   : $("#payment-api-click2pay-accountCode").val()
                    };
                    break;
            }

            // Disable input fields, show waiting cursor
            $("#paymentDetailsForm input, #paymentDetailsForm select").attr('disabled', 'disabled');
            $("html").addClass("paymentApiLoading");



            //
            // Call payment API
            // (paymentApi object is provided by javascript located at ${paymentContainer.jsApiUrl})
            //
            paymentApi.savePaymentData(mode, methodCode, data, function(result) {

                // Enable input fields, remove waiting cursor
                $("#paymentDetailsForm input, #paymentDetailsForm select").removeAttr('disabled');
                $("html").removeClass("paymentApiLoading");

                // ERROR handling
                if (result.isError()) {

                    var errorMsg = "";

                    if (result.getErrorType() == PaymentApi.ERROR_NO_PAYMENT_MODE_SELECTED) {
                        errorMsg = "<p>Bitte w�hlen Sie eine Zahlmethode!</p>";
                    } else if (result.getErrors().length > 0) {
                        for (index in result.getErrors()) {
                            errorMsg += "<p>" + result.getErrors()[index].msg + "</p>";
                        }
                    } else {
                        errorMsg = "<p>Ein Fehler trat auf - bitte versuchen Sie es erneut!</p>";
                    }

                    $("#globalMessages").html('<div class="information_message negative"><span class="single"></span>' + errorMsg + '</div>');
                    $("html, body").animate({scrollTop: 0}, 500);

                }

                // SUCCESS handling
                else {

                    // IMPORTANT: Replace plain cc number with masked one, remove CVV => never send this plain data to our server (important to stay PCI compliant!!!)
                    if (mode == 'creditcard') {
                        $("#payment-api-creditcard-no").val(result.getResponse().maskedNo);
                        $("#payment-api-creditcard-cvv").val("***");
                    }

                    // Finally submit payment form (to store payment address via regular POST form submission)
                    $("#billingAddressForm").submit();

                }

            });


            // Avoid form being submitted (waiting for above API callback)
            return false;

        });

    });

    /*]]>*/
</script>

<div id="breadcrumb" class="breadcrumb">
    <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
</div>

<div id="globalMessages">
    <common:globalMessages/>
</div>

<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="3" stepName="paymentMethod"/>

<div class="span-20 multicheckout">
<div class="item_container_holder">
<div class="title_holder">
    <h2><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.header" text="Payment Details"/></h2>
</div>
<div class="item_container">



<%--

    Payment data form

 --%>

<form method="post" id="paymentDetailsForm" class="create_update_payment_form" data-synced-with-backend="<c:if test="${not empty paymentContainer.paymentInfo}">true</c:if>">

<div class="payment_details_left_col">

<h1>Wie m&ouml;chten Sie zahlen?</h1>
<p>Bitte geben Sie Ihre Zahlungsdaten ein.</p>
<%--<p><spring:theme code="form.required"/></p>--%>

<c:forEach var="mode" items="${paymentContainer.modes}" varStatus="modeStatus">

<fieldset class="payment-api-mode-${mode.type}">

<legend>
    <input type="radio" name="paymentMode" id="payment-api-mode-${mode.type}" value="${mode.type}" <c:if test="${not usingAvailablePaymentInfo and mode.type eq paymentContainer.paymentInfo.paymentModeType}">checked="checked"</c:if> />
    <label for="payment-api-mode-${mode.type}">${mode.name}</label>
</legend>

<div class="payment-api-fields">

<c:choose>

    <%-- Credit card input --%>
    <c:when test="${mode.type eq 'creditcard'}">

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-methodCode">Herausgeber:</label>
        </div>
        <div class="form_field-input">
            <select id="payment-api-${mode.type}-methodCode" name="methodCode">
                <option value=""><spring:theme code='payment.cardType.pleaseSelect'/></option>
                <c:forEach var="method" items="${mode.methods}" varStatus="methodStatus">
                    <option value="${method.code}" data-mode-type="${method.type}" <c:if test="${method.code eq paymentContainer.paymentInfo.paymentMethodCode}">selected="selected"</c:if>>${method.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-name">Name:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-name" name="name" value="${paymentContainer.paymentInfo[mode.type].name}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-no">No:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-no" name="no" value="${paymentContainer.paymentInfo[mode.type].no}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-cvv">CVV:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-cvv" name="cvv" value="${paymentContainer.paymentInfo[mode.type].cvv}" />
        </div>

        <fieldset class="cardDate">
            <legend>
                <label for="payment-api-${mode.type}-expMonth">Ablauf:</label>
            </legend>
            <div class="form_field-input">
                <select id="payment-api-${mode.type}-expMonth" name="expMonth">
                    <option value=""></option>
                    <c:forEach var="month" items="${months}" varStatus="monthStatus">
                        <option value="${month.code}" <c:if test="${month.code eq paymentContainer.paymentInfo[mode.type].expMonth}">selected="selected"</c:if>>${month.name}</option>
                    </c:forEach>
                </select>
                <select id="payment-api-${mode.type}-expYear" name="expYear">
                    <option value=""></option>
                    <c:forEach var="year" items="${expiryYears}" varStatus="yearStatus">
                        <option value="${year.code}" <c:if test="${year.code eq paymentContainer.paymentInfo[mode.type].expYear}">selected="selected"</c:if>>${year.name}</option>
                    </c:forEach>
                </select>
            </div>
        </fieldset>

        <div class="form_field-label issNo">
            <label for="payment-api-${mode.type}-issNo">Ausgabenummer:</label>
        </div>
        <div class="form_field-input issNo">
            <input type="text" id="payment-api-${mode.type}-issNo" name="issNo" value="${paymentContainer.paymentInfo[mode.type].issNo}" />
        </div>

        <div class="issueDate">
            <fieldset class="cardDate">
                <legend>
                    <label for="payment-api-${mode.type}-issMonth">Ausgestellt:</label>
                </legend>
                <div class="form_field-input">
                    <select id="payment-api-${mode.type}-issMonth" name="issMonth">
                        <option value=""></option>
                        <c:forEach var="month" items="${months}" varStatus="monthStatus">
                            <option value="${month.code}" <c:if test="${month.code eq paymentContainer.paymentInfo[mode.type].issMonth}">selected="selected"</c:if>>${month.name}</option>
                        </c:forEach>
                    </select>
                    <select id="payment-api-${mode.type}-issYear" name="issYear">
                        <option value=""></option>
                        <c:forEach var="year" items="${startYears}" varStatus="yearStatus">
                            <option value="${year.code}" <c:if test="${year.code eq paymentContainer.paymentInfo[mode.type].expYear}">selected="selected"</c:if>>${year.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </fieldset>
        </div>

    </c:when>

    <%-- Direct debit input --%>
    <c:when test="${mode.type eq 'directdebit'}">

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-accountOwner">Kontoinhaber:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-accountOwner" name="accountOwner" value="${paymentContainer.paymentInfo[mode.type].accountOwner}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-bankCountry">Land der Bank:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-bankCountry" name="bankCountry" value="${paymentContainer.paymentInfo[mode.type].bankCountry}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-bankName">Name der Bank:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-bankName" name="bankName" value="${paymentContainer.paymentInfo[mode.type].bankName}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-bankCode">Bankleitzahl:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-bankCode" name="bankCode" value="${paymentContainer.paymentInfo[mode.type].bankCode}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-accountCode">Kontonummer:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-accountCode" name="accountCode" value="${paymentContainer.paymentInfo[mode.type].accountCode}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-iban">IBAN:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-iban" name="iban" value="${paymentContainer.paymentInfo[mode.type].iban}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-bic">BIC:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-bic" name="bic" value="${paymentContainer.paymentInfo[mode.type].bic}" />
        </div>

    </c:when>

    <%-- Giropay input --%>
    <c:when test="${mode.type eq 'giropay'}">

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-accountOwner">Kontoinhaber:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-accountOwner" name="accountOwner" value="${paymentContainer.paymentInfo[mode.type].accountOwner}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-bankCode">BLZ:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-bankCode" name="bankCode" value="${paymentContainer.paymentInfo[mode.type].bankCode}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-accountCode">Kontonummer:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-accountCode" name="accountCode" value="${paymentContainer.paymentInfo[mode.type].accountCode}" />
        </div>

    </c:when>

    <%-- Paybox input --%>
    <c:when test="${mode.type eq 'paybox'}">

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-accountCode">Kontonummer:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-accountCode" name="accountCode" value="${paymentContainer.paymentInfo[mode.type].accountCode}" />
        </div>

    </c:when>

    <%-- click2pay input --%>
    <c:when test="${mode.type eq 'click2pay'}">

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-username">Benutzername:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-username" name="username" value="${paymentContainer.paymentInfo[mode.type].username}" />
        </div>

        <div class="form_field-label">
            <label for="payment-api-${mode.type}-accountCode">Kontonummer:</label>
        </div>
        <div class="form_field-input">
            <input type="text" id="payment-api-${mode.type}-accountCode" name="accountCode" value="${paymentContainer.paymentInfo[mode.type].accountCode}" />
        </div>

    </c:when>

</c:choose>

</div>

</fieldset>

</c:forEach>
</div>
</form>



<%--

    Billing address form

 --%>
<form:form method="post" commandName="billingAddressForm" class="create_update_payment_form" action="${addBillingAddressUrl}">

    <%-- <div class="payment_details_right_col">

         <h1><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddress"/></h1>
         <p><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddressDiffersFromDeliveryAddress"/></p>
         <p><spring:theme code="form.required"/></p>

         <form:checkbox id="differentAddress" path="newBillingAddress" tabindex="9"/>
         <label for="differentAddress"><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.enterDifferentBillingAddress"/></label>

         <div id="newBillingAddressFields">
             <form:hidden path="addressId" class="create_update_address_id"/>
             <formElement:formSelectBox idKey="address.title" labelKey="address.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="address.title.pleaseSelect" items="${titles}" tabindex="10"/>
             <formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="firstName" inputCSS="text" mandatory="true" tabindex="11"/>
             <formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName" inputCSS="text" mandatory="true" tabindex="12"/>
             <formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="text" mandatory="true" tabindex="14"/>
             <formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="text" mandatory="false" tabindex="15"/>
             <formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="text" mandatory="true" tabindex="16"/>
             <formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="text" mandatory="true" tabindex="17"/>
             <formElement:formSelectBox idKey="address.country" labelKey="address.country" path="countryIso" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectCountry" items="${billingCountries}" itemValue="isocode" tabindex="18"/>
             <form:hidden path="shippingAddress"/>
             <form:hidden path="billingAddress"/>
         </div>

     </div>
 --%>
    <div class="save_payment_details">

        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
            <form:checkbox id="SaveDetails" path="saveInAccount" tabindex="19"/>
            <label for="SaveDetails"><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.savePaymentDetailsInAccount"/></label>
        </sec:authorize>

                        <span class="clear_fix">
                            <ycommerce:testId code="editPaymentMethod_savePaymentMethod_button">
                                <button class="form" tabindex="20" id="lastInTheForm" type="submit">
                                    <spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.useThesePaymentDetails"/>
                                </button>
                            </ycommerce:testId>
                            <c:if test="${not hasNoPaymentInfo}">
                                <button class="form" type="button" onclick="window.location='${summaryPageUrl}'"><spring:theme code="checkout.multi.cancel" text="Cancel"/></button>
                            </c:if>
                        </span>

    </div>

</form:form>



</div>
</div>
</div>

