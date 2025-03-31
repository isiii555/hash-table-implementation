package org.example.implementations;

import com.sun.net.httpserver.HttpServer;
import org.example.interfaces.HashTable;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HashTableController  {

    private final FileInputHandlerServer fileHandler;
    private final ManualInputHandlerServer manualHandler;

    public HashTableController() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 0);
        HashTable<Integer, String> table = new HashTableImpl<>();
        fileHandler = new FileInputHandlerServer(table);
        manualHandler = new ManualInputHandlerServer(table);
        server.createContext("/file", fileHandler);
        server.createContext("/manual", manualHandler);
        System.out.println("Server running at port : 8080");
//        server.createContext("/table",exchange -> manualHandler.handle(exchange));
    }

}
