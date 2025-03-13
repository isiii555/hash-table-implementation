package org.example.implementations;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.interfaces.HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileInputHandlerServer implements HttpHandler {

    private HashTable<Integer, String> table;

    public FileInputHandlerServer(HashTable<Integer, String> table) {
        this.table = table;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            var body = exchange.getRequestBody();
            var filepath = new String(body.readAllBytes(), StandardCharsets.UTF_8);
            if (!filepath.isEmpty()) {
                filepath  = filepath.replace("\\", "/").replace("\"", "");
                File file = new File(filepath);

                try (Scanner fileReader = new Scanner(file)) {
                    while (fileReader.hasNextLine()) {
                        String value = fileReader.nextLine();
                        int key = (int) (Math.random() * 101);
                        table.put(key, value);
                    }
                    table.print();
                    sendResponse(exchange,201,"Values added to table");
                } catch (FileNotFoundException e) {
                    sendResponse(exchange,404,"File not found");
                }
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
