package deque;


/**
 *  1. 除了调整大小，add和remove应该O1
 *  2. get和size必须O1
 *  3. 数组初始size = 8
 *  4. 使用因子小于0.25时，数组长度减半
 */
public class ArrayDeque<MyType> {

    private MyType[] myNode;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double R;

    final int minSize = 16;


    // create an empty array
    public ArrayDeque() {
        myNode = (MyType[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
        R = 0.0;
    }

    // create a deep copy of other
    public ArrayDeque(ArrayDeque<MyType> other) {
        myNode = (MyType[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
        R = 0.0;
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
        MyType[] temp = (MyType[]) new Object[newSize];
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

    // add to front
    public void addFirst(MyType item) {
        if (size == myNode.length) {
            resizemyNode(2 * size);
        }

        myNode[nextFirst] = item;
        nextFirst = leftMove(nextFirst);
        size++;
        setR();
    }

    // find the next last after inserting
    private int rightMove(int idx) {
        return (idx + 1) % myNode.length;
    }

    // add to last
    public void addLast(MyType item) {
        // array is full
        if (size == myNode.length) {
            resizemyNode(2 * size);
        }
        myNode[nextLast] = item;
        nextLast = rightMove(nextLast);
        size++;
        setR();
    }

    // if array is empty, return true
    public boolean isEmpty() {
        return size == 0;
    }

    // return #item
    public int size() {
        return size;
    }

    // print items separated by one space, with a new line in the end
    public void printDeque() {
        for (MyType x : myNode) {
            System.out.print(x + " ");
        }
        System.out.println();
    }


    // remove and return first, or return null
    public MyType removeFirst() {
        nextFirst = rightMove(nextFirst);
        if (nextFirst == nextLast) {
            nextFirst = leftMove(nextFirst);
            return null;
        }
        size--;
        MyType res = myNode[nextFirst];
        setR();
        if (myNode.length >= minSize && R < 0.25) {
            resizemyNode(myNode.length / 2);
        }
        return res;
    }

    // remove and return last, or return null
    public MyType removeLast() {
        nextLast = leftMove(nextLast);
        if (nextLast == nextFirst) {
            nextLast = rightMove(nextLast);
            return null;
        }
        size--;
        MyType res = myNode[nextLast];
        setR();
        if (myNode.length >= minSize && R < 0.25) {
            resizemyNode(myNode.length / 2);
        }
        return res;
    }

    // get ith item, or return null
    public MyType get(int index) {
        if (index > myNode.length - 1) {
            return null;
        }
        return myNode[(rightMove(nextFirst) + index) % myNode.length];
    }


}
