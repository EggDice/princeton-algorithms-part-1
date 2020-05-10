import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DequeTest {

  @Test
  public void testCreation() {
    Deque<Integer> d = new Deque();
    assertEquals(d.isEmpty(), true);
    assertEquals(d.size(), 0);
  }

  @Test
  public void testOutOfBound() {
    Deque<Integer> d = new Deque();
    assertThrows(NoSuchElementException.class, () -> {
      d.removeFirst();
    });
    assertThrows(NoSuchElementException.class, () -> {
      d.removeLast();
    });
    Iterator it = d.iterator();
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
    Deque<Integer> d = new Deque();
    assertThrows(IllegalArgumentException.class, () -> {
      d.addFirst(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      d.addLast(null);
    });
  }

  @Test
  public void testAddOneToFirstRemoveFirst() {
    Deque<Integer> d = new Deque();
    d.addFirst(1);
    assertEquals(d.isEmpty(), false);
    assertEquals(d.size(), 1);
    assertEquals(d.removeFirst(), 1);
    assertEquals(d.size(), 0);
    assertThrows(NoSuchElementException.class, () -> {
      d.removeFirst();
    });
    assertThrows(NoSuchElementException.class, () -> {
      d.removeLast();
    });
  }

  @Test
  public void testAddOneToFirstRemoveLast() {
    Deque<Integer> d = new Deque();
    d.addFirst(1);
    assertEquals(d.removeLast(), 1);
    assertEquals(d.size(), 0);
    assertThrows(NoSuchElementException.class, () -> {
      d.removeLast();
    });
    assertThrows(NoSuchElementException.class, () -> {
      d.removeFirst();
    });
  }

  @Test
  public void testAddOneIterate() {
    Deque<Integer> d = new Deque();
    d.addFirst(1);
    int iterations = 0;
    for (Integer i : d) {
      assertEquals(i, 1);
      ++iterations;
    }
    assertEquals(iterations, 1);
  }

  @Test
  public void testAddThreeToFirstRemoveFirsts() {
    Deque<Integer> d = new Deque();
    d.addFirst(1);
    d.addFirst(2);
    d.addFirst(3);
    assertEquals(d.size(), 3);
    assertEquals(d.removeFirst(), 3);
    assertEquals(d.removeFirst(), 2);
    assertEquals(d.removeFirst(), 1);
    assertEquals(d.size(), 0);
    assertThrows(NoSuchElementException.class, () -> {
      d.removeLast();
    });
    assertThrows(NoSuchElementException.class, () -> {
      d.removeFirst();
    });
  }

  @Test
  public void testAddThreeToLastRemoveLasts() {
    Deque<Integer> d = new Deque();
    d.addLast(1);
    d.addLast(2);
    d.addLast(3);
    assertEquals(d.size(), 3);
    assertEquals(d.removeLast(), 3);
    assertEquals(d.removeLast(), 2);
    assertEquals(d.removeLast(), 1);
    assertEquals(d.size(), 0);
    assertThrows(NoSuchElementException.class, () -> {
      d.removeLast();
    });
    assertThrows(NoSuchElementException.class, () -> {
      d.removeFirst();
    });
  }

  @Test
  public void testAddThreeToFirstRemoveLasts() {
    Deque<Integer> d = new Deque();
    d.addFirst(1);
    d.addFirst(2);
    d.addFirst(3);
    assertEquals(d.size(), 3);
    assertEquals(d.removeLast(), 1);
    assertEquals(d.removeLast(), 2);
    assertEquals(d.removeLast(), 3);
    assertEquals(d.size(), 0);
    assertThrows(NoSuchElementException.class, () -> {
      d.removeLast();
    });
    assertThrows(NoSuchElementException.class, () -> {
      d.removeFirst();
    });
  }

  @Test
  public void testAddThreeToLastRemoveFirst() {
    Deque<Integer> d = new Deque();
    d.addLast(1);
    d.addLast(2);
    d.addLast(3);
    assertEquals(d.size(), 3);
    assertEquals(d.removeFirst(), 1);
    assertEquals(d.removeFirst(), 2);
    assertEquals(d.removeFirst(), 3);
    assertEquals(d.size(), 0);
    assertThrows(NoSuchElementException.class, () -> {
      d.removeLast();
    });
    assertThrows(NoSuchElementException.class, () -> {
      d.removeFirst();
    });
  }

  @Test
  public void testRandomSequences() {
    Deque<Integer> deque = new Deque();
    deque.addLast(1);
    assertEquals(deque.removeLast(), 1);
    deque.addFirst(3);
    deque.addLast(4);
    deque.addLast(5);
    assertEquals(deque.removeLast(), 5);
    Iterator it = deque.iterator();
    assertEquals(it.next(), 3);
    assertEquals(it.next(), 4);
    assertEquals(it.hasNext(), false);
  }
}
