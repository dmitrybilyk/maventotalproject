<html>
<head>
    <script type="text/javascript">

        function upload_Bult_Data()
        {
            alert("test")
        }

        function changeFunc() {
//            var selectBox = document.getElementById("selectBox");
//            var selectedValue = selectBox.options[selectBox.selectedIndex].value;
            var selectedValue = selectBox.options[selectBox.selectedIndex].value;
            alert(selectedValue);
        }

    </script>
</head>
<body>
<h1>
    <input name="Upload_Data_Import" type="button" class="btnstyle"
           value="Import" onclick="javascript:upload_Bult_Data();" />
</h1>

<select id="selectBox" onchange="changeFunc();">
    <option value="1">Option #1</option>
    <option value="2">Option #2</option>
</select>
</body>
</html>