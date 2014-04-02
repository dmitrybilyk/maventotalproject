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
    <title>Students for mvc</title>

    <script src="<c:url value="/resources/mytheme/js/jquery.1.10.2.min.js" />"></script>
    <link href="<c:url value="/resources/mytheme/css/styles.css" />" rel="stylesheet">

    <script type='text/javascript'>
        function showStudents() {
            $.ajax({
                url: '${pageContext.request.contextPath}/mvc/students/ShowStudents',
                type: "POST",
                dataType: 'json',
                data: jQuery(this).serialize(),
                success: function (data) {
                    debugger;
                    var content = '';
                    var sms = data;
                    for (var i = 0; i < sms.length; i++) {
                        content += "<p><h4>" + sms[i].name + "  suggests word:" + "</h4></p>";
                        content += "<p> " + sms[i].age + "</p>";
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
                url: '${pageContext.request.contextPath}/mvc/students/SaveStudent?age='+age,
                type: "POST",
                dataType: 'json',
                data: jQuery(this).serialize(),
                success: function (data) {
                    debugger;
                    var content = '';
                    var sms = data;
                    for (var i = 0; i < sms.length; i++) {
                        content += "<p><h4>" + sms[i].name + "  suggests word:" + "</h4></p>";
                        content += "<p> " + sms[i].age + "</p>";
                        content += "<a href='#'";
                        content += "onclick='saveStudent("
                        content += sms[i].age;
                        content += ")'>saveAgain</a></div>"
                    }
                    $('#showStudentsContainer').html(content);
                }
            });
        }
    </script>
</head>
<body>

<div>
    <a href="#" id="showLink" onclick="showStudents()">Show students</a>
</div>

<div id="showStudentsContainer">

</div>

</body>
</html>
