package deque;

import java.util.Iterator;

/**
 * 1. add和remove复杂度为O1
 * 2. get必须遍历，不能递归
 * size必须O1
 * 不在队列中的应该删除
 */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private static class DequeNode<T> {
        private final T item;
        private DequeNode<T> prev;
        private DequeNode<T> next;

        DequeNode(T i) {
            item = i;
        }
    }

    private final DequeNode<T> sentinel;
    private int size;

    // create empty LinkedList
    public LinkedListDeque() {
        sentinel = new DequeNode<>(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
    // create a deep copy of other (last to do)
    public LinkedListDeque(LinkedListDeque<T> other) {
        DequeNode<T> t = other.sentinel;
        sentinel = new DequeNode<>(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        while (t.next != other.sentinel) {
            t = t.next;
            this.addLast(t.item);
        }
    }
     */

    private T getRecursiveHelp(DequeNode<T> node, int index) {
        if (index == 0) {
            return node.item;
        }
        if (node.next == sentinel) {
            return null;
        }
        return getRecursiveHelp(node.next, index - 1);
    }

    // get in recursive version
    public T getRecursive(int index) {
        if (sentinel.next == sentinel) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }

    @Override
    // add to front
    public void addFirst(T item) {
        DequeNode<T> temp = new DequeNode<>(item);
        temp.next = sentinel.next;
        sentinel.next.prev = temp;
        sentinel.next = temp;
        temp.prev = sentinel;
        size += 1;
    }

    @Override
    // add to back
    public void addLast(T item) {
        DequeNode<T> temp = new DequeNode<>(item);
        temp.prev = sentinel.prev;
        sentinel.prev.next = temp;
        temp.next = sentinel;
        sentinel.prev = temp;
        size += 1;
    }

    @Override
    // return the #item
    public int size() {
        return  size;
    }

    @Override
    // print all item separated by one space, with a new line
    public void printDeque() {
        DequeNode<T> temp = sentinel;
        while (temp.next != sentinel) {
            temp = temp.next;
            System.out.print(temp.item + " ");
        }
        System.out.println();
    }

    @Override
    // remove and return the first item, or return null
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        DequeNode<T> res = sentinel.next;
        sentinel.next = res.next;
        res.next.prev = sentinel;
        size--;
        return res.item;
    }

    @Override
    // remove and return the last item, or return null
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        DequeNode<T> res = sentinel.prev;
        res.prev.next = sentinel;
        sentinel.prev = res.prev;
        size--;
        return res.item;
    }

    @Override
    // get item in the giving index, 0 is the front; or return null
    public T get(int index) {
        if (sentinel.next == null) {
            return null;
        }
        DequeNode<T> temp = sentinel;
        while (temp.next != sentinel) {
            temp = temp.next;
            if (index == 0) {
                return temp.item;
            }
            index--;
        }
        return null;
    }


    // implements the iterator method
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    // help method of iterator
    private class LinkedListDequeIterator implements Iterator<T> {
        DequeNode<T> p = sentinel.next;

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public T next() {
            T item = p.item;
            p = p.next;
            return item;
        }
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedListDeque<?>)) {
            return false;
        }
        LinkedListDeque<?> other = (LinkedListDeque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }


}
