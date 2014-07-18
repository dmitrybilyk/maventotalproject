<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="mod-student">
    <div class="inner">

        <p><spring:theme code="form.student.heading" /></p>

        <ul class="input-list">

            <formElement:dateFields
                    pathDay="graduationDay"
                    pathMonth="graduationMonth"
                    pathYear="graduationYear"
                    idDay="graduationDay"
                    idMonth="graduationMonth"
                    idYear="graduationYear"
                    label=""
                    labelKey="form.student.graduation.date.label"
                    cssClass=""
                    placeholderDay=""
                    placeholderDayKey="form.userdata.placeholderDay"
                    placeholderMonth=""
                    placeholderMonthKey="form.userdata.placeholderMonth"
                    placeholderYear=""
                    placeholderYearKey="form.userdata.placeholderYear"
                    msg=""
                    msgType=""
                    required="true" />
        </ul>

    </div>
</div>