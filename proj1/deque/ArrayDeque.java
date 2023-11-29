package deque;
import java.util.Iterator;

/**
 *  1. 除了调整大小，add和remove应该O1
 *  2. get和size必须O1
 *  3. 数组初始size = 8
 *  4. 使用因子小于0.25时，数组长度减半
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] myNode;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double R;

    final int minSize = 16;
    private boolean isFull;


    // create an empty array
    public ArrayDeque() {
        myNode = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
        R = 0.0;
        isFull = false;
    }

    // create a deep copy of other
    public ArrayDeque(ArrayDeque<T> other) {
        myNode = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
        R = 0.0;
        isFull = false;
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }

    // reset R = size / myNode.length
    private void setR() {
        R = (double) size / myNode.length;
    }

    // resize the array
    private void resizemyNode(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        nextFirst = rightMove(nextFirst);
        nextLast = leftMove(nextLast);
        int cnt = 0;
        while (nextFirst != nextLast) {
            temp[cnt++] = myNode[nextFirst];
            nextFirst = rightMove(nextFirst);
        }
        temp[cnt++] = myNode[nextFirst];
        myNode = temp;
        nextFirst = myNode.length - 1;
        size = cnt;
        nextLast = size;
        setR();
    }

    // find the next first after insert to front
    private int leftMove(int idx) {
        return (idx + myNode.length - 1) % myNode.length;
    }

    @Override
    // add to front
    public void addFirst(T item) {
        if (size == myNode.length) {
            resizemyNode(2 * size);
        }

        myNode[nextFirst] = item;
        nextFirst = leftMove(nextFirst);
        size++;
        setR();
        if (leftMove(nextLast) == nextFirst) {
            isFull = true;
        }
    }

    // find the next last after inserting
    private int rightMove(int idx) {
        return (idx + 1) % myNode.length;
    }

    @Override
    // add to last
    public void addLast(T item) {
        // array is full
        if (size == myNode.length) {
            resizemyNode(2 * size);
        }
        myNode[nextLast] = item;
        nextLast = rightMove(nextLast);
        size++;
        setR();
        if (leftMove(nextLast) == nextFirst) {
            isFull = true;
        }
    }

    @Override
    // return #item
    public int size() {
        return size;
    }

    @Override
    // print items separated by one space, with a new line in the end
    public void printDeque() {
        for (T x : myNode) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    @Override
    // remove and return first, or return null
    public T removeFirst() {
        nextFirst = rightMove(nextFirst);
        if (nextFirst == nextLast && !isFull) {
            nextFirst = leftMove(nextFirst);
            return null;
        }
        size--;
        T res = myNode[nextFirst];
        setR();
        if (isFull) {
            isFull = false;
        }
        if (myNode.length >= minSize && R < 0.25) {
            resizemyNode(myNode.length / 2);
        }
        return res;
    }


    @Override
    // remove and return last, or return null
    public T removeLast() {
        nextLast = leftMove(nextLast);
        if (nextLast == nextFirst && !isFull) {
            nextLast = rightMove(nextLast);
            return null;
        }
        size--;
        if (isFull) {
            isFull = false;
        }
        T res = myNode[nextLast];
        setR();
        if (myNode.length >= minSize && R < 0.25) {
            resizemyNode(myNode.length / 2);
        }
        return res;
    }

    @Override
    // get ith item, or return null
    public T get(int index) {
        if (index > myNode.length - 1) {
            return null;
        }
        return myNode[(rightMove(nextFirst) + index) % myNode.length];
    }


    // make the class iterable
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    // help method of iterator
    private class ArrayDequeIterator implements Iterator<T> {
        private int firstIdx = rightMove(nextFirst);
        @Override
        public boolean hasNext() {
            return firstIdx != nextLast;
        }

        @Override
        public T next() {
            T item = myNode[firstIdx];
            firstIdx = rightMove(firstIdx);
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
        if (!(o instanceof ArrayDeque<?>)) {
            return false;
        }
        ArrayDeque<?> other = (ArrayDeque<?>) o;
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
