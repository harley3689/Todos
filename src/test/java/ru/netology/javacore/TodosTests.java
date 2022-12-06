package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TodosTests {
    private Todos todos;

    @BeforeEach
    void setUpApp() {
        todos = new Todos();
    }


    @ParameterizedTest
    @MethodSource("getArguments")
    @DisplayName("Test add.Task")
    void addTask(List<String> tasks, String expected) {
        for (String task : tasks) {
            todos.addTask(task);
        }
        Assertions.assertEquals(expected, todos.getAllTasks());
    }


    @ParameterizedTest
    @MethodSource("getArguments")
    @DisplayName("test add.ExtraTask")
    void addExtraTask(List<String> tasks, String expected) {
        for (String task : tasks) {
            todos.addTask(task);
        }
        todos.addTask("Ф");
        Assertions.assertEquals(expected, todos.getAllTasks());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForRemoveTask")
    @DisplayName("Test removeTask")
    void removeTask(List<String> tasks, String expected) {
        for (String task : tasks) {
            todos.addTask(task);
        }
        todos.removeTask("А");
        Assertions.assertEquals(expected, todos.getAllTasks());
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList("В", "А", "И", "Р", "З", "Э", "Г"), "А В Г З И Р Э"));
    }

    private static Stream<Arguments> getArgumentsForRemoveTask() {
        return Stream.of(
                Arguments.of(Arrays.asList("В", "А", "И", "Р", "З", "Э", "Г"), "В Г З И Р Э"));
    }

}
