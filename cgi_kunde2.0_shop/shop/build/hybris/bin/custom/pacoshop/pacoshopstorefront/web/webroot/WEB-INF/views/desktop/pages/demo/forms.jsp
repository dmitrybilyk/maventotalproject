<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<template:pageRegular pageTitle="TITLE">
	
	<div class="page page-forms">
	
		<common:message
			msgType="info"
			msg="Just for your information." />
	
		<common:message
			msgType="warning"
			msg="Please pay attention!" />
	
		<common:message
			msgType="error"
			msg="An error occured!" />
	
		<common:message
			msgType="success"
			msg="Successfully saved!" />
	
	    
	    <form:form method="post" commandName="dummyForm">
	                 
	         <ul class="input-list">
	         
				<formElement:infotext
					text="Lorem ipsum dolor sit amet."
					textKey=""
					cssClass="" />  
				
	            <formElement:select
	            	path="dummyField"
	            	id="title"
	            	label="Anrede:"
	            	labelKey=""
	            	selectMsg="--- please select ---"
	            	options="${dummyOptions}"
	            	optionValueAttr=""
	            	optionLabelAttr=""
	            	cssClass=""
	            	msg="Just an info."
	            	msgType="info"
	            	tooltip="Some dummy information."
	            	tooltipKey=""
	            	required="true"></formElement:select>				
				
				<%--
	         
	            <li class="has-select has-field-birthdate">
	                <span class="label multiple multiple-3"><label for="birthdate-day">Datum:</label></span>
	                <span class="field multiple multiple-3">
	                    <select id="birthdate-day" name="birthdate-day"><option>25</option><option>26</option></select>
	                    <select id="birthdate-month" name="birthdate-month"><option>10</option><option>11</option></select>
	                    <select id="birthdate-year" name="birthdate-year"><option>1982</option><option>1983</option><option>1984</option></select>
	                </span>
	                <span class="msg"><span></span></span>
	            </li>
	            
	            --%>

				<formElement:date
				         path="dummyField"
				         id="delivery-date"
				         label="Datum (jQueryUI datepicker):"
	            		 labelKey=""
				         cssClass="date"
				         placeholder="25.02.1982"
				         placeholderKey=""
				         msg=""
				         msgType=""
				         tooltip="Some dummy information."
	            	     tooltipKey=""
				         required="true" />
	            
	            <formElement:text
		            path="dummyField"
	            	id="prename"
	            	label="Prename"
	            	labelKey=""
	            	cssClass=""
	            	placeholder="Lorem ipsum"
	            	placeholderKey=""
	            	msg="Dont waffle."
	            	msgType="warning"
	            	tooltip="Some dummy information."
	            	tooltipKey=""
	            	required="true" />

                 <formElement:password
                         path="dummyField"
                         id="new-password"
                         label="Password"
	            		 labelKey=""
                         cssClass=""
                         placeholder="******"
                         msg="Please enter your correct password!"
                         msgType="error"
                         tooltip="Some dummy information."
	            		 tooltipKey=""
                         required="true" />

                 <formElement:textarea
                         path="dummyField"
                         id="message"
                         label="Deine Nachricht:"
	            		 labelKey=""
                         cssClass=""
                         placeholder="Lorem ipsum"
                         msg="Erfolgreich gespeichert!"
                         msgType="success"
                         tooltip="Some dummy information."
	            		 tooltipKey=""
                         required="true" />

	            <%--

	            <li class="has-text has-field-zip has-field-city">
	                <span class="label multiple multiple-2"><label for="zip">PLZ</label>/<label for="city">Ort</label>:</span>
	                <span class="field multiple multiple-2"><input type="text" id="zip" name="zip" placeholder="Lorem Ipsum" /><input type="text" id="city" name="city" placeholder="Lorem Ipsum" /></span>
	                <span class="msg"><span></span></span>
	            </li>
	            
	            --%>
				
	            <formElement:checkbox
	            	path="dummyField8"
	            	id="dummyField8a"
	            	label="Standalone checkbox 1 - sharing bean with 2."
	            	labelKey=""
	            	value="true"
	            	cssClass=""
	            	msg="Just an info."
	            	msgType="info"
                    tooltip="Some dummy information."
	            	tooltipKey=""
	            	required="false" />
				
	            <formElement:checkbox
	            	path="dummyField8"
	            	id="dummyField8b"
	            	label="Standalone checkbox 2 - sharing bean with 1."
	            	labelKey=""
	            	value="false"
	            	cssClass=""
	            	msg="Just an info."
	            	msgType="info"
                    tooltip="Some dummy information."
	            	tooltipKey=""
	            	required="false" />
				
	            <formElement:checkbox
	            	path="dummyField7"
	            	id="dummyField7"
	            	label="Standalone checkbox 3 - separate bean."
	            	labelKey=""
	            	value="false"
	            	cssClass=""
	            	msg="Just an info."
	            	msgType="info"
                    tooltip="Some dummy information."
	            	tooltipKey=""
	            	required="false" />
				
	            <formElement:checkboxes
	            	path="dummyField6"
	            	options="${dummyOptions}"
	            	optionValueAttr=""
	            	optionLabelAttr=""
	            	optionTooltipAttr="name"
	            	cssClass=""
	            	msg="Just an info."
	            	msgType="info"
	            	required="true" />	            	
				
	            <formElement:radiobutton
	            	path="dummyField2"
	            	id="dummyField2a"
	            	label="Standalone radio box 1 - sharing bean with 2."
	            	labelKey=""
	            	value="true"
	            	cssClass=""
	            	msg="Warning!"
	            	msgType="warning"
                    tooltip="Some dummy information."
	            	tooltipKey=""
	            	required="false" />
				
	            <formElement:radiobutton
	            	path="dummyField2"
	            	id="dummyField2b"
	            	label="Standalone radio box 2 - sharing bean with 1."
	            	labelKey=""
	            	value="false"
	            	cssClass=""
	            	msg="Warning!"
	            	msgType="warning"
                    tooltip="Some dummy information."
	            	tooltipKey=""
	            	required="false" />
				
	            <formElement:radiobutton
	            	path="dummyField3"
	            	id="dummyField3"
	            	label="Standalone radio box 3 - separate bean."
	            	labelKey=""
	            	value="false"
	            	cssClass=""
	            	msg="Warning!"
	            	msgType="warning"
                    tooltip="Some dummy information."
	            	tooltipKey=""
	            	required="false" />
				
	            <formElement:radiobuttons
	            	path="dummyField5"
	            	options="${dummyOptions}"
	            	optionValueAttr=""
	            	optionLabelAttr=""
	            	optionTooltipAttr="code"
	            	cssClass=""
	            	msg="Warning!"
	            	msgType="warning"
	            	required="true" />
	         
				<formElement:legaltext
					text="Alle mit * gekennzeichneten Felder sind Pflichtfelder."
					textKey=""
					cssClass="" />
	         
				<formElement:link
					text="Passwort vergessen?"
					textKey=""
					url="#"
					title="Wir senden Ihnen ein neues Passwort zu."
					titleKey=""
					cssClass="" />
	         
				<formElement:html
					label="Irgendwelche HTML-Daten:"
					labelKey=""
					html="foobar"
					cssClass=""
					msg=""
					msgType=""
					tooltip="Some dummy information."
	            	tooltipKey=""
					required="true" />
					
				<formElement:button
					name="submitForm"
					id="submitFormButton"
					type="submit"
					text="Speichern"
					textKey=""
					cssClass=""
					msg=""
					msgType=""
					tooltip="foo"
	            	tooltipKey=""
					label=""
	            	labelKey="" />
	    
	         </ul>
	     
	     </form:form>
	        
	</div>
	
</template:pageRegular>
