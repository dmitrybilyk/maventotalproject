<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="checkoutMulti" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>

<c:set var="isNewsletterProductType" value="${displaymappings['NewsletterFormElementGroup']}"/>
<c:choose>
    <c:when test="${isNewsletterProductType}">
        <checkoutMulti:summaryNewsletterCustomerInfo />
    </c:when>
    <c:otherwise>
        <checkoutMulti:summaryCustomerInfo />
    </c:otherwise>
</c:choose>
