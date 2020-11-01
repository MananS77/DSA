package amazon.phone;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
* Problem - https://leetcode.com/problems/cut-off-trees-for-golf-event/
* Time -  O(m^2 * n^2) -> (m * n) trees and for each tree BFS costs (m * n) time
* Space - O(m * n) -> Queue could hold (m * n) trees
* */

public class CutOffTreesForGolfEvent {

  static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public static int cutOffTree(List<List<Integer>> forest) {
    int m = forest.size();
    int n = forest.get(0).size();

    // Min-Heap according to height of the tree
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        if (forest.get(i).get(j) > 1)
          queue.add(new int[]{i, j, forest.get(i).get(j)});

    int[] start = new int[2];
    int sum = 0;

    /* Incrementally poll smaller height tree first, and see if you can cut any trees (step > 0)
      from that tree */
    while (!queue.isEmpty()) {
      int[] tree = queue.poll();
      int step = minStep(forest, start, tree, m, n);

      if (step < 0)
        return -1;

      sum += step;

      start[0] = tree[0];
      start[1] = tree[1];
    }

    return sum;
  }

  private static int minStep(List<List<Integer>> forest, int[] start, int[] tree, int m, int n) {
    int step = 0;
    boolean[][] visited = new boolean[m][n];
    Queue<int[]> queue = new LinkedList<>();

    queue.add(start);
    visited[start[0]][start[1]] = true;

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        int[] curr = queue.poll();
        if (curr[0] == tree[0] && curr[1] == tree[1])
          return step;

        for (int[] d : dir) {
          int nr = curr[0] + d[0];
          int nc = curr[1] + d[1];
          if (isInsideGrid(m, n, nr, nc) && isNotObstacle(forest, nr, nc) && !visited[nr][nc]) {
            queue.add(new int[]{nr, nc});
            visited[nr][nc] = true;
          }
        }
      }
      step++;
    }

    return -1;
  }

  private static boolean isNotObstacle(List<List<Integer>> forest, int nr, int nc) {
    return forest.get(nr).get(nc) != 0;
  }

  private static boolean isInsideGrid(int m, int n, int nr, int nc) {
    return nr >= 0 && nr < m && nc >= 0 && nc < n;
  }

  public static void main(String[] args) {
    List<List<Integer>> forest1 = Arrays.asList(
      Arrays.asList(1,2,3),
      Arrays.asList(0,0,4),
      Arrays.asList(7,6,5)
    );
    System.out.println("Forest = " + forest1);
    System.out.println("Min Steps to chop down = " + cutOffTree(forest1) + "\n");

    List<List<Integer>> forest2 = Arrays.asList(
        Arrays.asList(1,2,3),
        Arrays.asList(0,0,0),
        Arrays.asList(7,6,5)
    );
    System.out.println("Forest = " + forest2);
    System.out.println("Min Steps to chop down = " + cutOffTree(forest2) + "\n");

    List<List<Integer>> forest3 = Arrays.asList(
        Arrays.asList(2,3,4),
        Arrays.asList(0,0,5),
        Arrays.asList(8,7,6)
    );
    System.out.println("Forest = " + forest3);
    System.out.println("Min Steps to chop down = " + cutOffTree(forest3) + "\n");

    List<List<Integer>> forest4 = Arrays.asList(
        Arrays.asList(54581641,64080174,24346381,69107959),
        Arrays.asList(86374198,61363882,68783324,79706116),
        Arrays.asList(668150,92178815,89819108,94701471),
        Arrays.asList(83920491,22724204,46281641,47531096),
        Arrays.asList(89078499,18904913,25462145,60813308)
    );
    System.out.println("Forest = " + forest4);
    System.out.println("Min Steps to chop down = " + cutOffTree(forest4) + "\n");
  }
}