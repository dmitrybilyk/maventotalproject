<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.cgi.pacoshop.mock.uag.MockUAGUtil" %>

<html>
<head>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/url.min.js"></script>
    <link rel="stylesheet" type="text/css"  href="css/fingerprint.css" />

    <script>
        function calculate() {
            var urlStr = $('#sourceUrl').val();
            // append the fingerprintsecretno default value if it doesn't exist
            var fingerprintSecretNo = url("?fingerprintsecretno", urlStr);
            if (!fingerprintSecretNo) {
                if (!url("?", urlStr)) {
                    urlStr += "?fingerprintsecretno=" + <%=MockUAGUtil.DEFAULT_FINGERPRINT_SECRET_NO%>;
                } else {
                    urlStr += "&fingerprintsecretno=" + <%=MockUAGUtil.DEFAULT_FINGERPRINT_SECRET_NO%>;
                }
                // update source url with a default fingerprintsecretno value
                $('#sourceUrl').val(urlStr);
            }

            $.ajax({
                url: "rest/uag2/fingerprint?url=" + encodeURIComponent(urlStr),
                type: "get",
                success: onResponse
            });
        }

        function onResponse(response) {
            var fingerprint = response;
            if (!fingerprint) {
                $("#fingerprint").val([error]);
                $("#resultUrl").val([error]);
            }
            else {
                // update result url with a calculated fingerprint value
                var urlStr = $('#sourceUrl').val();
                if (urlStr.search(/(?![\?|\&])(fingerprint=)([^\&]*)(?=\&|$)/g) >= 0) {
                    urlStr = urlStr.replace(/(?![\?|\&])(fingerprint=)([^\&]*)(?=\&|$)/g, "$1" + fingerprint);
                }
                else {
                    urlStr += "&fingerprint=" + fingerprint;
                }
                $("#fingerprint").val(fingerprint);
                $("#resultUrl").val(urlStr);
            }
        }
    </script>

    <title>Deeplink fingerprint calculator</title>
</head>
<body>
    <p>Input single URL to the "Source URL" input field and push "calculate fingerprint" button to update this URLs.</p>
    <table>
        <tr>
            <td>
                <label>Source URL:</label>
            </td>
            <td>
                <input type="url" id="sourceUrl" value="http://shop.site1.local:9001/startcheckout?productid=CRM:simplePaper2"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="button" value="calculate fingerprint" onclick="calculate()"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>Result URL:</label>
            </td>
            <td>
                <input type="url" id="resultUrl" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>Fingerprint:</label>
            </td>
            <td>
                <input type="text" id="fingerprint" class="fingerprint" readonly="readonly"/>
            </td>
        </tr>
    </table>
</body>
</html>
