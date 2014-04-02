package com.learn.bases.stringtest;

public class TestConcat {

    public static void main(String[] args) {

        long sbStart = System.nanoTime();

        for (int i = 0; i < 10000000; i++) {
            sbConcat();
        }

        long sbEnd = System.nanoTime();

        System.out.println("StringBuilder: " + (sbEnd - sbStart));

        long concatStart = System.nanoTime();

        for (int i = 0; i < 10000000; i++) {
            concat();
        }

        long concatEnd = System.nanoTime();

        System.out.println("String concat: " + (concatEnd - concatStart));

        System.out.println((double)((sbEnd - sbStart)/(concatEnd - concatStart)));
    }


    private static String sbConcat() {
        StringBuilder sb = new StringBuilder("a").append("a").append("a").append(String.class.getCanonicalName()).append("a").append(String.class.getCanonicalName());

        return sb.toString();
    }


    private static String concat() {

        return "a" + "a" + "a" + String.class.getCanonicalName() + "a" + String.class.getCanonicalName();
    }
}
