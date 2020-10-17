package dfs;

/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix
 */
public class LongestIncreasingPathInMatrix {

    private static final int dirs[][] = new int[][] {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 9, 2, 3 },
                { 8, 1, 4 },
                { 7, 6, 5 }
        };
        System.out.println("Longest Increasing Path = " + longestIncreasingPath(matrix));
    }

    private static int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0)
            return 0;

        int max = 1;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    private static int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0)
            return cache[i][j];

        int max = 1;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j])
                continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}
