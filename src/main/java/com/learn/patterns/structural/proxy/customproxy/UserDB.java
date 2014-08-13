package com.learn.patterns.structural.proxy.customproxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bid on 8/13/14.
 */
public class UserDB
{
   Map<String, String> users;

   public UserDB()
   {
      this.users = new HashMap<String, String>();
      users.put("dik81", "Dimon");
      users.put("rusya", "Ruslan");
   }

   public Map<String, String> getUsers()
   {
      return users;
   }
}
