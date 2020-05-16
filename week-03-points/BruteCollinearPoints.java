import java.util.Arrays;

public class BruteCollinearPoints {
  private LineSegment[] segments;
  private int count;

  public BruteCollinearPoints(Point[] ps) {
    if (ps == null) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < ps.length; ++i) {
      if (ps[i] == null) {
        throw new IllegalArgumentException();
      }
    }
    if (ps.length == 0) {
      segments = new LineSegment[0];
      return;
    }
    Point[] points = new Point[ps.length];
    for (int i = 0; i < ps.length; ++i) {
      points[i] = ps[i];
    }
    Arrays.sort(points);
    checkForSamePoints(points);
    if (ps.length < 4) {
      segments = new LineSegment[0];
      return;
    }
    segments = new LineSegment[points.length - 3];
    for (int i = 0; i < points.length - 3; ++i) {
      for (int j = i + 1; j < points.length - 2; ++j) {
        for (int k = j + 1; k < points.length - 1; ++k) {
          for (int m = k + 1; m < points.length; ++m) {
            double s0 = points[i].slopeTo(points[j]);
            double s1 = points[j].slopeTo(points[k]);
            double s2 = points[k].slopeTo(points[m]);
            if ((Math.abs(s0 - s1) < Double.MIN_NORMAL || Double.isNaN(s0 - s1)) &&
                (Math.abs(s1 - s2) < Double.MIN_NORMAL || Double.isNaN(s1 - s2))) {
              segments[count] = new LineSegment(points[i], points[m]);
              count = count + 1;
            }
          }
        }
      }
    }
    LineSegment[] finalSegments = new LineSegment[count];
    for (int i = 0; i < finalSegments.length; ++i) {
      finalSegments[i] = segments[i];
    }
    segments = finalSegments;
  }

  private void checkForSamePoints(Point[] points) {
    Point last = points[0];
    for (int i = 1; i < points.length; ++i) {
      if (last.compareTo(points[i]) == 0) {
        throw new IllegalArgumentException();
      }
      last = points[i];
    }
  }

  public int numberOfSegments() {
    return count;
  }

  public LineSegment[] segments() {
    LineSegment[] output = new LineSegment[segments.length];
    for (int i = 0; i < segments.length; ++i) {
      output[i] = segments[i];
    }
    return output;
  }
}
