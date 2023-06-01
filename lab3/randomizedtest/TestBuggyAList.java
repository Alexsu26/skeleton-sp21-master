package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    final int times = 5000;
    final int val = 100;
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> sureRight = new AListNoResizing<>();
        BuggyAList<Integer> mayBug = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            sureRight.addLast(i);
            mayBug.addLast(i);
        }

        assertEquals(sureRight.size(), mayBug.size());

        for (int i = 0; i < 3; i++) {
            assertEquals(sureRight.removeLast(), mayBug.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = times;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, val);
                correct.addLast(randVal);
                broken.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertEquals(correct.size(), broken.size());
            } else if (operationNumber == 2) {
                // getLast
                if (correct.size() == 0) {
                    continue;
                }
                assertEquals(correct.getLast(), broken.getLast());
            } else if (operationNumber == 3) {
                // removeLast
                if (correct.size() == 0) {
                    continue;
                }
                assertEquals(correct.removeLast(), broken.removeLast());
            }
        }
    }
}
