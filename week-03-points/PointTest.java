import java.util.Comparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointTest {

  @Test
  public void testCreation() {
    Point p = new Point(0, 0);
  }

  @Test
  public void testSlope() {
    Point p0 = new Point(1, 1);
    Point p1 = new Point(2, 3);
    assertEquals(p0.slopeTo(p1), 2.0);

    Point p2 = new Point(1, 2);
    Point p3 = new Point(2, 2);
    assertEquals(p2.slopeTo(p3), +0.0);

    Point p4 = new Point(2, 2);
    Point p5 = new Point(1, 2);
    assertEquals(p4.slopeTo(p5), +0.0);

    Point p6 = new Point(2, 1);
    Point p7 = new Point(2, 2);
    assertEquals(p6.slopeTo(p7), Double.POSITIVE_INFINITY);

    Point p8 = new Point(2, 2);
    Point p9 = new Point(2, 2);
    assertEquals(p8.slopeTo(p9), Double.NEGATIVE_INFINITY);
  }

  @Test
  public void testCompare() {
    Point p0 = new Point(1, 1);
    Point p1 = new Point(2, 2);
    assertTrue(p0.compareTo(p1) < 0);

    Point p2 = new Point(1, 2);
    Point p3 = new Point(2, 2);
    assertTrue(p2.compareTo(p3) < 0);

    Point p4 = new Point(2, 2);
    Point p5 = new Point(2, 2);
    assertTrue(p4.compareTo(p5) == 0);

    Point p6 = new Point(2, 2);
    Point p7 = new Point(1, 1);
    assertTrue(p6.compareTo(p7) > 0);

    Point p8 = new Point(2, 2);
    Point p9 = new Point(1, 2);
    assertTrue(p8.compareTo(p9) > 0);
  }

  @Test
  public void testComparator() {
    Point p0 = new Point(1, 1);
    Point p1 = new Point(2, 2);
    Point p2 = new Point(3, 4);
    Comparator<Point> comp0 = p0.slopeOrder();
    System.out.println(comp0.compare(p1, p2));
    assertTrue(comp0.compare(p1, p2) < 0);

    Point p3 = new Point(1, 1);
    Point p4 = new Point(2, 2);
    Point p5 = new Point(3, 0);
    Comparator<Point> comp1 = p3.slopeOrder();
    assertTrue(comp1.compare(p4, p5) > 0);
  }
}
