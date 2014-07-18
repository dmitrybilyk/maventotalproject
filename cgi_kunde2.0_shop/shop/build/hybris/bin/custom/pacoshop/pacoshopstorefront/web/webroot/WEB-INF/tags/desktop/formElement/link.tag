<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="url"		required="true"		rtexprvalue="true" %>
<%@ attribute name="text"		required="false"	rtexprvalue="true" %>
<%@ attribute name="textKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="title"		required="false"	rtexprvalue="true" %>
<%@ attribute name="titleKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="cssClass"	required="false"	rtexprvalue="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<%-- Optionally load texts from property files --%>
	<c:if test="${not empty textKey}">
		<spring:theme code="${textKey}" var="text" />	
	</c:if>
	<c:if test="${not empty titleKey}">
		<spring:theme code="${titleKey}" var="title" />	
	</c:if>


    <li class="has-link ${cssClass}">
        <span class="link"><a href="${url}" title="${title}">${text}</a></span>
    </li>