package com.learn.exercises;

public class NFE
{
    public static void main(String [] args) 
    {
    String s = "42";
        try 
        {
            s = s.concat(".5");  /* Line 8 */
            double d = Double.parseDouble(s);
            s = Double.toString(d);
            int x = (int) Math.ceil(Double.valueOf(s).doubleValue());
            System.out.println(x);
        }
        catch (NumberFormatException e) 
        {
            System.out.println("bad number");
        }
    }
}

//The ceil() method converts this to 43.0, which is cast to an int and assigned to x