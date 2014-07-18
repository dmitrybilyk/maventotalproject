<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-usercompany">
    <div class="inner">

    <p><spring:theme code="form.usercompany.heading" /></p>

        <ul class="input-list">

            <formElement:text
                    path="company"
                    id="company"
                    label=""
                    labelKey="form.usercompany.company.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.usercompany.company.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.usercompany.company.tooltip"
                    required="false" />

            <formElement:text
                    path="positionCompany"
                    id="company-role"
                    label=""
                    labelKey="form.usercompany.role.label"
                    cssClass=""
                    placeholder=""
                    placeholderKey="form.usercompany.role.placeholder"
                    msg=""
                    msgType=""
                    tooltip=""
                    tooltipKey="form.usercompany.role.tooltip"
                    required="false" />

        </ul>

    </div>
</div>