package com.hashtable.hash_table_implementation.repository;

import com.hashtable.hash_table_implementation.interfaces.HashTable;
import com.hashtable.hash_table_implementation.repository.entity.HashTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTableRepository extends JpaRepository<HashTableEntity, Integer> {
}
