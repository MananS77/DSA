package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search-ii/
 */
public class WordSearch_II {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                { 'o', 'a', 'a', 'n' },
                { 'e', 't', 'a', 'e' },
                { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' }
        };
        List<String> words = Arrays.asList("oath", "pea", "eat", "rain");
        System.out.println("Board = " + Arrays.deepToString(board));
        System.out.println("List of words to search in board = " + words);
        System.out.println("Words that belong in board = " + findWords(board, words));
    }

    private static List<String> findWords(char[][] board, List<String> words) {
        List<String> list = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i, j, root, list);
            }
        }
        return list;
    }

    private static void dfs(char[][] board, int i, int j, TrieNode root, List<String> list) {

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length)
            return;

        char c = board[i][j];

        if (c == '#' || root.children[c - 'a'] == null)
            return;

        root = root.children[c - 'a'];
        if (root.word != null) {
            list.add(root.word);
            root.word = null; //to avoid duplicate words (unique occurrence of word in array)
        }

        board[i][j] = '#';

        dfs(board, i - 1, j, root, list);
        dfs(board, i + 1, j, root, list);
        dfs(board, i, j + 1, root, list);
        dfs(board, i, j - 1, root, list);

        board[i][j] = c;
    }

    private static TrieNode buildTrie(List<String> words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.word = word;
        }
        return root;
    }
}
