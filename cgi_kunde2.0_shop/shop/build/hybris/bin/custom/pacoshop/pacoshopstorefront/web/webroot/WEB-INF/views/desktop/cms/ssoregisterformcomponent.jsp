<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<ul class="input-list">

    <formElement:button
            name=""
            id="${id}"
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
<script>
    //<![CDATA[
        document.getElementById("${id}").onclick = function(){
            window.location.href = '${wholeUrl}';
            return false;
        }
    //]]>
</script>
