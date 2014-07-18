<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="path"			required="true"		rtexprvalue="true" %>
<%@ attribute name="id"				required="true"		rtexprvalue="true" %>
<%@ attribute name="label"			required="false"	rtexprvalue="true" %>
<%@ attribute name="labelKey"		required="false"	rtexprvalue="true" %>
<%@ attribute name="cssClass"		required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholder"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="msg"			required="false"	rtexprvalue="true" %>
<%@ attribute name="msgType"		required="false"	rtexprvalue="true" %>
<%@ attribute name="tooltip"		required="false"	rtexprvalue="true" %>
<%@ attribute name="tooltipKey"		required="false"	rtexprvalue="true" %>
<%@ attribute name="required"		required="true"		rtexprvalue="true" type="java.lang.Boolean" %>
<%@ attribute name="pattern"		required="false"	rtexprvalue="true" %>
<%@ attribute name="maxlength"		required="false"	rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<%-- Optionally load texts from property files --%>
	<c:if test="${not empty labelKey}">
		<spring:theme code="${labelKey}" var="label" />	
	</c:if>
	<c:if test="${not empty placeholderKey}">
		<spring:theme code="${placeholderKey}" var="placeholder" />	
	</c:if>
	<c:if test="${not empty tooltipKey}">
		<spring:theme code="${tooltipKey}" var="tooltip" />	
	</c:if>

<spring:bind path="${path}">
	<%-- Message type CSS classes --%>
	<c:set var="msgClass" value="" />
    <c:set var="msgType" value="" />
	<c:if test="${status.error}">
		<c:set var="msgClass" value=" has-msg has-msg-${status.error ? 'error' : ''}" />
        <c:set var="msgType" value="${status.error ? 'error' : ''}" />
	</c:if>
</spring:bind>
	
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


    <li class="has-text has-field-${path} ${msgClass} ${tooltipClass} ${requiredClass} ${cssClass}">
        <span class="label">
            <label for="${id}">${label}${requiredIndicator}</label>
        </span>
        <span class="field">
            <form:input id="${id}" path="${path}"  placeholder="${placeholder}" autocapitalize="off" pattern="${pattern}" maxlength="${maxlength}" type="tel" />
            <span class="tooltip">${tooltip}</span>
        </span>
        <span class="msg ${msgType}">
            <span><form:errors path="${path}"/></span>
        </span>
    </li>
