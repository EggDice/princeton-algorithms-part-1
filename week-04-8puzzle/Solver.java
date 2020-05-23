import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solver {
  private final Deque<Board> solutions;

  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException();
    }
    final MinPQ<SearchNode> minpq = new MinPQ<SearchNode>();
    final MinPQ<SearchNode> minpqTwin = new MinPQ<SearchNode>();
    minpq.insert(new SearchNode(initial));
    minpqTwin.insert(new SearchNode(initial.twin()));
    while (!minpq.min().getBoard().isGoal() && !minpqTwin.min().getBoard().isGoal()) {
      for (SearchNode neighbor : minpq.min().neighbors()) {
        minpq.insert(neighbor);
      }
      minpq.delMin();
      for (SearchNode neighbor : minpqTwin.min().neighbors()) {
        minpqTwin.insert(neighbor);
      }
      minpqTwin.delMin();
    }
    if (minpqTwin.min().getBoard().isGoal()) {
      solutions = null;
    } else {
      solutions = minpq.delMin().getSolutions();
    }
  }

  public boolean isSolvable() {
    return solutions != null;
  }

  public int moves() {
    return solutions == null ? -1 : solutions.size() - 1;
  }

  public Iterable<Board> solution() {
    return solutions;
  }

  private class SearchNode implements Comparable<SearchNode> {
    private final int priority;
    private final int moves;
    private final Board board;
    private final SearchNode previous;

    public SearchNode(Board b) {
      moves = 0;
      board = b;
      priority = board.manhattan();
      previous = null;
    }

    private SearchNode(Board b, SearchNode p, int m) {
      moves = m;
      board = b;
      priority = m + board.manhattan();
      previous = p;
    }

    public int compareTo(SearchNode other) {
      return priority - other.priority;
    }

    public Iterable<SearchNode> neighbors() {
      final Deque<SearchNode> neighbors = new ArrayDeque<>();
      for (Board n : board.neighbors()) {
        if (previous == null || !n.equals(previous.board)) {
          SearchNode neighbor = new SearchNode(n, this, moves + 1);
          neighbors.add(neighbor);
        }
      }
      return neighbors;
    }

    public Deque<Board> getSolutions() {
      final Deque<Board> output = new ArrayDeque<>();
      SearchNode node = this;
      while (node != null) {
        output.addFirst(node.board);
        node = node.previous;
      }
      return output;
    }

    public Board getBoard() {
      return board;
    }
  }
}
