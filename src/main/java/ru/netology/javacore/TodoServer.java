package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private int port = 8989;
    private final Todos todos;
    String task;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public TodoServer(int port, Todos todos, String task) {
        this.port = port;
        this.todos = todos;
        this.task = task;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                ) {
                    System.out.printf("New connection is connected on the port %d%n", clientSocket.getPort());
                    String json = in.readLine();

                    Gson gson = new Gson();
                    TaskManager taskManager = gson.fromJson(json, TaskManager.class);

                    switch (taskManager.type) {
                        case ADD:
                            todos.addTask(taskManager.task);
                            break;
                        case REMOVE:
                            todos.removeTask(taskManager.task);
                            break;
                    }
                    out.println(todos.getAllTasks());
                    System.out.println(taskManager);
                }
            }
        }
    }
}