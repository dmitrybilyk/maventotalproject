package com.learn.bases.bloh;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 08.12.12
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */

//Singleton needs to be changed to private constructor
//Singleton creation based on private /constructor and pulic static memeber, than provides to
//clients access to this single member

class ThirdSingleton {
    private ThirdSingleton(){
    }

    private static class SingleToneHolder{
        private static ThirdSingleton INSTANCE = new ThirdSingleton();
    }

    public static ThirdSingleton getInstance(){
        return SingleToneHolder.INSTANCE;
    }
}


class SecondSingleton {
    private static SecondSingleton secondSingleton = new SecondSingleton();
    private SecondSingleton(){
    }
    public static SecondSingleton getInstance(){
        return secondSingleton;
    }
}

class FirstSingleton {
    private static FirstSingleton instance;

    private FirstSingleton() {
    }

    public static FirstSingleton getInstance(){
        if(instance==null){
            return new FirstSingleton();
        }else{
            return instance;
        }
    }
}

public class _2Singleton{
public static void main(String[]args){
            ThirdSingleton instance = ThirdSingleton.getInstance();
            ThirdSingleton instance2 = ThirdSingleton.getInstance();
    System.out.println(instance);
    System.out.println(instance2);
        }
        }
