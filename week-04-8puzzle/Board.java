import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

public class Board {
  private final int dimension;
  private final int[] tiles;

  public Board(int[][] input) {
    validateInput(input);
    dimension = input.length;
    tiles = new int[dimension * dimension];
    initTiles(input);
  }

  private Board(int[] input) {
    tiles = input.clone();
    dimension = (int) Math.sqrt(input.length);
  }

  public int dimension() {
    return dimension;
  }

  public boolean isGoal() {
    if (tiles[tiles.length - 1] != 0) {
      return false;
    }
    for (int i = 0; i < tiles.length - 1; ++i) {
      if (tiles[i] != i + 1) {
        return false;
      }
    }
    return true;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(dimension + "\n");
    for (int i = 0; i < dimension; ++i) {
      for (int j = 0; j < dimension; ++j) {
        sb.append(" ");
        sb.append(tiles[i * dimension + j]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public int hamming() {
    int out = 0;
    for (int i = 0; i < tiles.length; ++i) {
      if (tiles[i] != 0 && tiles[i] != i + 1) {
        ++out;
      }
    }
    return out;
  }

  public int manhattan() {
    int out = 0;
    for (int i = 0; i < tiles.length; ++i) {
      if (tiles[i] != 0) {
        int horizontal = Math.abs((tiles[i] - 1) % dimension - i % dimension);
        int vertical = Math.abs((tiles[i] - 1) / dimension - i / dimension);
        out += horizontal + vertical;
      }
    }
    return out;
  }

  @Override
  public boolean equals(Object that) {
    if (that == this) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (that.getClass() != this.getClass()) {
      return false;
    }
    Board other = (Board) that;
    return Arrays.equals(other.tiles, tiles);
  }

  public Iterable<Board> neighbors() {
    Stack<Board> out = new Stack<>();
    int holeIndex = indexOfHole();
    if (holeIndex % dimension > 0) {
      out.push(getNeighbor(holeIndex, holeIndex - 1));
    }
    if (holeIndex >= dimension) {
      out.push(getNeighbor(holeIndex, holeIndex - dimension));
    }
    if (holeIndex % dimension < dimension - 1) {
      out.push(getNeighbor(holeIndex, holeIndex + 1));
    }
    if (holeIndex < tiles.length - dimension) {
      out.push(getNeighbor(holeIndex, holeIndex + dimension));
    }
    return out;
  }

  public Board twin() {
    int from = 0;
    int to = 1;
    while (tiles[from] == 0 || tiles[to] == 0) {
      if (tiles[from] == 0) {
        ++from;
        ++to;
      } else {
        if (tiles[to] == 0) {
          ++to;
        }
      }
    }
    return getNeighbor(from, to);
  }

  private void validateInput(int[][] input) {
    if (input == null) {
      throw new IllegalArgumentException();
    }
    for (int[] row : input) {
      if (row == null) {
        throw new IllegalArgumentException();
      }
    }
  }

  private void initTiles(int[][] input) {
    int i = 0;
    for (int[] row : input) {
      for (int tile : row) {
        tiles[i++] = tile;
      }
    }
  }

  private int indexOfHole() {
    for (int i = 0; i < tiles.length; ++i) {
      if (tiles[i] == 0) {
        return i;
      }
    }
    return -1;
  }

  private Board getNeighbor(int holeIndex, int newHoleIndex) {
    Board neighbor = new Board(tiles);
    neighbor.swap(holeIndex, newHoleIndex);
    return neighbor;
  }

  private void swap(int indexA, int indexB) {
    int temp = tiles[indexA];
    tiles[indexA] = tiles[indexB];
    tiles[indexB] = temp;
  }
}
