/**
 * Wirecard PSP API
 * 
 * @author daniel.filzhut
 * 
 * Wirecard documentation at https://integration.wirecard.at/doku.php/start#wirecard_checkout_seamless.
 * Wirecard offers "seamless checkout" only for (https://integration.wirecard.at/doku.php/wcs:datastorage_init):
 * 
 *  - Credit Card									(IS  implemented)
 *  - Electronic Funds Transfer (DirectDebit)		(IS  implemented)
 *  - Paybox										(NOT implemented)
 *  - CLICK2PAY										(NOT implemented)
 *  - Giropay										(NOT implemented)
 * 
 */

function PaymentApiProviderWirecard() {

	
	
	// API data
	var apiData;
	
	// API callback method
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
		
		PaymentApiLogger.debug("Received submit().", "PaymentApiProviderWirecard.submit()");

		// Save given input data
		apiData = data;
		apiCallbackFunction = callbackFunction;

		// Wirecard datastore communication required?
		PaymentApiLogger.debug("Check whether datastore communication needed...", "PaymentApiProviderWirecard.submit()");
		
		if (getMappingTable("fromPSP") == undefined) {
			
			apiData.setStatus(PaymentApi.STATUS_SUCCESS);
			
			// Persist payment data PCI-compliant on hybris server
			PaymentApiLogger.debug("...NOT needed.", "PaymentApiProviderWirecard.submit()");
			PaymentApiLogger.debug("Preparing to persist non-PCI data on hybris server.", "PaymentApiProviderWirecard.handlePSPCallback()");
			PaymentApiHelper.persistNonPCIData(apiData, apiCallbackFunction);
			
		} else {
		
			// Load API JS
			PaymentApiLogger.debug("...IS needed.", "PaymentApiProviderWirecard.submit()");
			PaymentApiHelper.loadJsClass(paymentApiConfig.providers.wirecard.data.apiUrl, "WirecardCEE_DataStorage", apiData, sendDataToPSP, apiCallbackFunction);
			
		}

	}
	
	
	
	/**
	 * Sends payment data to opened PaymentServiceProvider API connection.
	 * 
	 * returns				-
	 * 
	 */
	function sendDataToPSP() {
		
		// Convert API input data to PSP specific data
		var pspData = PaymentApiHelper.mapValues(apiData.getRequest(), getMappingTable("toPSP"));
		
		// Initialize PSP data storage
		var dataStorage = new WirecardCEE_DataStorage();
		
		// Add data
		if (apiData.getPaymentMode() == "creditcard") {

			pspData["brand"] = apiData.getPaymentMethodType();
			PaymentApiLogger.info("Submitting creditcard data to wirecard API...", "PaymentApiProviderWirecard.sendDataToPSP()");
			dataStorage.storeCreditCardInformation(pspData, handlePSPCallback);
			
		} else if (apiData.getPaymentMode() == "directdebit" && apiData.getPaymentMethodCode() == "WC_ELV") {
		
			PaymentApiLogger.info("Submitting directdebit ELV data to wirecard API...", "PaymentApiProviderWirecard.sendDataToPSP()");
			dataStorage.storeElvInformation(pspData, handlePSPCallback);
			
		} else if (apiData.getPaymentMode() == "directdebit") {
		
			PaymentApiLogger.info("Submitting directdebit SEPA data to wirecard API...", "PaymentApiProviderWirecard.sendDataToPSP()");
			dataStorage.storeSepaDdInformation(pspData, handlePSPCallback);
			
		} else if (apiData.getPaymentMode() == "giropay") {
		
			PaymentApiLogger.info("Submitting giropay data to wirecard API...", "PaymentApiProviderWirecard.sendDataToPSP()");
			dataStorage.storeGiropayInformation(pspData, handlePSPCallback);
			
		} else if (apiData.getPaymentMode() == "paybox") {
		
			PaymentApiLogger.info("Submitting paybox data to wirecard API...", "PaymentApiProviderWirecard.sendDataToPSP()");
			dataStorage.storePayboxInformation(pspData, handlePSPCallback);
			
		} else if (apiData.getPaymentMode() == "click2pay") {
		
			PaymentApiLogger.info("Submitting click2pay data to wirecard API...", "PaymentApiProviderWirecard.sendDataToPSP()");
			dataStorage.storeClick2PayInformation(pspData, handlePSPCallback);
			
		} else {
		
			PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_WRONG_CONFIGURATION,
					"Payment mode not supported by wirecard API!", "PaymentApiProviderWirecard.sendDataToPSP()");
					
		}
		
	}
	
	
	
	/**
	 * Handles PSP callback and returns result data back to API user.
	 * 
	 * pspResponse			Response object from Wirecard JS API.
	 * 
	 * returns				-
	 * 
	 */
	function handlePSPCallback(pspResponse) {
		
		PaymentApiLogger.info("...received result from wirecard API.", "PaymentApiProviderWirecard.handlePSPCallback()");
		
		// Add PSP specific response to API data object
		apiData.setPlainResponse(pspResponse);
		
		// Success case
		if (pspResponse.getStatus() == 0) {

			// Set status
			apiData.setStatus(PaymentApi.STATUS_SUCCESS);
			
			// Add anonymized payment information
			apiData.setResponse(PaymentApiHelper.mapValues(pspResponse.getAnonymizedPaymentInformation(), getMappingTable("fromPSP")));
			
			// Persist payment data PCI-compliant on own server
			PaymentApiLogger.debug("Preparing to persist PSP reponse.", "PaymentApiProviderWirecard.handlePSPCallback()");
			PaymentApiHelper.persistNonPCIData(apiData, apiCallbackFunction);
			
		}
		
		// Error case
		else {
			
			// Add error messages
			var errors = pspResponse.getErrors();
			for (var i = 0; i < errors.length; i++) {
				apiData.addError({
						"code"		: errors[i].errorCode,
						"intMsg" 	: errors[i].message,
						"msg"		: errors[i].consumerMessage
				});
			}
			
			// Return error to API callback
			PaymentApiHelper.throwError(apiData, apiCallbackFunction, PaymentApi.ERROR_PSP_ERROR,
					"PSP returned an error!", "PaymentApiProviderWirecard.handlePSPCallback()");
			
		}

	}
	
	
	
	/**
	 * Returns requested mapping table 
	 * 
	 * mappingType			Mapping type that is requested ("toPSP" or "fromPSP")
	 * 
	 * returns				Mapping table.
	 * 
	 */
	function getMappingTable(mappingType) {
		if (PaymentApiProviderWirecard.mapping.modes[apiData.getPaymentMode()] == undefined) {
			return undefined;
		}
		if (PaymentApiProviderWirecard.mapping.modes[apiData.getPaymentMode()].methods == undefined) {
			return undefined;
		}
		if (PaymentApiProviderWirecard.mapping.modes[apiData.getPaymentMode()].methods[apiData.getPaymentMethodType()] == undefined) {
			return undefined;
		}
		return PaymentApiProviderWirecard.mapping.modes[apiData.getPaymentMode()].methods[apiData.getPaymentMethodType()][mappingType];
	}
	
}

