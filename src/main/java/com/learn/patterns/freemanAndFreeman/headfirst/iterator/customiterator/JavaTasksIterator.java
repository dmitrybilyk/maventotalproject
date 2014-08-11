package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiterator;

/**
 * Created by dmitry on 8/11/14.
 */
public class JavaTasksIterator implements Iterator {

    private Task[] tasks;
    private int position = 0;

    public JavaTasksIterator(Task[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean hasNext() {
        if (position < tasks.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        Task task = tasks[position];
        position += 1;
        return task;
    }
}
