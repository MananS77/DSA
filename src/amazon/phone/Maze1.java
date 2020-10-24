package amazon.phone;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/the-maze/

class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class Maze1 {
  public boolean hasPath(int[][] maze, int[] start, int[] destination) {

    Point startPoint = new Point(start[0], start[1]);
    boolean[][] visited = new boolean[maze.length][maze[0].length];

    Queue<Point> queue = new LinkedList<>();
    queue.offer(startPoint);

    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    while(!queue.isEmpty()) {
      Point point = queue.poll();
      visited[point.x][point.y] = true;

      if(isDestination(point.x, point.y, destination))
        return true;

      for(int[] dir : directions) {
        int i = point.x;
        int j = point.y;

        while(isWithinBoundaries(i + dir[0], j + dir[1], maze) &&
            isEmptySpace(i + dir[0], j + dir[1], maze)) {
          i += dir[0];
          j += dir[1];
        }

        if(!visited[i][j])
          queue.offer(new Point(i, j));
      }
    }

    return false;
  }

  private boolean isWithinBoundaries(int i, int j, int[][] maze) {
    return i >= 0 && i < maze.length && j >= 0 && j < maze[i].length;
  }

  private boolean isEmptySpace(int i, int j, int[][] maze) {
    return maze[i][j] == 0;
  }

  private boolean isDestination(int i, int j, int[] destination) {
    return (i == destination[0] && j == destination[1]);
  }
}