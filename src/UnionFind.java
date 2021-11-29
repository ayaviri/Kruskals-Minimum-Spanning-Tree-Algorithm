import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Union Find or Disjoint Set data structure. It is being represented by two arrays.
 * The first stores the immediate parent of a given node, and the other stores the size of tree
 * starting at that node.
 */
public class UnionFind {

  List<Integer> parent;
  List<Integer> size;

  /**
   * Constructs a new {@code UnionFind} object in which the nodes are labeled 0 through {@code
   * initialCapacity} - 1 as this UnionFind is to be used with graphs whose nodes are also labeled
   * with a 0-indexed system. There is no way to add elements to the UnionFind.
   *
   * @param initialCapacity the number of nodes in this UnionFind
   * @throws IllegalArgumentException if the initial capacity is negative
   */
  public UnionFind(int initialCapacity) throws IllegalArgumentException {
    InputValidation.ensureGreaterThan(initialCapacity, -1, "Initial capacity cannot be negative");

    this.parent = new ArrayList<Integer>(initialCapacity);
    this.size = new ArrayList<Integer>(initialCapacity);
    for (int index = 0; index < initialCapacity; index++) {
      this.parent.add(index);
      this.size.add(1);
    }
  }

  /**
   * Finds the root/parent/representative of this node. This method also compresses the path it
   * takes by assigning the root as the parent of EACH node it visits. This decreases the depth of
   * the tree used to represent each disjoint set
   *
   * @param element The node whose root/parent/representative we want
   * @return The root/parent/representative of the given node
   * @throws IllegalArgumentException if the node is not contained in the UnionFind
   */
  public int find(int element) throws IllegalArgumentException {
    InputValidation
        .ensureWithin(element, -1, this.parent.size(), "Element is not stored in the UnionFind");
    int elementParent = this.parent.get(element);
    if (elementParent == element) {
      return elementParent;
    }
    this.parent.set(element, find(elementParent));
    return this.parent.get(element);
  }

  /**
   * Unions the two given nodes by assigning the root of the larger tree as the parent of the root
   * of the smaller tree. The size of the larger tree is also increased by the number of elements in
   * the smaller tree. By calling the {@code find} method, it may be the case that paths are
   * compressed during the union, depending on the given parameters to the function
   *
   * @param firstElement  The first node we want to union
   * @param secondElement The second node we want to union
   * @throws IllegalArgumentException if either node is not contained in the UnionFind, or if both
   *                                  nodes are part of the same disjoint set. This is done because
   *                                  Kruskal's should not allow the union of two elements in the
   *                                  same set. As such, an IllegalArgumentException is thrown to
   *                                  make debugging easier
   */
  public void union(int firstElement, int secondElement) throws IllegalArgumentException {
    InputValidation.ensureWithin(firstElement, -1, this.parent.size(),
        "Element is not stored in the UnionFind");
    InputValidation.ensureWithin(secondElement, -1, this.parent.size(),
        "Element is not stored in the UnionFind");

    int firstParent = this.find(firstElement);
    int secondParent = this.find(secondElement);
    if (firstParent == secondParent) {
      throw new IllegalArgumentException("Both elements are part of the same set");
    }
    int firstSize = this.size.get(firstParent);
    int secondSize = this.size.get(secondParent);
    if (firstSize > secondSize) {
      // the first set is larger, union second into first
      this.parent.set(secondParent, firstParent);
      this.size.set(firstParent, firstSize + secondSize);
    } else {
      // the second set is larger, union first into second
      this.parent.set(firstParent, secondParent);
      this.size.set(secondParent, secondSize + firstSize);
    }
  }

  /**
   * Returns the size of the tree starting at the given node. If a node is a
   * root/parent/representative node, the size of disjoint set is returned
   *
   * @param element The node whose tree size we want
   * @return The size of the tree starting at the given node
   * @throws IllegalArgumentException if the node is not contained in the graph
   */
  public int size(int element) throws IllegalArgumentException {
    InputValidation
        .ensureWithin(element, -1, this.parent.size(), "Element is not stored in the UnionFind");
    int elementParent = this.find(element);
    return this.size.get(elementParent);
  }

  public List<Integer> getParents() {
    return this.parent;
  }
}
