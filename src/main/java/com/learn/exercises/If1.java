package com.learn.exercises;

public class If1
{
    static boolean b;
    public static void main(String [] args) 
    {
        short hand = 42;
       if (hand < 50 && !b) /* Line 7 */
       {
          hand++;
       }
       if (hand > 50)
       {
          ;     /* Line 9 */
       }
       else if (hand > 40)
       {
          hand += 7;
          hand++;
       }
       else
       {
          --hand;
       }
        System.out.println(hand);

//       int i = 1;
//       while(false){
//          System.out.println("fsdfs");
//       }
    }
}
//In Java, boolean instance variables are initialized to false, so the if test on line 7
// is true and hand is incremented. Line 9 is legal syntax, a do nothing statement.
// The else-if is true so hand has 7 added to it and is then incremented.