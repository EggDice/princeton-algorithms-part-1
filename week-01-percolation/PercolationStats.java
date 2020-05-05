import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private static final double CONFIDENCE_95 = 1.96;
  private final double[] openSites;
  private final int t;
  private double m;
  private double s;
  private double cLo;
  private double cHi;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
    t = trials;
    openSites = new double[trials];
    for (int trial = 0; trial < trials; ++trial) {
      Percolation p = new Percolation(n);
      while (!p.percolates()) {
        p.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
      }
      openSites[trial] = (double) p.numberOfOpenSites() / (double) (n * n);
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    if (m != 0.0) {
      return m;
    }
    m = StdStats.mean(openSites);
    return m;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    if (s != 0.0) {
      return s;
    }
    s = StdStats.stddev(openSites);
    return s;
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    if (cLo != 0.0) {
      return cLo;
    }
    cLo = mean() - CONFIDENCE_95 * stddev() / Math.sqrt(t);
    return cLo;
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    if (cHi != 0.0) {
      return cHi;
    }
    cHi =  mean() + CONFIDENCE_95 * stddev() / Math.sqrt(t);
    return cHi;
  }

  // test client (see below)
  public static void main(String[] args) {
    PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                                               Integer.parseInt(args[1]));
    StdOut.printf("mean                    = %f\n", ps.mean());
    StdOut.printf("stddev                  = %f\n", ps.stddev());
    StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
  }

}
