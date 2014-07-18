<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="pathDay"			required="true"		rtexprvalue="true" %>
<%@ attribute name="pathMonth"			required="true"		rtexprvalue="true" %>
<%@ attribute name="pathYear"			required="true"		rtexprvalue="true" %>
<%@ attribute name="idDay"				required="true"		rtexprvalue="true" %>
<%@ attribute name="idMonth"				required="true"		rtexprvalue="true" %>
<%@ attribute name="idYear"				required="true"		rtexprvalue="true" %>
<%@ attribute name="label"			required="false"	rtexprvalue="true" %>
<%@ attribute name="labelKey"		required="false"	rtexprvalue="true" %>
<%@ attribute name="cssClass"		required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderDay"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderDayKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderMonth"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderMonthKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderYear"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderYearKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="msg"			required="false"	rtexprvalue="true" %>
<%@ attribute name="msgType"		required="false"	rtexprvalue="true" %>
<%@ attribute name="required"		required="true"		rtexprvalue="true" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<%-- Optionally load texts from property files --%>
	<c:if test="${not empty labelKey}">
		<spring:theme code="${labelKey}" var="label" />	
	</c:if>
	<c:if test="${not empty placeholderDayKey}">
		<spring:theme code="${placeholderDayKey}" var="placeholderDay" />
	</c:if>
    <c:if test="${not empty placeholderMonthKey}">
        <spring:theme code="${placeholderMonthKey}" var="placeholderMonth" />
    </c:if>
    <c:if test="${not empty placeholderYearKey}">
        <spring:theme code="${placeholderYearKey}" var="placeholderYear" />
    </c:if>

<spring:bind path="${pathDay}">
    <%-- Message type CSS classes --%>
    <c:set var="msgClassDay" value="" />
    <c:if test="${status.error}">
        <c:set var="msgClassDay" value=" has-msg has-msg-${status.error ? 'error' : ''}" />
    </c:if>
</spring:bind>

<spring:bind path="${pathMonth}">
    <c:set var="msgClassMonth" value="" />
    <c:if test="${status.error}">
        <c:set var="msgClassMonth" value=" has-msg has-msg-${status.error ? 'error' : ''}" />
    </c:if>
</spring:bind>

<spring:bind path="${pathYear}">
    <c:set var="msgClassYear" value="" />
    <c:if test="${status.error}">
        <c:set var="msgClassYear" value=" has-msg has-msg-${status.error ? 'error' : ''}" />
    </c:if>
</spring:bind>

<c:if test="${msgClassDay ne '' || msgClassMonth ne '' || msgClassYear ne '' }">
    <c:set var="msgClass" value=" has-msg has-msg-error" />
    <c:set var="msgType" value="error" />
</c:if>

	<%-- Required indicator CSS classes --%>
	<c:set var="requiredClass" value="" />
	<c:set var="requiredIndicator" value="" />
	<c:if test="${required}">
		<c:set var="requiredClass" value=" is-required" />
		<c:set var="requiredIndicator" value="*" />	
	</c:if>



    <li class="has-dateFields has-field-${pathDay} ${msgClass} ${requiredClass} ${cssClass}">
        <span class="label multiple multiple-3">
            <label for="${idDay}">${label}${requiredIndicator}</label>
        </span>
        <span class="field multiple multiple-3">
            <form:input maxlength="2" type="tel" pattern="[0-9]*" id="${idDay}" path="${pathDay}" placeholder="${placeholderDay}" cssClass="${cssClass}"/>
            <form:input maxlength="2" type="tel" pattern="[0-9]*" id="${idMonth}" path="${pathMonth}" placeholder="${placeholderMonth}" cssClass="${cssClass}"/>
            <form:input maxlength="4" type="tel" pattern="[0-9]*" id="${idYear}" path="${pathYear}" placeholder="${placeholderYear}" cssClass="${cssClass}"/>
        </span>
        <span class="msg ${msgType}">
            <span><form:errors path="${pathDay}"/><form:errors path="${pathMonth}"/><form:errors path="${pathYear}"/></span>
        </span>
    </li>
