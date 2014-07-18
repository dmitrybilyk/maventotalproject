<%--

    PAYMENTEXTENSION

    CGI Payment Extension
    
    - displays billing address of given paymentInfo

 --%>

<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute   name="paymentInfo"    required="true"     type="com.cgi.hybris.payment.core.data.PaymentInfoData" %>
<%@ attribute   name="delimiter"      required="false"    type="java.lang.String" %>

<%@ taglib      prefix="c"            uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib      prefix="spring"       uri="http://www.springframework.org/tags" %>
<%@ taglib      prefix="fn"           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib      prefix="fmt"          uri="http://java.sun.com/jsp/jstl/fmt" %>



<%--
    Choose a delimiter
 --%>
 
<c:choose>
    <c:when test="${delimiter eq 'br' or delimiter eq '' or empty delimiter}">
        <c:set var="tagStart"   value="" />
        <c:set var="tagEnd"     value="<br />" />
    </c:when>
    <c:otherwise>
        <c:set var="tagStart"   value="<${delimiter}>" />
        <c:set var="tagEnd"     value="</${delimiter}>" />
    </c:otherwise>
</c:choose>



<%--
    Display billing address
 --%>
<c:if test="${not empty paymentInfo and not empty paymentInfo.billingAddress}">  

    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.title)}&nbsp;${fn:escapeXml(paymentInfo.billingAddress.firstName)}&nbsp;${fn:escapeXml(paymentInfo.billingAddress.lastName)}${tagEnd}
    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.line1)}${tagEnd}
    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.line2)}${tagEnd}
    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.town)}${tagEnd}
    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.region.name)}${tagEnd}
    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.postalCode)}${tagEnd}
    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.country.name)}${tagEnd}
    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.phone)}${tagEnd}
    ${tagStart}${fn:escapeXml(paymentInfo.billingAddress.mobile)}${tagEnd}
    <c:if test="${not empty paymentInfo.billingAddress.dateOfBirth}">
        ${tagStart}Geburtstag: <fmt:formatDate value="${paymentInfo.billingAddress.dateOfBirth}" pattern="dd.MM.yyyy"/>${tagEnd}
    </c:if>
         
</c:if>