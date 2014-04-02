<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
  Created by IntelliJ IDEA.
  User: dmitry.bilyk
  Date: 3/28/14
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>List of all users</title>
    <%--<script src="${pageContext.request.contextPath}/resources/mytheme/users.js"></script> // enabling java script--%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mytheme/css/styles.css" /> enabling css--%>
    <%--<link rel="stylesheet" type="text/css" href="/WEB-INF/static/css/styles.css" /> enabling css--%>
    <script src="<c:url value="/resources/mytheme/js/jquery.1.10.2.min.js" />"></script>
    <script src="<c:url value="/resources/mytheme/js/users.js" />"></script>
    <link href="<c:url value="/resources/mytheme/css/styles.css" />" rel="stylesheet">
</head>
<body>
<div class="divTable">
    <div class="headRow">
        <div class="divCell" align="center">Customer ID</div>
        <div  class="divCell">Customer Name</div>
        <div  class="divCell">Customer Address</div>
    </div>
    <div class="divRow">
        <div class="divCell">001</div>
        <div class="divCell">002</div>
        <div class="divCell">003</div>
    </div>
    <div class="divRow">
        <div class="divCell">xxx</div>
        <div class="divCell">yyy</div>
        <div class="divCell">www</div>
    </div>
    <div class="divRow">
        <div class="divCell">ttt</div>
        <div class="divCell">uuu</div>
        <div class="divCell">Mkkk</div>
    </div>

</div>

<h1>test of h1</h1>

</body>
</html>
