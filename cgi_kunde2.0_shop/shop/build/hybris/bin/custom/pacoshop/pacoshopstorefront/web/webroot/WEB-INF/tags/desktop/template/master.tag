<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="pageIdentifier" required="false" rtexprvalue="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="debug" tagdir="/WEB-INF/tags/desktop/debug" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html lang="${currentLanguage.isocode}" class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html lang="${currentLanguage.isocode}" class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html lang="${currentLanguage.isocode}" class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="${currentLanguage.isocode}" class="no-js"> <!--<![endif]-->
    <head>

        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        
        <title>${not empty pageTitle ? pageTitle : not empty cmsPage.title ? cmsPage.title : ''}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />

		<c:forEach var="metatag" items="${metatags}">
			<c:if test="${not empty metatag.content}" >
				<meta name="${metatag.name}" content="${metatag.content}" />
			</c:if>
		</c:forEach>

		<template:styles/>
		<template:scriptsHead/>
        
        <link rel="shortcut icon" href="/_ui/desktop/common/images/favicon.ico" type="image/x-icon"/>
        
    </head>
	<body class="page-${pageIdentifier} language-${currentLanguage.isocode}">
	    <jsp:doBody/>
	    
		<template:scriptsBody/>
		
		<debug:debugFooter/>
	    
	</body>
</html>