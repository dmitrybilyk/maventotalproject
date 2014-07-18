<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<div class="mod-footer">
	<div class="inner">

		<cms:globalSlot uid="FooterSlot" var="feature" element="div">
			<cms:component component="${feature}"/>
		</cms:globalSlot>

        <%-- TODO: STATIC CONTENT --%>
        <%-- TODO: Remove after implementation CMS slot --%>
        <%--<div class="mod-cms mod-cms-footer">
            <div class="inner">
                <ul class="l1">
                    <li class="l1">
                        <h3 class="l1">Footer Nav Headline 1</h3>
                        <ul class="l2">
                            <li class="l2"><a href="#">Link 1</a>
                            </li>
                            <li class="l2"><a href="#">Link 2</a>
                            </li>
                            <li class="l2"><a href="#">Link 3</a>
                            </li>
                        </ul>
                    </li>
                    <li class="l1">
                        <h3 class="l1">Footer Nav Headline 2</h3>
                        <ul class="l2">
                            <li class="l2"><a href="#">Link 1</a>
                            </li>
                            <li class="l2"><a href="#">Link 2</a>
                            </li>
                            <li class="l2"><a href="#">Link 3</a>
                            </li>
                        </ul>
                    </li>
                    <li class="l1">
                        <h3 class="l1">Footer Nav Headline 3</h3>
                        <ul class="l2">
                            <li class="l2"><a href="#">Link 1</a>
                            </li>
                            <li class="l2"><a href="#">Link 2</a>
                            </li>
                            <li class="l2"><a href="#">Link 3</a>
                            </li>
                        </ul>
                    </li>
                    <li class="l1">
                        <h3 class="l1">Footer Nav Headline 4</h3>
                        <ul class="l2">
                            <li class="l2"><a href="#">Link 1</a>
                            </li>
                            <li class="l2"><a href="#">Link 2</a>
                            </li>
                            <li class="l2"><a href="#">Link 3</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>--%>


            <%--<spring:eval var="handelsblat" expression="@configurationService.configuration.getProperty('temporary.demo.handelsblatt')"/>
            <c:choose>
                <c:when test="${handelsblat}">
                    <ul class="nav">
                        <li>
                            <a href="https://angebot.handelsblatt.com/meta/impressum.html">Impressum</a>
                        </li>
                        <li>
                            <a href="https://angebot.handelsblatt.com/meta/geschaefts-und-nutzungsbedingungen.html">Gesch&auml;fts- und Nutzungsbedingungen</a>
                        </li>
                        <li>
                            <a href="http://www.handelsblatt.com/">Handelsblatt.com</a>
                        </li>
                    </ul>

                    <p class="copyright">
                        &copy; 2013 Handelsblatt GmbH - ein Unternehmen der Verlagsgruppe Handelsblatt GmbH &amp; Co. KG <br/>
                        Keine Gew&auml;hr f&uuml;r die Richtigkeit der Angaben. Bitte beachten Sie auch unsere Gesch&auml;fts- und Nutzungsbedingungen und das Impressum.
                    </p>
                </c:when>
                <c:otherwise>
                    <ul class="nav">
                        <li style="color: #ff6600;">
                            &copy; CGI in Deutschland
                        </li>
                        <li>
                            <a href="http://www.cgi-customer360.com/web/cgi_magazine/imprint">Impressum</a>
                        </li>
                        <li>
                            <a href="http://www.cgi-customer360.com/web/cgi_magazine/protection_of_privacy">Datenschutz</a>
                        </li>
                    </ul>
                </c:otherwise>
          </c:choose>--%>

    </div>
</div>