<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>

<c:if test="${displaymappings['PaymentInAdvanceFormElementGroup']}">
    <div class="information_message negative">
        <span class="single"></span>
        <p><spring:theme code="form.paymentMethods.prepayOnly.msg"/></p>
    </div>
</c:if>
