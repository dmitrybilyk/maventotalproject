package com.learn.groovy

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 23.03.13
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
class CollectionsMain {
    public static void main(String[] args) {
        def animals = ['dog', 'cat', 'bird']
        for (animal in animals){
//            println animal
        }

//        println animals.get(2)

        def names = ["Ted", "Fred", "Jed", "Ned"]
        System.out.println(names)
        def shortNames = filterLongerThan(names, 3)
        System.out.println(shortNames.size())
        for(s in shortNames){
            println s
        }
    }

    def static filterLongerThan(strings, length) {
        return strings.findAll{ it.size() <= length }
    }
}

