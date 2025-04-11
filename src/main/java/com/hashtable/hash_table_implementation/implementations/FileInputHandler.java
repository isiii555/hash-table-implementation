package com.hashtable.hash_table_implementation.implementations;

import com.hashtable.hash_table_implementation.interfaces.InputHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class FileInputHandler implements InputHandler {
    @Override
    public void handleInput(Scanner scanner) {
        var table = new HashTableImpl<Integer, String>();
        System.out.println("Please enter path of file (or type 'back' to return):");

        String path = scanner.nextLine();
        if (Objects.equals(path, "back")) {
            System.out.println("Returning to main menu...");
            return;
        }

        path = path.replace("\\", "/").replace("\"", "");
        File file = new File(path);

        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String value = fileReader.nextLine();
                int key = (int) (Math.random() * 101);
                table.put(key, value);
            }
            table.print();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
