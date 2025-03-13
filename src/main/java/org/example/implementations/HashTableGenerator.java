package org.example.implementations;

import org.example.interfaces.InputHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HashTableGenerator {

    public static void generate() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Enter values manually");
            System.out.println("2. Enter from file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                InputHandler inputHandler = InputHandlerFactory.getInputHandler(choice);
                if (inputHandler != null) {
                    inputHandler.handleInput(new Scanner(System.in));
                } else if (choice == 3) {
                    System.out.println("Exiting program...");
                    break;
                } else {
                    System.out.println("Wrong choice...");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong choice...");
                scanner.nextLine();
            }
        }
    }
}
