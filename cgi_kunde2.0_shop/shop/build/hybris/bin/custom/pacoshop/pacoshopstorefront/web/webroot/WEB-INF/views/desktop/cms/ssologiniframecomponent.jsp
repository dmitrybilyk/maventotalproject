<ul class="input-list">
    <li class="has-html">
        <iframe id="${id}" src="${src}" frameborder="${frameborder}" width="${width}" height="${height}"></iframe>
        <script>
            //<![CDATA[
            loginCorrect = function (e) {

                if (e.origin == '${baseUrl}') {
                    var action = e.data.split(':')[1];
                    if (action == 'true') {
                        window.location.href = '${wholeUrl}';
                        //window.location.href = '${baseUrl}?service=' + encodeURIComponent(window.location.href);
                    } else {
                        if ('console' in self && 'log' in console) console.log("Unknown message: " + e.data);
                        else alert("Unknown message: " + e.data);
                    }
                }

            }

            var iframe = document.getElementById('${id}');
            iframe.onload = function() {
                iframe.contentWindow.postMessage('login?', '${baseUrl}');
                if (!window.addEventListener) {
                    window.attachEvent('message', loginCorrect);
                } else {
                    window.addEventListener('message', loginCorrect, false);
                }
            }
            //]]>
        </script>
    </li>
</ul>