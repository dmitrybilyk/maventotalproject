$(document).ready(function(){

	/**
	 * Javascript to be run once the DOM has been loaded.
	 *
	 * Used on one single page: Checkout - Summary
	 *
	 */
	
	$(".page-checkout-summary").each(function() {

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

        jQuery('#summary-address').slideBlock({
            linkSlide:  'span.responsive-switcher',
            slideBlock: 'div.slide-block',
            openClass: 'slide-active',
            mode:	'accordion', //'accordion' - accordion mode or false - slide-block
            childSlide:	'accordion-child'
        });

        /*
         * Tooltips
         */
        $("#summary-address .tooltip").each(function() {
            $(this).attr("title", $(this).html());
            $(this).html("");
        }).tooltip();

	});

});