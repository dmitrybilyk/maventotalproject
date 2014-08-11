package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiteratorjavaversion;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by dmitry on 8/11/14.
 */
public class PythonTasksIterator implements Iterator {
    private Map<String, Task> tasks;
    private int sizeOfMap;

    public PythonTasksIterator(Map<String, Task> tasks) {
        this.tasks = tasks;
        sizeOfMap = tasks.size();
    }

    @Override
    public boolean hasNext() {
        Set<String> tasksList = tasks.keySet();
        return sizeOfMap != 0;
    }

    @Override
    public Object next() {
        Set<String> tasksList = tasks.keySet();
        String taskNameToExclude = null;
        for (String taskName : tasksList) {
            taskNameToExclude = taskName;
        }
        Task task = tasks.get(taskNameToExclude);
        tasks.remove(taskNameToExclude);
        sizeOfMap -= 1;
        return task;
    }

    @Override
    public void remove() {

    }
}
