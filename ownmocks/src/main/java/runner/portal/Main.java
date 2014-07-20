package runner.portal;

import runner.address.DefaultDeliveryAddressValidationRestService;
import runner.address.model.DeliveryAddress;

/**
 * Created by bid on 7/19/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      PortalRestTemplateClient templateClient = new PortalRestTemplateClient();
      OrderDTO answer = templateClient.getCartContent();
      System.out.println(answer);

   }
}
