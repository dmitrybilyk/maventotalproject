package runner.sso;

import java.net.URI;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

/**
 * Created by bid on 7/30/14.
 */
public class ReadAccountClient
{
   public ResponseCustomer getUserData(final Map<String, String> parameters)
   {
      final String wsTokenKey = shopConfigurationService.getServiceConfiguration().
              getString(ShopConfigurationService.CONFIG_KEY_UAG_HEADER_NAME_WS_TOKEN_KEY);
      final String wsToken = parameters.get(wsTokenKey);
      parameters.remove(wsTokenKey); // Remove it from parameters.

      final UriTemplate uriTemplate = new UriTemplate(shopConfigurationService.getSsoClientUriTemplate());
      final URI uri = uriTemplate.expand(parameters);
      debug(LOG, "REST call to URI={%s}", uri);

      final int connectTimeout = shopConfigurationService.getCustomerCallTimeoutConnect();
      final int readTimeout = shopConfigurationService.getCustomerCallTimeoutRead();

      final ResponseCustomer[] resultResponseCustomer = new ResponseCustomer[1];
      final RestRetryService.AbstractRestRetryCall restCall = new RestRetryService.AbstractRestRetryCall()
      {
         @Override
         public Object doAction(final RestTemplate restTemplate)
         {
            HttpHeaders headers = new HttpHeaders();
            String headerTokenName = shopConfigurationService
                    .getServiceConfiguration()
                    .getString(ShopConfigurationService.CONFIG_KEY_SSO_READ_ACCOUNT_AUTH_HEADER);
            headers.set(headerTokenName, wsToken);
            HttpEntity<ResponseCustomer> entity = new HttpEntity<>(headers);
            ResponseEntity<ResponseCustomer> responseEntity;

            try
            {
               // print debug info with headers and parameters
               debug(LOG, "Header auth token {'%s'}: {%s}", headerTokenName, wsToken);
               // print request parameters
               debug(LOG, "Call parameters:");
               if (LOG.isDebugEnabled())
               {
                  for (Map.Entry<String, String> param : parameters.entrySet())
                  {
                     debug(LOG, "Param {'%s'} : {%s}", param.getKey(), param.getValue());
                  }
               }

               responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, ResponseCustomer.class);
               return responseEntity.getBody();
            }
            catch (final ResourceAccessException exception)
            {
               error(LOG, "SSO Request -> call failed with error: %s", exception, exception.getMessage());
            }
            catch (final HttpServerErrorException exception)
            {
               warn(LOG, "Header token '{%s}': {%s}", headerTokenName, wsToken);
               error(LOG, "SSO Request -> call failed with error: %s", exception, exception.getMessage());
            }

            return null;
         }
}
