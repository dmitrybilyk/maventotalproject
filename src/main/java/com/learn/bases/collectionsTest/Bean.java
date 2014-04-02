package com.learn.bases.collectionsTest;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 09.05.12
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
public class Bean implements Comparable{
    private int a;
    private String b;

    public Bean(int a, String b) {
        this.a = a;
        this.b = b;
    }

    public Bean(){

    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }


    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        int a = ((Bean)o).getA();
        String b = ((Bean)o).getB();
        if(a>this.getA()){
            return 1;
        }else if(a<this.getA()){
            return -1;
        }else{
            return 0;
        }
    }
}
