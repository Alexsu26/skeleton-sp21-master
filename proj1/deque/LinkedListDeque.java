package deque;

import java.util.Iterator;

/**
 * 1. add和remove复杂度为O1
 * 2. get必须遍历，不能递归
 * size必须O1
 * 不在队列中的应该删除
 */
public class LinkedListDeque<MyType> implements Deque<MyType>, Iterable<MyType> {
    private static class DequeNode<MyType> {
        private MyType item;
        private DequeNode<MyType> prev;
        private DequeNode<MyType> next;

        public DequeNode(MyType i) {
            item = i;
        }
    }

    private DequeNode<MyType> sentinel;
    private int size;

    // create empty LinkedList
    public LinkedListDeque() {
        sentinel = new DequeNode<>(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // create a deep copy of other (last to do)
    public LinkedListDeque(LinkedListDeque<MyType> other) {
        DequeNode<MyType> t = other.sentinel;
        sentinel = new DequeNode<>(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        while (t.next != other.sentinel) {
            t = t.next;
            this.addLast(t.item);
        }
    }

    private MyType getRecursiveHelp(DequeNode<MyType> node, int index) {
        if (index == 0) {
            return node.item;
        }
        if (node.next == sentinel) {
            return null;
        }
        return getRecursiveHelp(node.next, index - 1);
    }

    // get in recursive version
    public MyType getRecursive(int index) {
        if (sentinel.next == sentinel) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }

    @Override
    // add to front
    public void addFirst(MyType item) {
        DequeNode<MyType> temp = new DequeNode<>(item);
        temp.next = sentinel.next;
        sentinel.next.prev = temp;
        sentinel.next = temp;
        temp.prev = sentinel;
        size += 1;
    }

    @Override
    // add to back
    public void addLast(MyType item) {
        DequeNode<MyType> temp = new DequeNode<>(item);
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
        DequeNode<MyType> temp = sentinel;
        while (temp.next != sentinel) {
            temp = temp.next;
            System.out.print(temp.item + " ");
        }
        System.out.println();
    }

    @Override
    // remove and return the first item, or return null
    public MyType removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        DequeNode<MyType> res = sentinel.next;
        sentinel.next = res.next;
        res.next.prev = sentinel;
        size--;
        return res.item;
    }

    @Override
    // remove and return the last item, or return null
    public MyType removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        DequeNode<MyType> res = sentinel.prev;
        res.prev.next = sentinel;
        sentinel.prev = res.prev;
        size--;
        return res.item;
    }

    @Override
    // get item in the giving index, 0 is the front; or return null
    public MyType get(int index) {
        if (sentinel.next == null) {
            return null;
        }
        DequeNode<MyType> temp = sentinel;
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
    public Iterator<MyType> iterator() {
        return new LinkedListDequeIterator();
    }

    // help method of iterator
    private class LinkedListDequeIterator implements Iterator<MyType> {
        DequeNode<MyType> p = sentinel.next;

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public MyType next() {
            MyType item = p.item;
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
        if (this.getClass() != o.getClass()) {
            return false;
        }
        LinkedListDeque<MyType> other = (LinkedListDeque<MyType>) o;
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
