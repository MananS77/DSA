package trie;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree
 */

public class Trie {

    class TrieNode {
        char val;
        TrieNode[] children = new TrieNode[26];
        boolean isWord;

        TrieNode() {}

        TrieNode(char c) {
            TrieNode node = new TrieNode();
            node.val = c;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode node = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null)
                node.children[c - 'a'] = new TrieNode(c);
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        test(trie.search("apple"), true);
        test(trie.search("app"), false);
        test(trie.startsWith("app"), true);
        trie.insert("app");
        test(trie.search("app"), true);
    }

    private static void test(boolean actual, boolean expected) {
        if (actual != expected)
            throw new AssertionError(String.format("Expected %b, but actual %b", expected, actual));
    }
}
