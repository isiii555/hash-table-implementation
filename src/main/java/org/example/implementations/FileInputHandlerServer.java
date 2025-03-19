package org.example.implementations;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.interfaces.HashTable;
import org.example.interfaces.InputHandlerServer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileInputHandlerServer implements HttpHandler, InputHandlerServer {

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
                filepath = filepath.replace("\\", "/").replace("\"", "");
                File file = new File(filepath);
                var jsonConverter = new JsonConverter();
                try (Scanner fileReader = new Scanner(file)) {
                    while (fileReader.hasNextLine()) {
                        String value = fileReader.nextLine();
                        int key = (int) (Math.random() * 101);
                        table.insert(new HashTableEntry<>(key, value));
                    }
                    table.print();
                    var json = jsonConverter.convertToJson(table);
                    saveDatabase(table);
                    sendResponse(exchange, 201, json);
                } catch (FileNotFoundException e) {
                    sendResponse(exchange, 404, "File not found");
                } catch (Exception e) {
                    sendResponse(exchange, 500, e.getMessage());
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
