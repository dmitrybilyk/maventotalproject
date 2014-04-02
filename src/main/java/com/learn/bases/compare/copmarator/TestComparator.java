package com.learn.bases.compare.copmarator;

import java.util.ArrayList;
import java.util.List;

public class TestComparator {
    public static void main(String[] args) {
                List<Human> humans = new ArrayList<Human>();
        Human human1 = new Human(188, "Alex");
        Human human2 = new Human(177, "Zet");
        Human human3 = new Human(191, "Man");
        Human human4 = new Human(250, "Man");
        humans.add(human1);
        humans.add(human2);
        humans.add(human3);
        humans.add(human4);

        for(Human human: humans){
            System.out.println(human.getSurName());
        }

//        Arrays.sort(humans, new ComplexComparator());

        System.out.println("After sorting...");

        for(int i=0; i<humans.size(); i++){
            System.out.print(humans.get(i).getSurName());
            System.out.print(humans.get(i).getHeight());
        }


    }
}
