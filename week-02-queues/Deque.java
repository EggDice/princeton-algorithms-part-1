import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
  private int size;
  private Node first;
  private Node last;

  // construct an empty deque
  public Deque() {
    size = 0;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    Node newFirst = new Node(item);
    if (first != null) {
      first.prev = newFirst;
    }
    newFirst.next = first;
    first = newFirst;
    if (last == null) {
      last = first;
    }
    ++size;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    Node newLast = new Node(item);
    if (last != null) {
      last.next = newLast;
    }
    newLast.prev = last;
    last = newLast;
    if (first == null) {
      first = last;
    }
    ++size;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (first == null) {
      throw new NoSuchElementException();
    }
    Item output = first.data;
    first = first.next;
    if (first == null) {
      last = null;
    } else {
      first.prev = null;
    }
    --size;
    return output;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (last == null) {
      throw new NoSuchElementException();
    }
    Item output = last.data;
    last = last.prev;
    if (last == null) {
      first = null;
    } else {
      last.next = null;
    }
    --size;
    return output;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class Node {
    Item data;
    Node next;
    Node prev;

    Node(Item d) {
      data = d;
    }
  }

  private class DequeIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    // return current data and update pointer
    public Item next() {
      if (current == null) {
        throw new NoSuchElementException();
      }
      Item output = current.data;
      current = current.next;
      return output;
    }

    // implement if needed
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> d = new Deque<Integer>();
    d.addLast(1);
    d.addLast(2);
    d.addLast(3);
    d.addFirst(4);
    d.addFirst(5);
    d.addFirst(6);
    StdOut.println(d.removeLast());
    StdOut.println(d.removeFirst());
    StdOut.println(d.size());
    StdOut.println(d.isEmpty());
    for (Integer i : d) {
      StdOut.println(i);
    }
  }

}
