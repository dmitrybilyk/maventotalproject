package com.learn.bases.collectionsTest;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 09.05.12
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */
public class TestCollections {
    public static void main(String[] args){
//        testLists();
//        testSets();
//          testMaps();
//            testStack();
        testQueue();
    }

    private static void testMaps() {
//        Map<String, Bean> map = new TreeMap<String, Bean>();
        Map<String, Bean> map = new HashMap<String, Bean>();
//        Map<String, Bean> map = new LinkedHashMap<String, Bean>();
        for(int i=0; i<10000; i++){
            map.put(String.valueOf(i), new Bean(10 + i, String.valueOf(i)));
        }

        Iterator iterator = map.entrySet().iterator();

        Date date = new Date();
        long beginTime= date.getTime();

        while(iterator.hasNext()){
             Map.Entry<String, Bean> mapEntry = (Map.Entry<String, Bean>) iterator.next();
            System.out.println(mapEntry.getKey()+"   "+mapEntry.getValue().getA());
        }
        Date finishDate = new Date();
        long finishTime = finishDate.getTime();
        System.out.println("time "+(finishTime-beginTime));
        
    }

    private static void testSets() {
//        Set<Bean> beanSet = new HashSet<Bean>();
//        Set<Bean> beanSet = new LinkedHashSet<Bean>();
        Set<Bean> beanSet = new TreeSet<Bean>();
        Bean bean = new Bean(34, "testeDuplicatedBean");
        Bean bean2 = new Bean(374, "testfdsfeDuplicatedBean");
        for(int i=0; i<10000; i++){
            beanSet.add(new Bean(10+i, String.valueOf(i)));
        }
        beanSet.add(bean);
        beanSet.add(bean2);
        Date date = new Date();
        long beginTime= date.getTime();
//        System.out.println(beanSet.remove(bean));
        for (Bean bean1 : beanSet) {
            System.out.println(bean1.getA()+ " "+ bean1.getB());
        }
        Date finishDate = new Date();
        long finishTime = finishDate.getTime();
        System.out.println(finishTime-beginTime);
        
    }

    private static void testLists() {
        List<Bean> beanList = new ArrayList<Bean>();
        for(int i=0; i<100; i++){
            beanList.add(new Bean(20+i, String.valueOf(i)));
        }
        Date date = new Date();
        long beginTime= date.getTime();
        beanList.add(50, new Bean(50, "TestBean"));
        for(Bean bean: beanList){
            System.out.println(bean);
        }
//        System.out.println(beanList.get(50).getB());
//        System.out.println(beanList.get(52).getB());
        Date finishDate = new Date();
        long finishTime = finishDate.getTime();
        System.out.println(finishTime-beginTime);
    }

    private static void testStack(){
        Stack<String> stack = new Stack<String>();
        stack.push("Dioma");
        stack.push("Dioma2");
        stack.push("Dioma3");

        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.get(1));

    }

    private static void testQueue(){
        Queue<String> queue = new ArrayDeque<String>();
        queue.add("Dioma");
        queue.add("Dioma2");
        queue.add("Dioma3");

        System.out.println(queue.poll());
        System.out.println(queue.peek());

    }
}
