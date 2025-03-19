package org.example.implementations;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.interfaces.HashTable;
import org.example.interfaces.InputHandlerServer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ManualInputHandlerServer implements HttpHandler, InputHandlerServer {
    private final HashTable<Integer, String> table;

    public ManualInputHandlerServer(HashTable<Integer, String> table) {
        this.table = table;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            var body = exchange.getRequestBody();
            var value = new String(body.readAllBytes(), StandardCharsets.UTF_8);
            int key = (int) (Math.random() * 101);
            try {
                table.insert(new HashTableEntry<>(key, value));
                table.print();
                saveDatabase(table);
                sendResponse(exchange, 201, "Value added" + value);
            } catch (IllegalArgumentException e) {
                sendResponse(exchange, 400, e.getMessage());
            }


        } else {
            sendResponse(exchange, 405, "Method not allowed");
        }
    }

    void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
