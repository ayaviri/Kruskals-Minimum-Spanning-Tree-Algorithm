import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

// represents a suite of tests for kruskal's algorithm
public class TestKruskal {

  Graph exampleGraph;

  @Before
  public void initializeData() {
    exampleGraph = new Graph(7);
    exampleGraph.addEdge(0, 1, 4);
    exampleGraph.addEdge(0, 2, 26);
    exampleGraph.addEdge(0, 3, 14);
    exampleGraph.addEdge(1, 3, 12);
    exampleGraph.addEdge(1, 4, 18);
    exampleGraph.addEdge(2, 3, 30);
    exampleGraph.addEdge(2, 5, 16);
    exampleGraph.addEdge(3, 4, 2);
    exampleGraph.addEdge(3, 5, 3);
    exampleGraph.addEdge(4, 5, 10);
    exampleGraph.addEdge(4, 6, 8);
    exampleGraph.addEdge(5, 6, 5);
  }

  // tests the kruskal method
  @Test
  public void testKruskal() {
    assertEquals(new ArrayList<Pair<Integer, Integer>>(Arrays
            .asList(new Pair<Integer, Integer>(3, 4), new Pair<Integer, Integer>(3, 5),
                new Pair<Integer, Integer>(0, 1), new Pair<Integer, Integer>(5, 6),
                new Pair<Integer, Integer>(1, 3), new Pair<Integer, Integer>(2, 5))),
        Main.kruskal(exampleGraph));
  }
}
