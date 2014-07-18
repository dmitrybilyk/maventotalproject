/**
 * Payment API data object
 * 
 * @author daniel.filzhut
 * 
 */
 
function PaymentApiData() {



	this.status = PaymentApi.STATUS_NOT_SENT;	
	this.errorType;
	this.errors = [];
	
	this.paymentMode;
	
	this.paymentMethodType;
	this.paymentMethodCode;
	
	this.paymentInfoId;
	
	this.request = {};	
	this.response = {}
	this.plainResponse = {};
	
	
		
	this.getStatus = function() {
		return this.status;
	}
	
	this.setStatus = function(status) {
		if (status == PaymentApi.STATUS_SUCCESS || status == PaymentApi.STATUS_ERROR || status == undefined) {
			this.status = status;
		}
	}
	
	
	
	this.isSuccess = function() {
		return (this.status == PaymentApi.STATUS_SUCCESS);
	}
	
	this.isError = function() {
		return (this.status == PaymentApi.STATUS_ERROR);
	}
	
		
	
	this.getErrorType = function() {
		return this.errorType;
	}
	
	this.setErrorType = function(errorType) {
		if (errorType != "") {
			this.errorType = errorType;
		}
	}
	
	
		
	this.getErrors = function() {
		return this.errors;
	}
	
	this.setErrors = function(errors) {
		this.errors = errors;
	}
	
	this.addError = function(error) {
		this.errors.push(error);
	}
	
		
	
	this.getPaymentMode = function() {
		return this.paymentMode;
	}
	
	this.setPaymentMode = function(paymentMode) {
		if (paymentMode != "") {
			this.paymentMode = paymentMode;
		}
	}
	
		
	
	this.getPaymentMethodType = function() {
		return this.paymentMethodType;
	}
	
	this.setPaymentMethodType = function(paymentMethodType) {
		if (paymentMethodType != "") {
			this.paymentMethodType = paymentMethodType;
		}
	}
	
		
	
	this.getPaymentMethodCode = function() {
		return this.paymentMethodCode;
	}
	
	this.setPaymentMethodCode = function(paymentMethodCode) {
		if (paymentMethodCode != "") {
			this.paymentMethodCode = paymentMethodCode;
		}
	}
	
		
	
	this.getPaymentInfoId = function() {
		return this.paymentInfoId;
	}
	
	this.setPaymentInfoId = function(paymentInfoId) {
		if (paymentInfoId != "") {
			this.paymentInfoId = paymentInfoId;
		}
	}
	
		
	
	this.getRequest = function() {
		return this.request;
	}
	
	this.setRequest = function(request) {
		this.request = request;
	}
	
		
	
	this.getResponse = function() {
		return this.response;
	}
	
	this.setResponse = function(response) {
		this.response = response;
	}
	
	this.addResponseItem = function(key, value) {
		this.response[key] = value; 
	}
	
	
		
	this.getPlainResponse = function() {
		return this.plainResponse;
	}
	
	this.setPlainResponse = function(plainResponse) {
		this.plainResponse = plainResponse;
	}
	
}