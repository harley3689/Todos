package ru.netology.javacore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Todos {
    private ArrayList<String> taskList = new ArrayList<>();
    private static final int SIZE = 7;

    public void addTask(String task) {
        if (taskList.size() < SIZE) {
            taskList.add(task);
        }
    }

    public void removeTask(String task) {
        taskList.remove(task);
    }

    public String getAllTasks() {
        return taskList.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(" "));
    }
}