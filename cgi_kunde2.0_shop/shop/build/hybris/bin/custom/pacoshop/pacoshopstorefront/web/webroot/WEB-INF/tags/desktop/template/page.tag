<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header" %>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/desktop/common/footer" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>

<template:master pageTitle="${pageTitle}">

	<jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss"/>
	</jsp:attribute>

	<jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts"/>
	</jsp:attribute>
	<jsp:body>
		<div id="wrapper">
			<div id="page">
				<spring:theme code="text.skipToContent" var="skipToContent"/>
				<a href="#skip-to-content" class="skiptocontent" data-role="none">${skipToContent}</a>
				<spring:theme code="text.skipToNavigation" var="skipToNavigation"/>
				<a href="#skiptonavigation" class="skiptonavigation" data-role="none">${skipToNavigation}</a>
				<header:header hideHeaderLinks="${hideHeaderLinks}"/>
				<a id="skiptonavigation"></a>
				<nav:topNavigation/>
				<cart:addToCart/>
				<div id="content">
				<a id="skip-to-content"></a>
					<jsp:doBody/>
				</div>
				<footer:footer/>
			</div>
		</div>
	</jsp:body>
	
</template:master>
