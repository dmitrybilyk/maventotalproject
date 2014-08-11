package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiterator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dmitry on 8/11/14.
 */
public class PhpLanguageTasks implements Language{
    List<Task> tasks;

    public PhpLanguageTasks() {
        this.tasks = new ArrayList<Task>();
        tasks.add(new Task("PhpTask1", new Date()));
        tasks.add(new Task("PhpTask2", new Date()));
        tasks.add(new Task("PhpTask3", new Date()));
        tasks.add(new Task("PhpTask4", new Date()));
        tasks.add(new Task("PhpTask5", new Date()));
        tasks.add(new Task("PhpTask6", new Date()));
    }

    @Override
    public Iterator createIterator() {
        return new PhpTasksIterator(tasks);
    }
}
