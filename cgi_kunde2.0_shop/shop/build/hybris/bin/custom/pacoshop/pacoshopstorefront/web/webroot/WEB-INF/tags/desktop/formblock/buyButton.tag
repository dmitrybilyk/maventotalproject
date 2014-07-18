<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="labelKey"			required="false"	rtexprvalue="true" %>

<div class="mod-buy-button">
    <div class="inner">

        <button type="submit" name="" class="btn btn-submit">
            <spring:theme code="${labelKey}" />
        </button>

    </div>
</div>