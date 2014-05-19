<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 19.05.14
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<jsp:useBean id="userinfo" class="com.learn.customtags.Details"></jsp:useBean>
<jsp:setProperty property="*" name="userinfo"/>
You have enterted below details:<br>
<jsp:getProperty property="userName" name="userinfo"/><br>
<jsp:getProperty property="password" name="userinfo"/><br>
<jsp:getProperty property="age" name="userinfo" /><br>
</body>
</html>
