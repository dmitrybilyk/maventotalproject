<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="name"				required="true"		rtexprvalue="true" %>
<%@ attribute name="id"					required="true"		rtexprvalue="true" %>
<%@ attribute name="type"				required="true"		rtexprvalue="true" %>
<%@ attribute name="text"				required="false"	rtexprvalue="true" %>
<%@ attribute name="textKey"			required="false"	rtexprvalue="true" %>





<%@ attribute name="cssClass"			required="false"	rtexprvalue="true" %>
<%@ attribute name="msg"				required="false"	rtexprvalue="true" %>
<%@ attribute name="msgType"			required="false"	rtexprvalue="true" %>
<%@ attribute name="tooltip"			required="false"	rtexprvalue="true" %>
<%@ attribute name="tooltipKey"			required="false"	rtexprvalue="true" %>
<%@ attribute name="label"				required="false"	rtexprvalue="true" %>
<%@ attribute name="labelKey"			required="false"	rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<%-- Optionally load texts from property files --%>
	<c:if test="${not empty textKey}">
		<spring:theme code="${textKey}" var="text" />	
	</c:if>
	<c:if test="${not empty tooltipKey}">
		<spring:theme code="${tooltipKey}" var="tooltip" />	
	</c:if>
	<c:if test="${not empty labelKey}">
		<spring:theme code="${labelKey}" var="label" />	
	</c:if>
		
	<%-- Message type CSS classes --%>
	<c:set var="msgClass" value="" />
	<c:if test="${msg ne '' && msgType ne ''}">
		<c:set var="msgClass" value=" has-msg has-msg-${msgType}" />	
	</c:if>
	
	<%-- Tooltip CSS classes --%>
	<c:set var="tooltipClass" value="" />
	<c:if test="${tooltip ne ''}">
		<c:set var="tooltipClass" value=" has-tooltip" />
	</c:if>
     
     
     
    <li class="has-button has-field-${path} ${msgClass} ${tooltipClass} ${cssClass}">
        <span class="label">
            ${label}
        </span>
        <span class="button">
        	<button type="${type}" name="${name}" id="${id}" class="btn btn-${type} btn-${cssClass} ">${text}</button>
			<span class="tooltip">${tooltip}</span>
        </span>
        <span class="msg ${msgType}">
            <span>${msg}</span>
        </span>
    </li>