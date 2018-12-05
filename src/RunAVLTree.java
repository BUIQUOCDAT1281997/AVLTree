import java.util.Iterator;
public class RunAVLTree {
    public static void main(String[] args) {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.add(40);
        avlTree.add(41);
        avlTree.add(42);
        avlTree.add(43);
        avlTree.add(44);
        avlTree.add(45);
        avlTree.traverse();
        Iterator<Integer> it = avlTree.iterator();
        System.out.println();
        while (it.hasNext()){
            int i = it.next();
            if (i==42) it.remove();
        }
        avlTree.traverse();
    }
}
