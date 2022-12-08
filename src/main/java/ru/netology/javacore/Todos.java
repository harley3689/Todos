package ru.netology.javacore;

import java.util.ArrayList;
import java.util.Collections;

public class Todos {
    private static final int MAX_TASKS = 7;
    private final ArrayList<String> allTasks;

    public Todos() {
        this.allTasks = new ArrayList<>();
    }

    public void addTask(String task) {
        if (isMaxNumberOfTasks()) {
            this.allTasks.add(task);
        } else {
            System.out.println("You already have a lot of tasks");
        }

    }

    public void removeTask(String task) {
        this.allTasks.remove(task);
    }

    private boolean isMaxNumberOfTasks() {
        return allTasks.size() < MAX_TASKS;
    }

    public String getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(this.allTasks);
        for (String task : this.allTasks
        ) {
            stringBuilder.append(task);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Todos{" +
                "allTasks=" + allTasks +
                '}';
    }
}