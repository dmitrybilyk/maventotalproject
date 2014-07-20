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
package runner.servicebus;


import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import runner.address.model.DeliveryAddress;
import static runner.util.LogHelper.debug;


/**
 * Represents the HTTP client implementation of the CAClient.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class HttpProductsClient
{
   private RestTemplate restTemplateGetSingleProducts = new RestTemplate();

   /** The Constant LOG. */
   private static final Logger LOG = Logger.getLogger(HttpProductsClient.class);


   public SingleProductList getSingleProducts()
   {


      final UriTemplate uriTemplate =
              new UriTemplate("http://localhost:8082/ownmocks/rest/servicebus/single?delay=0");
      final int readTimeout = 3;


      // set timeout params to request factory
      // important: the same request factory used on consequent queries
      // each restTemplate spring bean has own request factory (with specific params)
//		Assert.isInstanceOf(PacoshopRequestFactory.class, restTemplateDeliveryAddressValidation.getRequestFactory());
//		PacoshopRequestFactory requestFactory = (PacoshopRequestFactory) restTemplateDeliveryAddressValidation.getRequestFactory();
//		requestFactory.setReadTimeout(readTimeout);

      Map<String, String> parameters = new HashMap<>();
      final URI uri = uriTemplate.expand(parameters);
      return restTemplateGetSingleProducts.getForObject(uri, SingleProductList.class);
   }

}
