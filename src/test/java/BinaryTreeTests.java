import org.example.implementations.HashTableEntry;
import org.example.interfaces.BinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.implementations.*;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTests {
    private BinaryTree<HashTableEntry<Integer, String>> tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTreeImpl<>();
    }

    @Test
    void test_insert_and_search() {
        var entry1 = new HashTableEntry<>(1, "A");
        tree.insert(entry1);
        assertEquals(entry1, tree.search(entry1));
    }

    @Test
    void test_delete() {
        var entry1 = new HashTableEntry<>(1, "A");
        tree.insert(entry1);
        tree.delete(entry1);
        assertNull(tree.search(entry1));
    }

    @Test
    void test_size() {
        var entry1 = new HashTableEntry<>(1,"A");
        var entry2 = new HashTableEntry<>(2,"B");

        tree.insert(entry1);
        tree.insert(entry2);

        assertEquals(2,tree.size());
    }
}
