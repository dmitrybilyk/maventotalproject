package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiterator;

/**
 * Created by dmitry on 8/11/14.
 */
public class TeamLeader {
    private JavaLanguageTasks javaLanguageTasks;
    private PhpLanguageTasks phpLanguageTasks;
    private PythonLanguageTasks pythonLanguageTasks;

    public TeamLeader(JavaLanguageTasks javaLanguageTasks, PhpLanguageTasks phpLanguageTasks, PythonLanguageTasks pythonLanguageTasks) {
        this.javaLanguageTasks = javaLanguageTasks;
        this.phpLanguageTasks = phpLanguageTasks;
        this.pythonLanguageTasks = pythonLanguageTasks;
    }

    public void viewTasks(){
        print(javaLanguageTasks.createIterator());
        print(phpLanguageTasks.createIterator());
        print(pythonLanguageTasks.createIterator());
    }


    private void print(Iterator iterator){
        while(iterator.hasNext()){
            System.out.println(((Task)iterator.next()).getName());
        }
    }

}
