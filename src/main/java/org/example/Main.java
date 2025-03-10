package org.example;

import org.example.implementations.HashTableImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            var scanner = new Scanner(System.in);
            System.out.println("1.Enter values manually");
            System.out.println("2.Enter from file");
            System.out.print("Enter your choice:");
            var choice = scanner.nextInt();

            if (choice == 1) {
                firstChoice(scanner);
                break;
            } else if (choice == 2) {
                secondChoice(scanner);
            } else {
                System.out.println("Wrong choice");
            }
        }
    }

    public static void firstChoice(Scanner scanner) {
        var table = new HashTableImpl<Integer, String>();
        System.out.print("Please enter size of table: ");
        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            System.out.print("Please enter the key(number):");
            int key = scanner.nextInt();
            System.out.print("Please enter the value(symbol):");
            var value = scanner.next();
            table.put(key, value);
            System.out.println((i + 1) + "Entry is added to table.");
        }
        table.print();
    }

    public static void secondChoice(Scanner scanner) {
        var table = new HashTableImpl<Integer, String>();
        System.out.println("Please enter path of file:");
        var path = scanner.next();
        path = path.replace("\\", "/");
        path = path.replace("\"", "");
        var file = new File(path);
        try {
            var fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                var data = fileReader.nextLine();
                var key = Integer.parseInt(data.split(" ")[0]);
                var value = data.split(" ")[1];
                table.put(key, value);
            }
            table.print();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}