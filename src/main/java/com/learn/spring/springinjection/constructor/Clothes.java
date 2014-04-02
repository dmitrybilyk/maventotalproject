package com.learn.spring.springinjection.constructor;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
public class Clothes {

    public Clothes() {
    }

    public Clothes(String boots, String skirt, String teeshort, int size) {
        this.boots = boots;
        this.skirt = skirt;
        this.teeshort = teeshort;
        this.size = size;
    }

    private String boots;
    private String skirt;
    private String teeshort;
    private int size;

    public String getBoots() {
        return boots;
    }

    public void setBoots(String boots) {
        this.boots = boots;
    }

    public String getSkirt() {
        return skirt;
    }

    public void setSkirt(String skirt) {
        this.skirt = skirt;
    }

    public String getTeeshort() {
        return teeshort;
    }

    public void setTeeshort(String teeshort) {
        this.teeshort = teeshort;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Firm = "+ getClass().getName() + " skirt -" + skirt+"\n" +
                " size - " + size;
    }
}
