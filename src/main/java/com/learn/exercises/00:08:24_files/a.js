function _dtsq(k){var a=document.referrer.split('?');if(a.length<2)return false;var b=a[1].split('&');for(var i=0;i<b.length;i++){var c=b[i].split('=');if(c.length==2&&c[0]==k){return c[1];}}return false;};function _dtsi(){a=document.createElement("a");a.href=window.location.href;_dts.host=a.hostname;if((typeof document.referrer !== 'undefined')&&document.referrer.length>0){_dts.r=document.referrer;if(_dtsq("q")){_dts.q=_dtsq("q");}else if(_dtsq("p")){_dts.q=_dtsq("p");}else if(_dtsq("text")){_dts.q=_dtsq("text");}else{_dts.q=0;}}else{_dts.r=0;_dts.q=0;}}var _dts={};_dtsi();