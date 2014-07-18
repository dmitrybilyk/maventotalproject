<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-delivery-address">
    <div class="inner">

        <p class="intro"><spring:theme code="form.deliveryAddress.intro" /></p>

        <ul class="addresses">

            <li class="existing selected">
                <div class="address">
                    <common:address
                            prename="Frank"
                            surname="Muster"
                            street="Seeblick"
                            houseNumber="23"
                            zip="65789"
                            city="Hamburg-Elbdorf"
                            country="Deutschland" />

                    <ul class="actions">
                        <li class="use">
                            <button type="submit" name="use">
                                <spring:theme code="form.deliveryAddress.existingAddressSelected.btnUse" />
                            </button>
                        </li>
                        <li class="edit">
                            <button type="button" name="edit">
                                <spring:theme code="form.deliveryAddress.existingAddressSelected.btnEdit" />
                            </button>
                        </li>
                        <li class="remove">
                            <button type="button" name="remove">
                                <spring:theme code="form.deliveryAddress.existingAddressSelected.btnRemove" />
                            </button>
                        </li>
                    </ul>
                </div>
            </li>

            <li class="existing">
                <div class="address">
                    <common:address
                            prename="Theresa"
                            surname="Timmermann"
                            company="Bluesky Reisen GmbH"
                            street="Hauptstra&szlig;e"
                            houseNumber="233"
                            zip="65723"
                            city="Hamburg"
                            country="Deutschland" />

                    <ul class="actions">
                        <li class="use">
                            <button type="submit" name="use">
                                <spring:theme code="form.deliveryAddress.existingAddress.btnUse" />
                            </button>
                        </li>
                        <li class="edit">
                            <button type="button" name="edit">
                                <spring:theme code="form.deliveryAddress.existingAddress.btnEdit" />
                            </button>
                        </li>
                        <li class="remove">
                            <button type="button" name="remove">
                                <spring:theme code="form.deliveryAddress.existingAddress.btnRemove" />
                            </button>
                        </li>
                    </ul>
                </div>
            </li>

            <li class="new">
                <div class="address">

                    <span class="title"><spring:theme code="form.deliveryAddress.newAddress.title" /></span>

                    <ul class="input-list">

                        <formElement:select
                                path="dummyField"
                                id="salutation"
                                label=""
                                labelKey="form.deliveryAddress.salutation.label"
                                selectMsg=""
                                selectMsgKey="form.deliveryAddress.salutation.selectMsg"
                                options="${dummyOptions}"
                                optionValueAttr=""
                                optionLabelAttr=""
                                cssClass=""
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.deliveryAddress.salutation.tooltip"
                                required="true" />

                        <formElement:text
                                path="dummyField"
                                id="prename"
                                label=""
                                labelKey="form.deliveryAddress.prename.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.deliveryAddress.prename.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.deliveryAddress.prename.tooltip"
                                required="true" />

                        <formElement:text
                                path="dummyField"
                                id="surname"
                                label=""
                                labelKey="form.deliveryAddress.surname.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.deliveryAddress.surname.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.deliveryAddress.surname.tooltip"
                                required="true" />

                        <formElement:text
                                path="dummyField"
                                id="company"
                                label=""
                                labelKey="form.deliveryAddress.company.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.deliveryAddress.company.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.deliveryAddress.company.tooltip"
                                required="false" />

                        <formElement:text
                                path="dummyField"
                                id="street"
                                label=""
                                labelKey="form.deliveryAddress.street.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.deliveryAddress.street.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.deliveryAddress.street.tooltip"
                                required="true" />

                        <formElement:text
                                path="dummyField"
                                id="zip"
                                label="PLZ"
                                labelKey="form.deliveryAddress.zip.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.deliveryAddress.zip.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.deliveryAddress.zip.tooltip"
                                required="true" />

                        <formElement:text
                                path="dummyField"
                                id="city"
                                label=""
                                labelKey="form.deliveryAddress.city.label"
                                cssClass=""
                                placeholder=""
                                placeholderKey="form.deliveryAddress.city.placeholder"
                                msg=""
                                msgType=""
                                tooltip=""
                                tooltipKey="form.deliveryAddress.city.tooltip"
                                required="true" />

                    </ul>
                    <ul class="actions">
                        <li class="use">
                            <button type="submit" name="use">
                                <spring:theme code="form.deliveryAddress.newAddress.btnUse" />
                            </button>
                        </li>
                    </ul>

                </div>

            </li>


        </ul>

    </div>
</div>