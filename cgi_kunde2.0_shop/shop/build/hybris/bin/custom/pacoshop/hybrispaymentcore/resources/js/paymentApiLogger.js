/**
 * Payment API logger
 * 
 * @author daniel.filzhut
 * 
 */

function PaymentApiLogger() {}

PaymentApiLogger.enabled = true;



/**
 * Polyfill for missing console methods.
 * 
 */
if (!window.console) {
	window.console = {
		log: function(str) {}
	};
}
if (!window.console.debug) {
	window.console.debug = function(msg) {
		window.console.log(msg);
	}
}
if (!window.console.info) {
	window.console.info = function(msg) {
		window.console.log(msg);
	}
}
if (!window.console.warn) {
	window.console.warn = function(msg) {
		window.console.log(msg);
	}
}
if (!window.console.error) {
	window.console.error = function(msg) {
		window.console.log(msg);
	}
}



/**
 * Displays log message in console.
 * 
 * msg				Log message
 * codeHint			Code hint (source class / method)
 * 
 * returns			-
 */
PaymentApiLogger.debug = function(msg, codeHint) {
	if (PaymentApiLogger.enabled) {
		console.debug(codeHint + ":\t\t" + msg);
	}
}
PaymentApiLogger.info = function(msg, codeHint) {
	if (PaymentApiLogger.enabled) {
		console.info(codeHint + ":\t\t" + msg);
	}
}
PaymentApiLogger.warn = function(msg, codeHint) {
	if (PaymentApiLogger.enabled) {
		console.warn(codeHint + ":\t\t" + msg);
	}
}
PaymentApiLogger.error = function(msg, codeHint) {
	if (PaymentApiLogger.enabled) {
		console.error(codeHint + ":\t\t" + msg);
	}
}