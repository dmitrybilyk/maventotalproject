<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 3/16/14
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students for regulare servlet</title>

    <script src="<c:url value="/resources/mytheme/js/jquery.1.10.2.min.js" />"></script>
    <script src="<c:url value="/resources/mytheme/js/custom.js" />"></script>
    <link href="<c:url value="/resources/mytheme/css/styles.css" />" rel="stylesheet">

    <script type='text/javascript'>
        function showStudents() {
            $.ajax({
                url: '${pageContext.request.contextPath}/ShowStudentsServlet',
                type: "POST",
                dataType: 'json',
                data: jQuery(this).serialize(),
                success: function (data) {
                    debugger;
                    var content = '';
                    var sms = data;
                    for (var i = 0; i < sms.length; i++) {
                        content += "<p><h4>" + sms[i].name + "  suggests word:" + "</h4></p>";
                        content += "<p class='ageStyle'> " + sms[i].age + "</p>";
                        content += "<a href='#'";
                        content += "onclick='saveStudent("
                        content += sms[i].age;
                        content += ")'>saveAgain</a></div>"
                    }
                    $('#showStudentsContainer').html(content);
                }
            });
        }

        function test(){
            alert("fdsfdsfdsf");
        }

        function saveStudent(age) {
            $.ajax({
                url: '${pageContext.request.contextPath}/SaveStudentServlet?age='+age,
                type: "POST",
                dataType: 'json',
                data: jQuery(this).serialize(),
                success: function (data) {
                    debugger;
                    var content = '';
                    var sms = data;
                    for (var i = 0; i < sms.length; i++) {
                        content += "<p><h4>" + sms[i].name + "  suggests word:" + "</h4></p>";
                        content += "<p class='ageStyle'> " + sms[i].age + "</p>";
                        content += "<a href='#'";
                        content += "onclick='saveStudent("
                        content += sms[i].age;
                        content += ")'>saveAgain</a></div>"
                    }
                    $('#showStudentsContainer').html(content);
                }
            });
        }

//        for ajax call
        function ajaxAsyncRequest(reqURL)
        {
            //Creating a new XMLHttpRequest object
            var xmlhttp;
            if (window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
            } else {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
            }
            //Create a asynchronous GET request
            xmlhttp.open("GET", reqURL, true);

            //When readyState is 4 then get the server output
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4) {
                    if (xmlhttp.status == 200)
                    {
                        document.getElementById("message").innerHTML = xmlhttp.responseText;
                        //alert(xmlhttp.responseText);
                    }
                    else
                    {
                        alert('Something is wrong !!');
                    }
                }
            };

            xmlhttp.send(null);
        }
    </script>
</head>
<body>

<%--<div>--%>
    <%--<a href="#" id="showLink" onclick="showStudents()">Show students</a>--%>
<%--</div>--%>

<div id="showStudentsContainer">

    <table>
        <tr><th>ID</th><th>Name</th><th>Last Name</th></tr>
        <%--<div id="tableDiv">--%>

        <c:forEach items="${users}" var="user">
        <tr>
            <%--<td><c:out value="${user.id}"></c:out></td>--%>
            <td><input type="text" name="Name of user" value="${user.firstName}"></td>
            <td><input type="text" name="Last name of user" value="${user.lastName}"></td>
            <td><a href="${pageContext.request.contextPath}/user/edit/${user.id}">Edit</a></td>
            <%--<td><a href="#" id="deleteStudentAction" onclick="deleteUser(${user.id})">Delete</a></td>--%>
            <%--<td><a href="#" id="copyStudentAction" onclick="copyStudent(${student.age})">Copy</a></td>--%>
        <tr>
            </c:forEach>

        <%--</div>--%>
            <a href="${pageContext.request.contextPath}/user/add"/>create user</a>
        </tr>
    </table>

</div>

<div id="message">
    current time will be here
</div>

<input type="button" value="Show Server Time" onclick='ajaxAsyncRequest("${pageContext.request.contextPath}/get-current-time")'/>
</body>
</html>
