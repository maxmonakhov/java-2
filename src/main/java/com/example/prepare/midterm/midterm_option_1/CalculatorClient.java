package com.example.prepare.midterm.midterm_option_1;

import java.io.*;
import java.net.*;

public class CalculatorClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Локальный сервер
        int port = 12345; // Порт сервера

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Подключено к серверу. Введите 'exit' для выхода.");

            // Чтение приветственного сообщения от сервера
            System.out.println(in.readLine());

            String userInput;
            while (true) {
                System.out.print("Введите выражение: ");
                userInput = consoleReader.readLine();
                if (userInput.equalsIgnoreCase("exit")) {
                    out.println(userInput);
                    break;
                }

                out.println(userInput);
                System.out.println("Ответ сервера: " + in.readLine());
            }
        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
    }
}
