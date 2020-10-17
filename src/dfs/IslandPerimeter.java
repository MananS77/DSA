package dfs;

/**
 * https://leetcode.com/problems/island-perimeter/
 */
public class IslandPerimeter {
    public static void main(String[] args) {
        int[][] grid = new int[][] {
                { 0, 1, 0, 0 },
                { 1, 1, 1, 0 },
                { 0, 1, 0, 0 },
                { 1, 1, 0, 0 }
        };
        System.out.println("Perimeter of Island = " + islandPerimeter(grid));
    }

    private static int islandPerimeter(int[][] grid) {
        if (grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count += 4; //add 4 for every land
                    if (i > 0 && grid[i - 1][j] == 1) count -= 2; //subtract 2 for every edge
                    if (j > 0 && grid[i][j - 1] == 1) count -= 2; //subtract 2 for every edge
                }
            }
        }

        return count;
    }
}
