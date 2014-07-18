/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package com.cgi.pacoshop.core.service.security;

import com.cgi.pacoshop.core.daos.impl.DeeplinkFingerprintSecretDaoImpl;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.DeeplinkNotSecureException;
import com.cgi.pacoshop.core.exceptions.deeplink.status403.DeeplinkCompromizedException;
import com.cgi.pacoshop.core.model.FingerprintSecretModel;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.security.impl.DeeplinkFingerprintSecretServiceImpl;
import com.cgi.pacoshop.core.service.security.impl.DeeplinkSecurityServiceImpl;
import com.cgi.pacoshop.core.strategies.FingerprintCalculationStrategy;
import com.cgi.pacoshop.core.strategies.impl.DefaultFingerprintCalculationStrategy;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * Unit test for DeeplinkSecurityService.
 *
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @version 1.0v Jan 30 , 2014
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/"
 *
 */
@UnitTest
public class DeeplinkSecurityServiceTest
{

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	private HttpServletRequest request;

	@Mock
	private DeeplinkSecurityServiceImpl deeplinkSecurityService;

	@Mock
	private DeeplinkFingerprintSecretServiceImpl deeplinkFingerprintSecretService;

	@Mock
	private FingerprintSecretModel deeplinkFingerprintModel;

	@Mock
	private DeeplinkFingerprintSecretDaoImpl deeplinkFingerprintDao;

	private DefaultFingerprintCalculationStrategy fingerprintCalculationStrategy;

	private String fingerprintSecretNo;
	private String secret1;
	private String portalFingerprint;

