$(document).ready(function(){

	/**
	 * Javascript to be run once the DOM has been loaded.
	 *
	 * Used on ALL pages, used for configuring various feature attributes.
	 *
	 */
	
	if (!window.jsConfig) {
		window.jsConfig = {};
	}
	
	/**
	 * Dummy: Dummy configuration.
	 * 
	 */
	jsConfig.dummyFeature = ( jsConfig.dummyFeature || {} );
	jsConfig.dummyFeature.feature1 = false;
	jsConfig.dummyFeature.dummyGroup = ( jsConfig.dummyFeature.dummyGroup || {} );
	jsConfig.dummyFeature.dummyGroup.feature2 = false;
	jsConfig.dummyFeature.dummyGroup.feature3 = true;
	
});