<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>


<div class="mod-miles-and-more">
    <div class="inner">

        <p><spring:theme code="form.abonumber.heading" /></p>

        <ul class="input-list">

            <formElement:text
                    path="aboNumber"
                    id="abonumber"
                    label=""
                    labelKey="form.abonumber.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.abonumber.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.abonumber.tooltip"
                    required="true" />

        </ul>

    </div>
</div>