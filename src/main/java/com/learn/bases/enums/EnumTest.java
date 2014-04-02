package com.learn.bases.enums;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 08.05.12
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */

//Перечисление - это определенный программистом тип данных, значения которого ограничиваются некоторым набором.
//Элементы enum Season (WINTER, SPRING и т.д.) - это статически доступные экземпляры enum-класса Season.
// Их статическая доступность позволяет нам выполнять сравнение с помощью оператора сравнения ссылок ==

//Довольно часто возникает задача получить элемент enum по его строковому представлению.
// Для этих целей в каждом enum-классе компилятор автоматически создает специальный статический метод: public static EnumClass valueOf(String name),
// который возвращает элемент перечисления EnumClass с названием, равным name
//Иногда необходимо получить список всех элементов enum-класса во время выполнения. Для этих целей в каждом enum-классе компилятор создает метод:
//public static EnumClass[] values()

//С помощью enum в Java можно реализовать иерархию классов, объекты которой создаются в единственном экземпляре и доступны статически.
// При этом элементы enum могут содержать собственные конструкторы

//в Java использование генериков в enum запрещено

public class EnumTest {
    static enum Season {
        WINTER {
            @Override
            public Object parse(String string) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }
        , SPRING {
            @Override
            public Object parse(String string) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }
        , SUMMER {
            @Override
            public Object parse(String string) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }
        , AUTUMN {
            @Override
            public Object parse(String string) {
                return string;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        public abstract Object parse(String string);
    };
    public static void main(String[] args){
        Season season = Season.SPRING;
        season = Season.SUMMER;
        System.out.println(season);
        System.out.println(Season.class.getSuperclass());
        System.out.println("ValueOfTest "+Season.valueOf("SUMMER").name());
        System.out.println("ValuesTest "+ Season.AUTUMN.parse("ckeckckeck"));
        
        EnumClass enumClass = EnumClass.JUNIOR;
        System.out.println(enumClass.toString());
        
    }
}
