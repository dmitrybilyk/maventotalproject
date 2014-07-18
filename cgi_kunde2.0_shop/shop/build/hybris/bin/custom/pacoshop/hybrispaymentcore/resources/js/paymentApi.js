/**
 * PaymentAPI
 * 
 * @author daniel.filzhut
 * 
 */

function PaymentApi() {

	
	
	// API data
	var apiData;
	
	// APi callback method
	var apiCallbackFunction;
	
	// Define payment provider APIs
	var pspApis = {};

	if (typeof PaymentApiProviderWirecard === 'function') {
		pspApis["wirecard"] = new PaymentApiProviderWirecard();
	}
	if (typeof PaymentApiProviderIntercard === 'function') {
		pspApis["intercard"] = new PaymentApiProviderIntercard();
	}
	if (typeof PaymentApiProviderRatePAY === 'function') {
		pspApis["ratepay"] = new PaymentApiProviderRatePAY();
	}

	
	
	/**
	 * Public API method - selects existing payment info ("token payment").
	 * 
	 * paymentInfoId     	Existing payment info's code.
	 * callbackFunction		Method to be called once finished.
	 * 
	 * returns				-
	 * 
	 */
	this.choosePaymentInfo = function(paymentInfoId, callbackFunction) {
		
		PaymentApiLogger.debug("Received choosePaymentInfo().", "PaymentApi.choosePaymentInfo()");

		// Save given input data
		apiData = new PaymentApiData();
		apiData.setPaymentInfoId(paymentInfoId);

		// Save callback method
		apiCallbackFunction = callbackFunction;
		
		// Delegate to helper
		PaymentApiLogger.debug("Delegating to helper.", "PaymentApi.choosePaymentInfo()");
		PaymentApiHelper.postJson(paymentApiConfig.choosePaymentInfoUrl, {"id" : apiData.getPaymentInfoId()}, apiData, apiCallbackFunction);		
		
	};

	
	
	/**
	 * Public API method - removes an existing payment info ("token payment").
	 * 
	 * paymentInfoId     	Existing payment info's code.
	 * callbackFunction		Method to be called once finished.
	 * 
	 * returns				-
	 * 
	 */
	this.removePaymentInfo = function(paymentInfoId, callbackFunction) {
		
		PaymentApiLogger.debug("Received choosePaymentInfo().", "PaymentApi.choosePaymentInfo()");

		// Save given input data
		apiData = new PaymentApiData();
		apiData.setPaymentInfoId(paymentInfoId);

		// Save callback method
		apiCallbackFunction = callbackFunction;
		
		// Delegate to helper
		PaymentApiLogger.debug("Delegating to helper.", "PaymentApi.choosePaymentInfo()");
		PaymentApiHelper.postJson(paymentApiConfig.removePaymentInfoUrl, {"id" : apiData.getPaymentInfoId()}, apiData, apiCallbackFunction);		
		
	};

	
	
	/**
	 * Public API method - saves a given billing address.
	 * 
	 * data					JSON object with user-entered billing address.
	 * callbackFunction		Method to be called once finished.
	 * 
	 * returns				-
	 * 
	 */
	this.saveBillingAddress = function(billingAddressData, callbackFunction) {
		
		PaymentApiLogger.debug("Received saveBillingAddress().", "PaymentApi.saveBillingAddress()");

		// Save given input data
		apiData = new PaymentApiData();
		apiData.setRequest(billingAddressData);

		// Save callback method
		apiCallbackFunction = callbackFunction;
		
		// Check boolean values
		billingAddressData.newBillingAddress	= (billingAddressData.newBillingAddress	!= undefined 	&& billingAddressData.newBillingAddress != "false"	&& billingAddressData.newBillingAddress);
		billingAddressData.saveInAccount 		= (billingAddressData.saveInAccount		!= undefined 	&& billingAddressData.saveInAccount != "false"		&& billingAddressData.saveInAccount);
		
		// Delegate to helper
		PaymentApiLogger.debug("Delegating to helper.", "PaymentApi.saveBillingAddress()");
		PaymentApiHelper.postJson(paymentApiConfig.saveBillingAddressUrl, billingAddressData, apiData, apiCallbackFunction);		
		
	};

	
	
	/**
	 * Public API method - saves payment data and submits it to PSP (where possible).
	 * 
	 * paymentMode     		Requested payment mode.
	 * paymentMethodCode   	Requested payment method code.
	 * data					JSON object with user-entered payment data.
	 * callbackFunction		Method to be called once finished.
	 * 
	 * returns				-
	 * 
	 */
	this.savePaymentData = function(paymentMode, paymentMethodCode, data, callbackFunction) {
		
		PaymentApiLogger.debug("Received savePaymentData().", "PaymentApi.savePaymentData()");

		// Save given input data
		apiData = new PaymentApiData();
		apiData.setPaymentMode(paymentMode);
		apiData.setPaymentMethodCode(paymentMethodCode);
		apiData.setRequest(data);

		// Save callback method
		apiCallbackFunction = callbackFunction;
	
		// Payment mode provided?
		if (apiData.getPaymentMode() == undefined || apiData.getPaymentMode() == "") {
			return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_NO_PAYMENT_MODE_SELECTED,
					"No payment mode provided!", "PaymentApi.savePaymentData()");
		}
	
		// Payment mode exists?
		if (paymentApiConfig.modes[apiData.getPaymentMode()] == undefined) {
			return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_PAYMENT_MODE_INVALID,
					"Payment mode '" + apiData.getPaymentMode() + "' not defined!", "PaymentApi.savePaymentData()");
		}
        
		// In case no paymentMethodCode provided: Try to select the only existing one
		if (paymentMethodCode == undefined || paymentMethodCode == "") {
			
			var methodCount = 0;
			for (key in paymentApiConfig.modes[paymentMode].methods) {
				paymentMethodCode = paymentApiConfig.modes[paymentMode].methods[key].code;
				methodCount++;
			}
			
			// More than one paymentMethodCode existing? Throw error...
			if (methodCount > 1) {
				return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_NO_PAYMENT_METHOD_SELECTED,
						"Payment method code not provided for payment mode '" + apiData.getPaymentMode() + "' - and there is more than one payment method!", "PaymentApi.savePaymentData()");
			}
			
			apiData.setPaymentMethodCode(paymentMethodCode);
		}
		
		// Payment method code exists?
		if (paymentApiConfig.modes[apiData.getPaymentMode()].methods[apiData.getPaymentMethodCode()] == undefined) {
			return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_PAYMENT_METHOD_INVALID,
					"Payment method code '" + apiData.getPaymentMethodCode() + "' for payment mode '" + apiData.getPaymentMode() + "' not defined!", "PaymentApi.savePaymentData()");
		}

		// Derive payment method type from code
		apiData.setPaymentMethodType(paymentApiConfig.modes[paymentMode].methods[paymentMethodCode].type);
			
		// Payment method type found?
		if (apiData.getPaymentMethodType() == undefined) {
			return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_PAYMENT_METHOD_INVALID,
					"No payment payment method type defined!", "PaymentApi.savePaymentData()");
		}

        // Select PSP
		var psp = paymentApiConfig.modes[apiData.getPaymentMode()].methods[apiData.getPaymentMethodCode()].psp;
		
		// Return directly if seamless checkout not offered for this payment mode
		if (psp == "-") {
			
			// Set success
			apiData.setStatus(PaymentApi.STATUS_SUCCESS);

			// Persist payment data PCI-compliant on own server
			PaymentApiLogger.debug("Preparing to persist request.", "PaymentApi.savePaymentData()");
			PaymentApiHelper.persistNonPCIData(apiData, apiCallbackFunction);
			
			return;
		}

		// Existing PSP API?
		if (pspApis[psp] == undefined) {
			return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_PSP_NOT_FOUND,
					"PSP API for selected payment mode not found!", "PaymentApi.savePaymentData()");
		}		
		
		// Delegate to PSP API
		PaymentApiLogger.debug("Forwarding to '" + psp + "' API plugin.", "PaymentApi.savePaymentData()");
		pspApis[psp].savePaymentData(apiData, apiCallbackFunction);
		
	}
	
}

PaymentApi.STATUS_ERROR						= "error";
PaymentApi.STATUS_NOT_SENT					= "notSent";
PaymentApi.STATUS_SUCCESS					= "success";

PaymentApi.ERROR_NO_PAYMENT_MODE_SELECTED	= "noPaymentModeSelected";
PaymentApi.ERROR_NO_PAYMENT_METHOD_SELECTED	= "noPaymentMethodSelected";
PaymentApi.ERROR_PAYMENT_MODE_INVALID		= "paymentModeInvalid";
PaymentApi.ERROR_PAYMENT_METHOD_INVALID		= "paymentMethodInvalid";
PaymentApi.ERROR_PSP_NOT_FOUND				= "pspNotFound";
PaymentApi.ERROR_WRONG_CONFIGURATION		= "wrongConfiguration";
PaymentApi.ERROR_TIMEOUT					= "timeout";
PaymentApi.ERROR_PSP_ERROR					= "pspError";
PaymentApi.ERROR_SERVER_ERROR				= "serverError";
