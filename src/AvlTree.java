import java.util.*;

public class AvlTree<T extends Comparable<T>> implements SortedSet<T> {

    private static class AVLNode<T> {
        private T data;
        int height = 0;
        AVLNode<T> left = null;
        AVLNode<T> right = null;

        AVLNode(T data) {
            this.data = data;
        }
    }

    private void traversal(AVLNode<T> node) {
        if (node == null) return;
        traversal(node.left);
        System.out.println(node.data);
        traversal(node.right);
    }

    public void traversal() {
        traversal(root);
    }

    private AVLNode<T> root = null;

    private int size = 0;

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        AVLNode<T> closest = find(t);
        return closest != null && closest.data.compareTo(t) == 0;
    }

    private AVLNode<T> find(T data) {
        if (root == null) return null;
        return findNode(root, data);
    }

    private AVLNode<T> findNode(AVLNode<T> start, T data) {
        int comparison = data.compareTo(start.data);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return findNode(start.left, data);
        } else {
            if (start.right == null) return start;
            return findNode(start.right, data);
        }
    }

    @Override
    public boolean add(T t) {
        if (contains(t)) return false;
        root = insertNode(root, t);
        size++;
        return true;
    }

    @Override
    public T first() {
        return getMinNode(root).data;
    }

    @Override
    public T last() {
        return getMaxNode(root).data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        if (!contains(o)) return false;
        root = removeNode(root, t);
        size--;
        return true;
    }

    private AVLNode<T> insertNode(AVLNode<T> currentNode, T dataToInsert) {
        if (currentNode == null) {
            return new AVLNode<>(dataToInsert);
        }
        if (dataToInsert.compareTo(currentNode.data) < 0) {
            currentNode.left = insertNode(currentNode.left, dataToInsert);
        } else {
            currentNode.right = insertNode(currentNode.right, dataToInsert);
        }
        currentNode = balanceTree(currentNode, dataToInsert);

        currentNode.height = calculateTreeHeight(currentNode);

        return currentNode;
    }

    private int height(AVLNode<T> currentNode) {
        if (currentNode == null) {
            return -1;
        }
        return currentNode.height;
    }

    private int calculateTreeHeight(AVLNode<T> currentNode) {
        return Math.max(height(currentNode.left), height(currentNode.right)) + 1;
    }

    private int getBalanceValue(AVLNode<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }
        return height(currentNode.left) - height(currentNode.right);
    }

    private boolean isRightHeavy(AVLNode<T> currentNode) {
        return getBalanceValue(currentNode) < -1;
    }

    private boolean isLeftHeavy(AVLNode<T> currentNode) {
        return getBalanceValue(currentNode) > 1;
    }

    private AVLNode<T> balanceTree(AVLNode<T> currentNode, T dataToInsert) {
        if (isRightHeavy(currentNode) && dataToInsert.compareTo(currentNode.right.data) > 0) {
            return leftRotate(currentNode);
        }

        if (isLeftHeavy(currentNode) && dataToInsert.compareTo(currentNode.left.data) < 0) {
            return rightRotate(currentNode);
        }

        if (isRightHeavy(currentNode) && dataToInsert.compareTo(currentNode.right.data) < 0) {
            currentNode.right = rightRotate(currentNode.right);
            return leftRotate(currentNode);
        }

        if (isLeftHeavy(currentNode) && dataToInsert.compareTo(currentNode.left.data) > 0) {
            currentNode.left = leftRotate(currentNode.left);
            return rightRotate(currentNode);
        }

        return currentNode;
    }

    private AVLNode<T> balanceTree(AVLNode<T> current) {
        int balance = getBalanceValue(current);

        if (balance > 1) {
            if (getBalanceValue(current.left) < 0) {
                current.left = leftRotate(current.left);
            }
            return rightRotate(current);
        }

        if (balance < -1) {
            if (getBalanceValue(current.right) > 0) {
                current.right = rightRotate(current.right);
            }
            return leftRotate(current);
        }
        return current;
    }

    private AVLNode<T> rightRotate(AVLNode<T> currentNode) {
        AVLNode<T> newRootNode = currentNode.left;
        currentNode.left = newRootNode.right;
        newRootNode.right = currentNode;
        return newRootNode;
    }

    private AVLNode<T> leftRotate(AVLNode<T> currentNode) {
        AVLNode<T> newRootNode = currentNode.right;
        currentNode.right = newRootNode.left;
        newRootNode.left = currentNode;
        return newRootNode;
    }

    private AVLNode<T> getMaxNode(AVLNode<T> currentNode) {
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode;
    }

    private AVLNode<T> getMinNode(AVLNode<T> currentNode) {
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    private AVLNode<T> removeNode(AVLNode<T> current, T dataToRemove) {
        if (current == null) {
            return null;
        }

        AVLNode<T> leftChild = current.left;
        AVLNode<T> rightChild = current.right;
        if (dataToRemove.compareTo(current.data) == 0) {
            if (leftChild == null && rightChild == null) {
                return null;
            } else if (leftChild == null) {
                current = null;
                return rightChild;
            } else if (rightChild == null) {
                current = null;
                return leftChild;
            } else {
                AVLNode<T> successor = getMaxNode(leftChild);
                current.data = successor.data;
                current.left = removeNode(current.left, successor.data);
            }
        } else if (dataToRemove.compareTo(current.data) < 0) {
            current.left = removeNode(leftChild, dataToRemove);
        } else {
            current.right = removeNode(rightChild, dataToRemove);
        }
        current.height = calculateTreeHeight(current);

        return balanceTree(current);
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return new ImpSortedSet<>(this, false, fromElement, false, toElement);
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return new ImpSortedSet<>(this, true, null, false, toElement);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return new ImpSortedSet<>(this, false, fromElement, true, null);
    }

    public class AvlTreeIterator implements Iterator<T> {

        private AVLNode<T> current = null;

        private int location = 0;

        private List<AVLNode<T>> list;

        private T toElement , fromElement;

        private AvlTreeIterator() {
            list = new ArrayList<>();
            addToList(root);
        }

        private AvlTreeIterator(T toElement, T fromElement){
            list = new ArrayList<>();
            this.toElement = toElement;
            this.fromElement = fromElement;
            addToSubList(root);
        }

        private void addToSubList(AVLNode<T> node){
            if (node!= null){
                addToSubList(node.left);
                if (inRange(node.data)){
                    list.add(node);
                }
                addToSubList(node.right);
            }
        }

        private boolean inRange(T t){
            if (toElement != null && fromElement != null) {
                return t.compareTo(toElement) > 0 && t.compareTo(fromElement) < 0;
            } else if (toElement == null) {
                return t.compareTo(fromElement) < 0;
            } else return t.compareTo(toElement) >= 0;
        }

        private void addToList(AVLNode<T> node) {
            if (node != null) {
                addToList(node.left);
                list.add(node);
                addToList(node.right);
            }
        }

        private AVLNode<T> findNext() {
            return list.get(location++);
        }

        @Override
        public boolean hasNext() {
            return location < list.size();
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.data;
        }

        @Override
        public void remove() {
            AvlTree.this.remove(list.get(location - 1).data);
            list.remove(list.get(location - 1));
            location--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new AvlTreeIterator();
    }

    private Iterator<T> iterator(T toElement, T fromElement){
        return new AvlTreeIterator(toElement, fromElement);
    }

    @Override
    public Object[] toArray() {
        Object[] r = new Object[size()];
        Iterator<T> it = this.iterator();
        for (int i = 0; i < size(); i++) {
            if (it.hasNext()) {
                r[i] = it.next();
            }
        }
        return r;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] r = a.length >= size() ? a :
                (T1[]) java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), this.size());
        Iterator<T> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) {
                r[i] = null;
                return r;
            }
            r[i] = (T1) it.next();
        }
        return r;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean b = false;
        for (T e : c) {
            if (this.add(e)) b = true;
        }
        return b;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean b = false;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                b = true;
            }
        }
        return b;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean b = false;
        for (Object e : c){
            if (this.contains(e)){
                this.remove(e);
                b = true;
            }
        }
        return b;
    }

    @Override
    public void clear() {
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    static final class ImpSortedSet<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

        final AvlTree<T> m;
        final T lo, hi;
        final boolean fromStart, toEnd;

        ImpSortedSet(AvlTree<T> m,
                     boolean fromStart, T lo,
                     boolean toEnd, T hi) {
            this.m = m;
            this.fromStart = fromStart;
            this.lo = lo;
            this.toEnd = toEnd;
            this.hi = hi;
        }

        final boolean inRange(Object o) {
            @SuppressWarnings("unchecked")
            T t = (T) o;
            if (lo != null && hi != null) {
                return t.compareTo(lo) > 0 && t.compareTo(hi) < 0;
            } else if (lo == null) {
                return t.compareTo(hi) < 0;
            } else return t.compareTo(lo) >= 0;
        }

        @Override
        public boolean add(T t) {
            return inRange(t) && m.add(t);
        }

        @Override
        public boolean remove(Object o) {
            return inRange(o) && m.remove(o);
        }

        @Override
        public boolean contains(Object o) {
            return inRange(o) && m.contains(o);
        }

        @Override
        public SortedSet<T> subSet(T fromElement, T toElement) {
            if (fromElement.compareTo(toElement) > 0) throw new NoSuchElementException();
            return new ImpSortedSet<>(m, false, fromElement,
                    false, toElement);
        }

        @Override
        public SortedSet<T> headSet(T toElement) {
            return new ImpSortedSet<>(m, fromStart, lo, false, toElement);
        }

        @Override
        public SortedSet<T> tailSet(T fromElement) {
            return new ImpSortedSet<>(m, false, fromElement, toEnd, hi);
        }

        @Override
        public T first() {
            if (size() == 0) throw new NoSuchElementException();
            if (lo == null) {
                return m.first();
            } else if (toEnd) {
                return lo;
            } else {
                Iterator<T> bIterator = m.iterator();
                T current = null;
                while (bIterator.hasNext()) {
                    current = bIterator.next();
                    if (current.compareTo(lo) == 0) {
                        current = bIterator.next();
                        break;
                    }
                }
                return current;
            }
        }

        @Override
        public T last() {
            if (size() == 0) throw new NoSuchElementException();
            if (hi == null) {
                return m.last();
            } else {
                Iterator<T> bIterator = m.iterator();
                T current;
                T sCurrent = null;
                while (bIterator.hasNext()) {
                    current = bIterator.next();
                    if (current.compareTo(hi) == 0) {
                        break;
                    }
                    sCurrent = current;
                }
                return sCurrent;
            }
        }

        @Override
        public int size() {
            Iterator<T> it = m.iterator();
            int count = 0;
            while (it.hasNext()){
                if (contains(it.next())) count++;
            }
            return count;
        }

        @Override
        public Iterator<T> iterator() {
            return m.iterator(lo,hi);
        }

        @Override
        public Comparator<? super T> comparator() {
            return null;
        }
    }
}
