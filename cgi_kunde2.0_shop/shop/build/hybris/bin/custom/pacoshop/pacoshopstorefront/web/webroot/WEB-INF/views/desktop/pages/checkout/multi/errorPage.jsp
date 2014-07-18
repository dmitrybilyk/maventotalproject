<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="checkoutMulti" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="steps" tagdir="/WEB-INF/tags/desktop/steps" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<template:pageRegular pageTitle="${pageTitle}" pageIdentifier="checkout-error">
    <div class="content-main">
        <div class="content-heading">
            <spring:theme code="checkout.multi.errorPage.heading" var="textPageTitle"/>
            <common:heading
                    heading="${textPageTitle}"/>
        </div>

        <div id="globalMessages">
            <common:globalMessages/>
        </div>

        <div class="content-form">
            <spring:message code="checkout.multi.errorPage.error.label" arguments="${not empty httpStatus? httpStatus: 400}" htmlEscape="false"/>
            <br>
            <spring:theme code="${familyExceptionMessage}"/>
            <br>
            <spring:theme code="${exceptionMessage}"/>
            <spring:eval var="showStackTrace"
                         expression="@configurationService.configuration.getProperty('errorpage.showStackTrace')"/>
            <c:if test="${showStackTrace}">
            <br><br>
            ===============================================================================================================
            <br>
            <c:forEach var="nextedExceptionEntry" items="${exception.nestedExceptions}">
                <spring:message code="checkout.multi.errorPage.product.label" arguments="${nextedExceptionEntry.key}" htmlEscape="false"/><br>
                <c:forEach var="nestedException" items="${nextedExceptionEntry.value}">
                    * ${nestedException.class.simpleName} <br>
                </c:forEach>
            </c:forEach>
            <br>

            <c:set var="noInfo">
                <spring:theme code="checkout.multi.errorPage.additionalInfo.noInfo" />
            </c:set>
            <spring:message code="checkout.multi.errorPage.additionalInfo.label"
                            arguments="${exception.class.name};${not empty additionalInfo? additionalInfo : noInfo}"
                            htmlEscape="false" argumentSeparator=";"/>
            <br>
            ===============================================STACKTRACE====================================================
            <table>
                <tr valign="top">
                    <td>
                        <c:forEach var="trace"
                                   items="${pageContext.exception.stackTrace}">
                            <p>${trace}</p>
                        </c:forEach>
                    </td>
                </tr>
            </table>
                <%--
         <hr>
         <h1>request attributes:</h1>
         <table style="border:1px double black">
             <c:forEach var="requestEntries" items="${requestScope}">
                 <tr style="border:1px double black">
                     <td> ${requestEntries.key}</td>
                     <td>${requestEntries.value}</td>
                 </tr>
             </c:forEach>
         </table>
         <hr>
         <h1>session attributes:</h1>
         <table style="border:1px double black">
             <c:forEach var="sessionEntries" items="${sessionScope}">
                 <tr style="border:1px double black">
                     <td> ${sessionEntries.key}</td>
                     <td>${sessionEntries.value}</td>
                 </tr>
             </c:forEach>
         </table>--%>
            </c:if>

        </div>
    </div>


</template:pageRegular>


