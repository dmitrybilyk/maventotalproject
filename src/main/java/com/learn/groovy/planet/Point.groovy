package com.learn.groovy.planet

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 30.10.12
 * Time: 21:43
 * To change this template use File | Settings | File Templates.
 */
/**
 * Compute distance in Google Earth KML path file from a path
 * @author Marc DEXET ( marcdexet [at] gmail [dot] org )
 */
class Point {
    def lat
    def lon
    public Point(){}

    public Point(String gps) {
        def xyz = gps.tokenize(',');
        lat = Double.parseDouble( xyz[1] )
        lon = Double.parseDouble( xyz[0] )
    }
    public String toString() {
        return "LAT: ${lat} LON: ${lon}"
    }

    public static double distance(Point p0, Point p1) {
        return Haversine.compute(p0, p1)
    }
}
