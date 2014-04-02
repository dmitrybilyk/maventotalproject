package com.learn.bases.threads.waitandnotify;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 20.08.12
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public class DataManager {
    private static final Object monitor = new Object();
//    private Map<String, String> map = new HashMap<String, String>();
    private Map<String, String> map;

    public void sendData() {
        synchronized (monitor) {
            System.out.println("Waiting for data to be prepared...");
            try {

                if(map == null){
                    monitor.wait();
                } else {
                    System.out.println("Data is already prepared");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // continue execution and sending data
            System.out.println("Sending data...");
        }
    }

    public void sendDataForDeamonTest() {
        synchronized (monitor) {
            System.out.println("Waiting for data to be prepared...");
            try {

                if(map == null){
                    monitor.wait();
                } else {
                    System.out.println("Data is already prepared");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // continue execution and sending data
            System.out.println("Sending data...");
        }
    }

    public void sendDataForJoinTest(Thread thread) {
        synchronized (monitor) {
            System.out.println("Waiting for data to be prepared...");
            try {

                if(map == null){
                    thread.start();
                    thread.join();
                    System.out.println("joined finished");
                    monitor.wait();
                } else {
                    System.out.println("Data is already prepared");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // continue execution and sending data
            System.out.println("Sending data...");
        }
    }

    public void prepareData() {
        synchronized (monitor) {
            map = new HashMap<String, String>();
            map.put("key", "value");
            System.out.println("Data prepared for sending");
            monitor.notifyAll();
        }
    }

    public void prepareDataForSleep() {
        synchronized (monitor) {
            map = new HashMap<String, String>();
            map.put("key", "value");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Data prepared for sending");
            monitor.notifyAll();
        }
    }

    public void prepareDataForDeamonTest() {
        synchronized (monitor) {
            map = new HashMap<String, String>();
            map.put("key", "value");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Data prepared for sending");
            monitor.notifyAll();
        }
    }

    public void prepareDataForInterrupt() {
        synchronized (monitor) {
            int i = 100000;
            while(i>0){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Preparation failed  ");
                    return;
                }
                i--;
                System.out.println("executing");
            }
//            map = new HashMap<String, String>();
//            map.put("key", "value");
            System.out.println("Data prepared for sending");
            monitor.notifyAll();
        }
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
