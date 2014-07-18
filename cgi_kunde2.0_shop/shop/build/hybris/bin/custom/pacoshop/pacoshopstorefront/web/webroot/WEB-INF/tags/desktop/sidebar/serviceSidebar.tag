<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="mod-cms mod-cms-sidebar">
    <strong class="title"><spring:theme code="sidebar.title.label" /></strong>

    <div class="inner mod-cms-sidebar-safe-info-list">

        <cms:globalSlot uid="SideContentSlot1" var="component" element="ul">
            <cms:component component="${component}"/>
        </cms:globalSlot>

    </div>

    <div class="inner mod-cms-sidebar-safe-info">
        <cms:globalSlot uid="SideContentSlot2" var="component">
            <cms:component component="${component}"/>
        </cms:globalSlot>
    </div>

    <div class="inner mod-cms-sidebar-safe-info-contact-info">
        <cms:globalSlot uid="SideContentSlot3" var="component">
            <cms:component component="${component}"/>
        </cms:globalSlot>
    </div>

</div>