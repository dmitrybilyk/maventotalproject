<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="mod-delivery-address">
    <div class="inner">
        <c:if test="${(not empty form.suitableShipmentAddressModels) or (not empty form.unsuitableShipmentAddressModels)}" >
            <ul class="addresses">
                <p class="title-address"><spring:theme code="form.deliveryAddress.existingAddress.title" /></p>
                <c:forEach items="${form.suitableShipmentAddressModels}" var="addessModel" >
                    <li class="existing">
                        <div class="address">
                            <common:address
                                    salutation="${addessModel.salutation}"
                                    title="${addessModel.title}"
                                    titleCode="${addessModel.titleCode}"
                                    prename="${addessModel.firstname}"
                                    surname="${addessModel.lastname}"
                                    email="${addessModel.email}"
                                    street="${addessModel.street}"
                                    houseNumber="${addessModel.houseNumber}"
                                    additionalStreet="${addessModel.additionalStreet}"
                                    zip="${addessModel.postalcode}"
                                    city="${addessModel.town}"
                                    country="${addessModel.country}"
                                    countryCode="${addessModel.countryCode}"
                                    company="${addessModel.company}" />

                            <ul class="actions">
                                <li class="use">
                                    <button type="submit" name="use">
                                        <spring:theme code="form.deliveryAddress.existingAddressSelected.btnUse" />
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </li>
                </c:forEach>

                <c:forEach items="${form.unsuitableShipmentAddressModels}" var="addessModel" >
                    <li class="existing has-msg-error">
                        <div class="address">
                            <common:address
                                    salutation="${addessModel.salutation}"
                                    title="${addessModel.title}"
                                    prename="${addessModel.firstname}"
                                    surname="${addessModel.lastname}"
                                    email="${addessModel.email}"
                                    street="${addessModel.street}"
                                    houseNumber="${addessModel.houseNumber}"
                                    additionalStreet="${addessModel.additionalStreet}"
                                    zip="${addessModel.postalcode}"
                                    city="${addessModel.town}"
                                    country="${addessModel.country}"
                                    countryCode="${addessModel.countryCode}"
                                    company="${addessModel.company}" />

                            <span class="msg error"><spring:theme code="form.deliveryAddress.existingAddress.unsuitable.msg" /></span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

    </div>
</div>