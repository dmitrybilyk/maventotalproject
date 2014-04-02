package com.learn.groovy.planet

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 30.10.12
 * Time: 21:45
 * To change this template use File | Settings | File Templates.
 */
class Haversine {
    static def R = 6371d

    static def compute(Point p1, Point p2) {
        def dLat = Math.toRadians(p2.lat-p1.lat)
        def dLon = Math.toRadians(p2.lon-p1.lon);
        def a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos( Math.toRadians(p1.lat) ) *
                Math.cos( Math.toRadians(p2.lat) ) * Math.sin(dLon/2) * Math.sin(dLon/2);
        def c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        def d = R * c;
        return d
    }

}