package com.learn.ui.jquery.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExamplePrettyPrint {
    public static void main(String[] args) {

        DataObject obj = new DataObject();
        // Gson gson = new Gson();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(obj);

        System.out.println(json);

    }
}