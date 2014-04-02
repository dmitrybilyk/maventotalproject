package com.learn.patterns.behavioral.visitor.pankaj;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 */

//The benefit of this pattern is that if the logic of operation changes,
// then we need to make change only in the visitor implementation rather
// than doing it in all the item classes.

//    Another benefit is that adding a new item to the system is easy,
// it will require change only in visitor interface and implementation
// and existing item classes will not be affected.

//    The drawback of visitor pattern is that we should know the return
// type of visit() methods at the time of designing otherwise we will
// have to change the interface and all of its implementations.
// Another drawback is that if there are too many implementations
// of visitor interface, it makes it hard to extend.
public class ShoppingCartClient {

    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{new Book(20, "1234"),new Book(100, "5678"),
                new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple")};

        int total = calculatePrice(items);
        System.out.println("Total Cost = "+total);
    }

    private static int calculatePrice(ItemElement[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum=0;
        for(ItemElement item : items){
            sum = sum + item.accept(visitor);
        }
        return sum;
    }

}
