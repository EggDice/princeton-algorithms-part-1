import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;
    private int capacity;

    // construct an empty randomized queue
    public RandomizedQueue() {
      items = (Item[]) new Object[2];
      size = 0;
      capacity = 4;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
      return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
      return size;
    }

    // add the item
    public void enqueue(Item item) {
      if (item == null) {
        throw new IllegalArgumentException();
      }
      int index = StdRandom.uniform(0, size + 1);
      if (index == size) {
        items[size] = item;
      } else {
        items[size] = items[index];
        items[index] = item;
      }
      ++size;
      increaseCapacity();
    }

    // remove and return a random item
    public Item dequeue() {
      if (size == 0) {
        throw new NoSuchElementException();
      }
      Item output = items[size - 1];
      items[size - 1] = null;
      --size;
      decreaseCapacity();
      return output;
    }

    private void decreaseCapacity() {
      if (size <= capacity / 4) {
        capacity /= 2;
        resizeItems();
      }
    }

    private void increaseCapacity() {
      if (size >= capacity / 2) {
        capacity *= 2;
        resizeItems();
      }
    }

    private void resizeItems() {
      Item[] newItems = (Item[]) new Object[capacity];
      for (int i = 0; i < size; ++i) {
        newItems[i] = items[i];
      }
      items = newItems;
    }

    // return a random item (but do not remove it)
    public Item sample() {
      if (size == 0) {
        throw new NoSuchElementException();
      }
      return items[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
      return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
      private final Item[] iteratorItems = (Item[]) new Object[size];
      private final int iteratorSize = size;
      private int index = 0;

      RandomizedQueueIterator() {
        for (int i = 0; i < size; ++i) {
          iteratorItems[i] = items[i];
        }
        StdRandom.shuffle(iteratorItems);
      }

      public boolean hasNext() {
        return index < iteratorSize;
      }

      // return current data and update pointer
      public Item next() {
        if (index >= iteratorSize) {
          throw new NoSuchElementException();
        }
        Item output = iteratorItems[index];
        ++index;
        return output;
      }

      // implement if needed
      public void remove() {
        throw new UnsupportedOperationException();
      }
    }

    // unit testing (required)
    public static void main(String[] args) {
      RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
      r.enqueue(1);
      r.enqueue(2);
      r.enqueue(2);
      StdOut.println(r.dequeue());
      StdOut.println(r.sample());
      StdOut.println(r.size());
      StdOut.println(r.isEmpty());
      for (Integer i : r) {
        StdOut.println(i);
      }
    }

}
