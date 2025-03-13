package org.example.implementations;

import org.example.interfaces.InputHandler;

public class InputHandlerFactory {
    public static InputHandler getInputHandler(int choice)  {
        return switch (choice) {
            case 1 -> new ManualInputHandler();
            case 2 -> new FileInputHandler();
            default -> null;
        };
    }
}
