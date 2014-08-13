package com.learn.exercises;

public class Test2
{
    public static int x;
    public static int foo(int y) 
    {
        return y * 2;
    }
    public static void main(String [] args) 
    {
        int z = 5;
        assert z > 0; /* Line 11 */
        assert z > 2: foo(z); /* Line 12 */
        if ( z < 7 )
            assert z > 4; /* Line 14 */

        switch (z) 
        {
            case 4: System.out.println("4 ");
            case 5: System.out.println("5 ");
            default: assert z < 10;
        }

        if ( z < 10 )
            assert z > 4: z++; /* Line 22 */
        System.out.println(z);
    }
}


//Line 22 is inappropriate

//Assert statements should not cause side effects. Line 22 changes
// the value of z if the assert statement is false.

//        Option A is fine; a second expression in an assert statement is not required.

//        Option B is fine because it is perfectly acceptable to call a
// method with the second expression of an assert statement.

//        Option C is fine because it is proper to call an assert statement conditionally.
