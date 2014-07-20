package runner.portal;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

/**
 * Created by bid on 7/20/14.
 */
public class PortalRestTemplateClient
{
   private RestTemplate restTemplatePortal = new RestTemplate();

   public OrderDTO getCartContent()
   {

      Map<String, String> parameters = new HashMap<>();
      parameters.put("cartId", "valid");

      final UriTemplate uriTemplate =
              new UriTemplate("http://localhost:8082/ownmocks/rest/portal");
      final URI uri = uriTemplate.expand(parameters);
      final int readTimeout = 3;


      // set timeout params to request factory
      // important: the same request factory used on consequent queries
      // each restTemplate spring bean has own request factory (with specific params)
//		Assert.isInstanceOf(PacoshopRequestFactory.class, restTemplateDeliveryAddressValidation.getRequestFactory());
//		PacoshopRequestFactory requestFactory = (PacoshopRequestFactory) restTemplateDeliveryAddressValidation.getRequestFactory();
//		requestFactory.setReadTimeout(readTimeout);
      return restTemplatePortal.getForObject(uri + "?cartid=" + "valid", OrderDTO.class);
   }
}
