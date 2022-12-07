package ru.netology.javacore;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        try (Socket clientSocket = new Socket(HOST, PORT)) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("{ \"type\": \"REMOVE\", \"task\": \"fitness\" }");
            System.out.println(in.readLine());
        }
    }
}