	/**
	 * Setup test.
	 *
	 * @throws Exception if Exception occurs
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		fingerprintCalculationStrategy = new DefaultFingerprintCalculationStrategy();
		//Generated shahex hash for {productId = 1; secretNO=1; secret=emjp7n95Wavw}
		portalFingerprint = "c773045e47f16bbf7abe677436c91177700db410";
	}

	/**
	 * Test security deeplink.
	 */
	@Test
	public void testDeeplinkSecurityUtil()
	{
		fingerprintSecretNo = "1";
		secret1 = "emjp7n95Wavw";
		portalFingerprint = "c773045e47f16bbf7abe677436c91177700db410";

		when(request.getParameterMap()).thenReturn(newRequestParamMap("product1", fingerprintSecretNo, portalFingerprint));
		when(deeplinkFingerprintModel.getSecret()).thenReturn(secret1);
		when(deeplinkFingerprintModel.getFingerPrintSecretNo()).thenReturn(fingerprintSecretNo);
		when(request.getParameter("productid")).thenReturn("product1");
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM)).thenReturn(portalFingerprint);
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_SECRET_NO_REQUEST_PARAM)).thenReturn(fingerprintSecretNo);

		final Object calculatedFingerprint = fingerprintCalculationStrategy.calculateDeeplinkFingerprint(
				  request, deeplinkFingerprintModel.getSecret());
		assertNotNull("Calculated DeeplinkFingerprint should not be null", calculatedFingerprint);
		assertEquals("Calculated and portal fingerprint should be equal", portalFingerprint, calculatedFingerprint);
	}

	/**
	 * Test deeplink security service on success.
	 */
	@Test
	public void testDeeplinkSecurityServiceOnSuccess()
	{
		fingerprintSecretNo = "1";
		secret1 = "emjp7n95Wavw";

		when(request.getParameterMap()).thenReturn(newRequestParamMap("product1", fingerprintSecretNo, portalFingerprint));
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM)).thenReturn(portalFingerprint);
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_SECRET_NO_REQUEST_PARAM)).thenReturn(fingerprintSecretNo);
		when(request.getParameter("productid")).thenReturn("product1");
		when(deeplinkFingerprintDao.getFingerprintSecretByNumber(fingerprintSecretNo)).thenReturn(deeplinkFingerprintModel);
		when(deeplinkFingerprintSecretService.getFingerprintSecretByNumber(fingerprintSecretNo)).thenReturn(
				  deeplinkFingerprintModel);
		when(deeplinkFingerprintModel.getSecret()).thenReturn(secret1);
		when(deeplinkFingerprintModel.getFingerPrintSecretNo()).thenReturn(fingerprintSecretNo);
		when(deeplinkSecurityService.getDeeplinkFingerprintSecretService()).thenReturn(deeplinkFingerprintSecretService);
		when(deeplinkSecurityService.getFingerprintCalculationStrategy()).thenReturn(fingerprintCalculationStrategy);
		doCallRealMethod().when(deeplinkSecurityService).validate(request);

		deeplinkSecurityService.validate(request);
	}

	/**
	 * Test deeplink security service on fail.
	 */
	@Test
	public void testDeeplinkSecurityServiceOnFail()
	{
		fingerprintSecretNo = "1";
		secret1 = "emjp7n95WavwXXX";

		expectedException.expect(DeeplinkCompromizedException.class);

		when(request.getParameterMap()).thenReturn(newRequestParamMap("product1", fingerprintSecretNo, portalFingerprint));
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM)).thenReturn(portalFingerprint);
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_SECRET_NO_REQUEST_PARAM)).thenReturn(fingerprintSecretNo);
		when(deeplinkFingerprintModel.getSecret()).thenReturn(secret1);
		when(deeplinkFingerprintModel.getFingerPrintSecretNo()).thenReturn(fingerprintSecretNo);
		when(deeplinkFingerprintDao.getFingerprintSecretByNumber(fingerprintSecretNo)).thenReturn(deeplinkFingerprintModel);
		when(deeplinkFingerprintSecretService.getFingerprintSecretByNumber(anyString())).thenReturn(deeplinkFingerprintModel);
		when(deeplinkSecurityService.getDeeplinkFingerprintSecretService()).thenReturn(deeplinkFingerprintSecretService);
		when(deeplinkSecurityService.getFingerprintCalculationStrategy()).thenReturn(fingerprintCalculationStrategy);
		doCallRealMethod().when(deeplinkSecurityService).validate(request);

		deeplinkSecurityService.validate(request);
	}

	/**
	 * Test security validatation.
	 */
	@Test
	public void testSecurityValidateOnNullParams()
	{
		when(request.getParameterMap()).thenReturn(newRequestParamMap("product1", "", ""));
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM)).thenReturn("");
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_SECRET_NO_REQUEST_PARAM)).thenReturn("");
		doCallRealMethod().when(deeplinkSecurityService).validate(request);

		expectedException.expect(DeeplinkNotSecureException.class);
		deeplinkSecurityService.validate(request);
	}

	/**
	 * Test security validation on invalid secret.
	 */
	@Test
	public void testSecurityValidateOnInvalidSecretNO()
	{
		fingerprintSecretNo = "11111111";
		portalFingerprint = "ShlaSashaPoShose";

		when(request.getParameterMap()).thenReturn(newRequestParamMap("1",fingerprintSecretNo, portalFingerprint));
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM)).thenReturn(portalFingerprint);
		when(request.getParameter(ShopConfigurationService.FINGERPRINT_SECRET_NO_REQUEST_PARAM)).thenReturn(fingerprintSecretNo);
		when(deeplinkSecurityService.getDeeplinkFingerprintSecretService()).thenReturn(deeplinkFingerprintSecretService);
		when(deeplinkFingerprintDao.getFingerprintSecretByNumber(anyString())).thenThrow(new ModelNotFoundException("test"));
		when(deeplinkFingerprintSecretService.getFingerprintSecretByNumber(fingerprintSecretNo)).thenReturn(null);
		doCallRealMethod().when(deeplinkSecurityService).validate(request);

		expectedException.expect(DeeplinkCompromizedException.class);
		deeplinkSecurityService.validate(request);
	}



	private Map newRequestParamMap(final String productId, final String secretNO, final String fingerprint)
	{
		return new HashMap()
		{
			{
				put("productid", productId);
				put(ShopConfigurationService.FINGERPRINT_SECRET_NO_REQUEST_PARAM, secretNO);
				put(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM, fingerprint);
			}
		};
	}
}
