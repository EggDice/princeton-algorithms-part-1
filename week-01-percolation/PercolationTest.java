import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PercolationTest {

  @Test
  public void testCreation() {
    Percolation p = new Percolation(1);
    assertEquals(p.isOpen(1, 1), false);
    assertEquals(p.isFull(1, 1), false);
    assertEquals(p.percolates(), false);
    assertEquals(p.numberOfOpenSites(), 0);
  }

  @Test
  public void testOutOfBound() {
    Percolation p = new Percolation(1);
    assertThrows(IllegalArgumentException.class, () -> {
      p.isOpen(0, 0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      p.isOpen(0, 1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      p.isOpen(1, 0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      p.isOpen(2, 2);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      p.isOpen(2, 1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      p.isOpen(1, 2);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      p.open(2, 2);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      p.isFull(2, 2);
    });
  }

  @Test
  public void testSingleOpen() {
    Percolation p = new Percolation(1);
    p.open(1, 1);
    assertEquals(p.isOpen(1, 1), true);
    assertEquals(p.isFull(1, 1), true);
    assertEquals(p.percolates(), true);
    assertEquals(p.numberOfOpenSites(), 1);
  }

  @Test
  public void testBigThatDoesNotPercolates() {
    Percolation p = new Percolation(5);
    p.open(1, 1);
    p.open(2, 2);
    p.open(3, 3);
    p.open(4, 4);
    p.open(5, 5);
    assertEquals(p.isOpen(1, 1), true);
    assertEquals(p.isOpen(2, 1), false);
    assertEquals(p.isFull(1, 1), true);
    assertEquals(p.isFull(2, 1), false);
    assertEquals(p.percolates(), false);
    assertEquals(p.numberOfOpenSites(), 5);
  }

  @Test
  public void testBigThatPercolates() {
    Percolation p = new Percolation(5);
    p.open(1, 3);
    p.open(2, 3);
    p.open(3, 3);
    p.open(4, 3);
    p.open(5, 3);
    assertEquals(p.percolates(), true);
    assertEquals(p.numberOfOpenSites(), 5);
  }

  @Test
  public void testBackwash() {
    Percolation p = new Percolation(5);
    p.open(1, 3);
    p.open(2, 3);
    p.open(3, 3);
    p.open(4, 3);
    p.open(5, 3);
    p.open(5, 5);
    assertEquals(p.percolates(), true);
    assertEquals(p.isFull(5, 5), false);
  }

}
