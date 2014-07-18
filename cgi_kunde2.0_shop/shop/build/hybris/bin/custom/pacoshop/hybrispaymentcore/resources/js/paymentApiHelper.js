/**
 * Payment API helper
 * 
 * @author daniel.filzhut
 * 
 */

function PaymentApiHelper() {}



/**
 * Loads an external javascript file and waits until a specific class is available in global context.
 * Executes callback method then.
 * 
 * Important: Will load file only ONCE.
 * 
 * scriptUrl				URL to be loaded via SCRIPT tag
 * className				JavaScript class, which availability triggers execution of 'successCallbackFunction'
 * apiData					PaymentAPI data object.
 * successCallbackFunction	Function to be called once file is loaded and 'className' is available
 * errorCallbackFunction	Function to receive error in case of timeout
 * timeout					Milliseconds, after which timeout happens
 * 
 * returns					-
 * 
 */
PaymentApiHelper.loadJsClass = function(scriptUrl, className, apiData, successCallbackFunction, errorCallbackFunction, timeout) {
	

	PaymentApiLogger.debug("Received request to load '" + scriptUrl +"'." , "PaymentApiHelper.loadJsClass");
	
	// Fallback timeout value in milliseconds
	if (!timeout) {
		timeout = 10000;
	}
	
	// File reference needs to be added only ONCE
	if (document.getElementById(scriptUrl) == undefined) {
		
		var scriptTag = document.createElement("script");
		scriptTag.src = scriptUrl;
		scriptTag.id = scriptUrl;
		
		document.body.appendChild(scriptTag);
		
		PaymentApiLogger.debug("Added SCRIPT tag.", "PaymentApiHelper.loadJsClass");
		
	} else {
		
		PaymentApiLogger.debug("SCRIPT tag already exists.", "PaymentApiHelper.loadJsClass");	
		
	}

	// Wait until file is loaded and requested object exists in global context
	var cycle = 100;
	var count = 0;
	function waitUntilLoaded() {
		
		setTimeout(function() {
			
			count = count + cycle;
			
			// Timeout handling
			if (count >= timeout) {
				PaymentApiHelper.throwError(apiData, errorCallbackFunction, PaymentApi.ERROR_TIMEOUT,
						"TIMEOUT!", "PaymentApiHelper.loadJsClass");
			}
			
			// Waiting is over!
			else if (window[className] !== undefined) {
				PaymentApiLogger.debug("Class '" + className + "' exists (took " + count + " ms), executing callback.", "PaymentApiHelper.loadJsClass");
				successCallbackFunction();
			}
			
			// Object not there yet - keep on waiting...
			else {
				waitUntilLoaded();
			}
			
		}, cycle);
		
	}
	waitUntilLoaded();
		
}



/**
 * Maps values from an input object to an output object, using a given mappingTable.
 * 
 * input			Object with input values.
 * mappingTable		Map to define which input value is mapped to which output value.
 * 
 * returns			Converted output object.
 * 
 */
PaymentApiHelper.mapValues = function(input, mappingTable) {
	
	var output = {};
	
	for (var key in mappingTable) {
		output[mappingTable[key]] = input[key];
	}
	
	return output;
	
}



/**
 * Returns an error object to given callback method and logs a warning on browser console.
 * 
 * apiData				PaymentAPI data object.
 * apiCallbackFunction	Callback function.
 * errorType			PaymentApi.ERROR_* error constant.
 * warnMsg				Warning message for the console.
 * warnCodeHint			Warning code hint for the console.
 * 
 * returns			-
 * 
 */
PaymentApiHelper.throwError = function(apiData, apiCallbackFunction, errorType, warnMsg, warnCodeHint) {
	
	if (warnMsg != undefined) {
		PaymentApiLogger.warn(warnMsg, warnCodeHint);
	}
	
	apiData.setStatus(PaymentApi.STATUS_ERROR);
	
	apiData.setErrorType(errorType);
	
	apiCallbackFunction(apiData);
	
}



/**
 * Post given JSON data to server
 * 
 * url					URL to be used
 * jsonData				JSON data to be posted
 * apiData				PaymentAPI result object
 * apiCallbackFunction	Method to be called once finished.
 * 
 * returns				-
 * 
 */
