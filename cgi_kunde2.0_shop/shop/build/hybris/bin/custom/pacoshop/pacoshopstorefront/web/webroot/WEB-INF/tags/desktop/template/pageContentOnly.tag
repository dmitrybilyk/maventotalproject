<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<template:master pageTitle="${pageTitle}">

	<div class="wrapper">
	    
	    <div id="content">
	        <div class="container">
	            <jsp:doBody/>
	        </div>
	    </div>
	    
	</div>
	
</template:master>