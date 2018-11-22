import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {
    private SortedSet<Integer> aTree = new AvlTree<>();

    private void insert() {
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
        SortedSet<Integer> tree = new AvlTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(2);
        tree.add(7);
        tree.add(9);
        tree.add(10);
        tree.add(8);
        tree.add(4);
        tree.add(3);
        tree.add(6);
        SortedSet<Integer> subSet = tree.subSet(4, 8);
        assertEquals(false, subSet.contains(1));
        assertEquals(false, subSet.contains(2));
        assertEquals(false, subSet.contains(3));
        assertEquals(false, subSet.contains(4));
        assertEquals(false, subSet.contains(8));
        assertEquals(false, subSet.contains(9));
        assertEquals(false, subSet.contains(10));
        assertEquals(true, subSet.contains(5));
        assertEquals(true, subSet.contains(6));
        assertEquals(true, subSet.contains(7));

        assertEquals(10, tree.size());
        assertEquals(3, subSet.size());
        tree.add(12);
        assertFalse(subSet.contains(12));
        tree.remove(7);
        assertFalse(subSet.contains(7));
        subSet.add(7);
        assertTrue(tree.contains(7));
        subSet.remove(6);
        assertFalse(tree.contains(6));
    }

    @Test
    void headSet() {
        SortedSet<Integer> tree = new AvlTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(2);
        tree.add(7);
        tree.add(9);
        tree.add(10);
        tree.add(8);
        tree.add(4);
        tree.add(3);
        tree.add(6);
        SortedSet<Integer> headSet = tree.headSet(100);
        for (int i = 1; i < 11; i++) {
            assertEquals(true, headSet.contains(i));
        }
        headSet = tree.headSet(8);
        assertEquals(true, headSet.contains(1));
        assertEquals(true, headSet.contains(2));
        assertEquals(true, headSet.contains(3));
        assertEquals(true, headSet.contains(4));
        assertEquals(true, headSet.contains(5));
        assertEquals(true, headSet.contains(6));
        assertEquals(true, headSet.contains(7));
        assertEquals(false, headSet.contains(8));
        assertEquals(false, headSet.contains(9));
        assertEquals(false, headSet.contains(10));

        assertEquals(10, tree.size());
        assertEquals(7, headSet.size());
        tree.add(11);
        assertFalse(headSet.contains(12));
        tree.remove(5);
        assertFalse(headSet.contains(5));
        headSet.remove(4);
        assertFalse(tree.contains(4));
    }

    @Test
    void tailSet() {
        SortedSet<Integer> tree = new AvlTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(2);
        tree.add(7);
        tree.add(9);
        tree.add(10);
        tree.add(8);
        tree.add(4);
        tree.add(3);
        tree.add(6);
        SortedSet<Integer> tailSet = tree.tailSet(4);
        assertEquals(false, tailSet.contains(1));
        assertEquals(false, tailSet.contains(2));
        assertEquals(false, tailSet.contains(3));
        assertEquals(true, tailSet.contains(4));
        assertEquals(true, tailSet.contains(5));
        assertEquals(true, tailSet.contains(6));
        assertEquals(true, tailSet.contains(7));
        assertEquals(true, tailSet.contains(8));
        assertEquals(true, tailSet.contains(9));
        assertEquals(true, tailSet.contains(10));

        tree.add(0);
        assertFalse(tailSet.contains(0));
        tree.add(11);
        assertTrue(tailSet.contains(11));
        tree.remove(7);
        assertFalse(tailSet.contains(7));
        tailSet.remove(2);
        assertTrue(tree.contains(2));

    }

    @Test
    void iterator() {
        SortedSet<Integer> tree = new AvlTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(2);
        tree.add(7);
        tree.add(9);
        tree.add(10);
        tree.add(8);
        tree.add(4);
        tree.add(3);
        tree.add(6);
        Iterator<Integer> it = tree.iterator();
        for (int i = 1; i < 11; i++) {
            assertEquals(i, (int) it.next());
        }
        Iterator<Integer> int2 = tree.iterator();
        while (int2.hasNext()) {
            int2.next();
            int2.remove();
        }
        assertEquals(0, tree.size());
    }

    @Test
    void toArray() {
        SortedSet<String> tree = new AvlTree<>();
        tree.add("don");
        tree.add("doc");
        tree.add("se");
        tree.add("giup");
        tree.add("ban");
        tree.add("chinh");
        tree.add("chan");
        tree.add("hon");
        tree.add("nghin");
        tree.add("lan");
        String[] strings = new String[10];
        strings[0] = "ban";
        strings[1] = "chan";
        strings[2] = "chinh";
        strings[3] = "doc";
        strings[4] = "don";
        strings[5] = "giup";
        strings[6] = "hon";
        strings[7] = "lan";
        strings[8] = "nghin";
        strings[9] = "se";
        assertTrue(Arrays.equals(tree.toArray(), strings));
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