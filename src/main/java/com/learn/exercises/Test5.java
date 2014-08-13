package com.learn.exercises;

public class Test5
{ 
    public void foo() 
    {
        assert false; /* Line 5 */
        assert false; /* Line 6 */
    } 
    public void bar()
    {
        while(true)
        {
            assert false; /* Line 12 */
            break; // break fixes the problem. Because now downsside assert is reachable.
        } 
        assert false;  /* Line 14 */
    } 
}
