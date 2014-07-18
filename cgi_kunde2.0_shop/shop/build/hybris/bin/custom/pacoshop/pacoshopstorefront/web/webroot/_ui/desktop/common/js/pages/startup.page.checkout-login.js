$(document).ready(function(){

	/**
	 * Javascript to be run once the DOM has been loaded.
	 *
	 * Used on one single page: Checkout - Login
	 *
	 */
	
	$(".page-checkout-login").each(function() {

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
	    
	    
	});

});