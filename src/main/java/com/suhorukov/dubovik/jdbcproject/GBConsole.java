package com.suhorukov.dubovik.jdbcproject;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GBConsole {

    GBConsole() {

        try (GuestBook guestBook = new GuestBook(DriverManager.getConnection("jdbc:h2:mem:mydatabase"))) {

            Scanner scanner = new Scanner(System.in);
            boolean check = true;
            String command;

            while (check) {

                System.out.println("Введите: list, add или exit:");

                command = scanner.nextLine();

                if ("EXIT".equalsIgnoreCase(command)) {
                    check = false;

                } else {

                    if ("ADD".equalsIgnoreCase(command)) {
                        System.out.println("Введите текст сообщения:");
                        guestBook.addRecord(scanner.nextLine());

                    } else if ("LIST".equalsIgnoreCase(command)) {
                        List<Record> records = guestBook.getRecords();

                        if (records.isEmpty()) {
                            System.out.println("В базе нет элементов для отображения");
                        } else {

                            System.out.println(String.format("%-5s%-15s%s", "ID", "postDate", "postMessage"));
                            for (Record record : records) {
                                System.out.println(String.format("%-5s%-15s%s", record.getId(),
                                        record.getPostDate(), record.getPostMessage()));
                            }}
                    } else {
                        System.out.println("Неверная команда.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
