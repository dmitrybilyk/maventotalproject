<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="mod-login-guest">
    <div class="inner">

        <p><spring:theme code="form.loginGuest.heading" /></p>

        <ul class="input-list">

            <formElement:button
                    name=""
                    id="loginAsGuestButton"
                    type="submit"
                    text=""
                    textKey="form.loginGuest.btnNext.text"
                    cssClass="default"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey=""
                    label=""
                    labelKey="form.loginGuest.btnNext.label" />

        </ul>

    </div>
</div>
