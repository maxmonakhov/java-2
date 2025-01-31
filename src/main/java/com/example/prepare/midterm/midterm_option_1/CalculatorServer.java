package com.example.prepare.midterm.midterm_option_1;

import java.io.*;
import java.net.*;

public class CalculatorServer {
    public static void main(String[] args) {
        int port = 12345; // Порт сервера

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен, ожидает подключения клиентов...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключен: " + clientSocket.getInetAddress());

                // Обработка клиента в отдельном потоке
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Введите операцию в формате: ЧИСЛО ОПЕРАЦИЯ ЧИСЛО (например, 5 + 3)");

            String input;
            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) {
                    out.println("Отключение...");
                    break;
                }

                try {
                    String result = calculate(input);
                    out.println("Результат: " + result);
                } catch (IllegalArgumentException e) {
                    out.println("Ошибка: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка связи с клиентом: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Ошибка закрытия сокета: " + e.getMessage());
            }
        }
    }

    private String calculate(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат. Используйте: ЧИСЛО ОПЕРАЦИЯ ЧИСЛО");
        }

        double num1, num2;
        try {
            num1 = Double.parseDouble(parts[0]);
            num2 = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка: некорректные числа.");
        }

        String operator = parts[1];
        switch (operator) {
            case "+":
                return String.valueOf(num1 + num2);
            case "-":
                return String.valueOf(num1 - num2);
            case "*":
                return String.valueOf(num1 * num2);
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Ошибка: деление на ноль.");
                }
                return String.valueOf(num1 / num2);
            default:
                throw new IllegalArgumentException("Ошибка: неподдерживаемая операция.");
        }
    }
}
