package com.learn.bases.collections.hashcode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
public class HashTest {
    public static void main(String[] args){
    
        Worker workerForChoose = new Worker("Choosed", 23, 188);
        
        //filling the map
        Map map = new HashMap<Worker, String>();
        
        for(int i = 0; i<100000; i++){
            Worker worker = new Worker("testWorker", 34, 166);
            map.put(worker, "testString"+String.valueOf(i));
        }
        map.put(workerForChoose, "ChoosedWorker");

        Date date = new Date();

        long beginTime = date.getTime();
        String s = null;
        for(int i = 0; i < 100000; i++){
            s = String.valueOf(map.get(workerForChoose));
        }
        Date date2 = new Date();
        long finishTime = date2.getTime();
        System.out.println(beginTime+"   " + finishTime);
        System.out.println(s+(finishTime-beginTime));
    }
}
