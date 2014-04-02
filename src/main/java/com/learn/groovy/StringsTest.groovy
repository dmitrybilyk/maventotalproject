package com.learn.groovy

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 23.03.13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
class StringsTest {
    public static void main(String[] args) {
        def firstName = "Dmitry"
        def surName = "Bilyk"
        assert firstName * 2 == "DmitryDmitry"
        def name = "Gulya"
        println "$name, hi"
        def fullName = "$firstName $surName"
        assert fullName == 'Dmitry Bilyk'
        assert fullName - firstName == " Bilyk"
        assert fullName.padLeft(13) == " Dmitry Bilyk"

        //multi lines strings
        def multiString = """
            Dmitry
            Bilyk
            Vladimirovich
        """.trim()
        def lines = multiString.split('\n')
        assert lines.size() == 3

        //more substrings
        def string = 'hippopotamus'
        assert string - 'hippopotam' + 'mus' == 'usmus'

        // processing characters
        assert 'apple'.toList() == ['a', 'p', 'p', 'l', 'e']
//also: 'apple' as String[], 'apple'.split(''), 'apple'.each{}
        string = "an apple a day"
        assert string.toList().unique().sort().join() == ' adelnpy'

        // reversing chars/words
        assert 'string'.reverse() == 'gnirts'

        string = 'Yoda said, "can you see this?"'
        def revwords = string.split(' ').toList().reverse().join(' ')
        assert revwords == 'this?" see you "can said, Yoda'

        def words = ['bob', 'alpha', 'rotator', 'omega', 'reviver']
        def bigPalindromes = words.findAll{w -> w == w.reverse() && w.size() > 5}
        assert bigPalindromes == ['rotator', 'reviver']
    }
}
