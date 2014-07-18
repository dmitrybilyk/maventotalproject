<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="text"		required="false"	rtexprvalue="true" %>
<%@ attribute name="textKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="cssClass"	required="false"	rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<%-- Optionally load texts from property files --%>
	<c:if test="${not empty textKey}">
		<spring:theme code="${textKey}" var="text" />
	</c:if>
 
 
 
	<li class="has-infotext ${cssClass}">
	    <span class="infotext">${text}</span>
	</li>