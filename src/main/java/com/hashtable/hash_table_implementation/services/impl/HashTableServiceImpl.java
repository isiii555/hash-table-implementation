package com.hashtable.hash_table_implementation.services.impl;

import com.hashtable.hash_table_implementation.exception.NotFoundException;
import com.hashtable.hash_table_implementation.repository.BucketRepository;
import com.hashtable.hash_table_implementation.repository.EntryRepository;
import com.hashtable.hash_table_implementation.repository.HashTableRepository;
import com.hashtable.hash_table_implementation.implementations.HashTableEntry;
import com.hashtable.hash_table_implementation.implementations.HashTableImpl;
import com.hashtable.hash_table_implementation.interfaces.Bucket;
import com.hashtable.hash_table_implementation.repository.entity.BucketEntity;
import com.hashtable.hash_table_implementation.repository.entity.EntryEntity;
import com.hashtable.hash_table_implementation.repository.entity.HashTableEntity;
import com.hashtable.hash_table_implementation.services.HashTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class HashTableServiceImpl implements HashTableService {
    private HashTableRepository hashTableRepository;
    private EntryRepository entryRepository;
    private BucketRepository bucketRepository;

    @Autowired
    public HashTableServiceImpl(HashTableRepository hashTableRepository, EntryRepository entryRepository, BucketRepository bucketRepository) {
        this.hashTableRepository = hashTableRepository;
        this.entryRepository = entryRepository;
        this.bucketRepository = bucketRepository;
    }

    @Override
    public int createTableAndSave() {
        var hashTable = new HashTableImpl<Integer, String>();
        var hashTableEntity = new HashTableEntity();
        hashTableEntity.setSize(hashTable.size());
        hashTableEntity.setThreshold(hashTable.getThreshold());
        hashTableEntity = hashTableRepository.save(hashTableEntity);

        int bucketIndex = 0;
        for (Bucket<HashTableEntry<Integer, String>> bucket : hashTable.getBuckets()) {
            var bucketEntity = new BucketEntity();
            bucketEntity.setBucketIndex(bucketIndex);
            bucketEntity.setHashTable(hashTableEntity);
            bucketEntity.setSize(bucket.getSize());

            bucketEntity = bucketRepository.save(bucketEntity);
            bucketIndex++;
        }
        return hashTableEntity.getId();
    }

    @Override
    public int insertEntryManual(int hashTableId, String value) {
        var hashTable = hashTableRepository.findById(hashTableId)
                .orElseThrow(() -> new NotFoundException("HashTable not found with ID: " + hashTableId));

        int key = (int) (Math.random() * 101);
        var entry = new EntryEntity();
        entry.setKey(key);
        entry.setValue(value);

        var index = Integer.hashCode(key) % hashTable.getBuckets().size();
        var bucket = bucketRepository.findByHashTable_IdAndBucketIndex(hashTableId,index)
                .orElseThrow(() -> new NotFoundException("Bucket not found with tableID: " + hashTableId));

        entry.setBucket(bucket);
        bucket.setSize(bucket.getSize()+1);

        entry = entryRepository.save(entry);
        bucket.setSize(bucket.getSize()+1);
        bucketRepository.save(bucket);
        return entry.getId();
    }

    @Override
    public void insertEntryWithFile(int hashTableId, String filePath) {
        var hashTable = hashTableRepository.findById(hashTableId)
                .orElseThrow(() -> new NotFoundException("HashTable not found with ID: " + hashTableId));

        filePath = filePath.replace("\\", "/").replace("\"", "");

        var file = new File(filePath);

        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String value = fileReader.nextLine();
                int key = (int) (Math.random() * 101);
                var entry = new EntryEntity();
                entry.setKey(key);
                entry.setValue(value);

                var index = Integer.hashCode(key) % hashTable.getBuckets().size();
                var bucket = bucketRepository.findByHashTable_IdAndBucketIndex(hashTableId,index)
                        .orElseThrow(() -> new NotFoundException("Bucket not found with tableID: " + hashTableId));

                entry.setBucket(bucket);
                bucket.setSize(bucket.getSize()+1);

                entry = entryRepository.save(entry);
                bucket.setSize(bucket.getSize()+1);
                bucketRepository.save(bucket);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }


}
