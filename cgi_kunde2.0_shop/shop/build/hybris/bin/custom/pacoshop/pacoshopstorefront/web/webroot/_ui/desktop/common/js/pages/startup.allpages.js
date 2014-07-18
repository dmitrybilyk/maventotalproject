$(document).ready(function(){

	/**
	 * Javascript to be run once the DOM has been loaded.
	 *
	 * Used on ALL pages.
	 *
	 */
	
	/*
	 * Communicate DOM completion event to CSS
	 */
	$("html").addClass("domReady");
	
	
	/*
	 * Check whether cookies enabled
	 */
	$.cookie('cookiesenabled', 'true');
	if ($.cookie('cookiesenabled') != 'true') {
		$("div.mod-client-feature-detection div.cookies-disabled-message").show();
	}
	

	/*
	 * Tabs
	 */
	$(".tabs").tabs();

	
	
	/*
	 * Tooltips
	 */
	$("ul.input-list .tooltip").each(function() {
		$(this).attr("title", $(this).html());
		$(this).html("");
	}).tooltip();
	
	
	
	/*
	 * Datepicker
	 */
    $("ul.input-list input[type='text'].date").datepicker({
    	dateFormat: "dd.mm.yy",
    	dayNames: [ "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag" ],
    	dayNamesMin: [ "So", "Mo", "Di", "Mi", "Do", "Fr", "Sa" ],
    	monthNames: [ "Januar", "Februar", "M&auml;rz", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember" ],
    	showWeek: true,
    	weekHeader: "KW",
        showOn: "both"
    	//changeMonth: true,
    	//changeYear: true,
    	//minDate: "1"
    });

    /*
    * Responsive main nav

    $('#responsive-menu-button').sidr({
        name: 'sidr-main',
        body: 'div.wrapper, #mobile-header',
        source: '.holder-meta-nav'
    });
     */

});