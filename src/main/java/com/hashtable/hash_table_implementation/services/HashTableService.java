package com.hashtable.hash_table_implementation.services;

public interface HashTableService {
    int createTableAndSave();
    int insertEntryManual(int hashTableId, String value);
    void insertEntryWithFile();
}