PaymentApiHelper.postJson = function(url, jsonData, apiData, apiCallbackFunction) {
	
	PaymentApiLogger.info("Posting data to hybris server...", "PaymentApiHelper.postJson()");
	
	var xmlHttp = null;
	try {		
		// Firefox, Opera, Safari, IE7+
	    xmlHttp = new XMLHttpRequest();	    
	} catch(e) {		
	    try {	    	
	        // IE6
	        xmlHttp  = new ActiveXObject("Microsoft.XMLHTTP");	        
	    } catch(e) {	    	
	        try {	        	
	            // IE5
	            xmlHttp  = new ActiveXObject("Msxml2.XMLHTTP");	            
	        } catch(e) {	        	
	            xmlHttp  = null;
	            return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_SERVER_ERROR,
						"XMLHttpRequest object could not be initialized", "PaymentApiHelper.postJson()");
	        }	        
	    }	    
	}	
	
	try {
		
		// Add hybris CSFR token
		if (url.indexOf("CSRFToken") == -1 && window.ACC != undefined && window.ACC.config != undefined && window.ACC.config.CSRFToken != undefined ) {
			if (url.indexOf("?") == -1) {
				url = url + "?";
			}		
			url = url + "&CSRFToken=" + window.ACC.config.CSRFToken;	
		}
		
		// Open AJAX connection
	    xmlHttp.open('POST', url, true);
	    
	    // Set content header to JSON
	    xmlHttp.setRequestHeader('Content-type','application/json; charset=utf-8');
	    
	    xmlHttp.onreadystatechange = function() {
	    	
	    	// State changes to DONE
	        if (xmlHttp.readyState == 4) {
	        	
	        	// Connection seemed to work
	        	if (xmlHttp.status == 200) {
	        		
	        		try {
	        			
	        			var resultObj = JSON.parse(xmlHttp.responseText);
	        			
	        			// Server returned SUCCESS
	        			if (resultObj.status == 'success') {        				
	        				PaymentApiLogger.info("...server answered with 'success'.", "PaymentApiHelper.postJson()");
	        				apiData.setStatus(PaymentApi.STATUS_SUCCESS);
	        				return apiCallbackFunction(apiData);
	        			}
	        			
	        			// Server returned ERROR
	        			else {
	        				
	        				// Pass generic error message to API user
	        				if (resultObj.errorMessage != undefined) {
	        					apiData.addError({
	        						"msg"		: resultObj.errorMessage,
	        						"msgInt"	: undefined,
	        						"code"		: undefined,
	        						"field"		: undefined
	        					});
	        				}
	        				
	        				// Pass field specific error messages to API user
	        				if (resultObj.messages != undefined) {
		        				for (var fieldName in resultObj.messages) {
		        					apiData.addError({
		        						"msg"		: resultObj.messages[fieldName],
		        						"msgInt"	: undefined,
		        						"code"		: undefined,
		        						"field"		: fieldName
		        					});
		        				}
	        				}
	        				
	        				return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_SERVER_ERROR,
	        						"...server DID NOT answer with 'success'.", "PaymentApiHelper.postJson()");        				
	        			}
	        			
	        		} catch (e) {        			
	        			return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_SERVER_ERROR,
	    						"...error parsing server response JSON." + e, "PaymentApiHelper.postJson()");        			
	        		}
	        		
	        	}
	        	
	        	// Server error
	        	else {        		
	        		return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_SERVER_ERROR,
							"...server DIT NOT answer with 200 code.", "PaymentApiHelper.postJson()");        		
	        	}
	        	
	        }
	        
	    };
	    
	    // Server timeout handling
	    xmlHttp.ontimeout = function() {
	    	
	    	return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_SERVER_ERROR,
					"...server timeout occured.", "PaymentApiHelper.postJson()");
	    	
	    };
	    
	    // Send AJAX request
	    xmlHttp.send(JSON.stringify(jsonData));
    
	} catch(e) {
    	
    	return PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_SERVER_ERROR,
				"... an error occured: " + e, "PaymentApiHelper.postJson()");
    	
	}
	
}



/**
 * Sends PCI-compliant payment information to hybris server (to be persisted)
 * 
 * apiData				PaymentAPI result object
 * apiCallbackFunction	Method to be called once finished.
 * 
 * returns				-
 * 
 */
PaymentApiHelper.persistNonPCIData = function(apiData, apiCallbackFunction) {	
	
	// Only persist SUCCESSFUL payment requests
	if (!apiData.isSuccess()) {
		apiCallbackFunction(apiData);
		return;
	}
	
	var savePaymentData = {
		"paymentMethodCode"	: apiData.getPaymentMethodCode(),
		"request"			: apiData.getRequest(),
		"response"			: apiData.getResponse()
	};
	
	// IMPORTANT for CreditCard: Replace PCI-data with data returned from PSP
	if (apiData.getPaymentMode() == "creditcard") {
		savePaymentData.request.cvv	= "***";
		savePaymentData.request.no	= savePaymentData.response.maskedNo;
	}
	
	PaymentApiLogger.info("Persisting non-PCI data on hybris server...", "PaymentApiHelper.persistNonPCIData()");	
	PaymentApiHelper.postJson(paymentApiConfig.savePaymentDataUrl, savePaymentData, apiData, apiCallbackFunction);
	
}