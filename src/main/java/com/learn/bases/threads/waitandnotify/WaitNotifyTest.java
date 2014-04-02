package com.learn.bases.threads.waitandnotify;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 20.08.12
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
//Важно! Методы wait(), notify() и notifyAll() должны обязательно находиться внутри блока
//synchronized, либо внутри synchronized-метода, иначе вы получите Exception. Но возникает
//        вопрос, каким образом второй поток сможет попасть в блок синхронизации, чтобы вызвать
//        notifyAll(), если первый в этот момент будет владельцем monitor
//        'а и будет ждать notify() ? Все просто - как только поток достигает метода wait()
//        он перестает быть владельцем монитора, блокировка снимается, а поток уходит в сон.

public class WaitNotifyTest {
    public static void main(String[] args){
//        DataManager dataManager = new DataManager();
//
//        SomeBigTaskRunnable someBigTaskRunnable = new SomeBigTaskRunnable();
//        Thread someThreadForJoin = new Thread(someBigTaskRunnable);
//
////        SenderRunnable senderRunnable = new SenderRunnable(dataManager);
//        SenderRunnable senderRunnable = new SenderRunnable(dataManager, someThreadForJoin);
//        PrepareRunnable prepareRunnable = new PrepareRunnable(dataManager);
//        Thread senderThread = new Thread(senderRunnable);
////        senderThread.setDaemon(true);
//        Thread prepareThread = new Thread(prepareRunnable);
////        prepareThread.setDaemon(true);
//
//        senderThread.start();
//
//        if(dataManager.getMap() == null){
//            prepareThread.start();
////            prepareThread.interrupt();
//        }


    }
}
