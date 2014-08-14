package com.learn.exercises;

class Bar { }
class Test7
{  
    Bar doBar() 
    {
        Bar b = new Bar(); /* Line 6 */
        return b; /* Line 7 */
    } 
    public static void main (String args[]) 
    { 
        Test7 t = new Test7();  /* Line 11 */
        Bar newBar = t.doBar();  /* Line 12 */
        System.out.println("newBar"); 
        newBar = new Bar(); /* Line 14 */
        System.out.println("finishing"); /* Line 15 */
    } 
}


//Option B (after line 14) is correct. All references to the Bar object created on line 6 are destroyed
// when a new reference to a new Bar object is assigned to the variable newBar on line 14.
// Therefore the Bar object, created on line 6, is eligible for garbage collection after line 14.