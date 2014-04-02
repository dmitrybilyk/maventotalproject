package com.learn.patterns.creational.singleton.gupta;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
public class SingletoneMain {
    public static void main(String[] args) {
//        EagerSingleton eagerSingleton = EagerSingleton.getInstance();
//        EagerSingleton eagerSingleton1 = EagerSingleton.getInstance();
//        System.out.println(eagerSingleton==eagerSingleton1);
//
//
//        StaticBlockSingleton staticBlockSingleton = StaticBlockSingleton.getInstance();
//        StaticBlockSingleton staticBlockSingleton2 = StaticBlockSingleton.getInstance();
//        System.out.println(staticBlockSingleton==staticBlockSingleton2);
//
//        ENUMSingleton enumSingleton = ENUMSingleton.INSTANCE;
//        enumSingleton.setName("dima");
//        System.out.println(enumSingleton.getName());

        LazySingleton lazySingleton = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        LazySingleton lazySingleton3 = LazySingleton.getInstance();
        LazySingleton lazySingleton4 = LazySingleton.getInstance();
    }
}
