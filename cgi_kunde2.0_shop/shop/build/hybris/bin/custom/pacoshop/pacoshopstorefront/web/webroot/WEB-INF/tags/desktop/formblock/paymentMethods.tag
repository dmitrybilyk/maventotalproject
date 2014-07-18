<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="formblock" tagdir="/WEB-INF/tags/desktop/formblock" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mod-payment-methods">
    <div class="inner">

        <p><spring:theme code="form.paymentMethods.title"/></p>

        <ul class="input-list">
            <c:forEach var="mode" items="${paymentContainer.modes}" varStatus="modeStatus">

                <li class="has-radio has-field-paymentMode">
                    <span class="field radio">
                        <input type="radio" name="paymentMode" id="payment-api-mode-${mode.type}" value="${mode.type}" <c:if test="${not usingAvailablePaymentInfo and mode.type eq paymentContainer.paymentInfo.paymentModeType}">checked="checked"</c:if> />
                        <label for="payment-api-mode-${mode.type}"><spring:theme code="form.paymentMethods.${mode.type}.label"/></label>
                    </span>
                </li>

                <c:choose>

                    <%-- Credit card input --%>
                    <c:when test="${mode.type eq 'creditcard'}">
                        <li class="payment-method-details payment-method-creditcard payment-api-fields">
                            <div class="payment-inner">
                                <ul class="input-list has-form">

                                    <li class="has-select">
                                        <span class="label">
                                            <label for="payment-api-${mode.type}-methodCode"><spring:theme code='form.paymentMethodCreditCard.cartType'/></label>
                                        </span>
                                        <span class="field">
                                            <select id="payment-api-${mode.type}-methodCode" name="methodCode">
                                                <option value=""><spring:theme code='form.paymentMethodCreditCard.cartTypeSelect'/></option>
                                                <c:forEach var="method" items="${mode.methods}" varStatus="methodStatus">
                                                    <option value="${method.code}" data-mode-type="${method.type}" data-requires-issuer="${method.parameters.requiresIssuer}" <c:if test="${method.code eq paymentContainer.paymentInfo.paymentMethodCode}">selected="selected"</c:if>>${method.name}</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                        <span class="msg error"></span>
                                    </li>

                                    <li class="has-text">
                                        <span class="label">
                                            <label for="payment-api-${mode.type}-name"><spring:theme code='form.paymentMethodCreditCard.cartName'/></label>
                                        </span>
                                        <span class="field">
                                            <input type="text" id="payment-api-${mode.type}-name" name="name" value="${paymentContainer.paymentInfo[mode.type].name}" />
                                        </span>
                                        <span class="msg error"></span>
                                    </li>

                                    <li class="has-text">
                                        <span class="label">
                                            <label for="payment-api-${mode.type}-no"><spring:theme code='form.paymentMethodCreditCard.cartNo'/></label>
                                        </span>
                                        <span class="field">
                                            <input type="tel" id="payment-api-${mode.type}-no" name="no" value="${paymentContainer.paymentInfo[mode.type].no}" pattern="[0-9]*" maxlength="16" />
                                        </span>
                                        <span class="msg error"></span>
                                    </li>

                                    <li class="has-text">
                                        <span class="label">
                                            <label for="payment-api-${mode.type}-cvv"><spring:theme code='form.paymentMethodCreditCard.cartCVV'/></label>
                                        </span>
                                        <span class="field">
                                            <input type="tel" id="payment-api-${mode.type}-cvv" name="cvv" value="${paymentContainer.paymentInfo[mode.type].cvv}" pattern="[0-9]*" maxlength="3" />
                                        </span>
                                        <span class="msg error"></span>
                                    </li>

                                    <li class="has-dateFields cardDate">
                                        <span class="label multiple multiple-2">
                                            <label for="payment-api-${mode.type}-expMonth"><spring:theme code='form.paymentMethodCreditCard.cartDate'/></label>
                                        </span>
                                        <span class="field multiple multiple-2">
                                            <select id="payment-api-${mode.type}-expMonth" name="expMonth">
                                                <option value=""></option>
                                                <c:forEach var="month" items="${months}" varStatus="monthStatus">
                                                    <option value="${month.key}" <c:if test="${month.key eq paymentContainer.paymentInfo[mode.type].expMonth}">selected="selected"</c:if>>${month.value}</option>
                                                </c:forEach>
                                            </select>
                                            <select id="payment-api-${mode.type}-expYear" name="expYear">
                                                <option value=""></option>
                                                <c:forEach var="year" items="${expiryYears}" varStatus="yearStatus">
                                                    <option value="${year.key}" <c:if test="${year.key eq paymentContainer.paymentInfo[mode.type].expYear}">selected="selected"</c:if>>${year.value}</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                        <span class="msg error"></span>
                                    </li>

                                    <li class="has-text issNo">
                                        <span class="label">
                                            <label for="payment-api-${mode.type}-issNo"><spring:theme code="form.paymentMethods.type.issNo" /></label>
                                        </span>
                                        <span class="field">
                                            <input type="tel" id="payment-api-${mode.type}-issNo" name="issNo" value="${paymentContainer.paymentInfo[mode.type].issNo}" pattern="[0-9]*" />
                                        </span>
                                        <span class="msg error"></span>
                                    </li>

                                    <li class="has-dateFields issueDate">
                                        <span class="label multiple multiple-2">
                                            <label for="payment-api-${mode.type}-issMonth"><spring:theme code="form.paymentMethods.type.issMonth" /></label>
                                        </span>
                                        <span class="field multiple multiple-2">
                                            <select id="payment-api-${mode.type}-issMonth" name="issMonth">
                                                <option value=""></option>
                                                <c:forEach var="month" items="${months}" varStatus="monthStatus">
                                                    <option value="${month.key}" <c:if test="${month.key} eq paymentContainer.paymentInfo[mode.type].issMonth}">selected="selected"</c:if>>${month.value}</option>
                                                </c:forEach>
                                            </select>
                                            <select id="payment-api-${mode.type}-issYear" name="issYear">
                                                <option value=""></option>
                                                <c:forEach var="year" items="${startYears}" varStatus="yearStatus">
                                                    <option value="${year.key}" <c:if test="${year.key eq paymentContainer.paymentInfo[mode.type].expYear}">selected="selected"</c:if>>${year.value}</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                    </li>

                                </ul>
                            </div>
                        </li>
                    </c:when>

                    <%-- Direct debit input --%>
                    <c:when test="${mode.type eq 'directdebit'}">

                        <li id="payment-method-directdebit" class="payment-method-details payment-method-directdebit payment-api-fields">
                            <div class="inner">
                                <div class="tabs">
                                    <ul>
                                        <li class="<c:if test="${'WC_SEPA-DD' eq paymentContainer.paymentInfo.paymentMethodCode}">ui-tabs-active</c:if>">
                                            <a href="#bank-bic" data-directdebit="<c:if test="${'internal_directdebit' ne mode.methods[0].code}">WC_SEPA-DD</c:if>"/>
                                                <spring:theme code="form.referal.referalTabs.IBAN_BIC" />
                                            </a>
                                        </li>
                                        <li class="<c:if test="${('WC_ELV' eq paymentContainer.paymentInfo.paymentMethodCode)
                                                            || (paymentContainer.paymentInfo.paymentMethodCode eq 'internal_directdebit'
                                                                && not empty paymentContainer.paymentInfo[mode.type].bankCode)}">ui-tabs-active</c:if>">
                                            <a href="#bank-kto" data-directdebit="<c:if test="${'internal_directdebit' ne mode.methods[0].code}">WC_ELV</c:if>"/>
                                                <spring:theme code="form.referal.referalTabs.Kontonummer_BLZ" />
                                            </a>
                                        </li>
                                    </ul>
                                    <ul class="input-list has-form">
                                        <li class="has-text">
                                            <span class="label">
                                                <label for="payment-api-${mode.type}-accountOwner"><spring:theme code='form.paymentMethodDebitCard.name'/>*</label>
                                            </span>
                                            <span class="field">
                                                <input type="text" id="payment-api-${mode.type}-accountOwner" name="accountOwner" value="${paymentContainer.paymentInfo[mode.type].accountOwner}" />
                                            </span>
                                            <span class="msg error"></span>
                                        </li>

                                            <li class="has-text" <c:if test="${'internal_directdebit' ne mode.methods[0].code}"> style="display: none" </c:if>>
                                                <span class="label">
                                                    <label for="payment-api-${mode.type}-bankName"><spring:theme code='form.paymentMethodDebitCard.nameBank'/>*</label>
                                                </span>
                                                <span class="field">
                                                    <input type="text" id="payment-api-${mode.type}-bankName" name="bankName" value="${paymentContainer.paymentInfo[mode.type].bankName}" />
                                                </span>
                                                <span class="msg error"></span>
                                            </li>
                                    </ul>

                                    <ul class="input-list tab-list" id="bank-bic">
                                        <li class="has-text has-tooltip" >
                                            <span class="label">
                                                <label for="payment-api-${mode.type}-iban"><spring:theme code='form.paymentMethodDebitCard.iban'/>*</label>
                                            </span>
                                            <span class="field">
                                                <input type="text" id="payment-api-${mode.type}-iban" name="iban" value="${paymentContainer.paymentInfo[mode.type].iban}" />
                                                <span class="tooltip"><spring:theme code='form.paymentMethodDebitCard.ibanTooltip'/></span>
                                            </span>
                                            <span class="msg error"></span>
                                        </li>

                                        <li class="has-text has-tooltip">
                                            <span class="label">
                                                <label for="payment-api-${mode.type}-bic"><spring:theme code='form.paymentMethodDebitCard.bic'/>*</label>
                                            </span>
                                            <span class="field">
                                                <input type="text" id="payment-api-${mode.type}-bic" name="bic" value="${paymentContainer.paymentInfo[mode.type].bic}" />
                                                <span class="tooltip"><spring:theme code='form.paymentMethodDebitCard.bicTooltip'/></span>
                                            </span>
                                            <span class="msg error"></span>
                                        </li>
                                    </ul>

                                    <ul class="input-list tab-list" id="bank-kto">
                                            <c:if test="${'internal_directdebit' ne mode.methods[0].code}">
                                                <li class="has-select">
                                                    <span class="label">
                                                        <label for="payment-api-${mode.type}-bankCountry"><spring:theme code='form.paymentMethodDebitCard.landBank'/>*</label>
                                                    </span>
                                                    <span class="field">
                                                        <select id="payment-api-${mode.type}-bankCountry" name="bankCountry">
                                                            <c:forEach var="country" items="${billingCountries}">
                                                                <c:choose>
                                                                    <c:when test="${empty paymentContainer.paymentInfo[mode.type].bankCountry}">
                                                                        <option value="${fn:toLowerCase(country.isocode)}" <c:if test="${fn:toLowerCase(country.isocode) eq 'de'}">selected="selected"</c:if>>${country.name}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${fn:toLowerCase(country.isocode)}" <c:if test="${fn:toLowerCase(country.isocode) eq paymentContainer.paymentInfo[mode.type].bankCountry}">selected="selected"</c:if>>${country.name}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </span>
                                                    <span class="msg error"></span>
                                                </li>
                                            </c:if>
                                        <li class="has-text">
                                            <span class="label">
                                                <label for="payment-api-${mode.type}-bankCode"><spring:theme code='form.paymentMethodDebitCard.bankCode'/>*</label>
                                            </span>
                                            <span class="field">
                                                <input type="text" id="payment-api-${mode.type}-bankCode" name="bankCode" value="${paymentContainer.paymentInfo[mode.type].bankCode}" />
                                            </span>
                                            <span class="msg error"></span>
                                        </li>

                                        <li class="has-text">
                                            <span class="label">
                                                <label for="payment-api-${mode.type}-accountCode"><spring:theme code='form.paymentMethodDebitCard.accountNumber'/>*</label>
                                            </span>
                                            <span class="field">
                                                <input type="text" id="payment-api-${mode.type}-accountCode" name="accountCode" value="${paymentContainer.paymentInfo[mode.type].accountCode}" />
                                            </span>
                                            <span class="msg error"></span>
                                        </li>
                                    </ul>

                                    <ul class="input-list has-form">
                                        <li class="has-infotext ">
                                            <span class="infotext"><spring:theme code="form.paymentMethodDirectDebit.moreInformation" /></span>
                                        </li>

                                    </ul>

                                </div>
                            </div>

                        </li>
                    </c:when>

                    <%-- sofortueberweisung --%>
                    <c:when test="${mode.type eq 'sofortueberweisung'}">
                    <li class="payment-method-details payment-method-sofortueberweisung payment-api-fields">
                        <div class="payment-inner">
                            <ul class="input-list has-text">
                                <li><spring:theme code='form.paymentSofortueberweisung.text'/></li>
                            </ul>
                        </div>
                    </li>
                    </c:when>

                    <%-- paypal --%>
                    <c:when test="${mode.type eq 'paypal'}">
                        <li class="payment-method-details payment-method-paypal payment-api-fields">
                            <div class="payment-inner">
                                <ul class="input-list has-text">
                                    <li><spring:theme code='form.paymentPaypal.text'/></li>
                                </ul>
                            </div>
                        </li>
                    </c:when>

                    <%-- payondelivery --%>
                    <c:when test="${mode.type eq 'payondelivery'}">
                        <li class="payment-method-details payment-method-payondelivery payment-api-fields">
                            <div class="payment-inner">
                                <ul class="input-list has-text">
                                    <li><spring:theme code="form.paymentPayondelivery.text" arguments="${paymentModeFeeMap[mode.type].formattedValue}" htmlEscape="false" argumentSeparator=";"/></li>
                                </ul>
                            </div>
                        </li>
                    </c:when>

                    <%-- invoice --%>
                    <c:when test="${mode.type eq 'invoice'}">
                        <li class="payment-method-details payment-method-invoice payment-api-fields">
                            <div class="payment-inner">
                                <ul class="input-list has-text">
                                    <li><spring:theme code='form.paymentInvoice.text'/></li>
                                </ul>
                            </div>
                        </li>
                    </c:when>

                </c:choose>

            </c:forEach>

        </ul>

    </div>
</div>



