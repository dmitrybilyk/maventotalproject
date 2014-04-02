package com.learn.bases.enums.tutorial1;

//http://javarevisited.blogspot.com/2011/08/enum-in-java-example-tutorial.html
/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 11.04.13
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
public enum Currency implements Runnable{
    PENNY(1, "smallest") {
        @Override
        public String color() {
            return "yellow";
        }
    }, NICKLE(5, "middle") {
        @Override
        public String color() {
            return "silver";
        }
    }, DIME(10, "upper-middle") {
        @Override
        public String color() {
            return "blue";
        }
    }, QUARTER(25, "highest") {
        @Override
        public String color() {
            return "gold";
        }
    };
    private int meaning;
    private String name;

    private Currency(int value, String name) {
        this.meaning = value;
        this.name = name;
    }

    private Currency() {
    }

    public int getMeaning() {
        return meaning;
    }

    public void setMeaning(int meaning) {
        this.meaning = meaning;
    }


    //Enums can implement interfaces as any other java class. Implicitly enums implement
    //Comparable and Serializable.
    @Override
    public void run() {
        System.out.println("Enum in Java implement interfaces");
    }

    //we can declare abstract methods in enums. And then every enum constant
    //should implement it.
    public abstract String color();


    //we can override methods in enum
    @Override
    public String toString() {
        switch (this) {
            case PENNY:
                System.out.println("Penny: " + meaning);
                break;
            case NICKLE:
                System.out.println("Nickle: " + meaning);
                break;
            case DIME:
                return "Dime: rrr" + meaning;
//                System.out.println("Dime: rrr" + meaning);
//                break;
            case QUARTER:
                System.out.println("Quarter: " + meaning);
        }
        return super.toString();
    }



    public static void main(String[] args) {
        int howMuchCoins = Currency.DIME.getMeaning();
        System.out.println(howMuchCoins + " " + Currency.DIME.name);

        //switch example
        Currency usCoin = Currency.DIME;
        switch (usCoin) {
            case PENNY:
                System.out.println("Penny coin");
                break;
            case NICKLE:
                System.out.println("Nickle coin");
                break;
            case DIME:
                System.out.println(usCoin.meaning + "switch " + "Dime coin");
                break;
            case QUARTER:
                System.out.println("Quarter coin");
        }

        // '==' example

        Currency usCoin2 = Currency.DIME;
        if(usCoin2 == Currency.DIME){
            System.out.println("enum in java can be compared using ==");
        }

        //better way to compare - to use equals or compareTo (because enum values are objects)


        if(usCoin2.equals(Currency.DIME)){
            System.out.println(" equal enum in java can be compared using ==");
        }

        if(usCoin2.compareTo(Currency.DIME) == 0){
            System.out.println("compareTo equal enum in java can be compared using ==");
        }

        //Java creates values() method for every enum. It returns array of enum values
//        in order they were added to enum class

        for(Currency coin: Currency.values()){
            System.out.println("coin: " + coin);
        }

        //toString() checking
        System.out.println(Currency.DIME.toString());

        //abstract method's checking
        System.out.println(Currency.DIME.color());

        //valueOf(). We create enum from String with help of this method
        System.out.println(Currency.valueOf("DIME").meaning);

        //name(). The same as toString()
        System.out.println(Currency.DIME.name());

    }
}

