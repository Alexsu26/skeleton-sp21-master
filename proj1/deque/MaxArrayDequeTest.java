package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    @Test
    public void maxNoParaIntTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());
        for (int i = 0; i < 5; i += 1) {
            mad.addFirst(i);
        }
        int maxItem = mad.max();
        assertEquals( 4, maxItem);
    }

    @Test
    public void maxNoParaStrTest() {
        MaxArrayDeque<String> mads = new MaxArrayDeque<>(new StringComparator());
        mads.addFirst("hello");
        mads.addFirst("world");
        mads.addFirst("zzzz");

        assertEquals("zzzz", mads.max());
    }


    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
}
