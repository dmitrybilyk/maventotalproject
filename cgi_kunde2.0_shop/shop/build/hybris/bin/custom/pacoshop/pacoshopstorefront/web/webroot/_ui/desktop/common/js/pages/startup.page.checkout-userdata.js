$(document).ready(function(){

	/**
	 * Javascript to be run once the DOM has been loaded.
	 *
	 * Used on one single page: Checkout - Userdata
	 *
	 */
	
	$(".page-checkout-userdata").each(function() {

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
         * Switch user data fields by checkbox.
         *
         * @param $el jQuery object checkbox
         */
        function switchContactData($el) {
            var $userData = $(".mod-useraddress, .mod-userdata, .mod-usercompany");

            if (!$el.length) return;

            function switchLabels() {
                $userData.toggle($el.prop("checked"));
            }

            $el.on('click', switchLabels);

            switchLabels();
        }

        switchContactData($('.has-field-sendInvoice input[type="checkbox"]'));

	});

});