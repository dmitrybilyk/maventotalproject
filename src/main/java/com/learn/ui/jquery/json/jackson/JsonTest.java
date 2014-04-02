package com.learn.ui.jquery.json.jackson;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 28.03.13
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
public class JsonTest {
    public static User user = null;
    public static void main(String[] args) {
        user = new User();
        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        try {

            // convert user object to json string, and save to a file
            mapper.writeValue(new File("d:\\user.json"), user);

            // display to console
            System.out.println(mapper.writeValueAsString(user));

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

//        ObjectMapper mapper = new ObjectMapper();

        try {

            // read from file, convert it to user class
            User user = mapper.readValue(new File("d:\\user.json"), User.class);

            // display to console
            System.out.println(user);

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    }

