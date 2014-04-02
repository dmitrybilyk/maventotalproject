package com.learn.groovy.planet

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 30.10.12
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
/**
 * List of Points
 */
class PointList {
    def points
    def distance
    def partiels = []

    public PointList( List points ) {
        this.points = points
        compute()
    }

    void compute() {
        def nbPointList = points.size()
        distance = 0;
        partiels = []
        for( idx in 1..(nbPointList-1) ) {
            def p0 = points[(idx-1)]
            def p1 = points[idx]
            def dist = Point.distance(p0,p1)
            partiels << dist
            distance = distance+dist
        }
    }

}