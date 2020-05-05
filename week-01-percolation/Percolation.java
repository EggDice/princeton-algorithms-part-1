import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final int size;
  private final boolean[] isOpenStates;
  private final WeightedQuickUnionUF uf;
  private final WeightedQuickUnionUF ufBw;
  private int openSiteCount;
  private boolean alreadyPercolates;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    size = n;
    openSiteCount = 0;
    final int length = (size + 2) * (size + 2);
    isOpenStates = new boolean [length];
    uf = new WeightedQuickUnionUF(length);
    ufBw = new WeightedQuickUnionUF(length);
    for (int i = 0; i < n; i++) {
      isOpenStates[1 + i] = true;
      isOpenStates[length - (size + 1) + i] = true;
      uf.union(0, 1 + i);
      ufBw.union(0, 1 + i);
      ufBw.union(length - 1, length - (size + 1) + i);
    }
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    validateCoords(row, col);
    if (isOpenStates[coordsToIndex(row, col)]) {
      return;
    }
    isOpenStates[coordsToIndex(row, col)] = true;
    openSiteCount++;
    if (isOpenStates[getLeftIndex(row, col)]) {
      uf.union(coordsToIndex(row, col), getLeftIndex(row, col));
      ufBw.union(coordsToIndex(row, col), getLeftIndex(row, col));
    }
    if (isOpenStates[getRightIndex(row, col)]) {
      uf.union(coordsToIndex(row, col), getRightIndex(row, col));
      ufBw.union(coordsToIndex(row, col), getRightIndex(row, col));
    }
    if (isOpenStates[getTopIndex(row, col)]) {
      uf.union(coordsToIndex(row, col), getTopIndex(row, col));
      ufBw.union(coordsToIndex(row, col), getTopIndex(row, col));
    }
    if (isOpenStates[getBottomIndex(row, col)]) {
      uf.union(coordsToIndex(row, col), getBottomIndex(row, col));
      ufBw.union(coordsToIndex(row, col), getBottomIndex(row, col));
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    validateCoords(row, col);
    return isOpenStates[coordsToIndex(row, col)];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    validateCoords(row, col);
    return uf.find(0) == uf.find(coordsToIndex(row, col));
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return openSiteCount;
  }

  // does the system percolate?
  public boolean percolates() {
    if (alreadyPercolates) {
      return alreadyPercolates;
    }
    alreadyPercolates = ufBw.find(0) == ufBw.find(isOpenStates.length - 1);
    return alreadyPercolates;
  }

  private void validateCoords(int row, int col) {
    if (row <= 0 || col <= 0 || row > size || col > size) {
      throw new IllegalArgumentException();
    }
  }

  private int coordsToIndex(int row, int col) {
    return row * (size + 2) + col;
  }

  private int getLeftIndex(int row, int col) {
    return row * (size + 2) + col - 1;
  }

  private int getRightIndex(int row, int col) {
    return row * (size + 2) + col + 1;
  }

  private int getTopIndex(int row, int col) {
    return (row - 1) * (size + 2) + col;
  }

  private int getBottomIndex(int row, int col) {
    return (row + 1) * (size + 2) + col;
  }
}
