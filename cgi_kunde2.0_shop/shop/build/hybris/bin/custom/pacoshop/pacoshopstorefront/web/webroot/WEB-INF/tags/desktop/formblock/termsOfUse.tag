<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="mod-terms-of-use">
    <div class="inner">

        <p><spring:theme code="form.termsOfUse.title" /></p>

        <ul class="input-list">

            <spring:message var="agreeTermsLabel"
                    code="form.termsOfUse.agreeTerms.label"
                    arguments="${agreeTermsUrl},${contradictionUrl}"
                    htmlEscape="false"/>
            <formElement:checkbox
                    path="agb"
                    id="agb"
                    label="${agreeTermsLabel}"
                    labelKey=""
                    value="true"
                    tooltip=""
                    tooltipKey="form.termsOfUse.agreeTerms.tooltip"
                    cssClass=""
                    msg=""
                    msgType=""
                    required="true" />

            <c:if test="${form.optIn ne null}">
                <spring:message var="optInCheckboxLabel"
                        code="form.termsOfUse.optin.label"
                        arguments="${optInCheckboxUrl}"
                        htmlEscape="false"/>
                <formElement:checkbox
                        path="optIn"
                        id="optIn"
                        label="${optInCheckboxLabel}"
                        labelKey=""
                        value="true"
                        tooltip=""
                        tooltipKey="form.termsOfUse.optin.tooltip"
                        cssClass=""
                        msg=""
                        msgType=""
                        required="${not empty mandatoryFields['optIn']}" />
            </c:if>
        </ul>

    </div>
</div>

