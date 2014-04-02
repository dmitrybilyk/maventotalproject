package com.learn.bases.collections.map;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by dmitry on 1/27/14.
 */
public class Main {
    public static void main(String[] args) {
        Map<Soldier, String> map = new TreeMap<Soldier, String>();
        map.put(new Soldier("Dima", 22), "Dimon");
        map.put(new Soldier("Alina", 21), "Alinchik");
        map.put(new Soldier("Wowa", 26), "Wowan");
        map.put(new Soldier("Armin", 42), "Arminchik");

        Set<Soldier> soldierSet = map.keySet();

        for (Soldier soldier : soldierSet) {
            System.out.println(soldier.getName());
        }
    }
}
