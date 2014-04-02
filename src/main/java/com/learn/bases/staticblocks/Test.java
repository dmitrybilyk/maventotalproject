package com.learn.bases.staticblocks;

public class Test {

    static{
        System.out.println("Static");
    }

    {
        System.out.println("Non-static block");
    }

    public static void main(String[] args) {
        Test t = new Test();
        Test t2 = new Test();
        Foo foo = new Foo();
        System.out.println(foo.widgets);

        Usualinitializingblock usualinitializingblock = new Usualinitializingblock("value");
        System.out.println(usualinitializingblock.getValue2());

    }

}