<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="${commonResourcePath}/js/body.min.js"></script>

<c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
    <script type="text/javascript" src="${addOnJavaScript}"></script>
</c:forEach>