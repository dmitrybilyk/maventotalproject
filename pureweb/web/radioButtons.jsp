<%@ taglib prefix="myprefix" uri="/WEB-INF/tld/message.tld"%>
<%@ taglib prefix="mypSubstr" uri="http://viralpatel.net/blogs/jsp/taglib/substr"%>
<%@ taglib uri="http://journaldev.com/jsp/tlds/mytags" prefix="mytags"%>

<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 19.05.14
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/radioButtons" method="get">
      <input type="radio" name="preferred_color" value="Red" /> Red<br />
      <input type="radio" name="preferred_color" value="Blue" /> Blue<br />
      <input type="radio" name="preferred_color" value="Green" /> Green<br />
      <input type="submit" value="Submit">
  </form>

  </body>
</html>
