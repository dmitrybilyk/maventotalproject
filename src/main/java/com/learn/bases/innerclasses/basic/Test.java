package com.learn.bases.innerclasses.basic;

public class Test {

    public static void main(String[] args) {

        Test test = new Test();
        Dog dog = test.new Dog("Сабака Барабака");
        test.foo(dog);
        System.out.println("main 1 -> " + dog.name.equals("Сабака Барабака"));
        dog = test.bar(dog);
        System.out.println("main 2 -> " + dog.name.equals("Сaбака днюхусправляка"));

    }

    private Dog bar(Dog dog) {
        System.out.println("bar 1 -> " + dog.name.equals("Сабака Барабака"));

        dog = new Dog("Сaбака днюхусправляка");
        System.out.println("bar 2 -> " + dog.name.equals("Сaбака днюхусправляка"));
        return dog;
    }

    private void foo(Dog dog){
        System.out.println("foo 1 -> " + dog.name.equals("Сабака Барабака"));

        dog = new Dog("Сaбака днюхусправляка");
        System.out.println("foo 2 -> " + dog.name.equals("Сaбака днюхусправляка"));
        System.out.println("foo 2 -> " + dog.name.equals("Сaбака днюхусправляка"));
    }

    private class Dog {

        private Dog(String name) {
            this.name = name;
        }

        private String name;
    }

    private interface DogInterface{

    }
}
