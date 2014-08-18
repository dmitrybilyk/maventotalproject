package com.learn.bases.resourcebundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {


        // Load resource bundle for locale Locale.US

        // ResourceBundle_en_US.properties file will be used

        ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourceBundle", Locale.FRENCH);

        System.out.println("Message in " + Locale.US + " : " + resourceBundle.getString("name"));


        // Change the default locale to Greek and get the resource bundle for that locale

        // ResourceBundle_el_GR.properties file will be used

        Locale.setDefault(new Locale("el", "GR"));

        resourceBundle = ResourceBundle.getBundle("ResourceBundle");

        System.out.println("Message in " + Locale.getDefault() + " : " + resourceBundle.getString("name"));

    }
}
