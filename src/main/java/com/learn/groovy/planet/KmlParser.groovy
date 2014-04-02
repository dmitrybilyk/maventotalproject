package com.learn.groovy.planet
/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 30.10.12
 * Time: 21:45
 * To change this template use File | Settings | File Templates.
 */
class KmlParser {
    static ns = new groovy.xml.Namespace("http://earth.google.com/kml/2.1", 'ns')

    List parse( Object input ) {
        def kml = new groovy.util.XmlParser().parse( input );
        def coords = kml[ ns.Document ][ ns.Placemark ][ ns.LineString ][ ns.coordinates ].value[0]
        def myList = coords.tokenize();
        def points = []
        myList.each{ gestring -> points << new Point(gestring) }
        return points;
    }
//    private static String[] array = {"444"; "555"}
////    static void main(array){
//        def kmlParser = new KmlParser()
//        def points = kmlParser.parse( array[0] )
//        def PointList pointList = new PointList( points )
//        def partiels = pointList.partiels;
//        def distance = pointList.distance;
//
//        java.text.DecimalFormat f = new java.text.DecimalFormat( "0.000" );
//        java.text.DecimalFormat n = new java.text.DecimalFormat( "00" );
//        println "Distance totale: ${f.format(distance)} km"
//        partiels.eachWithIndex { d, i ->
//            println "${n.format(i)}) ${f.format(d)} km"
//    }
//}


    }


