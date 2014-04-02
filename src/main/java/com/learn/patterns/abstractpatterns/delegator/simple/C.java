package com.learn.patterns.abstractpatterns.delegator.simple;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 02.07.12
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class C {
    //В разработке ПО, шаблон делегирования (англ. delegation pattern) — это способ, которым объект внешне выражает некоторое поведение,
    // но в реальности передаёт ответственность за выполнение этого поведения связанному объекту.
    //В этом примере на языке Java, класс C имеет метод-заглушку (method stub), который передаёт методы f() и g() классу A.
    // Класс C делает вид, что он имеет атрибуты класса A.
    // Создаём объект методы которого будем делегировать
    A a = new A();

    void f() {
        a.f();
    }
    void g() {
        a.g();
    }

    // прочие атрибуты
//    X x = new X();
    void y() {
        /* некоторые действия */
    }
}
