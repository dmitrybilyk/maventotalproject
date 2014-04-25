<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 2/22/14
  Time: 1:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>JAX-RS @FormQuery Testing</h1>

<form action="user/add" method="post">
    <p>
        Name : <input type="text" name="name" />
    </p>
    <p>
        Age : <input type="text" name="age" />
    </p>
    <input type="submit" value="Add User" />
</form>

</body>
</html>
