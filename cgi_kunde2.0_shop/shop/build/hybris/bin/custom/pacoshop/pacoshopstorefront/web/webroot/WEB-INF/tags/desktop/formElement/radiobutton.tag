<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="path"				required="true"		rtexprvalue="true" %>
<%@ attribute name="id"					required="true"		rtexprvalue="true" %>
<%@ attribute name="label"				required="false"	rtexprvalue="true" %>
<%@ attribute name="labelKey"			required="false"	rtexprvalue="true" %>
<%@ attribute name="value"				required="true"	    rtexprvalue="true" %>





<%@ attribute name="cssClass"			required="false"	rtexprvalue="true" %>
<%@ attribute name="msg"				required="false"	rtexprvalue="true" %>
<%@ attribute name="msgType"			required="false"	rtexprvalue="true" %>
<%@ attribute name="tooltip"			required="false"	rtexprvalue="true" %>
<%@ attribute name="tooltipKey"			required="false"	rtexprvalue="true" %>
<%@ attribute name="required"			required="true"		rtexprvalue="true" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<%-- Optionally load texts from property files --%>
	<c:if test="${not empty labelKey}">
		<spring:theme code="${labelKey}" var="label" />	
	</c:if>
	<c:if test="${not empty tooltipKey}">
		<spring:theme code="${tooltipKey}" var="tooltip" />	
	</c:if>

	<%-- Fallback for option value/label/tooltip object attributes --%>	
	<c:if test="${empty optionValueAttr}">
		<c:set var="optionValueAttr" value="code" />
	</c:if>
	<c:if test="${empty optionLabelAttr}">
		<c:set var="optionLabelAttr" value="name" />
	</c:if>
	<c:if test="${empty optionTooltipAttr}">
		<c:set var="optionTooltipAttr" value="" />
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
	
	<%-- Required indicator CSS classes --%>
	<c:set var="requiredClass" value="" />
	<c:set var="requiredIndicator" value="" />
	<c:if test="${required}">
		<c:set var="requiredClass" value=" is-required" />
		<c:set var="requiredIndicator" value="*" />	
	</c:if>



    <li class="has-radio has-field-${path} ${msgClass} ${tooltipClass} ${requiredClass} ${cssClass}"> 
	    <span class="field radio">
	    	<form:radiobutton id="${id}" path="${path}" value="${value}" />
			<label for="${id}">${label}${requiredIndicator}</label>
			<span class="tooltip">
				${tooltip}
			</span>
        </span>
        <span class="msg ${msgType}">
            <span>${msg}</span>
        </span>
    </li>