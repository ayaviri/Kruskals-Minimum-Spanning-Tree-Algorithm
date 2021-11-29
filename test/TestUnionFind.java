import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

// represents a suite of tests for the UnionFind data structure
public class TestUnionFind {

  UnionFind exampleUnionFind;

  @Before
  public void initializeData() {
    exampleUnionFind = new UnionFind(7);
  }

  // ensures that the find method throws an IllegalArgumentException when given a negative element
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeFind() {
    exampleUnionFind.find(-1);
  }

  // ensures that the find method throws an IllegalArgumentException when given an out of bounds
  // element
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsFind() {
    exampleUnionFind.find(7);
  }

  // tests the find method
  public void testFind() {
    assertEquals(1, exampleUnionFind.find(1));
    exampleUnionFind.union(1, 2);
    assertEquals(2, exampleUnionFind.find(1));
    exampleUnionFind.union(1, 3);
    assertEquals(2, exampleUnionFind.find(1));
    assertEquals(2, exampleUnionFind.find(3));
  }

  // ensures that the union method throws an IllegalArgumentException when the first element is negative
  @Test(expected = IllegalArgumentException.class)
  public void testUnionNegativeFirst() {
    exampleUnionFind.union(-1, 4);
  }

  // ensures that the find method throws an IllegalArgumentException when given an out of bounds
  // element
  @Test(expected = IllegalArgumentException.class)
  public void testUnionOutOfBoundsFirst() {
    exampleUnionFind.union(7, 0);
  }

  // ensures that the find method throws an IllegalArgumentException when given a negative element
  @Test(expected = IllegalArgumentException.class)
  public void testUnionNegativeSecond() {
    exampleUnionFind.union(4, -1);
  }

  // ensures that the find method throws an IllegalArgumentException when given an out of bounds
  // element
  @Test(expected = IllegalArgumentException.class)
  public void testUnionOutOfBoundsSecond() {
    exampleUnionFind.union(5, 7);
  }

  // tests the union method
  @Test
  public void testUnion() {
    assertEquals(1, exampleUnionFind.find(1));
    exampleUnionFind.union(1, 2);
    assertEquals(2, exampleUnionFind.find(1));
    assertEquals(2, exampleUnionFind.find(2));
    assertEquals(2, exampleUnionFind.size(2));
    exampleUnionFind.union(0, 2);
    assertEquals(2, exampleUnionFind.find(0));
    assertEquals(2, exampleUnionFind.find(2));
    assertEquals(3, exampleUnionFind.size(2));
    exampleUnionFind.union(6, 5);
    assertEquals(5, exampleUnionFind.find(6));
    assertEquals(5, exampleUnionFind.find(5));
    assertEquals(2, exampleUnionFind.size(5));
    exampleUnionFind.union(6, 0);
    assertEquals(2, exampleUnionFind.find(6));
    assertEquals(2, exampleUnionFind.find(5));
    assertEquals(2, exampleUnionFind.find(2));
    assertEquals(5, exampleUnionFind.size(2));
    exampleUnionFind.union(5, 4);
    assertEquals(2, exampleUnionFind.find(4));
    assertEquals(6, exampleUnionFind.size(2));
  }
}
