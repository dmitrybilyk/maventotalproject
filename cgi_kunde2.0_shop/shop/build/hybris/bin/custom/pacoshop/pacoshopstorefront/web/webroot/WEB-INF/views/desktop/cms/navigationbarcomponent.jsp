<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${visible}">
    <c:forEach items="${children}" var="node">
        <c:forEach items="${node.links}" var="link">
            <li>
                <a href="${link.url}">${link.name}</a>
            </li>
        </c:forEach>
    </c:forEach>
</c:if>