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
    <myprefix:MyMsg>
        Test String
    </myprefix:MyMsg>
<%--fsdfsdfsdfsdfsdfsdfsdfsdf--%>
    <form action="userdetails.jsp" method="post">
        User Name: <input type="text" name="username"><br>
        User Password: <input type="password" name="password"><br>
        User Age: <input type="text" name="age"><br>
        <input type="submit" value="register">
    </form>
<p>Sbustring of \"Dimon\" from 0 to 3 is - </p> <h1><mypSubstr:substring input="Dimon" start="0" end="3" ></h1>

    Included content in body of tag
    </mypSubstr:substring>


    <h2>Number Formatting Example</h2>

    <mytags:formatNumber number="100050.574" format="#,###.00"/><br><br>

    <mytags:formatNumber number="1234.567" format="$# ###.00"/><br><br>

    <p><strong>Thanks You!!</strong></p>



    <td><a href="${pageContext.request.contextPath}/GoToAnotherPage">Go to another page</a></td>

    <td><a href="${pageContext.request.contextPath}/ShowXls">Show xls</a></td>
    <td><a href="${pageContext.request.contextPath}/show-image">Show image</a></td>
    <td><a href="${pageContext.request.contextPath}/show-headers">Show Headers</a></td>
    <td><a href="${pageContext.request.contextPath}/refresh">Go To Refresh Servlet</a></td>
    <td><a href="${pageContext.request.contextPath}/sendEmail">Send email</a></td>

  </body>
</html>
