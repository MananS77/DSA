package dfs;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {

  public static void main(String[] args) {
    int[][] matrix = new int[][]{
        {1, 1, 1},
        {1, 1, 0},
        {1, 0, 1}
    };
    int sr = 1, sc = 1;
    int newColor = 2;
    System.out
        .println("Painted Image = " + Arrays.deepToString(floodFill(matrix, sr, sc, newColor)));
  }

  private static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    floodFill(image, sr, sc, image.length, image[0].length, image[sr][sc], newColor);
    return image;
  }

  private static void floodFill(int[][] image, int i, int j, int m, int n, int oldColor,
      int newColor) {
    if (i < 0 || i >= m || j < 0 || j >= n) {
      return;
    }

    if (image[i][j] == newColor || image[i][j] != oldColor) {
      return;
    }

    image[i][j] = newColor;
    floodFill(image, i + 1, j, m, n, oldColor, newColor);
    floodFill(image, i - 1, j, m, n, oldColor, newColor);
    floodFill(image, i, j + 1, m, n, oldColor, newColor);
    floodFill(image, i, j - 1, m, n, oldColor, newColor);
  }
}
