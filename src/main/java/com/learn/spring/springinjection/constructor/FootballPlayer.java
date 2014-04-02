package com.learn.spring.springinjection.constructor;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class FootballPlayer {
    private String name;
    private int speed;
    private Clothes clothes;

//    setter injection testing
    private Award award;

    public FootballPlayer(String s, int s2, Clothes clothes) {
        this.name = s;
        this.speed = s2;
        this.clothes = clothes;
    }

    public FootballPlayer(String s, int s2) {
        this.name = s;
        this.speed = s2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    @Override
    public String toString() {
        return "name = "+name + " speed  = " + speed +"\n"+
                clothes.toString();
    }
}
