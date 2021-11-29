import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple, undirected, connected, weighted graph. For simplification, we will assume
 * that all edges in a graph have distinct weights. In this representation, we also store a list of
 * all the edges in order to make iterating through them in Kruskal's algorithm much more
 * efficient.
 */
public class Graph {

  private int numberOfNodes;
  private int numberOfEdges;
  private List<ArrayList<Pair<Integer, Integer>>> adjacencyList;
  // each each is stored as a 3-tuple: (startingNode, destinationNode, edgeWeight)
  private List<Pair<Integer, Pair<Integer, Integer>>> edges;

  /**
   * Constructs a new {@code Graph} object with no node or edges
   */
  public Graph() {
    this.numberOfNodes = 0;
    this.numberOfEdges = 0;
    this.adjacencyList = new ArrayList<ArrayList<Pair<Integer, Integer>>>();
    this.edges = new ArrayList<Pair<Integer, Pair<Integer, Integer>>>();
  }

  /**
   * Constructs a new {@code Graph} object with enough space for the given number of nodes. More
   * nodes can be added whenever, however
   *
   * @param numberOfNodes The number of nodes for which space is allocated
   * @throws IllegalArgumentException if the number of nodes is negative
   */
  public Graph(int numberOfNodes) throws IllegalArgumentException {
    this.numberOfNodes = InputValidation
        .ensureGreaterThan(numberOfNodes, -1, "Number of nodes must be zero or greater");
    this.numberOfEdges = 0;
    this.adjacencyList = new ArrayList<ArrayList<Pair<Integer, Integer>>>(this.numberOfNodes);
    this.edges = new ArrayList<Pair<Integer, Pair<Integer, Integer>>>();
    for (int index = 0; index < this.numberOfNodes; index++) {
      this.adjacencyList.add(new ArrayList<Pair<Integer, Integer>>());
    }
  }

  /**
   * Returns the number of nodes in the graph
   *
   * @return the number of nodes in the graph
   */
  public int getNumberOfNodes() {
    return this.numberOfNodes;
  }

  /**
   * Returns the number of edges in the graph
   *
   * @return the number of edges
   */
  public int getNumberOfEdges() {
    return this.numberOfEdges;
  }

  /**
   * Allocates space for one node in the graph
   */
  public void addNode() {
    this.numberOfNodes += 1;
    this.adjacencyList.add(new ArrayList<Pair<Integer, Integer>>());
  }

  /**
   * Allocates space for the given number of nodes
   *
   * @param numberOfNodes The number of nodes to be added
   * @throws IllegalArgumentException if the number of nodes is not positive
   */
  public void addNodes(int numberOfNodes) throws IllegalArgumentException {
    InputValidation.ensureGreaterThan(numberOfNodes, 0, "Number of nodes added must be positive");
    this.numberOfNodes += numberOfNodes;
    for (int index = 0; index < numberOfNodes; index++) {
      this.adjacencyList.add(new ArrayList<Pair<Integer, Integer>>());
    }
  }

  /**
   * Adds an undirected, weighted edge from {@code startinNode} to {@code destinationNode} with
   * weight {@code weight}
   *
   * @param startingNode    One of the nodes the edge touches
   * @param destinationNode The other node the edge touches
   * @param weight          The weight of the edge. Can be negative
   * @throws IllegalArgumentException if either node is not contained in the graph, or if the edge
   *                                  creates a self-loop
   */
  public void addEdge(int startingNode, int destinationNode, int weight)
      throws IllegalArgumentException {
    InputValidation.ensureWithin(startingNode, -1, this.numberOfNodes,
        "Starting node is not contained in the graph");
    InputValidation.ensureWithin(destinationNode, -1, this.numberOfNodes,
        "Starting node is not contained in the graph");

    // ensures that no self loops exist
    if (startingNode == destinationNode) {
      throw new IllegalArgumentException("No self-loops allowed");
    }

    List<Pair<Integer, Integer>> startingEdges = this.adjacencyList.get(startingNode);
    List<Pair<Integer, Integer>> destinationEdges = this.adjacencyList.get(destinationNode);
    List<Integer> startingNeighbors = ListUtilities
        .map(startingEdges, (edge) -> edge.getFirst());
    // check that an edge from startingNode to destinationNode does not already exist
    if (startingNeighbors.contains(destinationNode)) {
      throw new IllegalArgumentException(
          "There already exists an edge from the starting node to the destination node");
    }

    startingEdges.add(new Pair<Integer, Integer>(destinationNode, weight));
    destinationEdges.add(new Pair<Integer, Integer>(startingNode, weight));
    this.edges.add(new Pair<Integer, Pair<Integer, Integer>>(startingNode,
        new Pair<Integer, Integer>(destinationNode, weight)));
    this.numberOfEdges += 1;
  }

  /**
   * Obtains a copy of the edge from the given {@code nodeIndex}
   *
   * @param nodeIndex The node whose edges we want
   * @return A copy of the list of edges coming from the given node
   * @throws IllegalArgumentException if the node is not contained in the graph
   */
  public List<Pair<Integer, Integer>> getEdgesFrom(int nodeIndex) throws IllegalArgumentException {
    InputValidation
        .ensureWithin(nodeIndex, -1, this.numberOfNodes, "Node is not contained in the graph");

    List<Pair<Integer, Integer>> currentEdges = this.adjacencyList.get(nodeIndex);
    List<Pair<Integer, Integer>> copyOfEdges = new ArrayList<Pair<Integer, Integer>>(
        currentEdges.size());
    for (int index = 0; index < currentEdges.size(); index++) {
      Pair<Integer, Integer> currentEdge = currentEdges.get(index);
      copyOfEdges.add(new Pair<Integer, Integer>(currentEdge.getFirst(), currentEdge.getSecond()));
    }

    return copyOfEdges;
  }

  /**
   * Returns a reference to the list of all edges in this graph. We don't care to return a reference
   * in this case since we are only sorting the list by edge weight, and the order of the edges
   * doesn't matter otherwise
   *
   * @return A reference to the list of all edges in this graph
   */
  public List<Pair<Integer, Pair<Integer, Integer>>> getAllEdges() {
    return this.edges;
  }
}
