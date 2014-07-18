<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--TODO formatted price and currency should come from DefaultPriceDataFactory. Not possible to do now--%>
<div class="mod-order-items">
    <div class="inner">

    <c:set var="isNewsletterDisplayed" value="${displaymappings['NewsletterFormElementGroup']}"/>

        <table>
            <thead>
            <tr>
                <th class="image"><spring:theme code="checkout.orderItems.imageTitle"/></th>
                <th class="details"><spring:theme code="checkout.orderItems.detailsTitle"/></th>
                <th class="amount"><spring:theme code="checkout.orderItems.amount"/></th>
                <th class="sum"><spring:theme code="checkout.orderItems.sumTitle"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cartContentsFragmentDTO.products}" var="product">
                <tr>
                    <td class="image" data-title="Ihre bestellten Artikel">
                        <c:if test="${product.url != ''}">
                            <img src="${product.url}" alt=""/>
                        </c:if>
                    </td>
                    <td class="details" data-title="Leistungen">
                        <h3>${product.name}</h3>
                            ${product.description}
                        <c:if test="${product.subscriptionInfoDisplayed}">
                            <checkout:subscprionProductDescription product="${product}"/>
                        </c:if>
                    </td>
                    <td class="amount" data-title="Menge">
                        <span>${product.quantity}</span>
                    </td>
                    <td class="sum" data-title="Preis">
                        <c:choose>
                            <c:when test="${product.price > 0 && !isNewsletterDisplayed}">
                                <span>${product.priceWithCurrency}</span>
                            </c:when>
                            <c:otherwise>
                                <span><spring:theme code="form.product.price.free"/></span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach items="${cartContentsFragmentDTO.products}" var="product">
                <c:if test="${not empty product.bonuses}" >
                    <c:forEach items="${product.bonuses}" var="bonus">
                        <c:set var="showBonusAdditionalPayment" value="${not empty bonus.additionalPaymentWithCurrency}" />

                        <tr>
                            <td class="image" data-title="Ihre bestellten Artikel">
                                <c:if test="${bonus.url != ''}">
                                    <img src="${bonus.url}" alt=""/>
                                </c:if>
                            </td>
                            <td class="details" data-title="Leistungen">
                                <h3>${bonus.name}</h3>
                                    ${bonus.description}
                                <c:if test="${showBonusAdditionalPayment}">
                                    <br>
                                    <spring:theme code="checkout.bonus.additionalpayment"/>
                                </c:if>
                            </td>
                            <td class="amount" data-title="Menge">
                                <span>${bonus.quantity}</span>
                            </td>
                            <c:if test="${showBonusAdditionalPayment}">
                                <td class="sum" data-title="Preis">
                                        <span>${bonus.additionalPaymentWithCurrency}</span>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
