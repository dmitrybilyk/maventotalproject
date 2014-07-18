<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="pathPrefix"			required="true"		rtexprvalue="true" %>
<%@ attribute name="pathNumber"			required="true"		rtexprvalue="true" %>
<%@ attribute name="idPrefix"				required="true"		rtexprvalue="true" %>
<%@ attribute name="idNumber"				required="true"		rtexprvalue="true" %>
<%@ attribute name="label"			required="false"	rtexprvalue="true" %>
<%@ attribute name="labelKey"		required="false"	rtexprvalue="true" %>
<%@ attribute name="cssClass"		required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderPrefix"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderPrefixKey"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderNumber"	required="false"	rtexprvalue="true" %>
<%@ attribute name="placeholderNumberKey"	required="false"	rtexprvalue="true" %>
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
<c:if test="${not empty placeholderPrefixKey}">
    <spring:theme code="${placeholderPrefixKey}" var="placeholderPrefix" />
</c:if>
<c:if test="${not empty placeholderNumberKey}">
    <spring:theme code="${placeholderNumberKey}" var="placeholderNumber" />
</c:if>

<spring:bind path="${pathPrefix}">
    <%-- Message type CSS classes --%>
    <c:set var="msgClassPrefix" value="" />
    <c:if test="${status.error}">
        <c:set var="msgClassPrefix" value=" has-msg has-msg-${status.error ? 'error' : ''}" />
    </c:if>
</spring:bind>

<spring:bind path="${pathNumber}">
    <c:set var="msgClassNumber" value="" />
    <c:if test="${status.error}">
        <c:set var="msgClassNumber" value=" has-msg has-msg-${status.error ? 'error' : ''}" />
    </c:if>
</spring:bind>

<c:if test="${msgClassPrefix ne '' || msgClassNumber ne '' }">
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



<li class="has-dateFields has-field-${pathPrefix} ${msgClass} ${requiredClass} ${cssClass}">
        <span class="label multiple multiple-2">
            <label for="${idPrefix}">${label}${requiredIndicator}</label>
        </span>
        <span class="field multiple multiple-2-long">
            <form:input maxlength="5" type="tel" id="${idPrefix}" path="${pathPrefix}" placeholder="${placeholderPrefix}" cssClass="${cssClass}"/>
            <form:input maxlength="8" type="tel" id="${idNumber}" path="${pathNumber}" placeholder="${placeholderNumber}" cssClass="${cssClass}"/>
        </span>
        <span class="msg ${msgType}">
            <span><form:errors path="${pathPrefix}"/><form:errors path="${pathNumber}"/></span>
        </span>
</li>
