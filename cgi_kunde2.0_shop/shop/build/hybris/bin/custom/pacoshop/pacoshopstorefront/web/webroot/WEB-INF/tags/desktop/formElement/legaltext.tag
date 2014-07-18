<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="text"		required="false"	rtexprvalue="true" %>
<%@ attribute name="textKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="cssClass"	required="false"	rtexprvalue="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<%-- Optionally load texts from property files --%>
	<c:if test="${not empty textKey}">
		<spring:theme code="${textKey}" var="text" />	
	</c:if>


    <li class="has-legaltext ${cssClass}">
        <span class="legaltext">${text}</span>
    </li>