package com.learn.bases.appacheutils.tostringbuilder;

import java.util.Date;

public class ToStringDemoUsage
{
    public static void main(String[] args)
    {
        GuestUser guest = getGuestUser();
        System.out.println(guest);
    }
     
    public static GuestUser getGuestUser()
    {
        GuestUser user = new GuestUser();
        user.setId(100);
        user.setFirstName("Lokesh");
        user.setLastName("Gupta");
        user.setAge("30");
        user.setLastLoggedIn(new Date());
        user.setLocation("New Delhi");
        user.setDob(new Date());
        return user;
    }
}