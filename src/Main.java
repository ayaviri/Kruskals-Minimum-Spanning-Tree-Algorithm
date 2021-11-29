import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

  public static List<Pair<Integer, Integer>> kruskal(Graph graph) {
    UnionFind unionFind = new UnionFind(graph.getNumberOfNodes());
    List<Pair<Integer, Pair<Integer, Integer>>> edges = graph.getAllEdges();
    Collections.sort(edges, new EdgeComparator());
    List<Pair<Integer, Integer>> MST = new ArrayList<Pair<Integer, Integer>>(
        graph.getNumberOfNodes() - 1);
    for (int index = 0; index < edges.size(); index++) {
      Pair<Integer, Pair<Integer, Integer>> currentEdge = edges.get(index);
      int firstNode = currentEdge.getFirst();
      int secondNode = currentEdge.getSecond().getFirst();
      int firstNodeParent = unionFind.find(firstNode);
      int secondNodeParent = unionFind.find(secondNode);
      if (firstNodeParent != secondNodeParent) {
        // addition of edge would build the current mst
        MST.add(new Pair<Integer, Integer>(firstNode, secondNode));
        unionFind.union(firstNodeParent, secondNodeParent);
      }
    }
    return MST;
  }

  private static class EdgeComparator implements Comparator<Pair<Integer, Pair<Integer, Integer>>> {

    @Override
    public int compare(Pair<Integer, Pair<Integer, Integer>> firstEdge,
        Pair<Integer, Pair<Integer, Integer>> secondEdge) {
      int firstEdgeWeight = firstEdge.getSecond().getSecond();
      int secondEdgeWeight = secondEdge.getSecond().getSecond();
      return firstEdgeWeight - secondEdgeWeight;
    }
  }

  public static void main(String[] args) {
    System.out.println("hello world!");
    Graph exampleGraph = new Graph(7);
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

    List<Pair<Integer, Integer>> MST = kruskal(exampleGraph);
    System.out.println(MST);
  }
}
