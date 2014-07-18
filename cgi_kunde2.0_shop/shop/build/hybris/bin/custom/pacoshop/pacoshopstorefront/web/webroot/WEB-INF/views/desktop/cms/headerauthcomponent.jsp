<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sec:authorize ifAnyGranted="ROLE_REGISTEREDCUSTOMERGROUP">
    <li>${salutation}</li>
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_REGISTEREDCUSTOMERGROUP">
    <li><a href="${loginUrl}">${loginTitle}</a></li>
    <li><a href="${registerUrl}">${registerTitle}</a></li>
</sec:authorize>
