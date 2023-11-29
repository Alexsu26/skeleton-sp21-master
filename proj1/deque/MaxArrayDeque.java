package deque;


import java.util.Comparator;

/**
 * 1. 所有arraydeque的方法
 * 2. 不检查equals方法
 *
 */

public class MaxArrayDeque<MyType> extends ArrayDeque<MyType> {
    private Comparator<MyType> comparator;
    //
    public MaxArrayDeque(Comparator<MyType> c) {
        comparator = c;
    }

    public MyType max() {
        return max(comparator);
    }

    public MyType max(Comparator<MyType> c) {
        if (isEmpty()) {
            return null;
        }
        int maxIdx = 0;
        for (int i = 1; i < size(); i += 1) {
            if (c.compare(get(i), get(maxIdx)) > 0) {
                maxIdx = i;
            }
        }
        return get(maxIdx);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        MaxArrayDeque<MyType> other = (MaxArrayDeque<MyType>) o;
        for (int i = 0; i < this.size(); i++) {
            if (comparator.compare(this.get(i), other.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

}
