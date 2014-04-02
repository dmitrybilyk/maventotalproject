package com.learn.groovy.worktest

class TestCalendar {
    public static void main(String[] args) {


//        Date date = Calendar.getInstance().settgetTime();


        println myRoundTime().getTime().getDateTimeString()


//        println "fdsfdsf"+round2Time(Calendar.getInstance())
//
//
//        println new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        println new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();
//        println new Date().getHours()
//        Calendar calendar = myRoundTime()
//        Calendar gregorianCalendar = GregorianCalendar.getInstance();
//        println(calendar.getTime())
//        gregorianCalendar.with {
//            set(HOUR, 0)
//            set(MINUTE, 0)
//            set(SECOND, 0)
//            set(MILLISECOND, 0)
//        }
//        println(gregorianCalendar.getTime())
    }
    static Calendar roundTime() {
        Calendar cal = calendar();
        return roundTime(cal)
    }


    static Calendar myRoundTime(){
        Calendar calendar = GregorianCalendar.getInstance()
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.ZONE_OFFSET, 0)
        return calendar


    }

    static Calendar round2Time(Calendar calendar) {
        calendar.with{
            set(HOUR, 0)
            set(MINUTE, 0)
            set(SECOND, 0)
            set(MILLISECOND, 0)
        }

        return calendar
    }

    static Calendar roundTime(Calendar calendar) {

        return calendar.with{
            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
    }

    static Calendar calendar() {
        return GregorianCalendar.getInstance()
    }

    static  Date roundDateTime(){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.HOUR, 0-12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


}
