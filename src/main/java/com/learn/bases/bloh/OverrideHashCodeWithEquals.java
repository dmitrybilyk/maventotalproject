package com.learn.bases.bloh;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 10.12.12
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */

//If you several time call hashcode, it should return the same value in on application flow.
//    In  another run it may return another result.

//    If 2 objects are equal, they should return the same hashcode

//  If 2 objects are not equal, then they still may return the same hashcode.
//    But better situation if hashcodes are different too. This will increase effitien of
//    hash tables

public class OverrideHashCodeWithEquals {
//    import java.util.*;
//    public final class PhoneNumber {
//        private final short areaCode;
//        private final short exchange;
//        private final short extension;
//        public PhoneNumber(int areaCode, int exchange,
//                           int extension) {
//            rangeCheck(areaCode, 999, "area code");
//            rangeCheck(exchange, 999, "exchange");
//            rangeCheck(extension, 9999, "extension");
//            this.areaCode = (short) areaCode;
//            this.exchange = (short) exchange;
//            this.extension = (short) extension;
//        }
//        private static void rangeCheck(int arg, int max,
//                                       String name) {
//            if (arg < 0 || arg > max)
//                throw new IllegalArgumentException(name +": " + arg);
//        }
//        public boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (!(o instanceof PhoneNumber))
//                return false;
//            PhoneNumber pn = (PhoneNumber)o;
//            return pn.extension == extension &&
//                    pn.exchange == exchange &&
//                    pn.areaCode == areaCode;
//        }
//// Нет метода hashCode!
//// Ост альное опущено
//    }
//    Предположим, что вы попытались использовать этот класс с HashMap:
//    Мар m = new HashMap();
//    m. put (new PhbneNumber( 408, 867, 5309), "Jenny");
//    Вы вправе ожидать, что m.get(new PhoneNumber(408, 867, 5309)) возвратит

//    Отсутствие в классе PhoneNumber переопределенного метода
//    hashCode приводит к тому, что двум равным экземплярам соответствует разный хэш-код, т. е. имеем
//    нарушение соглашений для этого метода. Как следствие, метод get ищет указанный телефонный номер
//    в другом сегменте хэш-таблицы, а не там, где была сделана запись с помощью метода put. Разрешить
//    эту проблему можно, поместив в класс PhoneNumber правильный метод hashCode.
//    Как же должен выглядеть метод hashCode? Написать действующий, но не слишком хороший метод
//    нетрудно. Например, следующий метод всегда приемлем, но пользоваться им не надо никогда:
//    // Самая плохая из допу от имых хэш-фу нкций – никогда
//// не пользу йт ео еьюl
//    public int hashCode() { return 42; }

    //You should include to hashCode function fields that you use in Equals() method (valuable fields)


//    public int hashCode() {
//        int result = 17;
//        result = 37*result + areaCode;
//        result = 37*result + exchange;
//        result = 37*result + extension; \
//        return result;
//    }
}
