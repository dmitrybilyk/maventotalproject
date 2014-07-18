<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<div class="mod-recommended-items">
    <ul class="input-list">

        <spring:theme code="checkout.recommendedItems.heading" var="textRecommendItems" />
        <formElement:infotext
                text="${textRecommendItems}"
                cssClass="" />

    </ul>

    <div class="items-list">
        <div class="item">
            <a class="image" href="#">
                <img src="/_ui/desktop/common/images//surfer-magazin-2.gif" alt="" />
            </a>
            <strong><spring:theme code="checkout.recommendedItems.magazine.label" /></strong>
            <span><spring:theme code="checkout.recommendedItems.magazine.description" /></span>
        </div>
        <div class="item">
            <a class="image" href="#">
                <img src="/_ui/desktop/common/images//surfer-magazin-2.gif" alt="" />
            </a>
            <strong><spring:theme code="checkout.recommendedItems.magazine.label" /></strong>
            <span><spring:theme code="checkout.recommendedItems.magazine.description" /></span>
        </div>
        <div class="item">
            <a class="image" href="#">
                <img src="/_ui/desktop/common/images//surfer-magazin-2.gif" alt="" />
            </a>
            <strong><spring:theme code="checkout.recommendedItems.magazine.label" /></strong>
            <span><spring:theme code="checkout.recommendedItems.magazine.description" /></span>
        </div>
    </div>

</div>
