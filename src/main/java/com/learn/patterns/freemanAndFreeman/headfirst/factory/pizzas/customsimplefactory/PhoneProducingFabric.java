package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzas.customsimplefactory;

import org.apache.commons.lang.StringUtils;

/**
 * Created by bid on 8/1/14.
 */
public class PhoneProducingFabric
{
   public Phone createPhone(final String model)
   {
      if(StringUtils.equals(model, "cellPhone")){
         return new CellPhone();
      } else if(StringUtils.equals(model, "portablePhone")){
         return new PortablePhone();
      }
      return new DefaultNokiaPhone();
   }
}
