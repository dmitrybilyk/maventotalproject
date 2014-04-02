package com.learn.patterns.creational.builder.myown;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class Computer {
    private String model;   //required
    private String weight;
    private boolean forSale;

    private Computer(ComputerBuilder computerBuilder) {
        this.model = computerBuilder.model;
        this.weight = computerBuilder.weight;
        this.forSale = computerBuilder.forSale;
    }

    public static class ComputerBuilder{
        private final String model;
        private String weight;
        private boolean forSale;

        public ComputerBuilder(String model) {
            this.model = model;
        }

        public ComputerBuilder weight(String weight){
            this.weight = weight;
            return this;
        }

        public ComputerBuilder forSale(boolean forSale){
            this.forSale = forSale;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }
    public String getModel() {
        return model;
    }

    public String getWeight() {
        return weight;
    }

    public boolean isForSale() {
        return forSale;
    }

}
