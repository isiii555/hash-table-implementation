import org.example.implementations.HashTableImpl;
import org.example.interfaces.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTests {
    private HashTable<Integer,String> table;

    @BeforeEach
    void setUp() {
        table = new HashTableImpl<>(10);
    }

    @Test
    void test_put_and_get() {
        //act
        table.put(1,"A");
        table.put(2,"B");

        //assert
        assertEquals("A",table.get(1));
        assertEquals("B",table.get(2));
    }

    @Test
    void test_get_with_none_existing_key() {
        //act
        table.put(1,"A");

        //assert
        assertNull(table.get(5));
    }

    @Test
    void test_clear() {
        //arrange
        table.put(1,"A");
        table.put(2,"B");

        //act
        table.clear();

        //assert
        assertNull(table.get(1));
        assertNull(table.get(2));
    }

    @Test
    void test_duplicate_key() {
        //act
        table.put(1,"A");
        table.put(1,"B");

        //assert
        assertEquals("A",table.get(1));
        assertEquals(1,table.size());
    }

    @Test
    void test_size() {
        table.put(1,"A");
        table.put(21,"A");
        table.put(31,"A");
        table.put(41,"A");
        table.put(23,"A");
        assertEquals(5,table.size());
    }

    @Test
    void test_binary_tree() {
        table.put(1,"A");
        table.put(11,"B");
        table.put(21,"B");
        table.put(31,"B");
        table.put(41,"B");
        table.put(51,"B");
        table.put(61,"B");
        table.put(71,"C");
        table.put(81,"D");
        table.put(91,"E");

        assertEquals("E",table.get(91));
    }
}
