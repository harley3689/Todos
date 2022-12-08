package ru.netology.javacore;

public class TaskManager {
    Action type;
    String task;

    public TaskManager(Action type, String task) {
        this.type = type;
        this.task = task;
    }

    @Override
    public String toString() {
        return "type : " + type + ", task : " + task;
    }
}
