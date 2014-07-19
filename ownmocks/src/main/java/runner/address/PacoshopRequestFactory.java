///*
// * [y] hybris Platform
// *
// * Copyright (c) 2000-2013 hybris AG
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of hybris
// * ("Confidential Information"). You shall not disclose such Confidential
// * Information and shall use it only in accordance with the terms of the
// * license agreement you entered into with hybris.
// *
// *
// */
//package runner;
//
////import com.cgi.pacoshop.core.service.ShopConfigurationService;
//import static runner.util.LogHelper.error;
//import java.security.SecureRandom;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import javax.annotation.PostConstruct;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.X509TrustManager;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Required;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Component;
//
///**
// * Pacoshop Request Factory for REST clients that allows
// * to ignore server certificate errors. If invalid certificates
// * have to be ingnored is managed by the
//// * {@link com.cgi.pacoshop.core.service.ShopConfigurationService},
// * SECURITY_TRUST_ALL_HOSTNAMES property
// *
// *
// * @version 1.0v dec 24, 2013
// * @author symmetrics - a CGI Group brand <info@symmetrics.de>
// * @author Andrey Trubin <andrey.trubin@symmetrics.de>
// * @link http ://www.symmetrics.de/
// * @see "https://wiki.hybris.com/"
// */
//@Component
//public class PacoshopRequestFactory extends HttpComponentsClientHttpRequestFactory
//{
//	private static final Logger LOG = Logger.getLogger(PacoshopRequestFactory.class);
//	private static final String ENCRYPTION_PROTOCOL = "SSL";
//	private static final String TRANSPORT_PROTOCOL = "https";
//	private static final Integer PORT = 443;
//
////	private ShopConfigurationService shopConfigurationService;
//
//	/**
//	 * Initializes "fake" trust manager if
//	 * shopConfigurationService.getSecurityTrustAllHostnames() returns true.
//	 */
//	@PostConstruct
//	public void init()
//	{
////		if (shopConfigurationService.getSecurityTrustAllHostnames())
//		{
//			try
//			{
//				SSLContext sslContext = SSLContext.getInstance(ENCRYPTION_PROTOCOL);
//				javax.net.ssl.TrustManager[] trustManagers = {new X509TrustManager()
//				{
//					public X509Certificate[] getAcceptedIssuers()
//					{
//						return null;
//					}
//					public void checkServerTrusted(final X509Certificate[] certs, final String s) throws CertificateException
//					{
//					}
//
//					public void checkClientTrusted(final X509Certificate[] certs, final String s) throws CertificateException
//					{
//					}
//				}
//				};
//
//				sslContext.init(null, trustManagers, new SecureRandom());
//				SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//				Scheme scheme = new Scheme(TRANSPORT_PROTOCOL, PORT, sslFactory);
//				this.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
//			}
//			catch (Exception e)
//			{
//				error(LOG, e.getMessage(), e);
//			}
////		}
//	}
//
//	/**
//	 * Setter for shopConfigurationService injection.
//	 *
//	 * @param shopConfigurationService configuration service
//	 */
////	@Required
////	public void setShopConfigurationService(final ShopConfigurationService shopConfigurationService)
////	{
////		this.shopConfigurationService = shopConfigurationService;
////	}
//
//	/**
//	 * Getter for injected shopConfigurationService object.
//	 * @return shopConfigurationService object
//	 */
////	public ShopConfigurationService getShopConfigurationService()
////	{
////		return shopConfigurationService;
//	}
//}
