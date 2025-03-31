package org.example.implementations;

import org.example.interfaces.InputHandler;

import java.util.Objects;
import java.util.Scanner;

public class ManualInputHandler implements InputHandler {
    @Override
    public void handleInput(Scanner scanner) {
        var table = new HashTableImpl<Integer, String>();
        System.out.print("Please enter size of table: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }

        int size = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < size; i++) {
            int key = (int) (Math.random() * 101);
            System.out.print("Please enter the value (or type 'back' to return): ");
            String value = scanner.nextLine();

            if (Objects.equals(value, "back")) {
                System.out.println("Returning to main menu...");
                return;
            }

            table.put(key, value);
            System.out.println((i + 1) + " Entry added to table.");
        }
        table.print();
    }
}
