<%@taglib prefix="test" uri="http://com.daniel.taglibs/jsp/taglib/substr"%>
<%@ taglib uri="/WEB-INF/customTag" prefix="ct" %>
<%@ taglib uri="/WEB-INF/customTagMulti" prefix="ctm" %>
<%--
  Created by IntelliJ IDEA.
  User: dmitriy.bilyk
  Date: 02.07.13
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Taglib</title>



</head>
<body>

SUBSTR(GOODMORNING, 1, 6) is
<font color="blue">
    <test:substring input="GOODMORNING" start="1" end="6"/>
</font>

\br

<h2>
    Upper Case :
    <ct:changeCase case="upper">
        Hello World
    </ct:changeCase>
</h2>
<h2>
    Lower Case :
    <ct:changeCase case="lower">
        Hello World
    </ct:changeCase>
</h2>


<center>

    <ctm:loopText times="3">
        <h3>Loop Text!</h3>
    </ctm:loopText>

</center>

</body>
</html>