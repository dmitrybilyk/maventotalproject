package runner.address;

import runner.address.DefaultDeliveryAddressValidationRestService;
import runner.address.model.DeliveryAddress;

/**
 * Created by bid on 7/19/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      DeliveryAddress deliveryAddress = new DeliveryAddress();
      deliveryAddress.setCity("Gorlovka");
      DefaultDeliveryAddressValidationRestService defaultDeliveryAddressValidationRestService = new DefaultDeliveryAddressValidationRestService();
      String answer = defaultDeliveryAddressValidationRestService.validateAddress(deliveryAddress);
      System.out.println(answer);

   }
}
