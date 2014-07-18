$(document).ready(function(){

	/**
	 * Javascript to be run once the DOM has been loaded.
	 *
	 * Used on one single page: Checkout - Delivery
	 *
	 */
	
	$(".page-checkout-delivery").each(function() {

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
         * Switch subscription data fields by checkbox.
         *
         * @param $el jQuery object checkbox
         */
        function switchSubscriptionDate($el) {
            var $subscriptionDate = $("#deliveryStartDateStr"),
                $subscriptionDateButton = $(".has-field-deliveryStartDateStr .ui-datepicker-trigger");


            if (!$el.length) return;

            function switchLabels() {
                $subscriptionDate[!$el.prop("checked") ? 'removeAttr' : 'attr']('disabled', 'disabled');
            }

            $el.on('click', switchLabels);

            $subscriptionDateButton.on('click', function(){
                $el.prop("checked", false);
                $subscriptionDate.prop("disabled", false);
            });

            switchLabels();
        }

        switchSubscriptionDate($('.has-field-deliveryStart input[type="checkbox"]'));

        /**
         * Fill a new address
         */

         function fillNewAddress() {

            var addressForm = $('#new-address-form');
            var addressFields = {};
            addressForm.find('select, input[type="text"]').each(function(){
                var el = $(this);
                addressFields[el.attr('id')] = el;
            });


            $("div.mod-delivery-address div.address").each(function(){
                var address = $(this);
                var btn = address.find('button[name="use"]');
                var fields = {};

                address.find('span:not(.title-holder)').each(function(){
                    var el = $(this);
                    fields[el.attr('class')] = el.text();

                });

                btn.on('click', function() {
                    setFields(fields)

                    $('html, body').animate({
                        scrollTop: addressForm.offset().top
                    }, 500);

                    return false;
                });

            });

            function setFields(fields) {

                addressFields['gift-salutation'] ? addressFields['gift-salutation'].val(fields['salutation']) : '';
                addressFields['title'] ? addressFields['title'].val(fields['titleCode']) : '';
                addressFields['gift-prename'] ? addressFields['gift-prename'].val(fields['prename']) : '';
                addressFields['gift-surname'] ? addressFields['gift-surname'].val(fields['surname']) : '';
                addressFields['gift-email'] ? addressFields['gift-email'].val(fields['email']) : '';
                addressFields['gift-company'] ? addressFields['gift-company'].val(fields['company']) : '';
                addressFields['address'] ? addressFields['address'].val(fields['additionalStreet']) : '';
                addressFields['street'] ? addressFields['street'].val(fields['street']) : '';
                addressFields['gift-zip'] ? addressFields['gift-zip'].val(fields['zip']) : '';
                addressFields['gift-city'] ? addressFields['gift-city'].val(fields['city']) : '';
                addressFields['country'] ? addressFields['country'].val(fields['countryCode']) : '';
                addressFields['houseNumber'] ? addressFields['houseNumber'].val(fields['houseNumber']) : '';

            }
        }

        fillNewAddress();

    });

});