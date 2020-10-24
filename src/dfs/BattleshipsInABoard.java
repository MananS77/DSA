package dfs;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/battleships-in-a-board/
 */
public class BattleshipsInABoard {

  public static void main(String[] args) {
    char[][] board = new char[][]{
        {'X', '.', '.', 'X'},
        {'.', '.', '.', 'X'},
        {'.', '.', '.', 'X'}
    };
    System.out.println("Board = " + Arrays.deepToString(board));
    System.out.println("Number of Battleships for board = " + countBattleships(board));
  }

  private static int countBattleships(char[][] board) {
    if (board.length == 0) {
      return 0;
    }
    int m = board.length;
    int n = board[0].length;
    int count = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'X') {
          dfs(board, i, j, m, n);
          count++;
        }
      }
    }
    return count;
  }

  private static void dfs(char[][] board, int i, int j, int m, int n) {
    if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'X') {
      return;
    }
    board[i][j] = '.';
    dfs(board, i + 1, j, m, n);
    dfs(board, i, j + 1, m, n);
  }

    /*
    Follow-up : Don't modify the input matrix -

    public int countBattleships(char[][] board) {
    int count = 0;
    for(int i=0; i<board.length; i++)
        for(int j=0; j<board[0].length; j++)
            if(board[i][j] == 'X'
                && (i == 0 || board[i-1][j] != 'X')
                && (j == 0 || board[i][j-1] != 'X'))
                    count++;
    return count;
    */
}
