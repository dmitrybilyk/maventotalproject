<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="mod-login-new-customer">
    <div class="inner">

        <p><spring:theme code="form.loginNewCustomer.heading" /></p>

        <cms:globalSlot uid="SSORegisterFormSlot" var="feature">
            <cms:component component="${feature}" />
        </cms:globalSlot>
    </div>
</div>
