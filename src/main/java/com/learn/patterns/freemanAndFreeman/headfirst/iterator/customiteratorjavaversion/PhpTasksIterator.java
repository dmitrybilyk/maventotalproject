package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiteratorjavaversion;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dmitry on 8/11/14.
 */
public class PhpTasksIterator implements Iterator {

    private List<Task> tasks;
    private int position = 0;

    public PhpTasksIterator(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean hasNext() {
        return position < tasks.size();
    }

    @Override
    public Object next() {
        Task task = tasks.get(position);
        position += 1;
        return task;
    }

    @Override
    public void remove() {

    }
}
