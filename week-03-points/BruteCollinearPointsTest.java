import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BruteCollinearPointsTest {
  @Test
  public void testCreation() {
    Point[] points = {};
    BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 0);
  }

  @Test
  public void testSingleLine() {
    Point[] points = {
      new Point(1, 1),
      new Point(2, 2),
      new Point(3, 3),
      new Point(4, 4)
    };
    BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 1);
    LineSegment[] segments = bcp.segments();
    assertEquals(segments[0].toString(), "(1, 1) -> (4, 4)");
  }

  @Test void testCreationWithNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      BruteCollinearPoints bcp = new BruteCollinearPoints(null);
    });
  }

  @Test void testPointDuplication() {
    Point[] points = {
      new Point(2, 2),
      new Point(1, 1),
      new Point(3, 3),
      new Point(1, 1)
    };
    assertThrows(IllegalArgumentException.class, () -> {
      BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    });
  }

  @Test
  public void testNoSegment() {
    Point[] points = {
      new Point(1, 1),
      new Point(2, 2),
      new Point(3, 3),
      new Point(4, 5)
    };
    BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 0);
  }

  @Test
  public void testTwoSegments() {
    Point[] points = {
      new Point(1, 1),
      new Point(2, 2),
      new Point(2, 4),
      new Point(3, 3),
      new Point(4, 4),
      new Point(4, 2),
      new Point(5, 1)
    };
    BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 2);
    LineSegment[] segments = bcp.segments();
    assertEquals(segments[0].toString(), "(1, 1) -> (4, 4)");
    assertEquals(segments[1].toString(), "(5, 1) -> (2, 4)");
  }

  @Test
  public void testExampleSegments() {
    Point[] points = {
      new Point(1000, 17000),
      new Point(1000, 27000),
      new Point(1000, 28000),
      new Point(1000, 31000),
    };
    BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 1);
    LineSegment[] segments = bcp.segments();
    assertEquals(segments[0].toString(), "(1000, 17000) -> (1000, 31000)");
  }

  @Test
  public void testExampleSegments2() {
    Point[] points = {
      new Point(10000, 0),
      new Point(0, 10000),
      new Point(3000, 7000),
      new Point(7000, 3000),
      new Point(20000, 21000),
      new Point(3000, 4000),
      new Point(14000, 15000),
      new Point(6000, 7000)
    };
    BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 2);
    LineSegment[] segments = bcp.segments();
    assertEquals(segments[0].toString(), "(10000, 0) -> (0, 10000)");
    assertEquals(segments[1].toString(), "(3000, 4000) -> (20000, 21000)");
  }
}
