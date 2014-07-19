package runner.ca;

import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.BasicHttpContext;

/**
 * Created by bid on 7/19/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      HttpCAClient httpClient = new HttpCAClient();
      BasicHttpResponse basicHttpClient = httpClient.doSend("34343");
      System.out.println(basicHttpClient.getStatusLine());
   }
}
