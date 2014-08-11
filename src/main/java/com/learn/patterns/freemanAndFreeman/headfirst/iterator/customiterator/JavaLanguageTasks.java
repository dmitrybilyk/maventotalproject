package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiterator;

import java.util.Date;

/**
 * Created by dmitry on 8/11/14.
 */
public class JavaLanguageTasks implements Language {

    private int ARRAY_SIZE = 6;
    private Task[] tasks;

    public Task[] getTasks() {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public JavaLanguageTasks() {
        tasks = new Task[ARRAY_SIZE];
        addItem("ComplexTask1", new Date(), 0);
        addItem("ComplexTask2", new Date(), 1);
        addItem("ComplexTask3", new Date(), 2);
        addItem("ComplexTask4", new Date(), 3);
        addItem("ComplexTask5", new Date(), 4);
        addItem("ComplexTask6", new Date(), 5);
    }

    @Override
    public Iterator createIterator() {
        return new JavaTasksIterator(tasks);
    }

    public void addItem(String name, Date createDate, int position){
        tasks[position] = new Task(name, createDate);
    }
}
