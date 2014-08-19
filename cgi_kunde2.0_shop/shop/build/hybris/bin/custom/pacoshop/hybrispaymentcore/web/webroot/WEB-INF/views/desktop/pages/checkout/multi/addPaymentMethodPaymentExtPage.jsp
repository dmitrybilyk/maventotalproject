<%--

    PAYMENTEXTENSION

    CGI Payment Extension
    
    - checkout flow page to capture payment data and billing address
    
    - replacement for original: /XXXstorefront/web/webroot/WEB-INF/views/desktop/pages/checkout/multi/addPaymentMethodPaymentExtPage.jsp
    - to be referenced in:      MultistepCheckoutController

 --%>
 
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/desktop/payment" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="/store/checkout/multi/summary" var="summaryPageUrl"/>

<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
    
    
    
    <%--
        IMPORTANT: CSS only for demo purpose, remove when doing real implementation!!!
    --%>
    <style>
    
        /* BirthDate display */
        #billingAddressForm select#payment-api-billing-address-dateOfBirthDay,
        #billingAddressForm select#payment-api-billing-address-dateOfBirthMonth,
        #billingAddressForm select#payment-api-billing-address-dateOfBirthYear {
            width: 23%;
        }
        
        /* BillingAddress fields disabled: display them lighter */
        #billingAddressForm.disabled .form_field-label, 
        #billingAddressForm.disabled .form_field-input {
            opacity: 0.5;
        }        
        #billingAddressForm.disabled .newBillingAddress {
            opacity: 1;
        }

        /* Layout for (un)-selected payment modes */
        #paymentDetailsForm fieldset {
            padding-top: 0;
            padding-bottom: 0;
            border: none;
        }
        #paymentDetailsForm fieldset.selected {
            border: 1px solid #CCCCCC;
        }
        
        /* Layout for submit/cancel buttons */
        #nextPageForm {
            clear: both;
            float: right;
        }
        #nextPageForm .save_payment_details {
            clear: both;
            float: right;
            margin: 20px 0 0 0;
        }
        #nextPageForm .save_payment_details + button {
            clear: both;
        }
        #nextPageForm button#payment-api-submit-data,
        #nextPageForm button#payment-api-cancel-data {
            float: right;
            margin: 20px 0 5px 10px;
        }
        
        /* API loading animation */
        html.paymentApiLoading body,
        html.paymentApiLoading button#payment-api-submit-data {
            cursor: wait;
        }        
        html.paymentApiLoading button#payment-api-submit-data:before {
            content: "";
            width: 32px;
            height: 32px;
            background-image: url(${commonResourcePath}/images/spinner.gif);
            position: fixed;            
            top: 50%;
            left: 50%;
        }
        
    </style>
           
           
                            
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
            
            var allPaymentForms = $("#availablePaymentInfosForm, #paymentDetailsForm");
            
            
            
            /**
             * Track whether fields are in sync with backend (=> No API calls needed).
             *
             */
            allPaymentForms.bind("change", function() {
                 allPaymentForms.attr("data-synced-with-backend", "");
            });
            
            
            
            /**
             * Allow selection of EITHER available paymentInfo OR newly entered payment mode.
             *
             */
            var allPaymentOptions = allPaymentForms.find("input[name='paymentInfo'], input[name='paymentMode']");
            allPaymentOptions.bind("change", function() {
                if ($(this).attr('checked')) {
                    allPaymentOptions.attr('checked', false).trigger("updateLayout");
                    $(this).attr('checked', true);
                }
            });
            
            
            
            /**
             * Payment details form animations
             *
             */
            $("#paymentDetailsForm").each(function() {
                
                // Shiny accordeon animation (hide form fields for unselected payment modes)
                $("fieldset input[name='paymentMode']").bind("change updateLayout", function() {
                    $("fieldset input[name='paymentMode']:not(:checked)").parents("fieldset").removeClass("selected").find("div.payment-api-fields").hide();
                    $("fieldset input[name='paymentMode']:checked").parents("fieldset").addClass("selected").find("div.payment-api-fields").slideDown();
                }).trigger("updateLayout");
                
                // Special for some credit cards (Maestro): Show issue number and date.
                $("fieldset #payment-api-creditcard-methodCode").bind("change updateLayout", function() {
                    if ($(this).find("option:selected").attr("data-requires-issuer") == "true") {
                        $(this).parents("fieldset").find("div.issNo, div.issueDate").show();                     
                    } else {
                        $(this).parents("fieldset").find("div.issNo, div.issueDate").hide();
                    }
                }).trigger("updateLayout");
                
                // Special for some installment modes: Show bank information
                $("fieldset #payment-api-installment-methodCode").bind("change updateLayout", function() {
                    if ($(this).find("option:selected").attr("data-requires-direct-debit") == "true") {
                        $(this).parents("fieldset").find("div.directDebitData").show();                     
                    } else {
                        $(this).parents("fieldset").find("div.directDebitData").hide();
                    }
                }).trigger("updateLayout");
            
            });
            
            
            
            /**
             * Saving animation (disable input fields, show waiting cursor)
             *
             */
            function showApiLoadingAnimation(show) {
                var fields = $("#availablePaymentInfosForm input, #paymentDetailsForm input, #paymentDetailsForm select");
                if (show) {
                    fields.attr('disabled', 'disabled');
                    $("html").addClass("paymentApiLoading");                    
                } else {
                    fields.removeAttr('disabled');                    
                    $("html").removeClass("paymentApiLoading");                 
                }               
            }
            
            
            
            /**
             * Show error messages from API result
             */
            function showApiErrorMessages(apiResult) {

                var errorMsg = "";
                
                if (apiResult.getErrorType() == PaymentApi.ERROR_NO_PAYMENT_MODE_SELECTED) {                            
                    errorMsg = "<p>Bitte w&auml;hlen Sie eine Zahlmethode!</p>";                            
                } else if (apiResult.getErrorType() == PaymentApi.ERROR_NO_PAYMENT_METHOD_SELECTED) {                            
                    errorMsg = "<p>Bitte w&auml;hlen Sie eine Zahlmethode (z.B. Kreditkartentyp)!</p>";                            
                }
                
                else if (apiResult.getErrors().length > 0) {
                    for (index in apiResult.getErrors()) {
                        errorMsg += "<p>" + apiResult.getErrors()[index].msg + "</p>";
                    }
                }
                
                else {
                    errorMsg = "<p>Ein Fehler trat auf - bitte versuchen Sie es erneut!</p>";                           
                }
                
                $("#globalMessages").html('<div class="information_message negative"><span class="single"></span>' + errorMsg + '</div>');
                $("html, body").animate({scrollTop: 0}, 500);
                
            }
            
            
            
            /**
             * Toggle editability of address fields. 
             */
            $("#billingAddressForm #payment-api-billing-address-newBillingAddress").bind("change updateLayout", function() {
                if ($(this).is(":checked")) {
                    $(this).parents("form").find("input, select").removeAttr("disabled");
                    $(this).parents("form").removeClass("disabled");
                } else {
                    $(this).parents("form").find("input, select").attr("disabled", "disabled");
                    $(this).parents("form").addClass("disabled");
                    $(this).removeAttr("disabled");
                     $("#payment-api-billing-address-phone").removeAttr("disabled")
                     $("#payment-api-billing-address-mobile").removeAttr("disabled")
                     $("#payment-api-billing-address-dateOfBirthDay").removeAttr("disabled")
                     $("#payment-api-billing-address-dateOfBirthMonth").removeAttr("disabled")
                     $("#payment-api-billing-address-dateOfBirthYear").removeAttr("disabled")
                }
            }).trigger("updateLayout");
            
            
            
            /**
             *  Save billing address
             */
            function saveBillingAddress(nextPageUrl) {
                
                var data = {
                        "id"                : $("#payment-api-billing-address-id").val(),
                        "titleCode"         : $("#payment-api-billing-address-titleCode").val(),
                        "firstName"         : $("#payment-api-billing-address-firstName").val(),
                        "lastName"          : $("#payment-api-billing-address-lastName").val(),
                        "line1"             : $("#payment-api-billing-address-line1").val(),
                        "line2"             : $("#payment-api-billing-address-line2").val(),
                        "town"              : $("#payment-api-billing-address-town").val(),
                        "postalCode"        : $("#payment-api-billing-address-postalCode").val(),
                        "countryIso"        : $("#payment-api-billing-address-countryIso").val(),
                        "phone"             : $("#payment-api-billing-address-phone").val(),
                        "mobile"            : $("#payment-api-billing-address-mobile").val(),
                        "email"             : "",
                        "company"           : "",
                        "state"             : "",
                        "regionIso"         : "",
                        "dateOfBirthDay"    : $("#payment-api-billing-address-dateOfBirthDay").val(),
                        "dateOfBirthMonth"  : $("#payment-api-billing-address-dateOfBirthMonth").val(),
                        "dateOfBirthYear"   : $("#payment-api-billing-address-dateOfBirthYear").val(),
                        "newBillingAddress" : ($("#payment-api-billing-address-newBillingAddress:checked").length == 1 ? "true" : "false"),
                        "saveInAccount"     : ($("#payment-api-saveInAccount:checked").length == 1 ? "true" : "false")
                };
                
                // Call payment API - (paymentApi object is provided by javascript located at ${paymentContainer.jsApiUrl})
                showApiLoadingAnimation(true);
                paymentApi.saveBillingAddress(data, function(apiResult) { 
                    
                    showApiLoadingAnimation(false);
                    
                    if (apiResult.isError()) {
                        showApiErrorMessages(apiResult);
                    } else {
                        // Go to review page
                        if (nextPageUrl != undefined) {
                            document.location.href = nextPageUrl;
                        }
                    }
                    
                });

            }

            
            
            /**
             * Handle removing existing paymentInfo
             *
             */
            $("#availablePaymentInfosForm a.remove").bind("click", function() {
                
                // Visually remove payment info
                var paymentInfoId = $(this).attr("data-payment-info-id");
                $("fieldset.payment-api-payment-info-" + paymentInfoId).fadeOut(function() {
                    $(this).remove();
                });
                
                // Call payment API - (paymentApi object is provided by javascript located at ${paymentContainer.jsApiUrl})
                paymentApi.removePaymentInfo(paymentInfoId, function(apiResult) {
                });             
                
                return false;
                
            });

            
            
            /**
             * Store payment data:
             *   
             *   - Catch billing address form submission event
             *   - Store existing paymentInfoId OR new payment information PCI-compliant via PaymentAPI JavaScript API
             *   - Submit address form || show error
             *
             */
            $("#nextPageForm #payment-api-submit-data").bind("click", function() {
                
                var nextPageUrl = $(this).parents("form").attr("action");
                
                // Directly submit if payment data not changed
                if ($("#paymentDetailsForm").attr("data-synced-with-backend") == "true") {
                    saveBillingAddress(nextPageUrl);
                    return false;
                }
                
                
                
                // Error message: API init failed
                if (window.paymentApi == undefined) {
                    $("#globalMessages").html('<div class="information_message negative"><span class="single"></span><p>API init failed - payment is currently not possible.</p></div>');
                    $("html, body").animate({scrollTop: 0}, 500);
                    return false;                    
                }

                // Error message: No payment selected at all
                if ($("#availablePaymentInfosForm input[name='paymentInfo'], #paymentDetailsForm input[name='paymentMode']").filter(":checked").length == 0) {
                    $("#globalMessages").html('<div class="information_message negative"><span class="single"></span><p>Bitte w&auml;hlen Sie eine bestehende oder neue Zahlmethode.</p></div>');
                    $("html, body").animate({scrollTop: 0}, 500);
                    return false;      
                }
                
                
                
                // Choose existing paymentInfo
                var existingPaymentInfo = $("#availablePaymentInfosForm input[name='paymentInfo']:checked");
                if (existingPaymentInfo.length != 0) {
                    
                    // Call payment API - (paymentApi object is provided by javascript located at ${paymentContainer.jsApiUrl})
                    showApiLoadingAnimation(true);
                    paymentApi.choosePaymentInfo(existingPaymentInfo.val(), function(apiResult) { 
                        
                        showApiLoadingAnimation(false);

                        // Show errors or continue with saving billing address
                        if (apiResult.isError()) {
                            showApiErrorMessages(apiResult);
                        } else {
                            saveBillingAddress(nextPageUrl);
                        }
                        
                    });
                    
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
                                "accountOwner"  : $("#payment-api-directdebit-accountOwner").val(),
                                "bankCountry"   : $("#payment-api-directdebit-bankCountry").val(),
                                "bankName"      : $("#payment-api-directdebit-bankName").val(),
                                "bankCode"      : $("#payment-api-directdebit-bankCode").val(),
                                "accountCode"   : $("#payment-api-directdebit-accountCode").val(),
                                "iban"          : $("#payment-api-directdebit-iban").val(),
                                "bic"           : $("#payment-api-directdebit-bic").val()
                        };
                        break;
                    case 'installment':
                        methodCode = $("#payment-api-installment-methodCode").val();
                        data = {
                                "installmentAmount"     : $("#payment-api-installment-installmentAmount").val(),
                                "numberOfInstallments"  : $("#payment-api-installment-numberOfInstallments").val(),
                                "allowCreditInquiry"    : ($("#payment-api-installment-allowCreditInquiry:checked").length == 1),
                                "paymentFirstDay"       : $("#payment-api-installment-paymentFirstDay").val(),
                                "accountOwner"          : $("#payment-api-installment-accountOwner").val(),
                                "bankCountry"           : $("#payment-api-installment-bankCountry").val(),
                                "bankName"              : $("#payment-api-installment-bankName").val(),
                                "bankCode"              : $("#payment-api-installment-bankCode").val(),
                                "accountCode"           : $("#payment-api-installment-accountCode").val(),
                                "iban"                  : $("#payment-api-installment-iban").val(),
                                "bic"                   : $("#payment-api-installment-bic").val()
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
                
                // Store this paymentInfo in user account for further transactions?
                if ($("#payment-api-saveInAccount:checked").length == 1) {
                    data.saveInAccount = "true";
                }
                
                // Call payment API - (paymentApi object is provided by javascript located at ${paymentContainer.jsApiUrl})
                showApiLoadingAnimation(true);
                paymentApi.savePaymentData(mode, methodCode, data, function(apiResult) {

                    showApiLoadingAnimation(false);
                    
                    // Show errors or continue with saving billing address
                    if (apiResult.isError()) {
                        showApiErrorMessages(apiResult);
                    } else {
                        saveBillingAddress(nextPageUrl);
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
                
                
                
                <div class="payment_details_left_col">



                        <%--
                            
                            Available paymentInfos form (token payment)
                            
                         --%>
                                
                        <%-- Track whether there is a preselected paymentInfo - then we mustn't fillTheCashMachine the paymentDetailsForm form --%>
                        <c:set var="usingAvailablePaymentInfo" value="false" />
                        
                        <c:if test="${fn:length(availablePaymentInfos) gt 0}">
                            <form method="post" id="availablePaymentInfosForm" class="create_update_payment_form" data-synced-with-backend="<c:if test="${not empty paymentContainer.paymentInfo}">true</c:if>">
                                
                                    <h1>Vorhandene Zahlungsdaten nutzen</h1>
                                    
                                    <c:forEach var="paymentInfo" items="${availablePaymentInfos}" varStatus="paymentInfoStatus">
                                    
                                        <c:if test="${paymentInfo.id eq paymentContainer.paymentInfo.id}">
                                            <c:set var="usingAvailablePaymentInfo" value="true" />
                                        </c:if>
                                    
                                        <fieldset class="payment-api-payment-info-${paymentInfo.id}">
                                        
                                            <legend>
                                                <input type="radio" name="paymentInfo" id="payment-api-payment-info-${paymentInfo.id}" value="${paymentInfo.id}" <c:if test="${paymentInfo.id eq paymentContainer.paymentInfo.id}">checked="checked"</c:if> />
                                                <label for="payment-api-payment-info-${paymentInfo.id}">${paymentInfo.paymentModeName}</label>
                                            </legend>
    
                                            <div class="payment-api-fields">
                                                <payment:paymentData paymentInfo="${paymentInfo}" delimiter="br" /><br />
                                                <a href="#" class="remove" data-payment-info-id="${paymentInfo.id}">Zahlungsmethode entfernen</a>
                                            </div>
                                            
                                        </fieldset>                 
                                    
                                    </c:forEach>
                                
                            </form>
                        </c:if>



                        <%--
                            
                            Payment data form
                            
                         --%>
                         
                        <h1>Neue Zahlungsdaten eingeben</h1>
                        <p><spring:theme code="form.required"/></p>
                 
                        <form method="post" id="paymentDetailsForm" class="create_update_payment_form" data-synced-with-backend="<c:if test="${not empty paymentContainer.paymentInfo}">true</c:if>">
                        
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
                                                        <option value="${method.code}" data-mode-type="${method.type}" data-requires-issuer="${method.parameters.requiresIssuer}" <c:if test="${method.code eq paymentContainer.paymentInfo.paymentMethodCode}">selected="selected"</c:if>>${method.name}</option>
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
                                        
                                            <%--
                                            <div class="form_field-label">
                                                <label for="payment-api-${mode.type}-bankCountry">Land der Bank:</label>
                                            </div>
                                            <div class="form_field-input">
                                                <input type="text" id="payment-api-${mode.type}-bankCountry" name="bankCountry" value="${paymentContainer.paymentInfo[mode.type].bankCountry}" />
                                            </div>
                                            --%>    
                                        
                                            <div class="form_field-label">
                                                <label for="payment-api-${mode.type}-bankName">Name der Bank:</label>
                                            </div>
                                            <div class="form_field-input">
                                                <input type="text" id="payment-api-${mode.type}-bankName" name="bankName" value="${paymentContainer.paymentInfo[mode.type].bankName}" />
                                            </div>
                                        
                                            <%--
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
                                            --%>
                                        
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
                                        
                                        <%-- Installment input --%>
                                        <c:when test="${mode.type eq 'installment'}">
                                        
                                            <div class="form_field-label">
                                                <label for="payment-api-${mode.type}-methodCode">Zahlweise:</label>
                                            </div>
                                            <div class="form_field-input">
                                                <select id="payment-api-${mode.type}-methodCode" name="methodCode">
                                                    <option value="">Bitte ausw&auml;hlen</option>
                                                    <c:forEach var="method" items="${mode.methods}" varStatus="methodStatus">
                                                        <option value="${method.code}" data-mode-type="${method.type}" data-requires-direct-debit="${method.parameters.requiresDirectDebit}" <c:if test="${method.code eq paymentContainer.paymentInfo.paymentMethodCode}">selected="selected"</c:if>>${method.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form_field-label">
                                                <label for="payment-api-${mode.type}-installmentAmount">Gew&uuml;nschte monatliche Rate:</label>
                                            </div>
                                            <div class="form_field-input">
                                                <c:if test="${paymentContainer.paymentInfo[mode.type].calculationType eq 'ByRate'}">
                                                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${paymentContainer.paymentInfo[mode.type].installmentAmount}" var="installmentAmountSelected" />
                                                </c:if>
                                                <input type="text" id="payment-api-${mode.type}-installmentAmount" name="installmentAmount" value="${installmentAmountSelected}" />
                                            </div>

                                            <div class="form_field-label">
                                                <label for="payment-api-${mode.type}-numberOfInstallments">ODER gew&uuml;nschte Anzahl Raten:</label>
                                            </div>
                                            <div class="form_field-input">
                                                <c:if test="${paymentContainer.paymentInfo[mode.type].calculationType eq 'ByTime'}">
                                                    <c:set var="numberOfInstallmentsSelected" value="${paymentContainer.paymentInfo[mode.type].numberOfInstallments}" />
                                                </c:if>
                                                <select id="payment-api-${mode.type}-numberOfInstallments" name="numberOfInstallments">
                                                    <option value="">Bitte ausw&auml;hlen</option>
                                                    <c:forEach var="numberOfInstallments" items="${fn:split(mode.methods[0].parameters.numberOfInstallments, ',')}"> <%-- TODO fix with Javascript magic --%>
                                                        <option value="${numberOfInstallments}" <c:if test="${numberOfInstallments eq numberOfInstallmentsSelected}">selected="selected"</c:if>>${numberOfInstallments}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        
                                            <div class="form_field-label">
                                                <label for="payment-api-${mode.type}-paymentFirstDay">Zahlung beginnt:</label>
                                            </div>
                                            <div class="form_field-input">                                                
                                                <select id="payment-api-${mode.type}-paymentFirstDay" name="paymentFirstDay">
                                                    <option value="">egal</option>
                                                    <option value="BEGIN" <c:if test="${'BEGIN' eq (paymentContainer.paymentInfo[mode.type].paymentFirstDay)}">selected="selected"</c:if>>Anfang des Monats</option>
                                                    <option value="MID" <c:if test="${'MID' eq (paymentContainer.paymentInfo[mode.type].paymentFirstDay)}">selected="selected"</c:if>>Mitte des Monats</option>
                                                    <option value="END" <c:if test="${'END' eq (paymentContainer.paymentInfo[mode.type].paymentFirstDay)}">selected="selected"</c:if>>Ende des Monats</option>
                                                </select>
                                            </div>

                                            <div class="form_field-label">
                                                <label for="payment-api-${mode.type}-allowCreditInquiry">Kreditauskunft erlauben: </label>
                                            </div>
                                            <div class="form_field-input">
                                                <input type="checkbox" id="payment-api-${mode.type}-allowCreditInquiry" name="allowCreditInquiry" value="true" ${not empty paymentContainer.paymentInfo[mode.type].allowCreditInquiry ? 'checked="checked"' : ''} />
                                                <br/>Bitte beachten sie die  
                                                <a target="blank" href="http://www.ratepay.com/zusaetzliche-geschaeftsbedingungen-und-datenschutzhinweis">Zus&auml;tzlichen Gesch�ftsbedingungen und Datenschutzhinweis</a></a>
                                            </div>

                                            <div class="form_field-label directDebitData">
                                                <label for="payment-api-${mode.type}-accountOwner">Kontoinhaber:</label>
                                            </div>
                                            <div class="form_field-input directDebitData">
                                                <input type="text" id="payment-api-${mode.type}-accountOwner" name="accountOwner" value="${paymentContainer.paymentInfo[mode.type].accountOwner}" />
                                            </div>
                                        
                                            <div class="form_field-label directDebitData">
                                                <label for="payment-api-${mode.type}-bankCountry">Land der Bank:</label>
                                            </div>
                                            <div class="form_field-input directDebitData">
                                                <input type="text" id="payment-api-${mode.type}-bankCountry" name="bankCountry" value="${paymentContainer.paymentInfo[mode.type].bankCountry}" />
                                            </div>
                                        
                                            <div class="form_field-label directDebitData">
                                                <label for="payment-api-${mode.type}-bankName">Name der Bank:</label>
                                            </div>
                                            <div class="form_field-input directDebitData">
                                                <input type="text" id="payment-api-${mode.type}-bankName" name="bankName" value="${paymentContainer.paymentInfo[mode.type].bankName}" />
                                            </div>
                                        
                                            <div class="form_field-label directDebitData">
                                                <label for="payment-api-${mode.type}-bankCode">Bankleitzahl:</label>
                                            </div>
                                            <div class="form_field-input directDebitData">
                                                <input type="text" id="payment-api-${mode.type}-bankCode" name="bankCode" value="${paymentContainer.paymentInfo[mode.type].bankCode}" />
                                            </div>
                                        
                                            <div class="form_field-label directDebitData">
                                                <label for="payment-api-${mode.type}-accountCode">Kontonummer:</label>
                                            </div>
                                            <div class="form_field-input directDebitData">
                                                <input type="text" id="payment-api-${mode.type}-accountCode" name="accountCode" value="${paymentContainer.paymentInfo[mode.type].accountCode}" />
                                            </div>
                                        
                                            <div class="form_field-label directDebitData">
                                                <label for="payment-api-${mode.type}-iban">IBAN:</label>
                                            </div>
                                            <div class="form_field-input directDebitData">
                                                <input type="text" id="payment-api-${mode.type}-iban" name="iban" value="${paymentContainer.paymentInfo[mode.type].iban}" />
                                            </div>
                                        
                                            <div class="form_field-label directDebitData">
                                                <label for="payment-api-${mode.type}-bic">BIC:</label>
                                            </div>
                                            <div class="form_field-input directDebitData">
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
                        
                    </form>

                </div>
                
                
                
                <%--
                    
                    Billing address form
                    
                 --%>
                <form method="post" id="billingAddressForm" class="create_update_payment_form">

                    <div class="payment_details_right_col">

                        <h1><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddress"/></h1>
                        <p><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddressDiffersFromDeliveryAddress"/></p>
                        <p><spring:theme code="form.required"/></p>
                        
                        <div class="form_field-input form_field-label newBillingAddress">
                            <input type="checkbox" id="payment-api-billing-address-newBillingAddress" name="newBillingAddress" value="true" ${not empty paymentContainer.paymentInfo.billingAddress ? 'checked="checked"' : ''} />
                            <label for="payment-api-billing-address-newBillingAddress"><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.enterDifferentBillingAddress"/></label>
                        </div>
                             
                        <input type="hidden" id="payment-api-billing-address-id" name="id" value="${paymentContainer.paymentInfo.billingAddress.id}" />
                                        
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-titleCode"><spring:theme code="address.title"/></label>
                        </div>
                        <div class="form_field-input">
                            <select id="payment-api-billing-address-titleCode" name="titleCode">
                                <option value=""><spring:theme code="address.title.pleaseSelect"/></option>
                                <c:forEach var="title" items="${titles}" varStatus="titleStatus">
                                    <option value="${title.code}" <c:if test="${title.code eq paymentContainer.paymentInfo.billingAddress.titleCode}">selected="selected"</c:if>>${title.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                                        
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-firstName"><spring:theme code="address.firstName"/></label>
                        </div>
                        <div class="form_field-input">
                            <input type="text" id="payment-api-billing-address-firstName" name="firstName" value="${paymentContainer.paymentInfo.billingAddress.firstName}" />
                        </div>
                         
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-lastName"><spring:theme code="address.surname"/></label>
                        </div>
                        <div class="form_field-input">
                            <input type="text" id="payment-api-billing-address-lastName" name="lastName" value="${paymentContainer.paymentInfo.billingAddress.lastName}" />
                        </div>
                         
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-line1"><spring:theme code="address.line1"/></label>
                        </div>
                        <div class="form_field-input">
                            <input type="text" id="payment-api-billing-address-line1" name="line1" value="${paymentContainer.paymentInfo.billingAddress.line1}" />
                        </div>
                         
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-line2"><spring:theme code="address.line2"/></label>
                        </div>
                        <div class="form_field-input">
                            <input type="text" id="payment-api-billing-address-line2" name="line2" value="${paymentContainer.paymentInfo.billingAddress.line2}" />
                        </div>
                         
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-town"><spring:theme code="address.townCity"/></label>
                        </div>
                        <div class="form_field-input">
                            <input type="text" id="payment-api-billing-address-town" name="town" value="${paymentContainer.paymentInfo.billingAddress.town}" />
                        </div>
                         
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-postalCode"><spring:theme code="address.postcode"/></label>
                        </div>
                        <div class="form_field-input">
                            <input type="text" id="payment-api-billing-address-postalCode" name="postalCode" value="${paymentContainer.paymentInfo.billingAddress.postalCode}" />
                        </div>
                         
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-countryIso"><spring:theme code="address.country"/></label>
                        </div>
                        <div class="form_field-input">                             
                            <select id="payment-api-billing-address-countryIso" name="countryIso">
                                <option value=""><spring:theme code='address.selectCountry'/></option>
                                <option value="DE" <c:if test="${'DE' eq paymentContainer.paymentInfo.billingAddress.country.isocode}">selected="selected"</c:if>>Deutschland</option>
                                <option value="AT" <c:if test="${'AT' eq paymentContainer.paymentInfo.billingAddress.country.isocode}">selected="selected"</c:if>>&Ouml;sterreich</option>
                                <option value="CH" <c:if test="${'CH' eq paymentContainer.paymentInfo.billingAddress.country.isocode}">selected="selected"</c:if>>Schweiz</option>
                            </select>
                            
                        </div>
                         
                        <div class="form_field-label">
                            <label for="payment-api-billing-address-phone"><spring:theme code="address.phone"/></label>
                        </div>
                        <div class="form_field-input">
                            <input type="text" id="payment-api-billing-address-phone" name="phone" value="${paymentContainer.paymentInfo.billingAddress.phone}" />
                        </div>

                        <div class="form_field-label">
                            <label for="payment-api-billing-address-mobile"><spring:theme code="address.mobile"/></label>
                        </div>
                        <div class="form_field-input">
                            <input type="text" id="payment-api-billing-address-mobile" name="mobile" value="${paymentContainer.paymentInfo.billingAddress.mobile}" />
                        </div>

                        <div class="form_field-label">
                            <label for="payment-api-billing-address-dateOfBirthDay">Geburtsdatum:</label>
                        </div>
                        <div class="form_field-input">
                            <c:set var="dateOfBirthDay" value="" />
                            <c:set var="dateOfBirthMonth" value="" />
                            <c:set var="dateOfBirthYear" value="" />
                            <c:if test="${not empty paymentContainer.paymentInfo.billingAddress.dateOfBirth}">
                                <c:set var="dateOfBirthDay" value="${paymentContainer.paymentInfo.billingAddress.dateOfBirth.date}" />
                                <c:set var="dateOfBirthMonth" value="${paymentContainer.paymentInfo.billingAddress.dateOfBirth.month + 1}" />
                                <c:set var="dateOfBirthYear" value="${paymentContainer.paymentInfo.billingAddress.dateOfBirth.year + 1900}" />
                            </c:if>
                            <select id="payment-api-billing-address-dateOfBirthDay" name="dateOfBirthDay">
                                <option value="">Tag</option>
                                <c:forEach var="day" begin="1" end="31">
                                    <option value="${day}" <c:if test="${day eq dateOfBirthDay}">selected="selected"</c:if>>${day}</option>
                                </c:forEach>
                            </select>
                            <select id="payment-api-billing-address-dateOfBirthMonth" name="dateOfBirthMonth">
                                <option value="">Monat</option>
                                <c:forEach var="month" begin="1" end="12">
                                    <option value="${month}" <c:if test="${month eq dateOfBirthMonth}">selected="selected"</c:if>>${month}</option>
                                </c:forEach>
                            </select>
                            <select id="payment-api-billing-address-dateOfBirthYear" name="dateOfBirthYear">
                                <option value="">Jahr</option>
                                <c:forEach var="year" begin="1900" end="2015">
                                    <option value="${year}" <c:if test="${year eq dateOfBirthYear}">selected="selected"</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                    </div>
                    
                </form>
                
                
                
                <%--
                    
                    NextPage form - submit button
                    
                 --%>
                        
                <form id="nextPageForm" action="${summaryPageUrl}">
                    
                    <div class="save_payment_details">
                    
                        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
                            <div class="form_field-input form_field-label">
                                <input type="checkbox" id="payment-api-saveInAccount" name="saveInAccount" value="true" />
                                <label for="payment-api-saveInAccount"><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.savePaymentDetailsInAccount"/></label>
                            </div>
                        </sec:authorize>
                        
                    </div>
                
                    <ycommerce:testId code="editPaymentMethod_savePaymentMethod_button">
                        <button class="form" type="submit" id="payment-api-submit-data">
                            <spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.useThesePaymentDetails"/>
                        </button>
                    </ycommerce:testId>
                    
                    <c:if test="${not hasNoPaymentInfo}">                       
                        <button class="form" type="submit" id="payment-api-cancel-data">
                            <spring:theme code="checkout.multi.cancel" text="Cancel"/>
                        </button>                        
                    </c:if>
                    
                </form>
                
                
                
            </div>
        </div>
    </div>
    
    
    
    <cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 last side-content-slot">
        <div class="item_container_holder">
          <cms:component component="${feature}"/>
        </div>
    </cms:pageSlot>
    
</template:page>
