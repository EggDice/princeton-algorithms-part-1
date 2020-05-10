import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomizedQueueTest {

  @Test
  public void testCreation() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    assertEquals(r.isEmpty(), true);
    assertEquals(r.size(), 0);
  }

  @Test
  public void testOutOfBound() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    assertThrows(NoSuchElementException.class, () -> {
      r.dequeue();
    });
    assertThrows(NoSuchElementException.class, () -> {
      r.sample();
    });
    Iterator it = r.iterator();
    assertEquals(it.hasNext(), false);
    assertThrows(NoSuchElementException.class, () -> {
      it.next();
    });
    assertThrows(UnsupportedOperationException.class, () -> {
      it.remove();
    });
  }

  @Test
  public void testNullInput() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    assertThrows(IllegalArgumentException.class, () -> {
      r.enqueue(null);
    });
  }

  @Test
  public void testEnqueueOneThenDequeue() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    r.enqueue(1);
    assertEquals(r.isEmpty(), false);
    assertEquals(r.size(), 1);
    assertEquals(r.sample(), 1);
    assertEquals(r.dequeue(), 1);
    assertEquals(r.size(), 0);
    assertThrows(NoSuchElementException.class, () -> {
      r.dequeue();
    });
  }

  @Test
  public void testEnqueueOneThenIterate() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    r.enqueue(1);
    int iterations = 0;
    for (Integer i : r) {
      assertEquals(i, 1);
      ++iterations;
    }
    assertEquals(iterations, 1);
  }

  @Test
  public void testEnqueue100() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    for (int i = 0; i < 100; ++i) {
      r.enqueue(1);
    }
    for (int i = 0; i < 100; ++i) {
      r.dequeue();
    }
    assertEquals(r.size(), 0);
  }

  @Test
  public void testRandomDequeue() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    for (int i = 0; i < 100; ++i) {
      r.enqueue(i);
    }
    boolean wasDifferent = false;
    for (int i = 0; i < 100; ++i) {
      int item = r.dequeue();
      if (i != item && 100 - i - 1 != item) {
        wasDifferent = true;
      }
    }
    assertEquals(wasDifferent, true);
  }

  @Test
  public void testRandomIterators() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    for (int i = 0; i < 100; ++i) {
      r.enqueue(i);
    }
    Iterator<Integer> it1 = r.iterator();
    Iterator<Integer> it2 = r.iterator();
    boolean wasDifferent = false;
    for (int i = 0; i < 100; ++i) {
      int i1 = r.dequeue();
      int i2 = it1.next();
      int i3 = it2.next();
      if (i1 != i2 && i1 != i3 && i2 != i3) {
        wasDifferent = true;
      }
    }
    assertEquals(wasDifferent, true);
  }

  @Test
  public void testRandomSample() {
    RandomizedQueue<Integer> r = new RandomizedQueue();
    for (int i = 0; i < 100; ++i) {
      r.enqueue(i);
    }
    assertNotEquals(r.sample(), r.sample());
  }
}
