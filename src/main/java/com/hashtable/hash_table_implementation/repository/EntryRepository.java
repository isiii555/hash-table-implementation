package com.hashtable.hash_table_implementation.repository;

import com.hashtable.hash_table_implementation.repository.entity.EntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<EntryEntity, Integer> {
}
