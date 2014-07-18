$(document).ready(function(){

    /**
     * Javascript to be run once the DOM has been loaded.
     *
     * Used on one single page: Checkout - Payment
     *
     */

    $(".page-checkout-payment").each(function() {

        /**
         * Retrieve language strings.
         *
         */
        if (!window.jsLang) {
            jsLang = {};
        }
        if (!window.jsLang.home) {
            jsLang.home = {};
        }
        jsLang.home.dummy	= (jsLang.home.dummy	? jsLang.home.dummy	: 'Just a dummy item.');



        /**
         * Toggle payment method details
         *//*
         // Hide all
         $(".mod-payment-methods ul li.payment-method-details").hide();

         // Show on change
         $(".mod-payment-methods ul input[name='paymentMethod']").bind("change", function() {

         // Hide all payment detail sections
         $(".mod-payment-methods ul li.payment-method-details").slideUp();

         // Show current method's details section
         $(".mod-payment-methods ul li.payment-method-details.payment-method-" + $(this).val()).slideDown();
         });

         // Show initially selected one
         $(".mod-payment-methods ul input[name='paymentMethod']:checked").trigger("change");*/

        /**
         * Using PaymentExtension JavaScript API...
         *
         */
        (function(){

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
            allPaymentOptions.each(function() {
                if ($(this).prop('checked')) {
                    allPaymentOptions.attr('checked', false).trigger("updateLayout");
                    $(this).prop('checked', true);
                    $(this).parents("li.has-field-paymentMode").addClass("selected").next("li.payment-api-fields").show();
                }
            });

            /**
             * Payment details form animations
             *
             */
            $("#paymentDetailsForm").each(function() {

                // Shiny accordeon animation (hide form fields for unselected payment modes)
                $("input[name='paymentMode']").bind("change updateLayout", function() {
                    $("input[name='paymentMode']:not(:checked)").parents("li.has-field-paymentMode").removeClass("selected").next("li.payment-api-fields").slideUp();
                    $("input[name='paymentMode']:checked").parents("li.has-field-paymentMode").addClass("selected").next("li.payment-api-fields").slideDown();
                });

                // Special for some credit cards (Maestro): Show issue number and date.
                $("#payment-api-creditcard-methodCode").bind("change updateLayout", function() {
                    if ($(this).find("option:selected").attr("data-requires-issuer") == "true") {
                        $(this).closest("ul.input-list").find("li.issNo, li.issueDate").show();
                    } else {
                        $(this).closest("ul.input-list").find("li.issNo, li.issueDate").hide();
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
                removePaymentErrors();
                if (apiResult.getErrorType() == PaymentApi.ERROR_NO_PAYMENT_MODE_SELECTED) {
                    errorMsg = "<p>Bitte w&auml;hlen Sie eine Zahlmethode!</p>";
                } else if (apiResult.getErrorType() == PaymentApi.ERROR_NO_PAYMENT_METHOD_SELECTED) {
                    errorMsg = "<p>Bitte w&auml;hlen Sie eine Zahlmethode (z.B. Kreditkartentyp)!</p>";
                    fillErrorMessage('#payment-api-creditcard-methodCode', 'Bitte w&auml;hlen Sie eine Zahlmethode (z.B. Kreditkartentyp)!');
                }

                else if (apiResult.getErrors().length > 0) {
                    for (index in apiResult.getErrors()) {
                        errorMsg += "<p>" + apiResult.getErrors()[index].msg + "</p>";
                        setPaymentFieldErrors(apiResult.getErrors()[index]);
                    }
                }

                else {
                    errorMsg = "<p>Ein Fehler trat auf - bitte versuchen Sie es erneut!</p>";
                }

                $("#globalMessages").html('<div class="information_message negative"><span class="single"></span>' + errorMsg + '</div>');
                $("html, body").animate({scrollTop: 0}, 500);

            }

            function removePaymentErrors() {
                $('ul.input-list li').removeClass('has-msg has-msg-error')
            }

            function setPaymentFieldErrors(error) {
                if (error.code) {
                    var code = error.code + '';
                    code = code.substr(code.length - 3);
                    switch(code) {
                        case '115':
                            fillErrorMessage('#payment-api-creditcard-no', error.msg);
                            break;
                        case '116':
                            fillErrorMessage('#payment-api-creditcard-expMonth', error.msg);
                            break;
                        case '117':
                            fillErrorMessage('#payment-api-creditcard-cvv', error.msg);
                            break;
                        case '119':
                            fillErrorMessage('#payment-api-directdebit-accountCode', error.msg);
                            break;
                        case '120':
                            fillErrorMessage('#payment-api-directdebit-bankCode', error.msg);
                            break;
                        case '133':
                            fillErrorMessage('#payment-api-directdebit-bankCountry', error.msg);
                            break;
                        case '153':
                            fillErrorMessage('#payment-api-directdebit-bic', error.msg);
                            break;
                        case '152':
                            fillErrorMessage('#payment-api-directdebit-iban', error.msg);
                            break;
                        default:
                            console.log('Default');
                    }
                } else if (error.field) {
                    switch(error.field) {
                        case"accountOwner":
                            fillErrorMessage("#payment-api-directdebit-accountOwner", error.msg);
                            break;
                        case"name":
                            fillErrorMessage("#payment-api-creditcard-name", error.msg);
                            break;
                        case 'bankName':
                            fillErrorMessage('#payment-api-directdebit-bankName', error.msg);
                            break;
                        case 'iban':
                            fillErrorMessage('#payment-api-directdebit-iban', error.msg);
                            break;
                        case 'bic':
                            fillErrorMessage('#payment-api-directdebit-bic', error.msg);
                            break;
                        case 'bankCode':
                            fillErrorMessage('#payment-api-directdebit-bankCode', error.msg);
                            break;
                        case 'accountCode':
                            fillErrorMessage('#payment-api-directdebit-accountCode', error.msg);
                            break;
                        case 'bankCountry':
                            fillErrorMessage('#payment-api-directdebit-bankCountry', error.msg);
                            break;
                        default:
                            console.log('Default');
                    }
                }

            }
            function fillErrorMessage(id, message) {
                $(id).closest('li').addClass('has-msg has-msg-error').find('span.error').html(message);
            }

            /**
             * Store payment data:
             *
             *   - Catch billing address form submission event
             *   - Store existing paymentInfoId OR new payment information PCI-compliant via PaymentAPI JavaScript API
             *   - Submit address form || show error
             *
             */
            $("#nextPageForm #payment-api-submit-data").bind("click", function() {

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
                            $('#nextPageForm').submit();
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
                        methodCode = $('#payment-method-directdebit li.ui-tabs-active a').attr('data-directdebit');
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

                }

                // Call payment API - (paymentApi object is provided by javascript located at ${paymentContainer.jsApiUrl})
                showApiLoadingAnimation(true);
                paymentApi.savePaymentData(mode, methodCode, data, function(apiResult) {

                    showApiLoadingAnimation(false);

                    // Show errors or continue with saving billing address
                    if (apiResult.isError()) {
                        showApiErrorMessages(apiResult);
                    } else {
                        $('#nextPageForm').submit();
                    }

                });



                // Avoid form being submitted (waiting for above API callback)
                return false;

            });

        })();

    });

});