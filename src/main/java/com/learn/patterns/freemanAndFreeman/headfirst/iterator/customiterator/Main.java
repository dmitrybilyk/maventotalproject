package com.learn.patterns.freemanAndFreeman.headfirst.iterator.customiterator;

/**
 * Created by dmitry on 8/11/14.
 */
public class Main {
    public static void main(String[] args) {
        TeamLeader teamLeader = new TeamLeader(new JavaLanguageTasks(), new PhpLanguageTasks(), new PythonLanguageTasks());
        teamLeader.viewTasks();
    }
}
