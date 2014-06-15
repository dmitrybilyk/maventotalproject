<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<html>
<head>
    <title>Upload portal specification</title>
</head>
<body>
<c:if test="${not empty message}">
    <b>Message:</b> ${message}
    <hr/>
</c:if>
<form name="portal-upload" method="post" enctype="multipart/form-data">
    <div>
        <c:if test="${not empty portals}">
            Available portals:
            <ul>
                <c:forEach var="portal" items="${portals}">
                    <li>
                            <a href="${pageContext.request.contextPath}/rest/portal?cartid=${fn:replace(fn:trim(portal), ".xml", "")}" target="_blank">${fn:trim(portal)}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        Note: If you uploading with existing key, it will be overwritten. Also there is no any xml validation, so be
        careful with uploading file.
    </div>
    <hr/>


    Cart Id (cartid parameter): <input name="portal_id" value="valid"> <br/>
    Specification (*.xml): <input type="file" name="portal_spec" accept="application/xml-dtd"> <br/>
    <button type="submit" class='input_submit' style="margin-right: 15px;">Submit</button>
</form>
</body>
</html>
