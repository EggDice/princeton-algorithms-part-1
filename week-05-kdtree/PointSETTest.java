import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSETTest {
  @Test
  public void testCreation() {
    PointSET ps = new PointSET();
    assertTrue(ps.isEmpty());
    assertEquals(ps.size(), 0);
  }

  @Test
  public void testInsert() {
    PointSET ps = new PointSET();
    ps.insert(new Point2D(0.5, 0.5));
    assertFalse(ps.isEmpty());
    assertEquals(ps.size(), 1);
    assertThrows(IllegalArgumentException.class, () -> {
      ps.insert(null);
    });
  }

  @Test
  public void testContains() {
    PointSET ps = new PointSET();
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
  public void testDraw() {
    PointSET ps = new PointSET();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.8, 0.8);
    ps.insert(p1);
    ps.insert(p2);
    //ps.draw();
  }

  @Test
  public void testRange() {
    PointSET ps = new PointSET();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.8, 0.8);
    ps.insert(p1);
    ps.insert(p2);
    Point2D[] range = {p1};
    RectHV rect = new RectHV(0.1, 0.1, 0.6, 0.6);
    assertIterableEquals(ps.range(rect), Arrays.asList(range));
    assertThrows(IllegalArgumentException.class, () -> {
      ps.range(null);
    });
  }

  @Test
  public void testNearest() {
    PointSET ps = new PointSET();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(0.8, 0.8);
    ps.insert(p1);
    ps.insert(p2);
    assertEquals(ps.nearest(new Point2D(0.6, 0.6)), p1);
    assertThrows(IllegalArgumentException.class, () -> {
      ps.nearest(null);
    });
  }
}
