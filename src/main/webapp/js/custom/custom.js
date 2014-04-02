/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 9/11/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
//
//    function fillWithValues() {
//        document.getElementById('firstName').value = Math.floor(Math.random()*11)
//        document.getElementById('lastName').value = Math.floor(Math.random()*11)
//        document.getElementById('dob').value = '24/12/2011';
//        document.getElementById('email').value = Math.floor(Math.random()*11)
//    }
//    alert(document.getElementById('fillButton'));
//
//    alert(document.getElementById('useraddform'));
//
//    var form =  document.getElementById('useraddform');
//
//
//    document.getElementById("firstName").onblur = validateName();
//
//    function validateName() {
//        var value = document.getElementById('firstName').value;
//         if(value<5){
//             alert("error")
//         }
//    }

function myFunction(){
    document.getElementById("myP").innerHTML = "from external js";
}

function randomLink(){
    var x = document.getElementById("myP");
    var r = Math.random();

    if(r<5){
//        if you declare var without "var" then it becomes GLOBAL
        s = "2ffdfs";
        document.getElementById("myH1").innerHTML=s;
        x.innerHTML = "<a href='http://football.ua'>Football</a>"
    }else{
        x.innerHTML = "<a href='http://terrikon.com'>Terrikon</a>"
    }
}

function showTheDay()
{
    var x;
    var d=new Date().getDay();
    switch (d)
    {
        case 0:
            x="Today it's Sunday";
            break;
        case 1:
            x="Today it's Monday";
            break;
        case 2:
            x="Today it's Tuesday";
            break;
        case 3:
            x="Today it's Wednesday";
            break;
        case 4:
            x="Today it's Thursday";
            break;
        case 5:
            x="Today it's Friday";
            break;
        case 6:
            x="Today it's Saturday";
            break;
    }
    document.getElementById("myP").innerHTML=x;
}

function functionWithParams(pContent, randomValue){
    if(s != null){
        alert(s);
        alert(pContent + " " + s+randomValue);
    }
}

var x = 10;

function randomValue(){
   return x;
}

//the return value is optional
function myCompareFunction(a,b)
{
    if (a>b)
    {
        return;
    }
    x=a+b
}



// check cookie
function getMyCookie(c_name)
{
    var c_value = document.cookie;
    var c_start = c_value.indexOf(" " + c_name + "=");
    if (c_start == -1)
    {
        c_start = c_value.indexOf(c_name + "=");
    }
    if (c_start == -1)
    {
        c_value = null;
    }
    else
    {
        c_start = c_value.indexOf("=", c_start) + 1;
        var c_end = c_value.indexOf(";", c_start);
        if (c_end == -1)
        {
            c_end = c_value.length;
        }
        c_value = unescape(c_value.substring(c_start,c_end));
    }
    return c_value;
}

function setMyCookie(c_name,value,exdays)
{
    var exdate=new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
    document.cookie=c_name + "=" + c_value;
}

function checkMyCookie()
{
    var username=getMyCookie("username");
    if (username!=null && username!="")
    {
        alert("Welcome again " + username);
    }
    else
    {
        username=prompt("Please enter your name:","");
        if (username!=null && username!="")
        {
            setMyCookie("username",username,365);
        }
    }
}

function myTimingFunction()
{
    setTimeout(function(){alert("Hello")},3000);
}

function timedText()
{
    var x=document.getElementById('txt');
    var t1=setTimeout(function(){x.value="2 seconds"},2000);
    var t2=setTimeout(function(){x.value="4 seconds"},4000);
    var t3=setTimeout(function(){x.value="6 seconds"},6000);
}



var c=0;
var t;
var timer_is_on=0;

function timedCount()
{
    document.getElementById('txt2').value=c;
    c=c+1;
    t=setTimeout("timedCount()",1000);
}

function doTimer()
{
    if (!timer_is_on)
    {
        timer_is_on=1;
        timedCount();
    }
}

function stopCount()
{
    clearTimeout(t);
    timer_is_on=0;
}



function startTime()
{
    var today=new Date();
    var h=today.getHours();
    var m=today.getMinutes();
    var s=today.getSeconds();
// add a zero in front of numbers<10
    m=checkTime(m);
    s=checkTime(s);
    document.getElementById('clock').innerHTML=h+":"+m+":"+s;
    t=setTimeout(function(){startTime()},500);
}

function checkTime(i)
{
    if (i<10)
    {
        i="0" + i;
    }
    return i;
}

function person(firstname,lastname,age,eyecolor)
{
    this.firstname=firstname;
    this.lastname=lastname;
    this.age=age;
    this.eyecolor=eyecolor;
}

myFather=new person("John","Doe",50,"blue");

//document.write(<div id = "personDiv"></div>);
document.getElementById("personDiv").innerHTML = myFather.firstname + " is " + myFather.age + " years old."


function findMatchingInString(button, element){
      var testText = "Doe is John";
//      var s = document.getElementById("personDiv");
      button.value = testText.match(element);
}

function replaceText()
{
    var str=document.getElementById("replaceDiv").innerHTML;
    var m = str.match("good");
    if(m!=null){
        n=str.replace("good","bad");
    } else {
        n=str.replace("bad", "good");
    }
    document.getElementById("replaceDiv").innerHTML=n;
}

function setDateHere(){
    var d = new Date();
    document.getElementById("forDate").innerHTML = d.toString();
    document.write(d.toDateString());
    document.write(d.toGMTString());
    document.write(d.toISOString());
    document.write(d.toUTCString());
    document.write(d.toJSON());
}

function getDay(){
    var array = new Array(7);
    array[0] = "Monday";
    array[1] = "Tuesday";
    array[2] = "Wednesday";
    array[3] = "Thursday";
    array[4] = "Friday";
    array[5] = "Saturday";
    array[6] = "Sunday";

    var x = document.getElementById("forDay");
    var d = new Date();
    x.innerHTML = array[d.getDay() - 1]
}




