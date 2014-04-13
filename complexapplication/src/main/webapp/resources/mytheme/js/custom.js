//$(document).ready(function(){

function editStudent(age, name){
    $.ajax({
        url: '${pageContext.request.contextPath}/ShowStudentsServlet?edit=true',
        type: "POST",
        dataType: 'json',
        data: jQuery(this).serialize(),
        success: function (data) {
            alert("student is updated");
        }
    });
}

function deleteStudent(age){
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

function copyStudent(age){
    $.ajax({
        url: '${pageContext.request.contextPath}/SaveStudentServlet',
        type: "POST",
        dataType: 'json',
        data: jQuery(this).serialize(),
        success: function (data) {
            debugger;
            var content = '';
            var sms = data;
            for (var i = 0; i < sms.length; i++) {
                content += "<tr>";
                content += "<td><c:out value='" + ${student.id} + "'></c:out></td>";

//
//                        <td><input type="text" name="Name of person" value="${student.name}"></td>
//                            <td><input type="text" name="Age of person" value="${student.age}"></td>
//                                <td><a href="#" id="editStudentAction" onclick="editStudent('${student.age}', '${student.name}')">Edit</a></td>
//                                <td><a href="#" id="deleteStudentAction" onclick="deleteStudent(${student.age})">Delete</a></td>
//                                <td><a href="#" id="copyStudentAction" onclick="copyStudent(${student.age})">Copy</a></td>
//                                <tr>
//





//                content += "<p><h4>" + sms[i].name + "  The name of student " + "</h4></p>";
//                content += "<p> " + sms[i].age + "</p>";
//                content += "<a href='#'";
//                content += "onclick='saveStudent("
//                content += sms[i].age;
//                content += ")'>copy</a></div>"
            }
            $('#showStudentsContainer').html(content);
        }
    });
}




//})
function showStudentsInAlert(list){
    $.each(list, function( index, value ) {
        alert( index + ": " + value );
    });
}
