package com.learn.groovy

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 24.03.13
 * Time: 0:11
 * To change this template use File | Settings | File Templates.
 */
class MapsMain {
    public static void main(String[] args) {
        def mapTest = [a: 1, b: 2]
        assert mapTest['a'] == 1 && mapTest.b == 2

        def map = [a:1, 'b':2]
        println map            // ["a":1, "b":2]
        println map.a // 1
        println map['a']       // 1
        println map.keySet()   // ["a", "b"]
        map = [:]
// extend the map through assignment
        map[1] = 'a'; map[2] = 'b'
        map[true] = 'p'; map[false] = 'q'
        map[null] = 'x'; map['null'] = 'z'
        assert map == [ 1:'a', 2:'b', (true):'p',
                (false):'q',  (null):'x', 'null':'z' ]
        def sb = new StringBuffer()
        [1:'a', 2:'b', 3:'c'].each{ k, v-> sb << "$k:$v, " }
        assert sb.toString() == '1:a, 2:b, 3:c, '
        map = [1:'a', 2:'b', 3:'c']
        def string = map.collect{ k, v -> "$k:$v" }.join(', ')
        assert string == '1:a, 2:b, 3:c'


        assert [
                [ name: 'Clark',  city: 'London' ],
                [ name: 'Sharma',  city: 'London' ],
                [ name: 'Maradona', city: 'LA'     ],
                [ name: 'Zhang',  city: 'HK'     ],
                [ name: 'Ali',  city: 'HK'     ],
                [ name: 'Liu',  city: 'HK'     ]
        ].groupBy { it.city } == [
                London: [
                        [ name: 'Clark',  city: 'London' ],
                        [ name: 'Sharma',  city: 'London' ]
                ], LA: [
                [ name: 'Maradona', city: 'LA'     ]
        ], HK: [
                [ name: 'Zhang',  city: 'HK'     ],
                [ name: 'Ali',  city: 'HK'     ],
                [ name: 'Liu',  city: 'HK'     ]
        ]
        ]
    }
}
