<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="prename"	required="false" rtexprvalue="true" %>
<%@ attribute name="surname"		required="false" rtexprvalue="true" %>
<%@ attribute name="company"		required="false" rtexprvalue="true" %>
<%@ attribute name="additionalStreet"		required="false" rtexprvalue="true" %>
<%@ attribute name="street"		required="false" rtexprvalue="true" %>
<%@ attribute name="houseNumber"		required="false" rtexprvalue="true" %>
<%@ attribute name="zip"		required="false" rtexprvalue="true" %>
<%@ attribute name="city"		required="false" rtexprvalue="true" %>
<%@ attribute name="country"		required="false" rtexprvalue="true" %>
<%@ attribute name="homePhone"		required="false" rtexprvalue="true" %>
<%@ attribute name="businessPhone"		required="false" rtexprvalue="true" %>
<%@ attribute name="mobile"		required="false" rtexprvalue="true" %>
<%@ attribute name="email"		required="false" rtexprvalue="true" %>

<div class="mod-address">
    <div class="inner">
        <span class="title-holder">
            <span class="prename">${prename}</span>
            <span class="surname">${surname}</span>
            <span class="company">${company}</span>
        </span>
        <span class="additionalStreet">${additionalStreet}</span>
        <span class="street">${street}</span>
        <span class="houseNumber">${houseNumber}</span>
        <span class="zip">${zip}</span>
        <span class="city">${city}</span>
        <span class="country">${country}</span>
        <span class="homePhone">${homePhone}</span>
        <span class="businessPhone">${businessPhone}</span>
        <span class="mobile">${mobile}</span>
        <span class="email">${email}</span>

    </div>
</div>