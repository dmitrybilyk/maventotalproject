<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
    <%--<script src="<c:url value="/resources/mytheme/js/jquery.1.10.2.min.js" />"></script>--%>
    <%--<script src="<c:url value="/resources/mytheme/js/main.js" />"></script>--%>
    <%--<link href="<c:url value="/resources/mytheme/css/styles.css" />" rel="stylesheet">--%>
</head>
<body>
<%--<jsp:forward page="/UserController?action=listUser" />--%>
<%--<jsp:forward page="/users" />--%>

<h1>Test ossf styles3434232ddd</h1>

<form action="${pageContext.request.contextPath}/preautho">
    <input type="text" name="UserName" value="UserName"/>
    <input type="text" name="UserLastName" value="UserLastName"/>
    <input type="submit" name="AddUser"/>
</form>

<div id="msg"></div>
</body>
</html>