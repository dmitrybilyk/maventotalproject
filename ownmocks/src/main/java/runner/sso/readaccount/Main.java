package runner.sso.readaccount;

import java.util.HashMap;
import runner.sso.readaccount.model.ResponseCustomer;
import runner.sso.readaccount.service.SSOCustomerRestClientImpl;

/**
 * Created by bid on 8/5/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      SSOCustomerRestClientImpl ssoCustomerRestClient = new SSOCustomerRestClientImpl();
      ResponseCustomer responseCustomer = ssoCustomerRestClient.getUserData(new HashMap<String, String>());
      System.out.println(responseCustomer.getEmail());
   }
}
