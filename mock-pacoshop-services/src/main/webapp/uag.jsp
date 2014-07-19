<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.cgi.pacoshop.mock.uag.MockUAGUtil" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="uag.css" />
    <title>UAG mock</title>
</head>
<body>
<c:set var="initialsites" value="${initParam['shopTargetUrl']}"/>
<c:set var="sites" value="${fn:split(initialsites, newLineChar)}"/>

<form name="UAG" action="uag/Go" method="post">
    <%--SITES DROPDOWN START--%>
    <div>
        Redirect URL
        <ul>
            <li>
                <select style="width:50%" name="<%=MockUAGUtil.FORM_ITEM_REDIRECT_URL%>" id="dropdownredirecturl">
                <c:forEach var="site" items="${sites}">
                    <c:set var="trimmedSite" value="${fn:trim(site)}"/>
                    <option value="${trimmedSite}">
                            ${trimmedSite}
                    </option>
                </c:forEach>
                </select>
            </li>
            <li>
                <input style="width: 50%" name="<%=MockUAGUtil.FORM_ITEM_REDIRECT_URL%>" placeholder="if entered will ignore the dropdown and redirect to THIS URL"> <small>(must include protocol)</small>
            </li>
        </ul>
    </div>
    <hr>
    <%--SITES DROPDOWN END--%>
    <p>
        id <input name="<%=MockUAGUtil.FORM_ITEM_USER_ID%>" value="u001" />
        email <input name="<%=MockUAGUtil.FORM_ITEM_USER_EMAIL%>" value="u001_email@sample.com" />
    </p>
    <p>
        <input type="radio" checked="true" name="<%=MockUAGUtil.FORM_ITEM_USER_TYPE%>" value="user" /> User
    </p>
    <p>
        <input type="radio" name="<%=MockUAGUtil.FORM_ITEM_USER_TYPE%>" value="visitor" /> Visitor
    </p>
    <p>
        fingerprintSecretNo <input name="<%=MockUAGUtil.FORM_ITEM_FINGERPRINT_SECRET_NO%>" type="number" value="<%=MockUAGUtil.DEFAULT_FINGERPRINT_SECRET_NO%>" />
    </p>
    <p>
        wsToken <input name="<%=MockUAGUtil.FORM_ITEM_WS_TOKEN%>" />
    </p>
    <p>
        <button type="submit" class='input_submit' style="margin-right: 15px;">Submit</button>
    </p>
</form>
</body>
</html>
