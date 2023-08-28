package deque;
/**
 * 1. add和remove复杂度为O1
 * 2. get必须遍历，不能递归
 * size必须O1
 * 不在队列中的应该删除
 */
public class LinkedListDeque<MyType> {
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

    // add to front
    public void addFirst(MyType item) {
        DequeNode<MyType> temp = new DequeNode<>(item);
        temp.next = sentinel.next;
        sentinel.next.prev = temp;
        sentinel.next = temp;
        temp.prev = sentinel;
        size += 1;
    }

    // add to back
    public void addLast(MyType item) {
        DequeNode<MyType> temp = new DequeNode<>(item);
        temp.prev = sentinel.prev;
        sentinel.prev.next = temp;
        temp.next = sentinel;
        sentinel.prev = temp;
        size += 1;
    }

    // return if the deque is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // return the #item
    public int size() {
        return  size;
    }

    // print all item separated by one space, with a new line
    public void printDeque() {
        DequeNode<MyType> temp = sentinel;
        while (temp.next != sentinel) {
            temp = temp.next;
            System.out.print(temp.item + " ");
        }
        System.out.println();
    }

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

    // TODO : use iterator
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

    // TODO : add iterator and equals
}
