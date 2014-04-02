package com.learn.patterns.creational.builder;

//The builder pattern is a good choice when designing classes whose constructors
//or static factories would have more than a handful of parameters.
//One alternative you have to the Telescoping Constructor Pattern is the
// JavaBean Pattern where you call a constructor with the mandatory parameters
// and then call any optional setters after

//Pizza pizza = new Pizza(12);
//pizza.setCheese(true);
//pizza.setPepperoni(true);
//pizza.setBacon(true);

//The problem here is that because the object is created over several calls it
// may be in an inconsistent state partway through its construction.
// This also requires a lot of extra effort to ensure thread safety.
//The better alternative is to use the Builder Pattern.

//Because the Builder's setter methods return the Builder object they are able
// to be chained.
public class User {
    private final String firstName; // required
    private final String lastName; // required
    private final int age; // optional
    private final String phone; // optional
    private final String address; // optional

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}