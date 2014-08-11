package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiterator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitry on 8/11/14.
 */
public class PythonLanguageTasks implements Language {
    private Map<String, Task> tasks;

    public PythonLanguageTasks() {
        tasks = new HashMap<String, Task>();
        tasks.put("PythonTask1", new Task("PythonTask1", new Date()));
        tasks.put("PythonTask2", new Task("PythonTask2", new Date()));
        tasks.put("PythonTask3", new Task("PythonTask3", new Date()));
        tasks.put("PythonTask4", new Task("PythonTask4", new Date()));
        tasks.put("PythonTask5", new Task("PythonTask5", new Date()));
        tasks.put("PythonTask6", new Task("PythonTask6", new Date()));
    }

    @Override
    public Iterator createIterator() {
        return new PythonTasksIterator(tasks);
    }
}
