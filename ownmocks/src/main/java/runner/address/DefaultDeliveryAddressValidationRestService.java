/*
* [y] hybris Platform
*
* Copyright (c) 2000-2014 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package runner.address;


import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.UriTemplate;
import runner.address.model.DeliveryAddress;
import java.net.URI;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Joe Doe <joe.doe@symmetrics.de>
 * @version 1.0v Apr 04, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class DefaultDeliveryAddressValidationRestService
{
   protected static final Logger LOG = Logger.getLogger(DefaultDeliveryAddressValidationRestService.class);

   private static final String NONE_RESPONSE = "\"NONE\"";

   private static final String OFFER_ORIGIN_PARAM = "offerOrigin";

   private static final String OFFER_ID_PARAM = "offerId";

//   @Resource
   private RestTemplate restTemplateDeliveryAddressValidation = new RestTemplate();

   public String validateAddress(final DeliveryAddress deliveryAddress)
   {

      Map<String, String> parameters = new HashMap<>();
      parameters.put(OFFER_ORIGIN_PARAM, "offerOrigin");
      parameters.put(OFFER_ID_PARAM, "externalId");

      final UriTemplate uriTemplate =
              new UriTemplate("http://localhost:8082/ownmocks/rest/sb/offerorigin/rrr/offer/eee/deliverability");
      final URI uri = uriTemplate.expand(parameters);
      final int readTimeout = 3;


      // set timeout params to request factory
      // important: the same request factory used on consequent queries
      // each restTemplate spring bean has own request factory (with specific params)
//		Assert.isInstanceOf(PacoshopRequestFactory.class, restTemplateDeliveryAddressValidation.getRequestFactory());
//		PacoshopRequestFactory requestFactory = (PacoshopRequestFactory) restTemplateDeliveryAddressValidation.getRequestFactory();
//		requestFactory.setReadTimeout(readTimeout);
      return restTemplateDeliveryAddressValidation.postForObject(uri, deliveryAddress, String.class);
   }
}
