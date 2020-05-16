import java.util.Arrays;

public class FastCollinearPoints {
  private LineSegment[] segments;
  private int count;

  public FastCollinearPoints(Point[] ps) {
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
    segments = new LineSegment[2];
    Point[] slopeSorted = new Point[points.length];
    for (int j = 0; j < points.length; ++j) {
      slopeSorted[j] = points[j];
    }
    for (int i = 0; i < points.length - 1; ++i) {
      Arrays.sort(slopeSorted);
      Arrays.sort(slopeSorted, points[i].slopeOrder());
      int lowest = 1;
      int highest = 1;
      for (int j = 2; j < slopeSorted.length; ++j) {
        double s0 = points[i].slopeTo(slopeSorted[lowest]);
        double s1 = points[i].slopeTo(slopeSorted[j]);
        if (Math.abs(s0 - s1) < Double.MIN_NORMAL || Double.isNaN(s0 - s1) && points[i].compareTo(slopeSorted[j]) < 0) {
          highest = j;
        } else {
          if (highest - lowest >= 2 && points[i].compareTo(slopeSorted[lowest]) < 0 && points[i].compareTo(slopeSorted[highest]) < 0) {
            resizeSegments();
            segments[count] = new LineSegment(points[i], slopeSorted[highest]);
            count = count + 1;
          }
          lowest = j;
          highest = j;
        }
      }
      if (highest - lowest >= 2 && points[i].compareTo(slopeSorted[lowest]) < 0 &&  points[i].compareTo(slopeSorted[highest]) < 0) {
        resizeSegments();
        segments[count] = new LineSegment(points[i], slopeSorted[highest]);
        count = count + 1;
      }
    }
    LineSegment[] finalSegments = new LineSegment[count];
    for (int i = 0; i < finalSegments.length; ++i) {
      finalSegments[i] = segments[i];
    }
    segments = finalSegments;
  }

  private void resizeSegments() {
    if (count > segments.length / 2) {
      LineSegment[] newSegments = new LineSegment[segments.length * 2];
      for (int i = 0; i < count; ++i) {
        newSegments[i] = segments[i];
      }
      segments = newSegments;
    }
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
