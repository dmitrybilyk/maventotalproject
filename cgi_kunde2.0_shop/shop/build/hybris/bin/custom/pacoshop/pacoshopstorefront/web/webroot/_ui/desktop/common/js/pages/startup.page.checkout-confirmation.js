$(document).ready(function(){

	/**
	 * Javascript to be run once the DOM has been loaded.
	 *
	 * Used on one single page: Checkout - Confirmation
	 *
	 */
	
	$(".page-checkout-confirmation").each(function() {

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

        //hide bnt print for mobile devices
        if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
            $('#btn-print, #print-label').hide();
        }
	    // printing order
        $('#btn-print').on('click', function(){

            window.print();

            return false;
        });

        jQuery('#summary-address').slideBlock({
            linkSlide:  'span.responsive-switcher',
            slideBlock: 'div.slide-block',
            openClass: 'slide-active',
            mode:	'accordion', //'accordion' - accordion mode or false - slide-block
            childSlide:	'accordion-child'
        });

        function thankyouLigntbox() {
            var simplemodalOverlay = jQuery('<div id="simplemodal-overlay" />').hide(),
                lightbox = jQuery('.mod-lightbox-thankyou-registration').hide(),
                win = jQuery(window),
                doc = jQuery(document),
                linkClose = $('.link-close');

            jQuery('body').append(simplemodalOverlay);
            jQuery('body').append(lightbox);

            simplemodalOverlay.css({
                position: 'fixed',
                height: doc.height(),
                width: doc.width()
            }).show();

            lightbox.css({
                display: 'block',
                position: 'fixed'
            });
            if (doc.width() < 800) {
                lightbox.css({
                    width: doc.width() - 20
                });
            } else {
                lightbox.css({
                    width: 800
                });
            }
            lightbox.css({
                marginLeft: - lightbox.width() / 2,
                marginTop: - lightbox.height() / 2,
                top: '50%',
                left: '50%'
            });

            linkClose.on('click', function(){
                lightbox.hide();
                simplemodalOverlay.hide();
                return false;
            });

            win.on('resize', function () {
                if(doc.width() < 800) {
                    lightbox.css({
                        width: doc.width() - 20
                    });
                    lightbox.css({
                        marginLeft: - lightbox.width() / 2,
                        marginTop: - lightbox.height() / 2
                    });
                }
                simplemodalOverlay.css({
                    height: doc.height(),
                    width: doc.width()
                });
            });
        }

        if ($('#configurableDelay').val()) {
            setTimeout(thankyouLigntbox, $('#configurableDelay').val());
        }

	});

});