import org.example.implementations.HashTableEntry;
import org.example.implementations.LinkedListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTests {
    private LinkedListImpl<HashTableEntry<Integer, String>> list;

    @BeforeEach
    void setUp() {
        list = new LinkedListImpl<>();
    }

    @Test
    void test_insert_and_search() {
        var data = new HashTableEntry<>(1, "S");
        list.insert(data);
        assertEquals(data, list.search(data));
    }

    @Test
    void test_addFirst() {
        var data = new HashTableEntry<>(1, "S");
        list.addFirst(data);
        assertEquals(data, list.search(data));
    }

    @Test
    void test_remove() {
        var data = new HashTableEntry<>(1, "S");
        list.insert(data);
        var result = list.remove(data);
        assertTrue(result);
    }

    @Test
    void test_isEmpty() {
        var data = new HashTableEntry<>(1, "S");
        list.insert(data);
        var result = list.isEmpty();
        assertFalse(result);
    }

    @Test
    void test_size() {
        var data = new HashTableEntry<>(1, "S");
        list.insert(data);
        assertEquals(1, list.size());
    }
}
