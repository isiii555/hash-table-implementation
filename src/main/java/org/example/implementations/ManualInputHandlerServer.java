package org.example.implementations;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.interfaces.HashTable;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ManualInputHandlerServer implements HttpHandler {
    private HashTable<Integer, String> table;

    public ManualInputHandlerServer(HashTable<Integer, String> table) {
        this.table = table;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            var body = exchange.getRequestBody();
            var value = new String(body.readAllBytes(), StandardCharsets.UTF_8);
            if (!value.isEmpty()) {
                int key = (int) (Math.random() * 101);
                table.put(key, value);
                table.print();
                sendResponse(exchange, 201, "Value added" + value);
            } else {
                sendResponse(exchange, 400, "Value is empty");
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
