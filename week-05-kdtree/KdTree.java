import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;
import java.util.List;

public class KdTree {
  private int size;
  private Node top;

  public KdTree() {
    size = 0;
    top = null;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void insert(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }
    try {
      if (top == null) {
        top = new Node(p);
      } else {
        top.insert(p);
      }
      size++;
    } catch (IllegalArgumentException e) {
      size += 0;
    }
  }

  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }
    if (top == null) {
      return false;
    }
    return top.contains(p);
  }

  public void draw() {
    if (top == null) {
      return;
    }
    top.draw();
  }

  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) {
      throw new IllegalArgumentException();
    }
    List<Point2D> out = new ArrayList<Point2D>();
    if (top != null) {
      top.range(rect, out);
    }
    return out;
  }

  public Point2D nearest(Point2D reference) {
    if (reference == null) {
      throw new IllegalArgumentException();
    }
    return top == null ? null : top.nearest(reference);
  }

  private class Node {
    private final Point2D point;
    private Node left;
    private Node right;
    private Node parent;
    private boolean isVertical;
    private RectHV rect;


    public Node(Point2D p) {
      point = p;
      left = null;
      right = null;
      parent = null;
      isVertical = true;
      rect = new RectHV(p.x(), p.y(), p.x(), p.y());
    }

    public void insert(Point2D p) {
      if (p.equals(point)) {
        throw new IllegalArgumentException();
      }
      if (isVertical ? p.x() < point.x() : p.y() < point.y()) {
        if (left == null) {
          left = new Node(p);
          left.parent = this;
          left.isVertical = !isVertical;
        } else {
          left.insert(p);
        }
      } else {
        if (right == null) {
          right = new Node(p);
          right.parent = this;
          right.isVertical = !isVertical;
        } else {
          right.insert(p);
        }
      }
      Node n = this;
      while (n != null) {
        n.updateRect(p);
        n = n.parent;
      }
    }

    public void updateRect(Point2D p) {
      if (rect.contains(p)) {
        return;
      }
      rect = new RectHV(
        Math.min(rect.xmin(), p.x()),
        Math.min(rect.ymin(), p.y()),
        Math.max(rect.xmax(), p.x()),
        Math.max(rect.ymax(), p.y())
      );
    }

    public boolean contains(Point2D p) {
      if (p.equals(point)) {
        return true;
      }
      if (isVertical ? p.x() < point.x() : p.y() < point.y()) {
        if (left == null) {
          return false;
        } else {
          return left.contains(p);
        }
      } else {
        if (right == null) {
          return false;
        } else {
          return right.contains(p);
        }
      }
    }

    public void draw() {
      point.draw();
      if (right != null) {
        right.draw();
      }
      if (left != null) {
        left.draw();
      }
    }

    public void range(RectHV r, List<Point2D> out) {
      if (!rect.intersects(r)) {
        return;
      }
      if (r.contains(point)) {
        out.add(point);
      }
      if (left != null) {
        if (left.rect.intersects(r)) {
          left.range(r, out);
        }
      }
      if (right != null) {
        if (right.rect.intersects(r)) {
          right.range(r, out);
        }
      }
    }

    public Point2D nearest(Point2D reference) {
      Point2D out = point;
      double distance = point.distanceSquaredTo(reference);
      if (
        left != null &&
        left.rect.distanceSquaredTo(reference) < distance
      ) {
        Point2D leftPoint = left.nearest(reference);
        double leftDistance = leftPoint.distanceSquaredTo(reference);
        if (leftDistance < distance) {
          out = leftPoint;
          distance = leftDistance;
        }
      }
      if (
        right != null &&
        right.rect.distanceSquaredTo(reference) < distance
      ) {
        Point2D rightPoint = right.nearest(reference);
        double rightDistance = rightPoint.distanceSquaredTo(reference);
        if (rightDistance < distance) {
          out = rightPoint;
        }
      }
      return out;
    }
  };
}
