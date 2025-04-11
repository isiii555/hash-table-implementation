package com.hashtable.hash_table_implementation.repository.entity;

import com.hashtable.hash_table_implementation.implementations.HashTableEntry;
import com.hashtable.hash_table_implementation.interfaces.HashTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "buckets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BucketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int size;

    @Column(name = "bucket_index")
    private int bucketIndex;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private HashTableEntity hashTable;

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL)
    private List<EntryEntity> entries;

}
