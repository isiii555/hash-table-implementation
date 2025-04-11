package com.hashtable.hash_table_implementation.repository;

import com.hashtable.hash_table_implementation.repository.entity.BucketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BucketRepository extends JpaRepository<BucketEntity, Integer> {
    Optional<BucketEntity> findByHashTable_IdAndBucketIndex(int tableId, int bucketIndex);
}
