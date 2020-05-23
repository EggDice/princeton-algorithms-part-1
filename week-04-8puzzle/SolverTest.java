import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SolverTest {
  @Test
  public void testCreation() {
    int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board b = new Board(tiles);
    Solver s = new Solver(b);
    assertEquals(s.moves(), 0);
    assertTrue(s.isSolvable());
    Board[] expectedSolution = {b};
    assertIterableEquals(s.solution(), Arrays.asList(expectedSolution));
  }

  @Test
  public void testCreationInputs() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Solver(null);
    });
  }

  @Test
  public void testOneStep() {
    int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
    int[][] tiles1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board b = new Board(tiles);
    Solver s = new Solver(b);
    assertEquals(s.moves(), 1);
    assertTrue(s.isSolvable());
    Board[] expectedSolution = {b, new Board(tiles1)};
    assertIterableEquals(s.solution(), Arrays.asList(expectedSolution));
  }

  @Test
  public void testSevenStep() {
    int[][] tiles = {{1, 2, 3}, {0, 7, 6}, {5, 4, 8}};
    int[][] tiles1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board b = new Board(tiles);
    Solver s = new Solver(b);
    assertEquals(s.moves(), 7);
    assertTrue(s.isSolvable());
  }

  @Test
  public void testUnsolvable() {
    int[][] tiles = {{0, 1}, {2, 3}};
    Board b = new Board(tiles);
    Solver s = new Solver(b);
    assertEquals(s.moves(), -1);
    assertFalse(s.isSolvable());
  }
}
