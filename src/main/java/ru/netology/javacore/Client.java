package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8989;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter TODO: ");
        String input = scanner.nextLine();
        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"ADD\", \"task\": \"" + input + "\" }");
            String response = in.readLine();
            System.out.println(response);
        }
    }
}