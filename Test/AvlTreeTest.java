import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {
    SortedSet<Integer> aTree = new AvlTree<>();

    void insert() {
        aTree.add(1);
        aTree.add(2);
        aTree.add(3);
        aTree.add(4);
        aTree.add(5);
        aTree.add(6);
        aTree.add(7);
        aTree.add(8);
        aTree.add(9);
        aTree.add(10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void contains() {
        insert();
        for (int i = 0; i < 10; i++)
            assertTrue(aTree.contains(i + 1));
    }

    @Test
    void add() {
        insert();
        for (int i = 0; i < 10; i++)
            assertTrue(aTree.contains(i + 1));
    }

    @Test
    void first() {
        insert();
        assertEquals(1, (int) aTree.first());
    }

    @Test
    void last() {
        insert();
        assertEquals(10, (int) aTree.last());
    }

    @Test
    void size() {
        assertEquals(0, aTree.size());
        insert();
        assertEquals(10, aTree.size());
    }

    @Test
    void isEmpty() {
        assertTrue(aTree.isEmpty());
        insert();
        assertFalse(aTree.isEmpty());
    }

    @Test
    void remove() {
        insert();
        aTree.remove(5);
        aTree.remove(7);
        assertFalse(aTree.contains(5));
        assertFalse(aTree.contains(7));
    }

    @Test
    void subSet() {
    }

    @Test
    void headSet() {
    }

    @Test
    void tailSet() {
    }

    @Test
    void iterator() {
    }

    @Test
    void toArray() {
    }

    @Test
    void containsAll() {
        insert();
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        assertTrue(aTree.containsAll(list));
        list.add(23);
        assertFalse(aTree.containsAll(list));
    }

    @Test
    void addAll() {
        insert();
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        assertFalse(aTree.addAll(list));
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        assertTrue(aTree.addAll(list));
        for (int i = 12; i < 16; i++) {
            assertTrue(aTree.contains(i));
        }
    }

    @Test
    void retainAll() {
        insert();
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        aTree.retainAll(list);
        assertTrue(aTree.containsAll(list));
        assertFalse(aTree.contains(1));
        assertFalse(aTree.contains(2));
        assertFalse(aTree.contains(7));
        assertFalse(aTree.contains(8));
        assertFalse(aTree.contains(9));
        assertFalse(aTree.contains(10));
    }

    @Test
    void removeAll() {
        insert();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        aTree.removeAll(list);
        assertFalse(aTree.containsAll(list));
    }

    @Test
    void clear() {
        insert();
        aTree.clear();
        assertTrue(aTree.isEmpty());
    }
}