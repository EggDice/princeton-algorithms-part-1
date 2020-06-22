import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTest {
  @Test
  public void testCreation() {
    KdTree ps = new KdTree();
    assertTrue(ps.isEmpty());
    assertEquals(ps.size(), 0);
  }

  @Test
  public void testInsert() {
    KdTree ps = new KdTree();
    ps.insert(new Point2D(0.5, 0.5));
    assertFalse(ps.isEmpty());
    assertEquals(ps.size(), 1);
    assertThrows(IllegalArgumentException.class, () -> {
      ps.insert(null);
    });
  }

  @Test
  public void testContains() {
    KdTree ps = new KdTree();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.8, 0.8);
    ps.insert(p1);
    assertTrue(ps.contains(p1));
    assertFalse(ps.contains(p2));
    assertThrows(IllegalArgumentException.class, () -> {
      ps.contains(null);
    });
  }

  @Test
  public void testContainsTwo() {
    KdTree ps = new KdTree();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.8, 0.8);
    ps.insert(p1);
    ps.insert(p2);
    assertTrue(ps.contains(p1));
    assertTrue(ps.contains(p2));
    assertThrows(IllegalArgumentException.class, () -> {
      ps.contains(null);
    });
  }

  @Test
  public void testDontInsertSame() {
    KdTree ps = new KdTree();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.5, 0.5);
    ps.insert(p1);
    ps.insert(p2);
    assertEquals(ps.size(), 1);
  }

  @Test
  public void testDraw() {
    KdTree ps = new KdTree();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.8, 0.8);
    ps.insert(p1);
    ps.insert(p2);
    //ps.draw();
  }

  @Test
  public void testRange() {
    KdTree ps = new KdTree();
    Point2D p1 = new Point2D(0.8, 0.8);
    Point2D p2 = new Point2D(0.5, 0.5);
    ps.insert(p1);
    ps.insert(p2);
    Point2D[] range = {p2};
    RectHV rect = new RectHV(0.1, 0.1, 0.6, 0.6);
    assertIterableEquals(ps.range(rect), Arrays.asList(range));
    assertThrows(IllegalArgumentException.class, () -> {
      ps.range(null);
    });
  }

  @Test
  public void testRangeBoth() {
    KdTree ps = new KdTree();
    Point2D p1 = new Point2D(0.8, 0.8);
    Point2D p2 = new Point2D(0.5, 0.5);
    ps.insert(p1);
    ps.insert(p2);
    Point2D[] range = {p1, p2};
    RectHV rect = new RectHV(0.1, 0.1, 0.9, 0.9);
    assertIterableEquals(ps.range(rect), Arrays.asList(range));
    assertThrows(IllegalArgumentException.class, () -> {
      ps.range(null);
    });
  }


  @Test
  public void testRangeDeep() {
    KdTree ps = new KdTree();
    Point2D p1 = new Point2D(0.8, 0.8);
    Point2D p2 = new Point2D(0.5, 0.5);
    Point2D p3 = new Point2D(0.2, 0.2);
    ps.insert(p1);
    ps.insert(p2);
    ps.insert(p3);
    Point2D[] range = {p3};
    RectHV rect = new RectHV(0.1, 0.1, 0.4, 0.4);
    assertIterableEquals(ps.range(rect), Arrays.asList(range));
    assertThrows(IllegalArgumentException.class, () -> {
      ps.range(null);
    });
  }

  @Test
  public void testRange4() {
    KdTree ps = new KdTree();
    Point2D A = new Point2D(0.25, 0.125);
    Point2D B = new Point2D(0.5625, 0.25);
    Point2D C = new Point2D(0.8125, 0.75);
    Point2D D = new Point2D(0.0625, 0.5);
    Point2D E = new Point2D(0.875, 0.375);
    Point2D F = new Point2D(0.3125, 0.625);
    Point2D G = new Point2D(0.6875, 0.0625);
    Point2D H = new Point2D(0.5, 0.1875);
    Point2D I = new Point2D(0.1875, 0.3125);
    Point2D J = new Point2D(0.9375, 0.9375);
    ps.insert(A);
    ps.insert(B);
    ps.insert(C);
    ps.insert(D);
    ps.insert(E);
    ps.insert(F);
    ps.insert(G);
    ps.insert(H);
    ps.insert(I);
    ps.insert(J);
    Point2D[] range = {B, H};
    RectHV rect = new RectHV(0.4375, 0.0, 0.625, 1.0);
    assertIterableEquals(ps.range(rect), Arrays.asList(range));
    assertThrows(IllegalArgumentException.class, () -> {
      ps.range(null);
    });
  }

  @Test
  public void testRange5() {
    KdTree ps = new KdTree();
    Point2D A = new Point2D(0.75, 1.0);
    Point2D B = new Point2D(0.5625, 0.25);
    Point2D C = new Point2D(0.9375, 0.8125);
    Point2D D = new Point2D(0.8125, 0.4375);
    Point2D E = new Point2D(0.25, 0.0);
    Point2D F = new Point2D(0.0625, 0.875);
    Point2D G = new Point2D(0.0, 0.6875);
    Point2D H = new Point2D(0.5, 0.5);
    Point2D I = new Point2D(0.3125, 0.125);
    Point2D J = new Point2D(0.875, 0.75);
    ps.insert(A);
    ps.insert(B);
    ps.insert(C);
    ps.insert(D);
    ps.insert(E);
    ps.insert(F);
    ps.insert(G);
    ps.insert(H);
    ps.insert(I);
    ps.insert(J);
    Point2D[] range = {H, D};
    RectHV rect = new RectHV(0.125, 0.3750, 1.0, 0.625);
    assertIterableEquals(ps.range(rect), Arrays.asList(range));
    assertThrows(IllegalArgumentException.class, () -> {
      ps.range(null);
    });
  }

  @Test
  public void testNearest() {
    KdTree ps = new KdTree();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.8, 0.8);
    ps.insert(p1);
    ps.insert(p2);
    assertEquals(ps.nearest(new Point2D(0.6, 0.6)), p1);
    assertThrows(IllegalArgumentException.class, () -> {
      ps.nearest(null);
    });
  }

  @Test
  public void testNearest2() {
    KdTree ps = new KdTree();
    Point2D A = new Point2D(0.7, 0.2);
    Point2D B = new Point2D(0.5, 0.4);
    Point2D C = new Point2D(0.2, 0.3);
    Point2D D = new Point2D(0.4, 0.7);
    Point2D E = new Point2D(0.9, 0.6);
    ps.insert(A);
    ps.insert(B);
    ps.insert(C);
    ps.insert(D);
    ps.insert(E);
    assertEquals(ps.nearest(new Point2D(0.711, 0.792)), E);
    assertThrows(IllegalArgumentException.class, () -> {
      ps.nearest(null);
    });
  }

  @Test
  public void testNearest3() {
    KdTree ps = new KdTree();
    Point2D A = new Point2D(0.7, 0.2);
    Point2D B = new Point2D(0.5, 0.4);
    Point2D C = new Point2D(0.2, 0.3);
    Point2D D = new Point2D(0.4, 0.7);
    Point2D E = new Point2D(0.9, 0.6);
    ps.insert(A);
    ps.insert(B);
    ps.insert(C);
    ps.insert(D);
    ps.insert(E);
    assertEquals(ps.nearest(new Point2D(0.54, 0.144)), A);
    assertThrows(IllegalArgumentException.class, () -> {
      ps.nearest(null);
    });
  }
}
