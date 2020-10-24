package amazon.phone;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/the-maze-ii/

class Maze2 {

  static class Point {
    int x;
    int y;
    int dist;

    public Point(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }
  }

  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    Point startPoint = new Point(start[0], start[1], 0);
    Queue<Point> queue = new LinkedList<>();
    queue.offer(startPoint);

    int[][] distance = new int[maze.length][maze[0].length];
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    for(int[] row : distance)
      Arrays.fill(row, Integer.MAX_VALUE);

    while(!queue.isEmpty()) {
      Point point = queue.poll();

      for(int[] dir : directions) {
        int i = point.x;
        int j = point.y;
        int dist = point.dist;

        while(isWithinBoundaries(i, j, maze) && isEmptySpace(i, j, maze)) {
          i += dir[0];
          j += dir[1];
          dist++;
        }

        i -= dir[0];
        j -= dir[1];
        dist--;

        if(dist > distance[i][j])
          continue;

        if(dist < distance[i][j]) {
          distance[i][j] = dist;
          queue.offer(new Point(i, j, dist));
        }
      }
    }

    return distance[destination[0]][destination[1]] == Integer.MAX_VALUE
        ? -1
        : distance[destination[0]][destination[1]];
  }

  private boolean isWithinBoundaries(int i, int j, int[][] maze) {
    return i >= 0 && i < maze.length && j >= 0 && j < maze[i].length;
  }

  private boolean isEmptySpace(int i, int j, int[][] maze) {
    return maze[i][j] == 0;
  }
}