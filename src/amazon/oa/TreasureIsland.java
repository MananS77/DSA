package amazon.oa;

/*
I - https://leetcode.com/discuss/interview-question/347457/Amazon-or-OA-2019-or-Treasure-Island
II - https://leetcode.com/discuss/interview-question/356150

Time complexity: O(r * c).
Space complexity: O(r * c).
*/

import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland {

  private static int minSteps(char[][] grid) {
    int minSteps = 1;

    Queue<Point> queue = new LinkedList<>();
    queue.offer(new Point(0, 0));
    grid[0][0] = 'D';

    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        Point point = queue.poll();

        for (int[] dir : directions) {
          int i = dir[0] + point.x;
          int j = dir[1] + point.y;

          if (isInside(i, j, grid) && grid[i][j] != 'D') {
            if (grid[i][j] == 'X') {
              return minSteps;
            }
            queue.offer(new Point(i, j));
            grid[i][j] = 'D';
          }
        }
      }
      minSteps++;
    }

    return -1;
  }

  public static int minDist(char[][] grid) {
    int minDist = 1; //initialize as 0 if checking for X outside dir loop

    Queue<Point> queue = collectSources(grid);
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    while (!queue.isEmpty()) {
      int size = queue.size();

      while (size-- > 0) {
        Point point = queue.poll();
        //if(grid[point.x][point.y] == 'X') return minDist;
        //grid[point.x][point.y] = 'D';

        for (int[] dir : directions) {
          int i = dir[0] + point.x;
          int j = dir[1] + point.y;

          if (isInside(i, j, grid) && grid[i][j] != 'D') {
            if (grid[i][j] == 'X') {
              return minDist;
            }
            queue.offer(new Point(i, j));
            grid[i][j] = 'D';
          }
        }
      }
      minDist++;
    }

    return -1;
  }

  private static Queue<Point> collectSources(char[][] grid) {
    Queue<Point> queue = new LinkedList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 'S') {
          queue.offer(new Point(i, j));
        }
      }
    }

    return queue;
  }

  private static boolean isInside(int i, int j, char[][] grid) {
    return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
  }

  public static void main(String[] args) {
    System.out.println("Treasure Island I");
    char[][] grid = {{'O', 'O', 'O', 'O'},
        {'D', 'O', 'D', 'O'},
        {'O', 'O', 'O', 'O'},
        {'X', 'D', 'D', 'O'}};
    int minSteps = minSteps(grid);
    System.out.println(
        String.format("Minimum steps to reach treasure = %d \n" +
            "Is this answer correct - %b", minSteps, minSteps == 5));

    System.out.println();

    System.out.println("Treasure Island II");
    char[][] grid2 = {
        {'S', 'O', 'O', 'S', 'S'},
        {'D', 'O', 'D', 'O', 'D'},
        {'O', 'O', 'O', 'O', 'X'},
        {'X', 'D', 'D', 'O', 'O'},
        {'X', 'D', 'D', 'D', 'O'}};
    int minDist = minDist(grid2);
    System.out.println(
        String.format("Minimum steps to reach treasure = %d \n" +
            "Is this answer correct - %b", minDist, minDist == 3));
  }

  static class Point {

    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
