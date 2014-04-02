package com.learn.ui.jquery.json.jackson;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.07.13
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class JsonMapTest {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> userInMap = new HashMap<String, Object>();
        userInMap.put("name", "mkyong");
        userInMap.put("age", 29);

        List<Object> list = new ArrayList<Object>();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        userInMap.put("messages", list);

        try {

            // write JSON to a file
            mapper.writeValue(new File("d:\\user.json"), userInMap);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            // read JSON from a file
            Map<String, Object> userInMap2 = mapper.readValue(
                    new File("d:\\user.json"),
                    new TypeReference<Map<String, Object>>() {});

            System.out.println(userInMap2.get("name"));
            System.out.println(userInMap2.get("age"));

            ArrayList<String> list2 =
                    (ArrayList<String>) userInMap2.get("messages");

            for (String msg : list2) {
                System.out.println(msg);
            }

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
