<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>



<%--  Common CSS files --%>
<link rel="stylesheet" href="${commonResourcePath}/css/common.min.css" />

<c:forEach items="${addOnCommonCssPaths}" var="addOnCommonCss">
    <link rel="stylesheet" href="${addOnCommonCss}" />
</c:forEach>



<%--  Theme CSS files --%>
<link rel="stylesheet" href="${themeResourcePath}/css/changes.min.css" />

<c:forEach items="${addOnThemeCssPaths}" var="addOnThemeCss">
    <link rel="stylesheet" href="${addOnThemeCss}" />
</c:forEach>

<%--  Restriction with external link to CSS files --%>
<cms:globalSlot uid="CampaignLinksSlot" var="link">
    <cms:component component="${link}" />
</cms:globalSlot>