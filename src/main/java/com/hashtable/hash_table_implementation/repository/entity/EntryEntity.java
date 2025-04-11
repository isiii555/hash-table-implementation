package com.hashtable.hash_table_implementation.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,name = "[key]")
    private int key;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "bucket_id", nullable = false)
    private BucketEntity bucket;
}