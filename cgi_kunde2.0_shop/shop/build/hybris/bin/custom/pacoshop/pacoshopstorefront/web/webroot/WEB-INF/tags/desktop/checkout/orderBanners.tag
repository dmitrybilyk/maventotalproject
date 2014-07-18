<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="mod-recommended-items">
    <ul class="input-list">
        <li class="has-infotext ">
            <span class="infotext"><cms:component uid="bannerConsiderationText"/></span>
        </li>
    </ul>
    <div class="items-list">
        <cms:globalSlot uid="BannerContentSlot" var="banner" limit="3">
            <div class="item">
                <cms:component component="${banner}"/>
            </div>
        </cms:globalSlot>
    </div>

</div>

