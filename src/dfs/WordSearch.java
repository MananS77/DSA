package dfs;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board = new char[][] {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        System.out.println("Board = " + Arrays.deepToString(board));
        System.out.println("Does " + word1 + " exist in board = " + exist(board, word1));
        System.out.println("Does " + word2 + " exist in board = " + exist(board, word2));
        System.out.println("Does " + word3 + " exist in board = " + exist(board, word3));
    }

    private static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (exist(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    private static boolean exist(char[][] board, String word, int i, int j, int index) {
        if (index == word.length())
            return true;

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != word.charAt(index))
            return false;

        board[i][j] = '*';
        boolean exist = exist(board, word, i - 1, j, index + 1) ||
                        exist(board, word, i + 1, j, index + 1) ||
                        exist(board, word, i, j - 1, index + 1) ||
                        exist(board, word, i, j + 1, index + 1);
        board[i][j] = word.charAt(index);
        return exist;
    }
}
