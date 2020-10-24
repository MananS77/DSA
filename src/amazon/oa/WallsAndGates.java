package amazon.oa;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
        Walls and Gates - https://leetcode.com/problems/walls-and-gates/

        You are given a m x n 2D grid initialized with these three possible values.

            a) -1 - A wall or an obstacle.
            b) 0 - A gate.
            c) INF - Infinity means an empty room = 2147483647

        Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

        Example: Given the 2D grid:

          INF  -1  0  INF
          INF INF INF  -1
          INF  -1 INF  -1
            0  -1 INF INF

        After running your function, the 2D grid should be:

          3  -1   0   1
          2   2   1  -1
          1  -1   2  -1
          0  -1   3   4

*/


class Point {

  int x;
  int y;
  int dist;

  public Point(int x, int y, int dist) {
    this.x = x;
    this.y = y;
    this.dist = dist;
  }
}

public class WallsAndGates {

  public static void wallsAndGates(int[][] rooms) {
    if (rooms == null || rooms.length == 0) {
      return;
    }
    int m = rooms.length;
    int n = rooms[0].length;
    Queue<Point> queue = new LinkedList<>();
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (rooms[i][j] == 0) {
          queue.offer(new Point(i, j, 0));
        }
      }
    }

    while (!queue.isEmpty()) {
      int size = queue.size();

      while (size-- > 0) {
        Point point = queue.poll();

        for (int[] dir : directions) {
          int i = dir[0] + point.x;
          int j = dir[1] + point.y;

          if (isInside(i, j, rooms) && rooms[i][j] == 2147483647) {
            queue.offer(new Point(i, j, point.dist + 1));
            rooms[i][j] = point.dist + 1;
          }
        }
      }
    }
  }

  private static boolean isInside(int i, int j, int[][] rooms) {
    return i >= 0 && i < rooms.length && j >= 0 && j < rooms[i].length;
  }

  public static void main(String[] args) {

    int[][] rooms = {
        {2147483647, -1, 0, 2147483647},
        {2147483647, 2147483647, 2147483647, -1},
        {2147483647, -1, 2147483647, -1},
        {0, -1, 2147483647, 2147483647},
    };
    System.out.println("Before - ");
    print(rooms);
    wallsAndGates(rooms);
    System.out.println();
    System.out.println("After - ");
    print(rooms);
  }

  private static void print(int[][] rooms) {
    for (int[] room : rooms) {
      System.out.println(Arrays.toString(room));
    }
  }
}