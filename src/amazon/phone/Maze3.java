package amazon.phone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/the-maze-iii/

class Maze3 {

  static class Point implements Comparable<Point> {
    int x;
    int y;
    int dist;
    String path;

    public Point(int x, int y, int dist, String path) {
      this.x = x;
      this.y = y;
      this.dist = dist;
      this.path = path;
    }

    @Override
    public int compareTo(Point that) {
      return this.dist == that.dist
          ? this.path.compareTo(that.path)
          : this.dist - that.dist;
    }
  }

  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
    Point startPoint = new Point(ball[0], ball[1], 0, "");
    PriorityQueue<Point> queue = new PriorityQueue<>();
    queue.offer(startPoint);

    boolean[][] visited = new boolean[maze.length][maze[0].length];

    Map<Character, int[]> directionMap = getDirectionMap();

    while(!queue.isEmpty()) {
      Point point = queue.poll();
      if(isHole(point.x, point.y, hole))
        return point.path;

      visited[point.x][point.y] = true;

      for(char direction : directionMap.keySet()) {
        int[] dir = directionMap.get(direction);
        int i = point.x;
        int j = point.y;
        int dist = point.dist;
        String path = point.path + direction;

        while(isWithinBoundaries(i + dir[0], j + dir[1], maze) &&
            isEmptySpace(i + dir[0], j + dir[1], maze)) {
          i += dir[0];
          j += dir[1];
          dist++;
          if(isHole(i, j, hole))
            break;
        }

        if(!visited[i][j])
          queue.offer(new Point(i, j, dist, path));
      }
    }

    return "impossible";
  }

  private boolean isWithinBoundaries(int i, int j, int[][] maze) {
    return i >= 0 && i < maze.length && j >= 0 && j < maze[i].length;
  }

  private boolean isEmptySpace(int i, int j, int[][] maze) {
    return maze[i][j] == 0;
  }

  private boolean isHole(int i, int j, int[] hole) {
    return i == hole[0] && j == hole[1];
  }

  private Map<Character, int[]> getDirectionMap() {
    Map<Character, int[]> directionMap = new HashMap() {{
      put('d', new int[] {1, 0});
      put('u', new int[] {-1, 0});
      put('l', new int[] {0, -1});
      put('r', new int[] {0, 1});
    }};
    return directionMap;
  }
}