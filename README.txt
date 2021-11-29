welcome to my implementation of kruskal's algorithm for finding minimum spanning trees ! in this
implementation, i use the UnionFind data structure to improve the runtime complexity from O(n^2) to
O(mlogn). it must be noted that these data representations have been created exclusively for the
implementation of this algorithm. it was not intended for these representations to be extensible.
for example, the graph represents a simple, undirected, weighted graph. edges are allowed to be
negative, and it is assumed that all edges are distinct. in addition to adjacency list used to
represent this graph, an additional list of all the edges is stored in order to make it easy to
obtain and sort the list for kruskal's algorithm. this list contains 3-tuples with the following
format: (node1, node2, edgeWeight). i was unsure of how to obtain a list of edges from the
adjacency list representation of this undirected graph in linear time, so i stored this list, which
does not worsen the asymptotic space usage, in order to resolve this issue. finally, i implemented
the UnionFind data structure using two arrays, one storing root/parent/representative node of each
node in the graph, and another storing the size of the tree starting at the given node. both the
graph and the UnionFind assume that nodes are labeled with a 0-indexing system, and the UnionFind
data structure does not support the addition of new nodes after construction. when a UnionFind
object is constructed, the user must simply pass the number of nodes desired. minor optimizations
were done on the UnionFind data structure, such as the union by size and compression of certain
paths.

finally, there is a non-exhaustive suite of tests available in the test folder that require the
addition of JUnit 4.13 to the classpath in order to run.   