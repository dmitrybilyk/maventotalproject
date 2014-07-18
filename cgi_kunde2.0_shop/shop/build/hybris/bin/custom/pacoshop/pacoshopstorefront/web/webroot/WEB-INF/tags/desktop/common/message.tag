<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="msgType"	required="false" rtexprvalue="true" %>
<%@ attribute name="msg"		required="false" rtexprvalue="true" %>


<div class="mod-message ${msgType} ">
	<div class="inner">

	    <p>${msg}</p>

    </div>
</div>