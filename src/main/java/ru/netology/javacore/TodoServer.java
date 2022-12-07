package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Deque;

public class TodoServer {
    int port;
    Todos todos;
    private Deque<Request> dequeRequest;

    public TodoServer(int port, Todos todos) {
        this.todos = todos;
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    Gson gson = new Gson();
                    Request request = gson.fromJson(in.readLine(), Request.class);

                    switch (request.getType()) {
                        case "ADD":
                            todos.addTask(request.getTask());
                            break;
                        case "REMOVE":
                            todos.removeTask(request.getTask());
                            break;
                        case "RESTORE":
                            if (!dequeRequest.isEmpty()) {
                                Request actualRequest = dequeRequest.pollLast();
                                if (actualRequest.getType().equals("ADD")) {
                                    todos.removeTask(actualRequest.getTask());
                                }
                                if (actualRequest.getType().equals("REMOVE")) {
                                    todos.addTask(actualRequest.getTask());
                                }
                            }
                            break;
                    }
                    out.println(todos.getAllTasks());

                }
            }

        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}