<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="path"				required="true"		rtexprvalue="true" %>
<%@ attribute name="id"					required="true"		rtexprvalue="true" %>
<%@ attribute name="label"				required="false"	rtexprvalue="true" %>
<%@ attribute name="labelKey"			required="false"	rtexprvalue="true" %>

<%@ attribute name="selectMsg"			required="false"	rtexprvalue="true" %>
<%@ attribute name="selectMsgKey"		required="false"	rtexprvalue="true" %>
<%@ attribute name="options"			required="true"		rtexprvalue="true" type="java.util.Collection" %>
<%@ attribute name="optionValueAttr"  required="false" rtexprvalue="true" %>
<%@ attribute name="optionLabelAttr"  required="false" rtexprvalue="true" %>
<%@ attribute name="optionNoAttrs"  required="false" rtexprvalue="true" type="java.lang.Boolean" %>


<%@ attribute name="cssClass"			required="false"	rtexprvalue="true" %>
<%@ attribute name="msg"				required="false"	rtexprvalue="true" %>
<%@ attribute name="msgType"			required="false"	rtexprvalue="true" %>
<%@ attribute name="tooltip"			required="false"	rtexprvalue="true" %>
<%@ attribute name="tooltipKey"			required="false"	rtexprvalue="true" %>
<%@ attribute name="required"			required="true"		rtexprvalue="true" type="java.lang.Boolean" %>
<%@ attribute name="disabled"			required="false"	rtexprvalue="true" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<%-- Optionally load texts from property files --%>
	<c:if test="${not empty labelKey}">
		<spring:theme code="${labelKey}" var="label" />	
	</c:if>
	<c:if test="${not empty selectMsgKey}">
		<spring:theme code="${selectMsgKey}" var="selectMsg" />	
	</c:if>
	<c:if test="${not empty tooltipKey}">
		<spring:theme code="${tooltipKey}" var="tooltip" />	
	</c:if>
	
	<%-- Fallback for option value/label object attributes --%>
<c:if test="${!optionNoAttrs}">
    <c:if test="${empty optionValueAttr}">
        <c:set var="optionValueAttr" value="code" />
    </c:if>
    <c:if test="${empty optionLabelAttr}">
        <c:set var="optionLabelAttr" value="name" />
    </c:if>
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
		<c:set var="requiredIndicator" value="*"/>
	</c:if>
	
	
     
    <li class="has-select has-field-${path} ${msgClass} ${tooltipClass} ${requiredClass} ${cssClass}">
        <span class="label">
            <label for="${id}">${label}${requiredIndicator}</label>
        </span>
        <span class="field">
			<form:select id="${id}" path="${path}" disabled="${disabled}">
				<c:if test="${selectMsg ne ''}">
					<option value="">${selectMsg}</option>
				</c:if>
                <c:if test="${optionNoAttrs}">
				    <form:options items="${options}"/>
                </c:if>
                <c:if test="${!optionNoAttrs}">
                    <form:options items="${options}" itemValue="${optionValueAttr}" itemLabel="${optionLabelAttr}" />
                </c:if>
			</form:select>
            <c:if test="${disabled}">
                <form:hidden path="${path}"/>
            </c:if>
            <span class="tooltip">${tooltip}</span>
        </span>
        <span class="msg ${msgType}">
            <span><form:errors path="${path}"/></span>
        </span>
    </li>