PaymentApiProviderWirecard.mapping = {
	"modes" : {
		"creditcard" : {
			"methods" : {
				"visa" : {
					"toPSP" : {
						"name"			: "cardholdername",
						"cvv"			: "cardverifycode",
						"no"			: "pan",
						"expMonth"		: "expirationMonth",
						"expYear"		: "expirationYear"
					},
					"fromPSP" : {
						"cardholdername": "name",
						""				: "cvv",
						""				: "no",
						""				: "expMonth",
						""				: "expYear",
						"expiry"		: "expiry",
						"brand"			: "brand",
						"financialInstitution" : "financialInstitution",
						"maskedPan"		: "maskedNo"
						/*"anonymousPan"	: ""*/
					}
				},
				"mastercard" : {
					"toPSP" : {
						"name"			: "cardholdername",
						"cvv"			: "cardverifycode",
						"no"			: "pan",
						"expMonth"		: "expirationMonth",
						"expYear"		: "expirationYear"
					},
					"fromPSP" : {
						"cardholdername": "name",
						""				: "cvv",
						""				: "no",
						""				: "expMonth",
						""				: "expYear",
						"expiry"		: "expiry",
						"brand"			: "brand",
						"financialInstitution" : "financialInstitution",
						"maskedPan"		: "maskedNo"
						/*"anonymousPan"	: ""*/
					}
				},
				"amex" : {
					"toPSP" : {
						"name"			: "cardholdername",
						"cvv"			: "cardverifycode",
						"no"			: "pan",
						"expMonth"		: "expirationMonth",
						"expYear"		: "expirationYear"
					},
					"fromPSP" : {
						"cardholdername": "name",
						""				: "cvv",
						""				: "no",
						""				: "expMonth",
						""				: "expYear",
						"expiry"		: "expiry",
						"brand"			: "brand",
						"financialInstitution" : "financialInstitution",
						"maskedPan"		: "maskedNo"
						/*"anonymousPan"	: ""*/
					}
				},
				"diners" : {
					"toPSP" : {
						"name"			: "cardholdername",
						"cvv"			: "cardverifycode",
						"no"			: "pan",
						"expMonth"		: "expirationMonth",
						"expYear"		: "expirationYear"
					},
					"fromPSP" : {
						"cardholdername": "name",
						""				: "cvv",
						""				: "no",
						""				: "expMonth",
						""				: "expYear",
						"expiry"		: "expiry",
						"brand"			: "brand",
						"financialInstitution" : "financialInstitution",
						"maskedPan"		: "maskedNo"
						/*"anonymousPan"	: ""*/
					}
				},
				"discover" : {
					"toPSP" : {
						"name"			: "cardholdername",
						"cvv"			: "cardverifycode",
						"no"			: "pan",
						"expMonth"		: "expirationMonth",
						"expYear"		: "expirationYear"
					},
					"fromPSP" : {
						"cardholdername": "name",
						""				: "cvv",
						""				: "no",
						""				: "expMonth",
						""				: "expYear",
						"expiry"		: "expiry",
						"brand"			: "brand",
						"financialInstitution" : "financialInstitution",
						"maskedPan"		: "maskedNo"
						/*"anonymousPan"	: ""*/
					}
				},
				"jcb" : {
					"toPSP" : {
						"name"			: "cardholdername",
						"cvv"			: "cardverifycode",
						"no"			: "pan",
						"expMonth"		: "expirationMonth",
						"expYear"		: "expirationYear"
					},
					"fromPSP" : {
						"cardholdername": "name",
						""				: "cvv",
						""				: "no",
						""				: "expMonth",
						""				: "expYear",
						"expiry"		: "expiry",
						"brand"			: "brand",
						"financialInstitution" : "financialInstitution",
						"maskedPan"		: "maskedNo"
						/*"anonymousPan"	: ""*/
					}
				},
				"uatp" : {
					"toPSP" : {
						"name"			: "cardholdername",
						"cvv"			: "cardverifycode",
						"no"			: "pan",
						"expMonth"		: "expirationMonth",
						"expYear"		: "expirationYear"
					},
					"fromPSP" : {
						"cardholdername": "name",
						""				: "cvv",
						""				: "no",
						""				: "expMonth",
						""				: "expYear",						
						"expiry"		: "expiry",
						"brand"			: "brand",
						"financialInstitution" : "financialInstitution",
						"maskedPan"		: "maskedNo"
						/*"anonymousPan"	: ""*/
					}
				},
				"maestro" : {
					"toPSP" : {
						"name"			: "cardholdername",
						"cvv"			: "cardverifycode",
						"no"			: "pan",
						"expMonth"		: "expirationMonth",
						"expYear"		: "expirationYear",
						"issNo"			: "issueNumber",
						"issMonth"		: "issueMonth",
						"issYear"		: "issueYear"
					},
					"fromPSP" : {
						"cardholdername": "name",
						""				: "cvv",
						""				: "no",
						""				: "expMonth",
						""				: "expYear",
						""				: "issNo",
						""				: "issMonth",
						""				: "issYear",						
						"expiry"		: "expiry",
						"brand"			: "brand",
						"financialInstitution" : "financialInstitution",
						"maskedPan"		: "maskedNo"
						/*"anonymousPan"	: ""*/
					}
				}
			}
		},
		"directdebit" : {
            "methods" : {
                "directdebit" : {
        			"toPSP" : {
        		        "accountOwner"		: "accountOwner",
        		        "bankCountry"		: "bankCountry",
        				"bankName"			: "bankName",
        		        "bankCode"			: "bankNumber",
        		        "accountCode"		: "bankAccount",
        			},
        			"fromPSP" : {
        		        "accountOwner"		: "accountOwner",
        		        "bankCountry"		: "bankCountry",
        				"bankName"			: "bankName",
        		        "bankNumber"		: "bankCode",
        		        "bankAccount"		: "accountCode",
        			}
                },
	            "sepadirectdebit" : {
	    			"toPSP" : {
	    		        "accountOwner"		: "accountOwner",
	    				"bankName"			: "bankName",
	    		        "iban"				: "bankAccountIban",
	    		        "bic"				: "bankBic"
	    			},
	    			"fromPSP" : {
	    		        "accountOwner"		: "accountOwner",
	    				"bankName"			: "bankName",
	    			    "bankAccountIban"	: "iban",
	    			    "bankBic"			: "bic"
	    			}
	            }
            }
		},
		"paypal" : {
            "methods" : {
                "paypal" : {
                }
            }
		},
		"sofortueberweisung" : {
            "methods" : {
                "sofortueberweisung" : {
                }
            }
		},
		/*"quick" : {
            "methods" : {
                "quick" : {
                }
            }
		},*/
		/*"advance" : {
            "methods" : {
                "advance" : {
                }
            }
		},*/
		/*"invoice" : {
            "methods" : {
                "invoice" : {
                }
            }
		},*/
		"giropay" : {
            "methods" : {
                "giropay" : {
        			"toPSP" : {
        		        "accountOwner"		: "accountOwner",
        		        "bankCode"			: "bankNumber",
        		        "accountCode"		: "bankAccount"
        			},
        			"fromPSP" : {
        		        "accountOwner"		: "accountOwner",
        		        "bankNumber"		: "bankCode",
        		        "bankAccount"		: "accountCode"
        			}
                }
            }
		},
		"paybox" : {
            "methods" : {
                "paybox" : {
        			"toPSP" : {
        		        "accountCode"		: "payerPayboxNumber"
        			},
        			"fromPSP" : {
        		        "payerPayboxNumber"	: "accountCode"
        			}
                }
            }
		},
		"click2pay" : {
            "methods" : {
                "click2pay" : {
        			"toPSP" : {
        		        "username"			: "username",
        		        "accountCode"		: "pan"
        			},
        			"fromPSP" : {
        		        "username"			: "username",
        		        "pan"				: "accountCode"
        			}
                }
            }
		}
	}
}