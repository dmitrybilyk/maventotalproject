/**
 *  Hpay PSP API
 * 
 * @author daniel.filzhut
 * 
 */

function PaymentApiProviderHpay() {

	
	
	// API input data
	var apiData;
	
	// APi callback method
	var apiCallbackFunction;
	
	
	
	/**
	 * API method - submits data to PSP.
	 * 
	 * data					PaymentAPI data object.
	 * callbackFunction		Method to be called once finished.
	 * 
	 * returns				-
	 * 
	 */
	this.savePaymentData = function(data, callbackFunction) {
		
		PaymentApiLogger.debug("Received submit().", "PaymentApiProviderHpay.submit()");
		
		// Save given input data
		apiData = data;
		apiCallbackFunction = callbackFunction;
		
		PaymentApiLogger.info("Submitted to hpay API (" + apiData.getPaymentMode() + ")!", "PaymentApiProviderHpay.submitPaymentData()");
		
		// Set success state
		apiData.setStatus(PaymentApi.STATUS_SUCCESS);
		
		// Persist payment data PCI-compliant on hybris server
		PaymentApiLogger.debug("Preparing to persist non-PCI data on hybris server.", "PaymentApiProviderHpay.submit()");
		PaymentApiHelper.persistNonPCIData(apiData, apiCallbackFunction);
		
	}
	
}