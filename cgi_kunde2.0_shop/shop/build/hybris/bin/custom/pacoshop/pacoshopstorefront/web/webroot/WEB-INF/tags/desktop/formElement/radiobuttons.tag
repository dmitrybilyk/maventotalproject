<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="path"				required="true"		rtexprvalue="true" %>




<%@ attribute name="options"			required="true"		rtexprvalue="true" type="java.util.Collection" %>
<%@ attribute name="optionValueAttr" 	required="false"	rtexprvalue="true" %>
<%@ attribute name="optionLabelAttr" 	required="false"	rtexprvalue="true" %>
<%@ attribute name="optionTooltipAttr" 	required="false"	rtexprvalue="true" %>
<%@ attribute name="cssClass"			required="false"	rtexprvalue="true" %>
<%@ attribute name="msg"				required="false"	rtexprvalue="true" %>
<%@ attribute name="msgType"			required="false"	rtexprvalue="true" %>

<%@ attribute name="required"			required="true"		rtexprvalue="true" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



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
	<c:if test="${not empty optionTooltipAttr}">		
		<c:forEach var="option" items="${options}">
			<c:if test="${not empty option[optionTooltipAttr]}">
				<c:set var="tooltipClass" value=" has-tooltip" />
			</c:if>
		</c:forEach>
	</c:if>
	
	<%-- Required indicator CSS classes --%>
	<c:set var="requiredClass" value="" />
	<c:set var="requiredIndicator" value="" />
	<c:if test="${required}">
		<c:set var="requiredClass" value=" is-required" />
		<c:set var="requiredIndicator" value="*" />	
	</c:if>



    <li class="has-radio has-field-${path} ${msgClass} ${tooltipClass} ${requiredClass} ${cssClass}"> 
		<c:forEach var="option" items="${options}">
			<c:set var="id" value="${path} - ${option[optionValueAttr]}" />
		    <span class="field radio">
		    	<form:radiobutton id="${id}" path="${path}" value="${option[optionValueAttr]}" />
                <label for="${id}">${option[optionLabelAttr]}${requiredIndicator}</label>
                <span class="tooltip">
                	<c:if test="${not empty optionTooltipAttr}">
                		${option[optionTooltipAttr]}
                	</c:if>
                </span>
            </span>			
		</c:forEach>  
        <span class="msg ${msgType}">
            <span>${msg}</span>
        </span>
    </li>