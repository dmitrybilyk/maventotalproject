package com.learn.refactoring.firstPart;

import java.io.*;

/**
 * Created by dmitry on 20.05.14.
 */
public class Main {
    public static void main(String[] args) {
        Movie dragonMovie = new Movie("Dragon", 3);
        dragonMovie.setPriceCode(Movie.REGULAR);
        Movie castleMovie = new Movie("Castle", 2);
        castleMovie.setPriceCode(Movie.CHILDRENS);
        Movie dragon2Movie = new Movie("Dragon2", 5);
        dragon2Movie.setPriceCode(Movie.NEW_RELEASE);

        Rental rental = new Rental(dragonMovie, 2);
        Rental rental2 = new Rental(dragon2Movie, 3);

        Customer customer = new Customer("Me");
        customer.addRental(rental);
        customer.addRental(rental2);

        File file = new File("/home/dmitry/dev/projects/totalmavenproject/src/main/java/com/learn/refactoring/firstPart/testFile.txt");
        StringBuilder sb = new StringBuilder();
        String sCurrentLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((sCurrentLine = br.readLine()) != null){
                    sb.append(sCurrentLine);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(customer.statement().replaceAll("(\n)", "").equals(sb.toString()));
    }
}
