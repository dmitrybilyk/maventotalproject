<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="meta-nav">
    <cms:globalSlot uid="HeaderNavigationBarSlot" var="component">
        <c:choose>
            <c:when test="${component.class.name == 'de.hybris.platform.acceleratorcms.model.components.NavigationBarComponentModel'}">
                <cms:component component="${component}"/>
            </c:when>
            <c:otherwise>
                <li>
                    <cms:component component="${component}"/>
                </li>
            </c:otherwise>
        </c:choose>
    </cms:globalSlot>

    <cms:globalSlot uid="HeaderLinksSlot" var="link">
        <cms:component component="${link}"/>
    </cms:globalSlot>
</ul>