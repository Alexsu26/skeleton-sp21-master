package deque;
import jh61b.junit.In;
import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDequeTest {

    @Test
    public void addTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        assertTrue("should be empty", ad1.isEmpty());

        ad1.addFirst(10);
        assertEquals(1, ad1.size());

        ad1.addLast(20);
        assertEquals(2, ad1.size());

    }

    @Test
    public void removeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.addLast(2);
        assertFalse("should not be empty", ad.isEmpty());

        int res = ad.removeLast();
        assertEquals(2, res);
        assertTrue("should be empty", ad.isEmpty());

    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
//        ad.addFirst(1);
//        ad.addFirst(2);
//        ad.addFirst(3);
//        ad.addFirst(4);
//        ad.addFirst(5);
//        ad.addFirst(6);
//        ad.addFirst(7);
//        ad.addFirst(8);
//        ad.addFirst(9);
//        ad.printDeque();
//        assertEquals(9, ad.size());
//        assertEquals(16, ad.Node.length);

        ad.addFirst(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addFirst(5);
        ad.addLast(6);
        ad.addFirst(7);
        ad.addLast(8);
        ad.addLast(9);
        assertEquals(9, ad.size());
//        assertEquals(16, ad.Node.length);

    }

    @Test
    public void removeNullTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertTrue(ad.isEmpty());
        assertNull(ad.removeLast());
        assertNull(ad.removeFirst());
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 1; i <= 9; i ++ ) {
            ad.addLast(i);
        }
        for (int i = 0; i < 9; i ++ ) {
            int t = ad.get(i);
            assertEquals(i + 1, t);
        }
    }

    @Test
    public void downSizeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 1; i <= 9; i ++ ) {
            ad.addLast(i);
        }

        for (int i = 0; i < 6; i ++ ) {
            ad.removeLast();
        }

        assertEquals(3, ad.size());
//        assertEquals(8, ad.Node.length);
    }

    // copy test
    @Test
    public void copyTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 1; i < 10; i ++ ){
            ad1.addLast(i);
        }

        ArrayDeque<Integer> ad2 = new ArrayDeque<>(ad1);
        assertNotSame("should not be same", ad1, ad2);
        for (int i = 0; i < ad1.size(); i ++ ) {
            int t = ad2.get(i);
            assertEquals(i + 1, t);
        }
    }
}
