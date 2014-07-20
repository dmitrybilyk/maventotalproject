package runner.servicebus;

import runner.address.DefaultDeliveryAddressValidationRestService;
import runner.address.model.DeliveryAddress;

/**
 * Created by bid on 7/19/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      HttpProductsClient httpProductsClient = new HttpProductsClient();
      SingleProductList list = httpProductsClient.getSingleProducts();
      for (SingleProductDTO singleProductDTO : list)
      {
         System.out.println(singleProductDTO.getProductId());
      }
   }
}
