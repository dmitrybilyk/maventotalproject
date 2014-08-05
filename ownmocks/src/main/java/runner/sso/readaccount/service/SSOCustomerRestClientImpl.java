package runner.sso.readaccount.service;

import java.net.URI;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import runner.sso.readaccount.model.ResponseCustomer;
import runner.sso.readaccount.model.ResponseTermAccepted;

/**
 * SSO Rest client.
 *
 * @module pacoshopcore
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 06, 2014
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SSOCustomerRestClientImpl
{
   private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SSOCustomerRestClientImpl.class);

   /**
    * Enrich rest template with jackson message converter.
    */
   private RestTemplate enrichRestTemplate(final RestTemplate restTemplate)
   {
      restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
      return restTemplate;
   }

//   @Override
   public ResponseCustomer getUserData(final Map<String, String> parameters)
   {
      final String wsTokenKey = "cgi-ws-token";

      final String wsToken = parameters.get(wsTokenKey);
      parameters.remove(wsTokenKey); // Remove it from parameters.

      final UriTemplate uriTemplate = new UriTemplate(
              "http://localhost:8082/ownmocks/rest/sso/readAccount?delay=0&accountId=u001&platformId=kunde360");
      final URI uri = uriTemplate.expand(parameters);

      final int connectTimeout = 2;
      final int readTimeout = 2;

      final ResponseCustomer[] resultResponseCustomer = new ResponseCustomer[1];
      final RestRetryService.AbstractRestRetryCall restCall = new RestRetryService.AbstractRestRetryCall()
      {
         @Override
         public Object doAction(final RestTemplate restTemplate)
         {
            HttpHeaders headers = new HttpHeaders();
            headers.add("x-authorization", "wsTokenDefault");
				HttpEntity<ResponseCustomer> entity = new HttpEntity<>(headers);
				ResponseEntity<ResponseCustomer> responseEntity;

				try
				{

					responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, ResponseCustomer.class);
					return responseEntity.getBody();
				}
				catch (final ResourceAccessException exception)
				{
					exception.printStackTrace();
				}
				catch (final HttpServerErrorException exception)
				{
					exception.printStackTrace();
				}

				return null;
			}

			@Override
			public void onSuccess(final Object result)
			{
				resultResponseCustomer[0] = (ResponseCustomer) result;
			}
		};


      RestTemplate restTemplate = new RestTemplate();
//      restTemplate.getForEntity(uri, ResponseTermAccepted.class);
		return (ResponseCustomer) restCall.doAction(restTemplate);
	}
}
