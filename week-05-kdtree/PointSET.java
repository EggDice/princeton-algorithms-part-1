import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
  private final SET<Point2D> set;

  public PointSET() {
    set = new SET<Point2D>();
  }

  public boolean isEmpty() {
    return set.isEmpty();
  }

  public int size() {
    return set.size();
  }

  public void insert(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }
    set.add(p);
  }

  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }
    return set.contains(p);
  }

  public void draw() {
    for (Point2D p : set) {
      p.draw();
    }
  }

  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) {
      throw new IllegalArgumentException();
    }
    SET<Point2D> out = new SET<Point2D>();
    for (Point2D p : set) {
      if (rect.contains(p)) {
        out.add(p);
      }
    }
    return out;
  }

  public Point2D nearest(Point2D reference) {
    if (reference == null) {
      throw new IllegalArgumentException();
    }
    Point2D out = null;
    for (Point2D p : set) {
      if (out == null) {
        out = p;
      } else if (out.distanceSquaredTo(reference) >= p.distanceSquaredTo(reference)) {
        out = p;
      }
    }
    return out;
  }
}
