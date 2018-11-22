import java.util.*;

public class RunAVLTree {
    public static void main(String[] args) {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.add(4);
        avlTree.add(5);
        avlTree.add(6);
        avlTree.add(7);
        avlTree.add(8);
        avlTree.add(9);
        avlTree.add(10);
        avlTree.add(11);
        SortedSet<Integer> sortedSet = avlTree.subSet(2,10 );
        Iterator<Integer> it = sortedSet.iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
        avlTree.traversal();
        System.out.println(sortedSet.contains(4));

    }
}
