<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>


<div class="mod-header">
	<div class="inner">

    	<div class="logo">
            <div class="mod-cms mod-cms-image">
                <div class="inner">
                    <img alt="" src="${themeResourcePath}/images/logo.png">
                    <img alt="" src="${themeResourcePath}/images/logo-print.png" class="logo-print">
                </div>
            </div>
	    </div>

        <div class="holder-meta-nav">
            <nav:topNavigation/>
        </div>

	</div>
</div>