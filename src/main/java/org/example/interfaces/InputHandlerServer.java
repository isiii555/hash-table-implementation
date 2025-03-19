package org.example.interfaces;

import org.example.dao.BucketDAO;
import org.example.dao.EntryDAO;
import org.example.dao.HashTableDAO;
import org.example.implementations.HashTableEntry;

public interface InputHandlerServer {
    default void saveDatabase(HashTable<Integer, String> table) {
        HashTableDAO hashTableDAO = new HashTableDAO();
        BucketDAO bucketDAO = new BucketDAO();
        EntryDAO entryDAO = new EntryDAO();

        int tableId = hashTableDAO.saveHashTable(table);
        if (tableId == -1) {
            System.out.println("Failed to save hash table.");
            return;
        }

        for (Bucket<HashTableEntry<Integer, String>> bucket : table.getBuckets()) {
            int bucketId = bucketDAO.saveBucket(tableId, bucket.size());
            if (bucketId == -1) {
                System.out.println("Failed to save bucket.");
                continue;
            }

            for (var element : bucket.getElements()) {
                entryDAO.saveEntry(bucketId, element.getKey(), element.getValue());
            }
        }
    }
}
