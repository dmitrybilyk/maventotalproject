package com.learn.bases.compare;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 10:00
 * To change this template use File | Settings | File Templates.
 */
public class CompareTest {
    public static void main(String [] args){
        Human[] humans = new Human[4];
        Human human1 = new Human(188, "Alex");
        Human human2 = new Human(177, "Zet");
        Human human3 = new Human(191, "Man");
        Human human4 = new Human(250, "Man");
        humans[0] = human1;
        humans[1] = human2;
        humans[2] = human3;
        humans[3] = human4;

        for(int i=0; i<humans.length; i++){
            System.out.println(humans[i].getSurName());
        }

        Arrays.sort(humans, new ComplexComparator());

        System.out.println("After sorting...");
        
        for(int i=0; i<humans.length; i++){
            System.out.print(humans[i].getSurName());
            System.out.print(humans[i].getHeight());
        }

        TestComparable testComparable1 = new TestComparable(188, "rlex");
        TestComparable testComparable2 = new TestComparable(1977, "Aalex");
        TestComparable testComparable3 = new TestComparable(191, "Man");
        TestComparable testComparable4 = new TestComparable(250, "Man");

        if(testComparable1.compareTo(testComparable2)>0){
            System.out.println("first grater then second");
        }else if(testComparable1.compareTo(testComparable2)<0){
            System.out.println("second grate then first");
        }else{
            System.out.println("They are equal!");
        }
        
        
    }
}
