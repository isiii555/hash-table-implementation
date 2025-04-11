package com.hashtable.hash_table_implementation.implementations;

import com.hashtable.hash_table_implementation.interfaces.InputHandler;
public class InputHandlerFactory {
    public static InputHandler getInputHandler(int choice)  {
        return switch (choice) {
            case 1 -> new ManualInputHandler();
            case 2 -> new FileInputHandler();
            default -> null;
        };
    }
}
