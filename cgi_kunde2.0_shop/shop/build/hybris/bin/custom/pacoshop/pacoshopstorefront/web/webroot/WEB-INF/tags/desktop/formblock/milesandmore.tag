<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>


<div class="mod-miles-and-more">
    <div class="inner">

        <p><spring:theme code="form.milesandmore.heading" /></p>

        <ul class="input-list">

            <formElement:tel
                    path="milesAndMoreNumber"
                    id="milesandmore"
                    label=""
                    labelKey="form.milesandmore.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.milesandmore.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.milesandmore.tooltip"
                    required="${not empty mandatoryFields['milesAndMoreNumber']}"
                    pattern="[0-9]*"
                    maxlength="15"/>

        </ul>

    </div>
</div>