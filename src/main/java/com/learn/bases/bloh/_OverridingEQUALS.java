package com.learn.bases.bloh;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 09.12.12
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
//You don't have to override EQUALS if class is an Active class (Random, Thread) -
//    In this case there is no matter if to objects of these classes are identical.

//    You have to override equals if you want to logically compare two objects or if you
//    want to use these objects in some set of objects.
//
//    There is only one case when you don't have to override EQUALS for class that have logical meaning.
//    - enums. Every enum - it's the realisation of super class. And there is garanti that will
//    be only single object of that child class. "==" is equal to .equeals for these classes.

public class _OverridingEQUALS {
    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s));
        System.out.println(s.equals(cis));

        //Транзитивность
        Point point = new Point(20, 30);
//        ColorPoint colorPoint = new ColorPoint(20, 30, Color.RED);
//        System.out.println(point.equals(colorPoint));
    }
}

//1. Рефлексия
//Объект должен быть равен самому себе
//2. Симметрия x.equals(y) the same as y.equals(x)!
class CaseInsensitiveString {
    private String s;
    public CaseInsensitiveString(String s) {
        if (s == null)
            throw new NullPointerException();
        this.s = s;
    }
    // Ошибка: нарушение симметрии !
//    public boolean equals(Object o) {
//        if (o instanceof CaseInsensitiveString)
//            return s.equalsIgnoreCase(
//                    ((CaseInsensitiveString)o).s);
//        if (o instanceof String) // One-way interoperability!
//            return s.equalsIgnoreCase((String)o);
//        return false;
//    }
//     Односторонне везаимодействие
//    Fixed
    public boolean equals(Object o) {
    return o instanceof CaseInsensitiveString &&
    ((CaseInsensitiveString)o).s.equalsIgnoreCase(s);
    }

// ... // Остальное опущено

}

//3. Транзитивность. If x.equals(y) and y.equals(z) then x.equals(z)
 class Point {
    public final int x;
    public final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        Point p = (Point)o;
        return p.x == x && p.y == y;
    }

}

//Предположим, что вы хотите расширить этот класс, добавив понятие цвета:
//class ColorPoint extends Point{
//private Color color;
//public ColorPoint(int х, int у, Color color) {
////        super(x, у);
//this.color = color;
//}
//}

//You'd better use composition instead of inheritance if you want that equals work.
//If you add new aspect to child class, then you will get different results, violation
//of other convention (transitivnost))) ). Better way - to use abstract class and adding of
// new aspects ot child classes. But problem will occur if you create the object with reference
// type of this abstract class.

//4. Непротиворечивость. Равные объекты должны быть равны до тех пор, пока один из них не
//изменится

//5. Неравенство нулю (null);


//Рекомендации:
//
//В самом начале, если объекты равны по == то возвращайте true
//
//Далее если instaceOf возвращает неправильный тип, то возвращайте false
//
//Далее приводите объект к правильному типу.
//
//Для простых полей применяйте ==. для обєектов - equals().
//
//Float приводите к int, Double to long.
//
//Переопределяя equals() переопределяйте hashcode();
//
//Параметром должен быть Object. Если уже конкреный класс, то это не переопределение, а
//перезагрузка.
//
//Первыми срвнивайте те поля, которые с бОльшей веротностью будут различаться.