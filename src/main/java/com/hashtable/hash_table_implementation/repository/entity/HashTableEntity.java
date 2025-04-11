package com.hashtable.hash_table_implementation.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "hashTables", schema = "dbo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private int threshold;

    @OneToMany(mappedBy = "hashTable", cascade = CascadeType.ALL)
    private List<BucketEntity> buckets;
}
