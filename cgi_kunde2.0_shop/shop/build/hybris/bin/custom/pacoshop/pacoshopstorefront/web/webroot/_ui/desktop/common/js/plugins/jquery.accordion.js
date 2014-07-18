/**
 * jQuery Accordion plugin
 */
(function($) {
    $.fn.reverse = [].reverse;

    $.fn.slideBlock = function(options){

        var options = $.extend({
            linkSlide: 'a.slide-block__link',
            slideBlock: 'div.slide-block__box',
            openClass: 'slide-block_active',
            durationSlide: 500,
            openComplete: false,
            closeComplete: false,
            mode:	false, //'accordion' - accordion mode or false - slide-block
            childSlide:	'accordion-child', //use only if mode: 'accordion'
        }, options);
        this.each(function() {
            var closest = $(this);
            if (options.mode === 'accordion') {
                var accordion = jQuery(this);
                var childSlide = accordion.find('> .' + options.childSlide, this);
                childSlide.each(function(){
                    var $this = jQuery(this);
                    if (!$this.is('.' + options.openClass)) {
                        $this.children(options.slideBlock).addClass('responsive-hide');
                    }
                });
                childSlide.each(function(){
                    var $this = jQuery(this);
                    var link = $(options.linkSlide, this).eq(0);
                    link.click(function(){
                        var that = $(this);
                        if (that.closest(childSlide).is('.'+options.openClass)) {
                            that.closest(childSlide).removeClass(options.openClass);
                            that.closest(childSlide).find(options.slideBlock).addClass('responsive-hide');
                        } else {
                            that.closest(accordion).find(childSlide).removeClass(options.openClass);
                            that.closest(accordion).find(childSlide).find(options.slideBlock).addClass('responsive-hide');

                            that.closest(childSlide).addClass(options.openClass);
                            that.closest(childSlide).find(options.slideBlock).removeClass('responsive-hide');

                            $('body,html').animate({scrollTop:that.offset().top},300);
                        }
                        return false;
                    });
                });
            }

        });

        return this;
    };

})(jQuery);