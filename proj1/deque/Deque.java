package deque;

public interface Deque<MyType> {
    public void addFirst(MyType item);
    public void addLast(MyType item);
    public default boolean isEmpty() {
        return size() == 0;
    }
    public int size();
    public void printDeque();
    public MyType removeFirst();
    public MyType removeLast();
    public MyType get(int i);
}
