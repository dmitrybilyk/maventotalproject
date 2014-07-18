<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="mod-login-existing-customer">
    <div class="inner">

        <p><spring:theme code="form.loginExistingCustomer.heading" /></p>

        <cms:globalSlot uid="SSOLoginIFramesSlot" var="feature">
            <cms:component component="${feature}" />
        </cms:globalSlot>
    </div>
</div>