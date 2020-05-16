import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FastCollinearPointsTest {

  @Test
  public void testCreation() {
    Point[] points = {};
    FastCollinearPoints bcp = new FastCollinearPoints(points);
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
    FastCollinearPoints bcp = new FastCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 1);
    LineSegment[] segments = bcp.segments();
    assertEquals(segments[0].toString(), "(1, 1) -> (4, 4)");
  }

  @Test void testCreationWithNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      FastCollinearPoints bcp = new FastCollinearPoints(null);
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
      FastCollinearPoints bcp = new FastCollinearPoints(points);
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
    FastCollinearPoints bcp = new FastCollinearPoints(points);
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
    FastCollinearPoints bcp = new FastCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 2);
    LineSegment[] segments = bcp.segments();
    assertEquals(segments[0].toString(), "(1, 1) -> (4, 4)");
    assertEquals(segments[1].toString(), "(5, 1) -> (2, 4)");
  }

  @Test
  public void testExampleSegments8() {
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
    FastCollinearPoints bcp = new FastCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 2);
    LineSegment[] segments = bcp.segments();
    assertEquals(segments[0].toString(), "(10000, 0) -> (0, 10000)");
    assertEquals(segments[1].toString(), "(3000, 4000) -> (20000, 21000)");
  }

  @Test
  public void testExampleSegmentsWithLongerThan4() {
    Point[] points = {
      new Point(19000, 10000),
      new Point(18000, 10000),
      new Point(32000, 10000),
      new Point(21000, 10000),
      new Point(1234, 5678),
      new Point(14000, 10000)
    };
    FastCollinearPoints bcp = new FastCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 1);
  }


  @Test
  public void testExampleSegments40() {
   Point[] points = {
      new Point( 1000, 17000),
      new Point( 14000, 24000),
      new Point( 26000,   8000),
      new Point( 10000,  28000),
      new Point( 18000,   5000),
      new Point(  1000,  27000),
      new Point( 14000,  14000),
      new Point( 11000,  16000),
      new Point( 29000,  17000),
      new Point(  5000,  21000),
      new Point( 19000,  26000),
      new Point( 28000,  21000),
      new Point( 25000,  24000),
      new Point( 30000,  10000),
      new Point( 25000,  14000),
      new Point( 31000,  16000),
      new Point(  5000,  12000),
      new Point(  1000,  31000),
      new Point(  2000,  24000),
      new Point( 13000,  17000),
      new Point(  1000,  28000),
      new Point( 14000,  16000),
      new Point( 26000,  26000),
      new Point( 10000,  31000),
      new Point( 12000,   4000),
      new Point(  9000,  24000),
      new Point( 28000,  29000),
      new Point( 12000,  20000),
      new Point( 13000,  11000),
      new Point(  4000,  26000),
      new Point(  8000,  10000),
      new Point( 15000,  12000),
      new Point( 22000,  29000),
      new Point(  7000,  15000),
      new Point( 10000,   4000),
      new Point(  2000,  29000),
      new Point( 17000,  17000),
      new Point(  3000,  15000),
      new Point(  4000,  29000),
      new Point( 19000,   2000)
    };
    FastCollinearPoints bcp = new FastCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 4);
    LineSegment[] segments = bcp.segments();
  }

  @Test
  public void testExampleSegmentsWithLongerThan4Again() {
    Point[] points = {
      new Point(5, 5),
      new Point(1, 1),
      new Point(3, 3),
      new Point(4, 4),
      new Point(6, 4),
      new Point(6, 7),
      new Point(2, 2)
    };
    FastCollinearPoints bcp = new FastCollinearPoints(points);
    assertEquals(bcp.numberOfSegments(), 1);
  }
}
