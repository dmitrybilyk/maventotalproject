<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="salutation"	required="false" rtexprvalue="true" %>
<%@ attribute name="title"	required="false" rtexprvalue="true" %>
<%@ attribute name="titleCode"	required="false" rtexprvalue="true" %>
<%@ attribute name="prename"	required="false" rtexprvalue="true" %>
<%@ attribute name="surname"		required="false" rtexprvalue="true" %>
<%@ attribute name="company"		required="false" rtexprvalue="true" %>
<%@ attribute name="additionalStreet"		required="false" rtexprvalue="true" %>
<%@ attribute name="street"		required="false" rtexprvalue="true" %>
<%@ attribute name="houseNumber"		required="false" rtexprvalue="true" %>
<%@ attribute name="zip"		required="false" rtexprvalue="true" %>
<%@ attribute name="city"		required="false" rtexprvalue="true" %>
<%@ attribute name="country"		required="false" rtexprvalue="true" %>
<%@ attribute name="countryCode"		required="false" rtexprvalue="true" %>
<%@ attribute name="email"		required="false" rtexprvalue="true" %>

<div class="mod-address">
    <div class="inner">
        <span class="title-holder">
            <span class="prename">${prename}</span>
            <span class="surname">${surname}</span>
            <span class="company">${company}</span>
        </span>

        <span class="salutation">${salutation}</span>

        <span class="title">${title}</span>
        <span class="titleCode">${titleCode}</span>
        <span class="additionalStreet">${additionalStreet}</span>
        <span class="street">${street}</span>
        <span class="houseNumber">${houseNumber}</span>
        <span class="zip">${zip}</span>
        <span class="city">${city}</span>
        <span class="country">${country}</span>
        <span class="countryCode">${countryCode}</span>
        <span class="email">${email}</span>

    </div>
</div>