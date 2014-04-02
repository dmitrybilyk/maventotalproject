<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="js/custom/custom.js"></script>
<script src="js/custom/jquery-scripts.js"></script>
    <script>
        document.write("Hello World");
//        document.getElementById('fillButton').onclick() = goToFootballUa();
        function goToFootballUa() {
            window.location="football.ua"
        }

        function myFunction2(){
            document.getElementById("myP").innerHTML = "from head";
        }
    </script>
<title>Add new user</title>
</head>
<body>
    <script>
        $(function() {
            $('input[name=dob]').datepicker();
        });
    </script>

    <h1 id="myH1">My First Web Page</h1>
    <p id="myP">This is the adding of the user</p>

    <div class="mainDiv">
        <form method="POST" action='UserController' name="frmAddUser" class="useraddform" id="useraddform">
            User ID : <input type="text" readonly="readonly" name="userid"
                value="<c:out value="${user.userid}" />" /> <br />
            First Name : <input
                type="text" name="firstName" id="firstName"
                value="<c:out value="${user.firstName}" />" /> <br />
            Last Name : <input
                type="text" name="lastName" id="lastName"
                value="<c:out value="${user.lastName}" />" /> <br />
            DOB : <input
                type="text" name="dob" id="dob"
                value="<fmt:formatDate pattern="MM/dd/yyyy" value="${user.dob}" />" /> <br />
            Email : <input type="text" name="email" id="email"
                value="<c:out value="${user.email}" />" /> <br /> <input
                type="submit" value="Submit" />
            <br /> <input
                    type="button" value="fill user" id="fillButton" onclick="myFunction()"/>
            <br /> <input
                    type="button" value="fill user" id="fillButton2" onclick="myFunction2()"/>
            <br /> <input
                    type="button" value="fill user" id="fillButton3" onclick="myFunction3()"/>
            <br /> <input
                type="button" value="random link" id="fillButton4" onclick="randomLink()"/>
            <br /> <input
                type="button" value="show the day" name="showTheDay" id="showTheDay" onclick="showTheDay()"/>

            <br /> <input
                type="button" value="with params" id="paramsButton" onclick="functionWithParams('dd', randomValue())"/><br /> <input
                type="button" value="check cookie" id="checkCookie" onclick="checkMyCookie()"/>

                <br />
            <p>Click the button to wait 3 seconds, then alert "Hello".</p>
            <button onclick="myTimingFunction()">Test timing</button>
            <br />
            <input type="button" value="Display timed text!" onclick="timedText()" />
            <input type="text" id="txt" />
            <br />
            <input type="button" value="Start count!" onClick="doTimer()">
            <input type="button" value="Stop count!" onClick="stopCount()">
            <input type="text" id="txt2">

            <br />
            <div id="clock" onclick="startTime()">Start clock</div>
            <div id="replaceDiv" onclick="replaceText()">Microsoft is good</div>
            <div id="forDate" onclick="setDateHere()">For date</div>
            <div id="forDay" onclick="getDay()">For day</div>
            <br />
            <input type button id="matchButton" value="Find Matching" onclick="findMatchingInString(this, 'is')">
        </form>
    </div>

    <div id="personDiv">for person</div>

<script type="text/javascript">
    function myFunction3(){
        document.getElementById("myP").innerHTML = "from body";
    }
</script>
</body>
</html>