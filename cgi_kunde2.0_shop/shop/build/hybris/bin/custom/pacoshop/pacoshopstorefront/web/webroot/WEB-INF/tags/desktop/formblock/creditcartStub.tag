<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mod-payment-method-direct-debit">
    <div class="inner">

        <div class="tabs">

            <%-- TODO: Set this ul after implementation tabs --%>
            <ul style="display: none;">
                <li><a href="#bank-bic">iban / BIC</a></li>
                <li><a href="#bank-kto">KTO / BLZ</a></li>
            </ul>

            <div>
                <ul class="input-list">



                    <%--<formElement:text--%>
                            <%--path=""--%>
                            <%--id="creditcart-account-prename"--%>
                            <%--label=""--%>
                            <%--labelKey="form.creditcart.creditCartFirstName.label"--%>
                            <%--cssClass=""--%>
                            <%--placeholder=""--%>
                            <%--placeholderKey="form.creditcart.creditCartFirstName.placeholder"--%>
                            <%--msg=""--%>
                            <%--msgType=""--%>
                            <%--tooltip=""--%>
                            <%--tooltipKey="form.creditcart.creditCartFirstName.tooltip"--%>
                            <%--required="false" />--%>

                    <%--<formElement:text--%>
                            <%--path=""--%>
                            <%--id="creditcart-account-surname"--%>
                            <%--label=""--%>
                            <%--labelKey="form.creditcart.creditCartLastName.label"--%>
                            <%--cssClass=""--%>
                            <%--placeholder=""--%>
                            <%--placeholderKey="form.creditcart.creditCartLastName.placeholder"--%>
                            <%--msg=""--%>
                            <%--msgType=""--%>
                            <%--tooltip=""--%>
                            <%--tooltipKey="form.creditcart.creditCartLastName.tooltip"--%>
                            <%--required="false" />--%>

                    <%--<formElement:text--%>
                            <%--path=""--%>
                            <%--id="creditcart-account-cartnumber"--%>
                            <%--label=""--%>
                            <%--labelKey="form.creditcart.cartNumber.label"--%>
                            <%--cssClass=""--%>
                            <%--placeholder=""--%>
                            <%--placeholderKey="form.creditcart.cartNumber.placeholder"--%>
                            <%--msg=""--%>
                            <%--msgType=""--%>
                            <%--tooltip=""--%>
                            <%--tooltipKey="form.creditcart.cartNumber.tooltip"--%>
                            <%--required="false" />--%>

                    <%--<formElement:text--%>
                            <%--path=""--%>
                            <%--id="creditcart-account-validthru"--%>
                            <%--label=""--%>
                            <%--labelKey="form.creditcart.validThru.label"--%>
                            <%--cssClass=""--%>
                            <%--placeholder=""--%>
                            <%--placeholderKey="form.creditcart.validThru.placeholder"--%>
                            <%--msg=""--%>
                            <%--msgType=""--%>
                            <%--tooltip=""--%>
                            <%--tooltipKey="form.creditcart.validThru.tooltip"--%>
                            <%--required="false" />--%>



                            <li class="has-text has-field-firstName">
                                <span class="label">
                                    <label for="">Vorname</label>
                                </span>
                                <span class="field">
                                    <input type="text" value="" name="" id=""><span class="tooltip" title=""></span>
                                </span>
                                <span class="msg ">
                                    <span></span>
                                </span>
                            </li>


                            <li class="has-text has-field-lastName">
                                <span class="label">
                                    <label for="">Nachname</label>
                                </span>
                                <span class="field">
                                    <input type="text" value="" name="" id=""><span class="tooltip" title=""></span>
                                </span>
                                <span class="msg ">
                                    <span></span>
                                </span>
                            </li>


                            <li class="has-text has-field-cartNumber has-tooltip">
                                <span class="label">
                                    <label for="">Kartennummer</label>
                                </span>
                                <span class="field">
                                    <input type="text" value="" name="" id=""><span class="tooltip" title="Kartennummer"></span>
                                </span>
                                <span class="msg ">
                                    <span></span>
                                </span>
                            </li>


                            <li class="has-text has-field-validThru has-tooltip">
                                <span class="label">
                                    <label for="">Ablaufdatum</label>
                                </span>
                                <span class="field">
                                    <input type="text" value="" name="" id=""><span class="tooltip" title="Ablaufdatum"></span>
                                </span>
                                <span class="msg ">
                                    <span></span>
                                </span>
                            </li>


                </ul>

            </div>

        </div>

    </div>
</div>