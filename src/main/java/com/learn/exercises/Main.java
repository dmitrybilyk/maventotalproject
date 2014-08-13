package com.learn.exercises;

/**
 * Created by bid on 8/13/14.
 */
public class Main
{
//   . 	  Which of the following class level (nonlocal) variable declarations will not compile?

//   protected int a;
//   transient int b = 3;

//   private synchronized int e;
//   volatile int d;

//   Option C will not compile; the synchronized modifier applies only to methods.








//    Which two statements are equivalent?

//        3/2
//        3<2
//        3*4
//        3<<2
//
//    Explanation:

//        (1) is wrong. 3/2 = 1 (integer arithmetic).
//public static void main(String[] args) {
//    System.out.println(3/2);
//}

//        (2) is wrong. 3 < 2 = false.

//        (3) is correct. 3 * 4 = 12.

//        (4) is correct. 3 <<2= 12. In binary 3 is 11, now shift the bits two places to the left and we get 1100 which is 12 in binary (3*2*2).
//public static void main(String[] args) {
//    System.out.println(3<<2);
//}






//    Which is true about a method-local inner class?
//    A.
//    It must be marked final.
//    B.
//    It can be marked abstract.
//    C.
//    It can be marked public.
//    D.
//    It can be marked static.

//    Option B is correct because a method-local inner class can be abstract, although it means a
// subclass of the inner class must be created if the abstract class is to be used (so an abstract
// method-local inner class is probably not useful).

//    Option A is incorrect because a method-local inner class does not have to be declared final
// (although it is legal to do so).

//    C and D are incorrect because a method-local inner class cannot be made public (remember-you
// cannot mark any local variables as public), or static.

//    static class Outer{
//        public void method(){
//            class InnerLocal{
//                public void print(){
//                    System.out.println("print from local inner class");
//                }
//            }
//            InnerLocal innerLocal = new InnerLocal();
//            innerLocal.print();
//            class InnerChild extends InnerLocal{
//                public void print(){
//                    System.out.println("print from child of inner local");
//                }
//            }
//
//            InnerChild innerChild = new InnerChild();
//            innerChild.print();
//        }
//    }
//
//    public static void main(String[] args) {
//        Outer outer = new Outer();
//        outer.method();
//
//    }








//    A.
//    In an assert statement, the expression after the colon ( : ) can be any Java expression.
//    B.
//    If a switch block has no default, adding an assert default is considered appropriate.
//        C.
//    In an assert statement, if the expression after the colon ( : ) does not have a value, the assert's error message will be empty.
//    D.
//    It is appropriate to handle assertion failures using a catch clause.

//    Option B is correct

//    Adding an assertion statement to a switch statement that previously had no default case is considered an excellent use of the assert mechanism.

//    Option A is incorrect because only Java expressions that return a value can be used. For instance, a method that returns void is illegal.

//    Option C is incorrect because the expression after the colon must have a value.

//    Option D is incorrect because assertions throw errors and not exceptions, and assertion errors do cause program termination and should not be handled.



//    static class ToTestAssert{
//        public void testAssert(){
//            assert true;
//        }
//    }
//
//    public static void main(String[] args) {
//        ToTestAssert toTestAssert = new ToTestAssert();
//        toTestAssert.testAssert();
//
//        int z = 5;
//        assert z > 0; /* Line 11 */
//        assert z > 2: foo(z); /* Line 12 */
//
//    }
//
////    private static void foo(int z) {
////    }
//
//    private static String foo(int z) {
//        return "";
//    }


















}
