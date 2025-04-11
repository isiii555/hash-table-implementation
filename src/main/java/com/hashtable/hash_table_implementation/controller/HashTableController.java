package com.hashtable.hash_table_implementation.controller;

import com.hashtable.hash_table_implementation.services.HashTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hash-tables")
public class HashTableController {
    private HashTableService hashTableService;
    @Autowired
    public HashTableController(HashTableService hashTableService) {
        this.hashTableService = hashTableService;
    }

    @PostMapping("/")
    public String create() {
        var id = hashTableService.createTableAndSave();
        return "Hash table created with id: " + id;
    }

    @PostMapping("/manual/{hashTableId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String manual(@PathVariable int hashTableId, @RequestBody String value ) {
        var entryId = hashTableService.insertEntryManual(hashTableId,value);
        return "Entry added with id: " + entryId;
    }

    @PostMapping("/file/{hashTableId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String file(@PathVariable int hashTableId, @RequestBody String filePath) {
//      fileInputService.insertEntry();
        return null;
    }
}
