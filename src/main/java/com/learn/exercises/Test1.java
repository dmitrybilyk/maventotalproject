package com.learn.exercises;

public class Test1
{
    public int value;
    public int hashCode() { return 42; }

   public static void main(String[] args)
   {
      int hc = new Test22().hashcode();
      System.out.println(hc);
   }

}
class Test22
{
    public int value;
    public int hashcode() { return (int)(value^5); }
}