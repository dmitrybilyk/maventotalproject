<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-lightbox-thankyou-registration">
    <div class="holder">
        <strong class="title">${headerText}</strong>
        <ul class="input-list main-form-actions">

            <formElement:legaltext
                        text=""
                        textKey="${label}"
                        cssClass=""/>

             <formElement:button
                            name=""
                            id="SSORegisterLightBox"
                            type="button"
                            text="${text}"
                            textKey=""
                            cssClass=""
                            msg=""
                            msgType=""
                            tooltip=""
                            tooltipKey=""
                            label="${label}"
                            labelKey="" />

        </ul>
        <a class="link-close" href="#"></a>
        <input type="hidden" id="configurableDelay" value="${timeout}" />
    </div>
</div>
<script>
    //<![CDATA[
    document.getElementById("SSORegisterLightBox").onclick = function(){
        window.location.href = '${registerUrl}';
        return false;
    }
    //]]>
</script>
