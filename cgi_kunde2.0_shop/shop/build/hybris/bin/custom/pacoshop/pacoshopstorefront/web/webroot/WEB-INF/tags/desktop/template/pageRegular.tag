<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="pageIdentifier" required="false" rtexprvalue="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/header" %>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/desktop/footer" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>


<template:master pageTitle="${pageTitle}" pageIdentifier="${pageIdentifier}">

	<div class="wrapper">
	
	    <a href="#content" class="accessbility"><spring:theme code="template.pageRegular.skipToContent" /></a>
	    <a href="#navigation" class="accessbility"><spring:theme code="template.pageRegular.skipToNavigation"/></a>
	    
	    <header id="header">
	        <div class="container">
	            <header:header/>
	        </div>
	    </header>
	    
	    <div id="content">
	        <div class="container">
	            <jsp:doBody/>
	        </div>
	    </div>
	    
	    <footer id="footer">
	        <div class="container">
	            <footer:footer/>
	            <footer:responsiveSwitch/>
	        </div>
	    </footer>
	    
	</div>

</template:master>