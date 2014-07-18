<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="steps" tagdir="/WEB-INF/tags/desktop/steps" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:pageRegular pageTitle="${pageTitle}" pageIdentifier="checkout-login">
    <div class="content-main">
        <div class="content-progress-bar">
            <steps:steps/>
        </div>

        <%@include file="productInOrderFragment.jspf" %>

        <div class="content-heading">
            <spring:theme code="checkout.multi.loginPage.heading" var="textPageTitle" />
            <common:heading
                    heading="${textPageTitle}" />
        </div>

        <div class="content-form">

            <form:form method="post" commandName="form" action="${pageContext.request.contextPath}/formsubmit">

                <input hidden="true" value="${stepName}" name="stepName" id="stepName"/>

                <formblock:loginExistingCustomer/>

                <formblock:loginNewCustomer/>
                <c:if test="${displaymappings['AnonymousFormElementGroup']}">
                    <formblock:loginGuest />
                </c:if>
            </form:form>

        </div>

        <div class="content-sidebar">
                <sidebar:serviceSidebar/>
            </div>
        </div>

    </div>



</template:pageRegular>

