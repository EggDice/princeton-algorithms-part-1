import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class BoardTest {

  @Test
  public void testCreation() {
    int[][] tiles = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    Board b = new Board(tiles);
    assertEquals(b.dimension(), 3);
  }

  @Test
  public void testDimension() {
    int[][] tiles = {{0, 1}, {2, 3}};
    Board b = new Board(tiles);
    assertEquals(b.dimension(), 2);
  }

  @Test
  public void testCreationInputs() {
    assertThrows(IllegalArgumentException.class, () -> {
      Board b = new Board(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      int[][] tiles = {null};
      Board b = new Board(tiles);
    });
  }

  @Test
  public void testIsGoal() {
    {
      int[][] tiles = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
      Board b = new Board(tiles);
      assertFalse(b.isGoal());
    }
    {
      int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
      Board b = new Board(tiles);
      assertTrue(b.isGoal());
    }
    {
      int[][] tiles = {{2, 1, 3}, {4, 5, 6}, {7, 8, 0}};
      Board b = new Board(tiles);
      assertFalse(b.isGoal());
    }
  }

  @Test
  public void testString() {
    int[][] tiles = {{0, 1}, {2, 3}};
    Board b = new Board(tiles);
    String expectedOutput = "2\n" +
      " 0 1\n"+
      " 2 3\n";
    assertEquals(b.toString(), expectedOutput);
  }

  @Test
  public void testHamming() {
    {
      int[][] tiles = {{1, 2}, {3, 0}};
      Board b = new Board(tiles);
      assertEquals(b.hamming(), 0);
    }
    {
      int[][] tiles = {{2, 1}, {3, 0}};
      Board b = new Board(tiles);
      assertEquals(b.hamming(), 2);
    }
  }

  @Test
  public void testManhattan() {
    {
      int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
      Board b = new Board(tiles);
      assertEquals(b.manhattan(), 0);
    }
    {
      int[][] tiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
      Board b = new Board(tiles);
      assertEquals(b.manhattan(), 10);
    }
  }

  @Test
  public void testEquals() {
    int[][] tiles0 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    int[][] tiles1 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    Board b0 = new Board(tiles0);
    Board b1 = new Board(tiles1);
    Board b2 = new Board(tiles0);
    assertTrue(b0.equals(b0));
    assertFalse(b0.equals(null));
    assertFalse(b0.equals(new Integer(0)));
    assertTrue(b0.equals(b2));
  }

  @Test
  void testNeighbors() {
    {
      int[][] tiles = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
      Board b = new Board(tiles);

      int[][] tiles1 = {{1, 2, 3}, {4, 7, 5}, {6, 0, 8}};
      int[][] tiles2 = {{1, 2, 3}, {4, 5, 0}, {6, 7, 8}};
      int[][] tiles3 = {{1, 0, 3}, {4, 2, 5}, {6, 7, 8}};
      int[][] tiles4 = {{1, 2, 3}, {0, 4, 5}, {6, 7, 8}};
      Board[] expectedNeighbors = {
        new Board(tiles1),
        new Board(tiles2),
        new Board(tiles3),
        new Board(tiles4)
      };
      assertIterableEquals(b.neighbors(), Arrays.asList(expectedNeighbors));
    }
    {
      int[][] tiles = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
      Board b = new Board(tiles);

      int[][] tiles1 = {{3, 1, 2}, {0, 4, 5}, {6, 7, 8}};
      int[][] tiles2 = {{1, 0, 2}, {3, 4, 5}, {6, 7, 8}};
      Board[] expectedNeighbors = {
        new Board(tiles1),
        new Board(tiles2)
      };
      assertIterableEquals(b.neighbors(), Arrays.asList(expectedNeighbors));
    }
    {
      int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
      Board b = new Board(tiles);

      int[][] tiles1 = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
      int[][] tiles2 = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
      Board[] expectedNeighbors = {
        new Board(tiles1),
        new Board(tiles2)
      };
      assertIterableEquals(b.neighbors(), Arrays.asList(expectedNeighbors));
    }
    {
      int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {0, 7, 8}};
      Board b = new Board(tiles);

      int[][] tiles1 = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
      int[][] tiles2 = {{1, 2, 3}, {0, 5, 6}, {4, 7, 8}};
      Board[] expectedNeighbors = {
        new Board(tiles1),
        new Board(tiles2)
      };
      assertIterableEquals(b.neighbors(), Arrays.asList(expectedNeighbors));
    }
    {
      int[][] tiles = {{3, 2}, {0, 1}};
      Board b = new Board(tiles);

      int[][] tiles1 = {{3, 2}, {1, 0}};
      int[][] tiles2 = {{0, 2}, {3, 1}};
      Board[] expectedNeighbors = {
        new Board(tiles1),
        new Board(tiles2)
      };
      assertIterableEquals(b.neighbors(), Arrays.asList(expectedNeighbors));
    }
  }

  @Test
  void testTwin() {
    int[][] tiles = {{0, 1}, {2, 3}};
    Board b = new Board(tiles).twin();
    String expectedOutput = "2\n" +
      " 0 2\n"+
      " 1 3\n";
    assertEquals(b.toString(), expectedOutput);
  }
}
