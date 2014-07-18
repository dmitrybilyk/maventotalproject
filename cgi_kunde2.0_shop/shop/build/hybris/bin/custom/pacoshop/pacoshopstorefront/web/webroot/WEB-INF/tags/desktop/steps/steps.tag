<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="current" required="false" rtexprvalue="true" %>

<c:if test="${displaybreadcrumb}">
    <div class="mod-progress-bar">
        <div class="inner">
            <ul>
                <c:set var="counter" value="0"/>
                <c:forEach var="step" items="${steps}">
                        <li class="${step.stepName == currentstep.stepName ? 'current' : 'todo'}">
                            <c:set var="counter" value="${counter+1}"/>
                            <span class="count">${counter}</span>
                            <span class="name"><spring:theme code="checkout.steps.${step.stepName}" /></span>
                        </li>
                </c:forEach>
            </ul>

        </div>
    </div>
</c:if>